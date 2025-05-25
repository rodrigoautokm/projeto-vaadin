package com.exemplo;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.ItemDoubleClickEvent;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.progressbar.ProgressBar;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.function.ValueProvider;
import com.vaadin.flow.component.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

public abstract class AbstractGridView<T> extends VerticalLayout implements BeforeEnterObserver {

    private static final Logger logger = LoggerFactory.getLogger(AbstractGridView.class);

    protected final String title;
    protected final String gridId;
    protected final Class<T> entityClass;
    protected final Supplier<List<T>> dataSupplier;
    protected Grid<T> grid;
    protected GridFilterUtil<T> gridUtil;
    protected List<T> items;
    protected List<GridFilterUtil.ColumnConfig<T>> columnConfigs;
    protected final String usuarioLogado;

    private VerticalLayout loadingOverlay;
    private ProgressBar loadingBar;
    private Span loadingMessage;

    @Autowired
    protected GridColumnConfigService gridColumnConfigService;

    @Autowired
    protected GridColumnConfigCadastroService gridColumnConfigCadastroService;

    @Autowired
    private ApplicationContext applicationContext;

    private static final Map<Class<?>, Class<? extends GenericaCadastro<?>>> CADASTRO_CLASSES = new HashMap<>();

    static {
        CADASTRO_CLASSES.put(Cliente.class, ClienteCadastro.class);
        CADASTRO_CLASSES.put(Fornecedor.class, FornecedorCadastro.class);
        CADASTRO_CLASSES.put(Produto.class, ProdutoCadastro.class);
        CADASTRO_CLASSES.put(Marca.class, MarcaCadastro.class);
    }

    public AbstractGridView(String title, Class<T> entityClass, Supplier<List<T>> dataSupplier) {
        logger.info("Construindo AbstractGridView: title={}, gridId={}", title, entityClass.getSimpleName());
        this.title = title;
        this.gridId = entityClass.getSimpleName().toLowerCase();
        this.entityClass = entityClass;
        this.dataSupplier = dataSupplier;
        this.usuarioLogado = getUsuarioId(); // Inicializa usuarioLogado

        setSizeFull();
        setPadding(false);
        setMargin(false);
        setSpacing(false);
        addClassName("abstract-grid-view");
        getStyle().set("position", "relative");
        getStyle().set("display", "flex");
        getStyle().set("flex-direction", "column");

        H1 titleComponent = new H1(title);
        titleComponent.getStyle()
            .set("font-size", "24px")
            .set("margin", "10px 0 5px 0")
            .set("padding", "0");
        add(titleComponent);

        grid = new Grid<>(entityClass, false);
        grid.getElement().getClassList().add("grid-container");
        grid.setHeight("100%");
        grid.setWidth("100%");

        grid.addItemDoubleClickListener(this::handleItemDoubleClick);

        initializeLoadingOverlay();
    }

    @SuppressWarnings("unchecked")
    private void handleItemDoubleClick(ItemDoubleClickEvent<T> event) {
        T item = event.getItem();
        if (item == null) {
            logger.warn("Item selecionado é nulo. Não é possível abrir o diálogo de edição.");
            return;
        }

        logger.info("Item selecionado para edição: {}", item);

        Class<? extends GenericaCadastro<?>> cadastroClass = CADASTRO_CLASSES.get(entityClass);

        if (cadastroClass == null) {
            logger.warn("Nenhuma classe de cadastro registrada para a entidade {}.", entityClass.getSimpleName());
            return;
        }

        try {
            Object repository = getRepository();
            if (repository == null) {
                logger.warn("Repositório não disponível para a entidade {}. Não será possível abrir o diálogo de edição.", entityClass.getSimpleName());
                return;
            }

            GenericaCadastro<T> cadastro = (GenericaCadastro<T>) applicationContext.getBean(cadastroClass, repository);
            cadastro.initialize(item, savedItem -> carregarDados());
            cadastro.open();
            logger.info("Diálogo de cadastro/edição aberto para a entidade: {}.", entityClass.getSimpleName());
        } catch (Exception e) {
            logger.error("Erro ao abrir diálogo de cadastro/edição para a entidade {}: {}", entityClass.getSimpleName(), e.getMessage(), e);
            if (e instanceof com.vaadin.flow.data.binder.BindingException) {
                logger.error("Detalhes do BindingException: {}", e.getCause() != null ? e.getCause().getMessage() : "Causa desconhecida");
            }
        }
    }

    protected abstract Object getRepository();

    protected void initializeLoadingOverlay() {
        loadingOverlay = new VerticalLayout();
        loadingOverlay.setSizeFull();
        loadingOverlay.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        loadingOverlay.setAlignItems(FlexComponent.Alignment.CENTER);
        loadingOverlay.getStyle().set("background-color", "rgba(255, 255, 255, 0.7)");
        loadingOverlay.getStyle().set("position", "absolute");
        loadingOverlay.getStyle().set("top", "0");
        loadingOverlay.getStyle().set("left", "0");
        loadingOverlay.getStyle().set("z-index", "999");

        loadingBar = new ProgressBar();
        loadingBar.setIndeterminate(true);
        loadingMessage = new Span("Carregando...");
        loadingOverlay.add(loadingBar, loadingMessage);
        loadingOverlay.setVisible(false);
        add(loadingOverlay);
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        logger.info("onAttach chamado para {} com gridId={}", title, gridId);
        if (gridColumnConfigService == null) {
            logger.error("gridColumnConfigService é nulo para {}. Verifique a configuração do Spring.", title);
            return;
        }
        showLoadingOverlay(true);
        logger.debug("Verificando UI para {}", title);
        getUI().ifPresentOrElse(ui -> {
            logger.debug("UI presente. Iniciando configuração do grid para {}", title);
            ui.access(() -> {
                try {
                    logger.debug("Chamando carregarDados para {}", title);
                    carregarDados();
                } catch (Exception e) {
                    logger.error("Erro ao configurar o grid para {}: {}", title, e.getMessage(), e);
                    e.printStackTrace();
                } finally {
                    showLoadingOverlay(false);
                    logger.debug("Configuração do grid concluída para {}.", title);
                }
            });
        }, () -> {
            logger.error("UI não presente para {}. Não é possível configurar o grid.", title);
            showLoadingOverlay(false);
        });
    }

    @Override
    protected void onDetach(DetachEvent detachEvent) {
        super.onDetach(detachEvent);
        logger.info("onDetach chamado para {} com gridId={}", title, gridId);
        grid.removeAllColumns();
        gridUtil = null;
        columnConfigs = null;
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        logger.debug("beforeEnter chamado para {} com gridId={}", title, gridId);
        if (gridColumnConfigService == null) {
            logger.error("gridColumnConfigService é nulo para {}. Verifique a configuração do Spring.", title);
            return;
        }
        this.columnConfigs = configureColumns();
        logger.info("ColumnConfigs gerados: {}", columnConfigs != null ? columnConfigs.size() : 0);
        if (columnConfigs == null || columnConfigs.isEmpty()) {
            logger.error("Nenhuma coluna configurada para {}. GridFilterUtil não será inicializado.", title);
            return;
        }

        // Certifique-se que this.columnConfigUsuarioRepository e this.vwColumnConfigRepository estão injetados e não são nulos.
        // Adicione verificações de nulidade se necessário, ou confie na garantia de injeção do Spring.
        if (this.columnConfigUsuarioRepository == null || this.vwColumnConfigRepository == null) {
            logger.error("Repositórios para GridFilterUtil não foram injetados corretamente em AbstractGridView para gridId: {}", gridId);
            // Decida como tratar isso: talvez não inicializar gridUtil ou lançar uma exceção.
            // Por agora, vamos logar e potencialmente pular a inicialização do gridUtil.
            return; // Ou outra forma de tratamento de erro
        }
        gridUtil = new GridFilterUtil<T>(
            this.getClass().getName(), // NOVO: Usar o nome completo da classe da View específica (ex: com.exemplo.ClienteView)
            gridId,
            grid,
            columnConfigs,
            getUsuarioId(),
            getCdEmpresaUsuario(),
            this.columnConfigUsuarioRepository, // Usar o campo injetado
            this.vwColumnConfigRepository       // Usar o campo injetado
        );
        logger.info("GridFilterUtil inicializado com className={}, gridId={}, usuarioId={}, cdEmpresa={}", this.getClass().getName(), gridId, getUsuarioId(), getCdEmpresaUsuario());
        Component filterLayout = gridUtil.getLayout();
        if (filterLayout != null) {
            add(filterLayout);
            logger.debug("Layout do GridFilterUtil adicionado à view.");
        } else {
            logger.error("Layout do GridFilterUtil é nulo para {}.", title);
        }
    }

protected void carregarDados() {
    try {
        showLoadingOverlay(true);
        logger.debug("Carregando dados para {} com gridId={}", title, gridId);
        this.items = dataSupplier.get();
        logger.info("Itens carregados para {}: {}", title, items != null ? items.size() : 0);
        if (items != null) {
            items.forEach(item -> logger.debug("Item: {}", item));
        } else {
            logger.warn("Itens nulos para {}.", title);
        }
        grid.setItems(items);
        if (gridUtil != null) {
            gridUtil.updateItems(items);
        }
    } catch (Exception e) {
        logger.error("Erro ao carregar dados para {}: {}", title, e.getMessage(), e);
        e.printStackTrace();
    } finally {
        showLoadingOverlay(false);
        logger.debug("Carregamento de dados concluído para {}.", title);
    }
}

    protected void showLoadingOverlay(boolean visible) {
        loadingOverlay.setVisible(visible);
    }

    protected String getUsuarioId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String usuarioId = auth != null && auth.getName() != null ? auth.getName() : "anonymousUser";
        logger.debug("UsuarioId obtido: {}", usuarioId);
        if (usuarioId.equals("anonymousUser")) {
            logger.warn("Nenhum usuário autenticado encontrado. Usando 'anonymousUser'.");
        }
        return usuarioId;
    }

    protected Integer getCdEmpresaUsuario() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getPrincipal() instanceof CustomUserDetails) {
            Integer cdEmpresa = ((CustomUserDetails) auth.getPrincipal()).getCdEmpresa();
            logger.debug("CdEmpresa obtido: {}", cdEmpresa);
            return cdEmpresa;
        }
        logger.warn("Principal não é CustomUserDetails ou autenticação nula. Retornando null para cdEmpresa. Principal: {}", 
            auth != null && auth.getPrincipal() != null ? auth.getPrincipal().getClass().getName() : "null");
        return null;
    }

public List<GridFilterUtil.ColumnConfig<T>> configureColumns() {
    logger.info("Configurando colunas para {} com gridId={}", title, gridId);
    List<GridFilterUtil.ColumnConfig<T>> configs = new ArrayList<>();
    List<GridColumnConfig> configList;
    try {
        logger.debug("Chamando gridColumnConfigService.listar('{}')", gridId);
        configList = gridColumnConfigService.listar(gridId);
        logger.info("Total de configurações carregadas: {}", configList != null ? configList.size() : 0);
    } catch (Exception e) {
        logger.error("Erro ao carregar configurações de column_config: {}", e.getMessage(), e);
        e.printStackTrace();
        return configs;
    }

    if (configList == null || configList.isEmpty()) {
        logger.warn("Nenhuma configuração encontrada em column_config para {}.", title);
        return configs;
    }

    configList.forEach(c -> logger.debug("Configuração: field={}, alias={}, header={}, visible={}, ordenacao_grid={}", 
        c.getField(), c.getAlias(), c.getHeader(), c.isVisible(), c.getOrdenacaoGrid()));

    // Ordenar configList com base em ordenacao_grid (0 ou null no final)
    configList.sort((c1, c2) -> {
        Integer ord1 = c1.getOrdenacaoGrid() != null ? c1.getOrdenacaoGrid() : Integer.MAX_VALUE;
        Integer ord2 = c2.getOrdenacaoGrid() != null ? c2.getOrdenacaoGrid() : Integer.MAX_VALUE;
        return ord1.compareTo(ord2);
    });

    List<String> validFields = new ArrayList<>();
    if (entityClass == Marca.class) {
        validFields.addAll(Arrays.asList("cdMarca", "dsMarca", "comissao", "prDescontoVendedor", 
            "prDescontoGerente", "alteraPreco", "situacao", "gtinUnico"));
    } else if (entityClass == Cliente.class) {
        validFields.addAll(Arrays.asList("cdEmpresa", "cdCliente", "nmCliente", "tipo", 
            "endereco", "bairro", "fone", "celular", "cpf"));
    } else if (entityClass == Fornecedor.class) {
        validFields.addAll(Arrays.asList("cdFornecedor", "nmFornecedor", "nmFantasia", "nmRepresentante", 
            "cdCidade", "cep", "endereco", "bairro", "fone", "fax", "email", "tipo", "contatoFinanceiro", 
            "foneFinanceiro", "faxFinanceiro", "celularFinanceiro", "ramalFinanceiro", "contatoComercial", 
            "foneComercial", "faxComercial", "celularComercial", "ramalComercial", "representante", 
            "foneRepresentante", "faxRepresentante", "celularRepresentante", "ramalRepresentante", 
            "contaCorrente", "cdBanco", "agencia", "cgc", "inscricaoEstadual", "freteNoIpi", "obs", 
            "contaContabil", "diasEntrega", "alteraPreco", "situacao", "cooperado", "tpPessoa", 
            "nroEndereco", "emailFinanceiro", "emailComercial", "emailRepresentante", "emailCotacao", 
            "celular", "infNegociacao", "obsComoHistoricoContabil", "tipoContribuinte", "cdPais", 
            "emailNfe", "contatoEmailNfe"));
    } else if (entityClass == Produto.class) {
        validFields.addAll(Arrays.asList("cdProduto", "nrDigito", "dsProduto", "dsAbreviacao", 
            "cdSubgrupo", "cdGrupo", "cdMarca", "cdCor", "voltagem", "cdFornecedor", "situacao", 
            "reducaoIcms", "icms", "ipi", "classificacaoFiscal", "origemSituacaoTributaria", 
            "situacaoTributaria", "vlContabil", "vlCompra", "vlVenda", "vlCusto", "comissao", 
            "lucro", "estoqueMinimo", "estoqueMaximo", "cdUnidade", "vlCustoMedio", 
            "prDescontoVendedor", "prDescontoGerente", "saldoNegativo", "cdBarra", "nrComponentes", 
            "referencia", "dtCadastro", "alteraPreco", "prateleira", "peso", "cubagem", "cdFabrica", 
            "capacidade", "combustivel", "renavam", "chassi", "motor", "potencia", "anoFabricacao", 
            "anoModelo", "cilindrada", "cdPerfil", "cdAcabamento", "gravura", "sombra", "ncm", 
            "vlIpi", "vlSubstituicao", "cdMontadora", "tamanho", "prProteina", "tpProduto", 
            "dsModelo", "comissao2", "comissao3", "numero", "capacidadeVolumetrica", "cdProdutoDnf", 
            "kgMilheiro", "nmUsuario", "diasEntrega", "classificacao", "qtPecasUmVolume", 
            "obsOrcamento", "obsNf", "nrMesesGarantia", "prDescontoGerente2", "prDescontoVendedor2", 
            "pesoBruto", "caminhoFoto", "vlDec", "montadora", "ipiVenda", "anoFabricabao", 
            "prioridade", "pisCofins", "dtVencimentoNota", "cdReceita", "cdDespesa", "cdTipo", 
            "vlMaoObra", "modalidadeBcIcms", "modalidadeBcIcmsSt", "enquadramentoIpi", 
            "situacaoTributariaIpi", "situacaoTributariaPis", "prAliquotaPis", 
            "situacaoTributariaCofins", "prAliquotaCofins", "prAdicionadoSubstituicao", 
            "prSubstituicao", "cdTipoEntrada", "dsAplicacao", "conversao", "prIcmsCompra", 
            "somenteCotacaoCompra", "cdGrupoServicoClassificacao", "cdServicoClassificacao", 
            "cdTributacaoIss", "cdTipoItemSped", "cdExcecaoNcmSped", "cdGeneroSped", 
            "prReducaoIcmsSt", "prFatorReducaoSnSt", "indiceAjusteMva", "movtoSped", 
            "prioridadeOrdem", "alteraDescricaoCompra", "liberaLocacao", "criptografia", "cdAnp", 
            "largura", "profundidade", "altura", "sazonal", "nrEspecificacaoIcms", 
            "nrEspecificacaoPisCofins", "nrEspecificacaoIpi", "situacaoTributariaCompra", 
            "prIcmsAjusteMva", "prReducaoIcmsCompra", "nrEspecificacaoCompras", "nmUsuarioAlteracao", 
            "dtAlteracao", "cest", "prDescontoDemander", "fci", "cdFabricante", 
            "pisCofinsMonofasico", "vlBcIcmsStRet", "prStRet", "vlIcmsRet", "vlIcmsStRet", 
            "cdSimilaridade", "verificadoAutokm", "dsFabricanteAutokm", "qtFracionada", 
            "cdUnidadeFracionada", "cdAliquota", "cdProdutoAgrupador", "obsLojaVirtual", 
            "icmsStRetidoAnteriormente"));
    } else {
        logger.warn("Classe de entidade desconhecida: {}. Nenhuma coluna será configurada.", entityClass.getSimpleName());
        return configs;
    }

    grid.removeAllColumns();
    logger.debug("Colunas existentes removidas do grid para evitar duplicatas.");

    Set<String> addedKeys = new HashSet<>();
    int columnIndex = 0;

    for (GridColumnConfig c : configList) {
        String alias = c.getAlias();
        String field = (alias != null && !alias.isEmpty()) ? alias : c.getField();
        if (field == null || field.isEmpty()) {
            logger.warn("Campo ou alias não especificado para a configuração: {}", c);
            continue;
        }

        if (!validFields.contains(field)) {
            logger.debug("Coluna {} (field={}) ignorada por não ser válida para {}", field, c.getField(), entityClass.getSimpleName());
            continue;
        }

        if (addedKeys.contains(field)) {
            logger.warn("Coluna duplicada detectada para '{}'. Ignorando esta entrada.", field);
            continue;
        }

        String columnName = c.getHeader() != null ? c.getHeader() : field;
        String uniqueKey = field + "_" + columnIndex;
        columnIndex++;

        addedKeys.add(field);

        try {
            ValueProvider<T, Object> extractor;
            if (entityClass == Cliente.class && field.equals("cdEmpresa")) {
                extractor = item -> ((Cliente) item).getId().getCdEmpresa();
            } else if (entityClass == Cliente.class && field.equals("cdCliente")) {
                extractor = item -> ((Cliente) item).getId().getCdCliente();
            } else {
                PropertyDescriptor pd = new PropertyDescriptor(field, entityClass);
                java.lang.reflect.Method method = pd.getReadMethod();
                if (method == null) {
                    logger.warn("Método getter não encontrado para o campo '{}' em {}. Ignorando coluna.", field, entityClass.getSimpleName());
                    continue;
                }
                extractor = item -> {
                    try {
                        return method.invoke(item);
                    } catch (Exception e) {
                        logger.error("Erro ao extrair valor do campo '{}': {}", field, e.getMessage());
                        return null;
                    }
                };
            }

            Grid.Column<T> column = grid.addColumn(extractor)
                                       .setHeader(columnName)
                                       .setKey(uniqueKey);
            column.setVisible(c.isVisible());
            GridFilterUtil.ColumnConfig<T> config = new GridFilterUtil.ColumnConfig<>(column, columnName, extractor::apply, c);

            c.setField(c.getField());                
            configs.add(config);
            logger.debug("Coluna configurada: field={}, alias={}, header={}, key={}, visible={}, ordenacao_grid={}", 
                c.getField(), field, columnName, uniqueKey, c.isVisible(), c.getOrdenacaoGrid());
        } catch (Exception e) {
            logger.error("Erro ao configurar coluna {} (alias={}): {}", c.getField(), field, e.getMessage(), e);
            e.printStackTrace();
        }
    }

    logger.info("Total de colunas configuradas: {}", configs.size());
    return configs;
}

    private String capitalize(String field) {
        if (field == null || field.isEmpty()) return field;
        return field.substring(0, 1).toUpperCase() + field.substring(1);
    }

    public abstract Class<T> getEntityClass();

    public void addFilters(Component filtrosLayout) {
        logger.debug("Adicionando layout de filtros para {}", title);
        add(filtrosLayout);
    }

    public void updateData(List<T> novaLista) {
        logger.info("Atualizando dados para {}: {}", title, novaLista != null ? novaLista.size() : 0);
        this.items = novaLista;
        if (gridUtil != null) {
            gridUtil.updateItems(novaLista);
        } else {
            grid.setItems(novaLista);
        }
    }

    public Component getGridLayout() {
        if (gridUtil != null && gridUtil.getLayout() != null) {
            return gridUtil.getLayout();
        }
        logger.warn("GridUtil ou seu layout não está inicializado. Retornando um layout vazio.");
        return new VerticalLayout();
    }

    private void salvarOrdenacaoUsuario(String className, String fieldName, String sortDirection) {
        String usuario = SecurityContextHolder.getContext().getAuthentication().getName();
        try {
            gridColumnConfigService.atualizarOrdenacaoUsuario(className, fieldName, usuario, sortDirection);
        } catch (Exception e) {
            logger.error("Erro ao salvar ordenação do usuário", e);
        }
    }
}