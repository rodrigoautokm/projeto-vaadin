package com.exemplo;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.*;
import com.vaadin.flow.component.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.sql.DataSource;
import java.util.*;

@Route(value = "rel_vendas", layout = MainLayout.class)
@RouteAlias(value = "rel_dre", layout = MainLayout.class)
@RouteAlias(value = "rel_listagem", layout = MainLayout.class)
@RouteAlias(value = "rel_ordens_servico", layout = MainLayout.class)
@PageTitle("Relatório Dinâmico")
public class RelatorioView extends AbstractGridView<RelatorioDinamicoResult> implements BeforeEnterObserver {

    private static final Logger logger = LoggerFactory.getLogger(RelatorioView.class);

    private final RelatorioService relatorioService;
    private final ParametroRelatorioService parametroService;
    private final GridColumnConfigRelatorioService gridColumnConfigRelatorioService;
    private final SystemParameterService systemParameterService;
    private final EmpresaConnectionManager empresaConnectionManager;

    private String procedureName;
    private String tituloPagina;
    private Map<String, Object> parametrosSelecionados;
    private HorizontalLayout topBar;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    public RelatorioView(RelatorioService relatorioService,
                         ParametroRelatorioService parametroService,
                         GridColumnConfigRelatorioService gridColumnConfigRelatorioService,
                         SystemParameterService systemParameterService,
                         EmpresaConnectionManager empresaConnectionManager) {
        super("Relatório", RelatorioDinamicoResult.class, () -> Collections.emptyList());
        this.relatorioService = relatorioService;
        this.parametroService = parametroService;
        this.gridColumnConfigRelatorioService = gridColumnConfigRelatorioService;
        this.systemParameterService = systemParameterService;
        this.empresaConnectionManager = empresaConnectionManager;

        H1 titulo = new H1("Relatório");
        Button parametrosBtn = new Button("Parâmetros", VaadinIcon.COG.create(), e -> abrirDialogParametros());
        topBar = new HorizontalLayout(titulo, parametrosBtn);
        topBar.setWidthFull();
        topBar.setSpacing(true);
        add(topBar);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        String path = event.getLocation().getPath();
        switch (path) {
            case "rel_vendas":
                procedureName = "pr_rel_vendas_r04";
                tituloPagina = "Relatório de Vendas";
                break;
            case "rel_dre":
                procedureName = "pr_rel_demonstrativo_resultado_arvore";
                tituloPagina = "Relatório DRE";
                break;
            case "rel_listagem":
                procedureName = "pr_listagem_pedidos";
                tituloPagina = "Listagem de Pedidos";
                break;
            case "rel_ordens_servico":
                procedureName = "pr_rel_ordens_servico_periodo";
                tituloPagina = "Relatório Ordem de Serviço";
                break;
            default:
                Notification.show("Rota inválida.");
                event.rerouteToError(NotFoundException.class);
                return;
        }

        topBar.removeAll();
        H1 titulo = new H1(tituloPagina);
        Button parametrosBtn = new Button("Parâmetros", VaadinIcon.COG.create(), e -> abrirDialogParametros());
        topBar.add(titulo, parametrosBtn);
        topBar.setWidthFull();
        topBar.setSpacing(true);

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

        ColumnConfigUsuarioRepository repository = applicationContext.getBean(ColumnConfigUsuarioRepository.class);
        VwColumnConfigRepository vwRepository = applicationContext.getBean(VwColumnConfigRepository.class);
        gridUtil = new GridFilterUtil<RelatorioDinamicoResult>(gridId, gridId, grid, columnConfigs, getUsuarioId(), getCdEmpresaUsuario(), repository, vwRepository);
        logger.info("GridFilterUtil inicializado com gridId={}, usuarioId={}, cdEmpresa={}", gridId, getUsuarioId(), getCdEmpresaUsuario());

        List<GridColumnConfig> columnConfigs = gridColumnConfigRelatorioService.getColumnConfigs(procedureName);
        logger.info("✅ Colunas carregadas para {}: {}", procedureName, columnConfigs.size());

        if (!columnConfigs.isEmpty()) {
            configureGrid(columnConfigs);
        } else {
            logger.warn("⚠️ Nenhuma coluna configurada para {}. Aguardando parâmetros.", procedureName);
        }

        Component filterLayout = gridUtil.getLayout();
        if (filterLayout != null) {
            add(filterLayout);
            logger.debug("Layout do GridFilterUtil adicionado à view.");
        } else {
            logger.error("Layout do GridFilterUtil é nulo para {}.", title);
        }
    }

    private void configureGrid(List<GridColumnConfig> columnConfigs) {
        ColumnConfigUsuarioRepository repository = applicationContext.getBean(ColumnConfigUsuarioRepository.class);
        VwColumnConfigRepository vwRepository = applicationContext.getBean(VwColumnConfigRepository.class);
        if (gridUtil == null) {
            gridUtil = new GridFilterUtil<RelatorioDinamicoResult>(
                gridId,
                gridId,
                grid,
                new ArrayList<GridFilterUtil.ColumnConfig<RelatorioDinamicoResult>>(),
                getUsuarioId(),
                getCdEmpresaUsuario(),
                repository,
                vwRepository
            );
            logger.info("✅ GridFilterUtil inicializado para gridId={}, procedureName={}", gridId, procedureName);
        } else {
            gridUtil.clearGrid();
            Component layout = gridUtil.getLayout();
            if (layout != null) {
                remove(layout);
            }
        }

        gridUtil.addColumns(columnConfigs);
        logger.info("✅ Colunas adicionadas ao GridFilterUtil para gridId={}, procedureName={}", gridId, procedureName);
        Component filterLayout = gridUtil.getLayout();
        if (filterLayout != null) {
            add(filterLayout);
        } else {
            logger.error("❌ Layout do GridFilterUtil é nulo para {}.", procedureName);
        }
    }

    private void abrirDialogParametros() {
        Integer cdEmpresa = getCdEmpresaUsuario();
        DataSource ds = empresaConnectionManager.getDataSourceForEmpresa(cdEmpresa);
        List<ParametroRelatorio> parametros = parametroService.getParametros(procedureName, ds);
        if (parametros == null || parametros.isEmpty()) {
            logger.warn("⚠️ Tabela config_parametro_procedure vazia para {}.", procedureName);
            Notification.show("Nenhum parâmetro configurado.", 5000, Notification.Position.MIDDLE);
            return;
        }

        boolean camposRetornoConfigurados = !gridColumnConfigRelatorioService.getColumnConfigs(procedureName).isEmpty();
        new ParametroDialogBuilder("Parâmetros", parametros, this::processarParametrosSelecionados,
                camposRetornoConfigurados).abrir();
    }

    private void processarParametrosSelecionados(Map<String, Object> valores) {
        this.parametrosSelecionados = valores;

        Integer cdEmpresa = getCdEmpresaUsuario();
        Map<String, Object> paramsValidados = new HashMap<>();
        for (Map.Entry<String, Object> entry : valores.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof Double && "NUMBER".equals(getParamType(valores, key))) {
                paramsValidados.put(key, ((Double) value).intValue());
            } else if (value instanceof String && ((String) value).isEmpty() && !"DATE".equals(getParamType(valores, key))) {
                paramsValidados.put(key, null);
            } else {
                paramsValidados.put(key, value);
            }
        }

        try {
            List<RelatorioDinamicoResult> dados = relatorioService.executarProcedure(procedureName, paramsValidados);
            if (dados == null || dados.isEmpty()) {
                Notification.show("Nenhum dado retornado.", 5000, Notification.Position.MIDDLE);
            } else {
                List<GridColumnConfig> columnConfigs = gridColumnConfigRelatorioService.getColumnConfigs(procedureName);
                if (columnConfigs.isEmpty()) {
                    logger.warn("⚠️ Nenhuma coluna configurada para {}.", procedureName);
                    Notification.show("Nenhuma coluna configurada para o relatório.", 5000, Notification.Position.MIDDLE);
                    return;
                }

                configureGrid(columnConfigs);
                gridUtil.updateItems(dados);
                Notification.show("Relatório carregado com " + dados.size() + " registros.");
            }
        } catch (Exception ex) {
            logger.error("❌ Erro ao executar relatório: {}", ex.getMessage(), ex);
            Notification.show("Erro: " + ex.getMessage(), 5000, Notification.Position.MIDDLE);
        }
    }

    private String getParamType(Map<String, Object> params, String field) {
        Integer cdEmpresa = getCdEmpresaUsuario();
        DataSource ds = empresaConnectionManager.getDataSourceForEmpresa(cdEmpresa);
        List<ParametroRelatorio> parametros = parametroService.getParametros(procedureName, ds);
        return parametros.stream()
                .filter(p -> p.getField().equals(field))
                .map(ParametroRelatorio::getTipo)
                .findFirst()
                .orElse("UNKNOWN");
    }

    protected RelatorioDinamicoResult createNewItem() {
        return new RelatorioDinamicoResult(new HashMap<>());
    }

    @Override
    public Class<RelatorioDinamicoResult> getEntityClass() {
        return RelatorioDinamicoResult.class;
    }

    @Override
    protected Object getRepository() {
        return null;
    }

    private List<String> getCamposFixos() {
        return gridColumnConfigRelatorioService.getColumnConfigs(procedureName).stream()
                .map(GridColumnConfig::getField)
                .toList();
    }
}