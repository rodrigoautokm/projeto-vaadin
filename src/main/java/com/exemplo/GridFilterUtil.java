package com.exemplo;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.MultiSelectComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridSortOrder;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.data.provider.SortDirection;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.shared.Registration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
import org.springframework.dao.DataIntegrityViolationException;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.persistence.OptimisticLockException;


public class GridFilterUtil<T> {

    private static final Logger logger = LoggerFactory.getLogger(GridFilterUtil.class);

    private final String className;
    private final ColumnConfigUsuarioRepository columnConfigUsuarioRepository;
    private Grid<T> grid;
    private List<T> items;
    private String gridId;
    private String usuarioId;
    private Consumer<Map<String, String>> filterChangeListener;
    private Map<String, Set<String>> activeFilters = new HashMap<>();
    private List<ColumnConfig<T>> columnConfigs;
    private GridAggregationUtil<T> aggregationUtil;
    private final HorizontalLayout activeFiltersLayout;
    private boolean filtersChanged = false;
    private boolean columnOrderChanged = false;
    private boolean visibilityChanged = false;
    private List<String> initialColumnOrder;
    private Registration sortOrderListener;
    private String sortColumn;
    private String sortDirection;
    private boolean sortChanged = false;
    private boolean isProcessingReorder = false;
    private final GridColumnConfigService gridColumnConfigService;
    private VerticalLayout layoutRaiz;
	private final VwColumnConfigRepository vwColumnConfigRepository;

    public GridFilterUtil(String className, String gridId, Grid<T> grid, List<ColumnConfig<T>> columnConfigs, String usuarioId, Integer cdEmpresa, ColumnConfigUsuarioRepository columnConfigUsuarioRepository,VwColumnConfigRepository vwColumnConfigRepository) {
        logger.info("Inicializando GridFilterUtil para gridId: {}", gridId);
        this.gridId = gridId;
        this.className = className;
        this.grid = grid;
        this.columnConfigs = columnConfigs;
        this.usuarioId = usuarioId;
        this.columnConfigUsuarioRepository = columnConfigUsuarioRepository;
        this.activeFiltersLayout = new HorizontalLayout();
        this.activeFiltersLayout.setAlignItems(VerticalLayout.Alignment.CENTER);
        this.activeFiltersLayout.setSpacing(true);
        this.activeFiltersLayout.setMinWidth("300px");
        this.activeFiltersLayout.getStyle()
                .set("margin-left", "10px")
                .set("white-space", "nowrap")
                .set("flex-shrink", "0");

        this.gridColumnConfigService = AppContext.getBean(GridColumnConfigService.class);
        this.items = new ArrayList<>();
        this.layoutRaiz = new VerticalLayout();
        this.layoutRaiz.setSizeFull();
        this.layoutRaiz.add(grid);
		this.vwColumnConfigRepository = vwColumnConfigRepository;

        initialize(gridId, grid, columnConfigs, () -> null);
    }

    public GridFilterUtil(Grid<T> grid, List<T> items, String gridId, String usuarioId, GridColumnConfigService gridColumnConfigService, ColumnConfigUsuarioRepository columnConfigUsuarioRepository,VwColumnConfigRepository vwColumnConfigRepository) {
        logger.info("Inicializando GridFilterUtil para gridId: {}", gridId);
        this.grid = grid;
        this.items = new ArrayList<>(items != null ? items : new ArrayList<>());
        this.gridId = gridId;
        this.usuarioId = usuarioId;
        this.gridColumnConfigService = gridColumnConfigService;
        this.columnConfigUsuarioRepository = columnConfigUsuarioRepository;
        this.className = "default";
        this.activeFiltersLayout = new HorizontalLayout();
        this.activeFiltersLayout.setAlignItems(VerticalLayout.Alignment.CENTER);
        this.activeFiltersLayout.setSpacing(true);
        this.activeFiltersLayout.setMinWidth("300px");
        this.activeFiltersLayout.getStyle()
                .set("margin-left", "10px")
                .set("white-space", "nowrap")
                .set("flex-shrink", "0");

        this.layoutRaiz = new VerticalLayout();
        this.layoutRaiz.setSizeFull();
        this.layoutRaiz.add(grid);
		this.vwColumnConfigRepository = vwColumnConfigRepository;		
    }

    private String getUsuarioId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || auth.getName() == null || auth.getName().isEmpty()) {
            logger.error("Nenhum usuário autenticado encontrado no SecurityContextHolder.");
            throw new IllegalStateException("Operação requer usuário autenticado, mas nenhum usuário foi encontrado.");
        }
        String usuarioId = auth.getName();
        logger.debug("UsuarioId obtido: {}", usuarioId);
        return usuarioId;
    }

public void initialize(String gridId, Grid<T> grid, List<ColumnConfig<T>> columnConfigs, Supplier<T> itemSupplier) {
    logger.info("Inicializando GridFilterUtil para gridId: {}. Colunas fornecidas: {}", gridId, columnConfigs.size());
    this.gridId = gridId;
    this.grid = grid;
    this.columnConfigs = new ArrayList<>(columnConfigs);
    this.aggregationUtil = new GridAggregationUtil<>(this.items, this.columnConfigs);

    grid.setItems(this.items);
    grid.setColumnReorderingAllowed(true);
    logger.debug("Reordenação de colunas habilitada: {}", grid.isColumnReorderingAllowed());
    grid.getElement().setProperty("resizable", true);

    initializeDefaultColumnConfigs();

    sortOrderListener = grid.addSortListener(event -> {
        logger.debug("Evento de ordenação disparado para gridId: {}", gridId);
        List<GridSortOrder<T>> sortOrders = event.getSortOrder();
        if (!sortOrders.isEmpty()) {
            GridSortOrder<T> sortOrder = sortOrders.get(0);
            Grid.Column<T> sortedColumn = sortOrder.getSorted();
            String columnKey = sortedColumn.getKey();
            // Mapear columnKey para fieldName
            String fieldName = columnConfigs.stream()
                .filter(cfg -> cfg.column.getKey().equals(columnKey))
                .findFirst()
                .map(cfg -> cfg.getGridColumnConfig().getField())
                .orElse(columnKey);
            sortColumn = fieldName; // Usar fieldName diretamente
            sortDirection = sortOrder.getDirection() == SortDirection.ASCENDING ? "ASC" : "DESC";
            logger.debug("Ordenação alterada para gridId: {}. Coluna: {}, Direção: {}", gridId, sortColumn, sortDirection);
            sortChanged = true;
        } else {
            sortColumn = null;
            sortDirection = null;
            logger.debug("Ordenação removida para gridId: {}", gridId);
            sortChanged = true;
        }
        salvarSortConfig();
    });

    grid.addColumnReorderListener(event -> {
        if (isProcessingReorder) {
            logger.debug("Evento de reordenação ignorado para evitar loop para gridId: {}", gridId);
            return;
        }

        try {
            isProcessingReorder = true;
            logger.debug("Evento de reordenação de colunas disparado para gridId: {}", gridId);
            final List<Grid.Column<T>> newOrder = event.getColumns();
            IntStream.range(0, newOrder.size()).forEach(i -> {
                Grid.Column<T> column = newOrder.get(i);
                String columnKey = column.getKey();
                if (columnKey != null) {
                    columnConfigs.stream()
                        .filter(cfg -> columnKey.equals(cfg.getColumn().getKey()))
                        .findFirst()
                        .ifPresent(cfg -> {
                            ColumnConfigUsuario config = new ColumnConfigUsuario();
                            config.setClassName(className);
                            String fieldName = cfg.getGridColumnConfig().getField();
                            config.setFieldName(fieldName);
                            try {
                                config.setUsuario(getUsuarioId());
                                config.setOrdenacaoGrid(i + 1);
                                saveColumnConfig(config);
                            } catch (IllegalStateException e) {
                                logger.error("Não foi possível salvar configuração de ordem devido à falta de autenticação: {}", e.getMessage());
                            }
                        });
                }
            });
            logger.debug("Nova ordem das colunas salva para gridId: {}", gridId);
        } finally {
            isProcessingReorder = false;
        }
    });

    logger.debug("GridFilterUtil inicializado com {} itens iniciais", this.items.size());
    adicionarFiltrosNoCabecalho(columnConfigs);
}

private void initializeDefaultColumnConfigs() {
    if (vwColumnConfigRepository == null) {
        logger.warn("vwColumnConfigRepository está nulo. Não foi possível carregar visibilidade padrão.");
        return;
    }

    try {
        List<VwColumnConfigEntity> configList = vwColumnConfigRepository.findByUsuarioAndClassName(usuarioId, className);

        Map<String, Boolean> visibilidadeMap = configList.stream()
            .collect(Collectors.toMap(
                VwColumnConfigEntity::getFieldName,
                config -> "1".equals(config.getVisible()), // Apenas verifica "1" para true, qualquer outro valor (incluindo "0") será false
                (v1, v2) -> v1 // Mantém o primeiro valor em caso de chaves duplicadas
            ));

        for (ColumnConfig<T> columnConfig : columnConfigs) {
            String fieldName = columnConfig.getGridColumnConfig().getField();
            Boolean visible = visibilidadeMap.getOrDefault(fieldName, true);
            columnConfig.getColumn().setVisible(visible);

            logger.debug("Coluna {} visibilidade definida como {} via vw_column_config", fieldName, visible);
        }
    } catch (Exception e) {
        logger.error("Erro ao carregar visibilidade padrão da vw_column_config: {}", e.getMessage(), e);
        for (ColumnConfig<T> columnConfig : columnConfigs) {
            columnConfig.getColumn().setVisible(true);
        }
    }
}


    public void saveChanges() {
        logger.debug("Verificando mudanças para salvar no gridId: {}", gridId);

        logger.debug("visibilityChanged: {}, filtersChanged: {}, columnOrderChanged: {}", 
                visibilityChanged, filtersChanged, columnOrderChanged);
        if (visibilityChanged) {
            salvarPreferencias();
            visibilityChanged = false;
        }
        if (filtersChanged) {
            salvarFiltros();
            filtersChanged = false;
        }
        if (columnOrderChanged) {
            List<Grid.Column<T>> columns = grid.getColumns();
            IntStream.range(0, columns.size()).forEach(i -> {
                Grid.Column<T> column = columns.get(i);
                String columnKey = column.getKey();
                if (columnKey != null) {
                    columnConfigs.stream()
                        .filter(cfg -> columnKey.equals(cfg.getColumn().getKey()))
                        .findFirst()
                        .ifPresent(cfg -> {
                            ColumnConfigUsuario config = new ColumnConfigUsuario();
                            config.setClassName(className);
                            String fieldName = cfg.getGridColumnConfig().getField();
                            config.setFieldName(fieldName);
                            try {
                                config.setUsuario(getUsuarioId());
                                config.setOrdenacaoGrid(i + 1);
                                saveColumnConfig(config);
                            } catch (IllegalStateException e) {
                                logger.error("Não foi possível salvar configuração de ordem devido à falta de autenticação: {}", e.getMessage());
                            }
                        });
                }
            });
            columnOrderChanged = false;
        }

        if (sortOrderListener != null) {
            sortOrderListener.remove();
            sortOrderListener = null;
        }
    }

    private List<VwColumnConfigEntity> loadColumnConfigs() {
        VwColumnConfigRepository repository = AppContext.getBean(VwColumnConfigRepository.class);
        List<VwColumnConfigEntity> configs = repository.findByClassName(className);
        logger.debug("Configurações carregadas da view vw_column_config para className {}: {} entradas", className, configs.size());
        return configs;
    }

    private void carregarPreferencias() {
        logger.debug("Carregando preferências para gridId: {} e usuarioId: {}", gridId, usuarioId);
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Integer cdEmpresa = null;
        if (auth != null && auth.getPrincipal() instanceof CustomUserDetails) {
            Integer cdEmpresaInteger = ((CustomUserDetails) auth.getPrincipal()).getCdEmpresa();
            cdEmpresa = cdEmpresaInteger != null ? cdEmpresaInteger.intValue() : null;
        }

        if (cdEmpresa == null || cdEmpresa <= 0) {
            logger.warn("cdEmpresa ausente ou inválido ({}), ignorando carregamento de preferências para gridId: {}", cdEmpresa, gridId);
            return;
        }

        Map<String, Boolean> preferencias = new HashMap<>();
        try {
            List<VwColumnConfigEntity> configs = loadColumnConfigs();
            for (VwColumnConfigEntity config : configs) {
                String visibleValue = config.getVisible();
                boolean isVisible = "1".equalsIgnoreCase(visibleValue) || "S".equalsIgnoreCase(visibleValue);
                preferencias.put(config.getFieldName(), isVisible);
            }
            logger.debug("Preferências carregadas para gridId {} e usuarioId {}: {}", gridId, usuarioId, preferencias);
            List<String> columnKeys = grid.getColumns().stream()
                .map(Grid.Column::getKey)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
            logger.debug("Chaves das colunas no grid: {}", columnKeys);
        } catch (Exception e) {
            logger.error("Erro ao carregar preferências para gridId: {} e usuarioId: {}. Usando configurações padrão.", gridId, usuarioId, e);
            preferencias = new HashMap<>();
        }
    
        if (!preferencias.isEmpty()) {
            final Map<String, Boolean> preferenciaFinal = preferencias;
            grid.getColumns().forEach(column -> {
                String columnKey = column.getKey();
                if (columnKey != null) {
                    String fieldName = columnConfigs.stream()
                        .filter(cfg -> cfg.column.getKey().equals(columnKey))
                        .findFirst()
                        .map(cfg -> cfg.getGridColumnConfig().getField())
                        .orElse(columnKey);
                    if (preferenciaFinal.containsKey(fieldName)) {
                        Boolean visible = preferenciaFinal.get(fieldName);
                        column.setVisible(visible);
                        logger.debug("Coluna {} visibilidade definida como {} via preferências do banco de dados", columnKey, visible);
                    } else {
                        logger.warn("Coluna {} não encontrada nas preferências, usando visibilidade padrão (true)", columnKey);
                        column.setVisible(true);
                    }
                }
            });
        } else {
            logger.debug("Nenhuma preferência encontrada para gridId: {} e usuarioId: {}, inicializando configurações padrão", gridId, usuarioId);
            initializeDefaultColumnConfigs();
            carregarPreferencias();
        }
    }

    private void salvarPreferencias() {
        logger.debug("Salvando preferências para gridId: {} e usuarioId: {}", gridId, usuarioId);
        try {
            String usuario = getUsuarioId();
            Map<String, Boolean> preferencias = grid.getColumns().stream()
                    .filter(col -> col.getKey() != null)
                    .collect(Collectors.toMap(
                            Grid.Column::getKey,
                            Grid.Column::isVisible
                    ));

            logger.debug("Preferências a serem salvas: {}", preferencias);

            for (Map.Entry<String, Boolean> entry : preferencias.entrySet()) {
                String columnKey = entry.getKey();
                Boolean visible = entry.getValue();

                String fieldName = columnConfigs.stream()
                    .filter(cfg -> cfg.column.getKey().equals(columnKey))
                    .findFirst()
                    .map(cfg -> cfg.getGridColumnConfig().getField())
                    .orElse(columnKey);

                List<ColumnConfigUsuario> existingList = columnConfigUsuarioRepository.findByUsuarioAndClassNameAndFieldName(usuario, className, fieldName);
                Optional<ColumnConfigUsuario> existingConfig = existingList.stream().findFirst();

                ColumnConfigUsuario config = existingConfig.orElse(new ColumnConfigUsuario());

                config.setClassName(className);
                config.setFieldName(fieldName);
                config.setUsuario(usuario);
                config.setVisible(visible ? "1" : "0");
                if (config.getOrdenacaoGrid() == null) {
                    config.setOrdenacaoGrid(0);
                }

                try {
                    columnConfigUsuarioRepository.save(config);
                    logger.debug("Configuração salva: fieldName={}, visible={}", fieldName, visible);
                } catch (DataIntegrityViolationException e) {
                    logger.warn("Configuração duplicada detectada para usuario={}, className={}, fieldName={}. Tentando atualizar...", 
                        usuario, className, fieldName);
                    existingList = columnConfigUsuarioRepository.findByUsuarioAndClassNameAndFieldName(usuario, className, fieldName);
                    existingConfig = existingList.stream().findFirst();
                    if (existingConfig.isPresent()) {
                        ColumnConfigUsuario existing = existingConfig.get();
                        existing.setVisible(visible ? "1" : "0");
                        columnConfigUsuarioRepository.save(existing);
                        logger.info("Configuração atualizada com sucesso: fieldName={}", fieldName);
                    } else {
                        logger.error("Erro ao salvar configuração: {}", e.getMessage(), e);
                        throw e;
                    }
                }
            }

            logger.info("Preferências de visibilidade salvas no banco de dados para gridId={} e usuario={}", gridId, usuario);
        } catch (IllegalStateException e) {
            logger.error("Não foi possível salvar preferências devido à falta de autenticação: {}", e.getMessage());
        } catch (Exception e) {
            logger.error("Erro ao salvar preferências de visibilidade para gridId: {} e usuarioId: {}", gridId, usuarioId, e);
            throw e;
        }
    }

private Map<String, String> carregarFiltros() {
    logger.debug("Carregando filtros para usuario={} e className={}", getUsuarioId(), className);
    Map<String, String> filtros = new HashMap<>();
    if (columnConfigUsuarioRepository != null) {
        try {
            String usuario = getUsuarioId();
            List<ColumnConfigUsuario> configs = columnConfigUsuarioRepository.findByUsuarioAndClassName(usuario, className);
            logger.debug("Configurações encontradas: {}", configs.size());
            for (ColumnConfigUsuario config : configs) {
                if (config.getFiltroAplicado() != null && !config.getFiltroAplicado().isEmpty()) {
                    filtros.put(config.getFieldName(), config.getFiltroAplicado());
                    logger.debug("Filtro encontrado: fieldName={}, filtroAplicado={}", config.getFieldName(), config.getFiltroAplicado());
                } else {
                    logger.debug("Nenhum filtro aplicado para fieldName={}", config.getFieldName());
                }
            }
        } catch (IllegalStateException e) {
            logger.error("Não foi possível carregar filtros devido à falta de autenticação: {}", e.getMessage());
        }
    } else {
        logger.warn("columnConfigUsuarioRepository é null, não foi possível carregar filtros");
    }
    logger.debug("Filtros carregados: {}", filtros);
    return filtros;
}

    private void salvarFiltros() {
        logger.debug("Salvando filtros para gridId: {} e usuarioId: {}", gridId, usuarioId);
        try {
            String usuario = getUsuarioId();
            Map<String, String> filtros = activeFilters.entrySet().stream()
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            entry -> String.join("-", entry.getValue())
                    ));

            for (Map.Entry<String, String> entry : filtros.entrySet()) {
                String columnKey = entry.getKey();
                String filterValue = entry.getValue();

                String fieldName = columnConfigs.stream()
                    .filter(cfg -> cfg.column.getKey().equals(columnKey))
                    .findFirst()
                    .map(cfg -> cfg.getGridColumnConfig().getField())
                    .orElse(columnKey);

                Optional<ColumnConfigUsuario> existingConfigOpt = columnConfigUsuarioRepository
                        .findByUsuarioAndClassNameAndFieldName(usuario, className, fieldName)
                        .stream()
                        .findFirst();

                ColumnConfigUsuario config = existingConfigOpt.orElse(new ColumnConfigUsuario());
                config.setClassName(className);
                config.setFieldName(fieldName);
                config.setUsuario(usuario);
                config.setFiltroAplicado(filterValue);

                if (config.getOrdenacaoGrid() == null) {
                    config.setOrdenacaoGrid(0);
                }
                if (config.getVisible() == null) {
                    config.setVisible("1");
                }

                columnConfigUsuarioRepository.save(config);
                logger.debug("Filtro salvo para coluna '{}': {}", fieldName, filterValue);
            }

            logger.info("Filtros salvos no banco de dados para gridId={} e usuario={}", gridId, usuario);
        } catch (IllegalStateException e) {
            logger.error("Não foi possível salvar filtros devido à falta de autenticação: {}", e.getMessage());
        } catch (Exception e) {
            logger.error("Erro ao salvar filtros para gridId: {} e usuarioId: {}", gridId, usuarioId, e);
        }
    }

    private String carregarOrdemColunas() {
        logger.debug("Carregando ordem das colunas para gridId: {} e usuarioId: {}", gridId, usuarioId);
        try {
            List<ColumnConfig<T>> configs = columnConfigs;
            List<String> orderedKeys = configs.stream()
                .filter(cfg -> cfg.getGridColumnConfig() != null)
                .filter(cfg -> cfg.getGridColumnConfig().getOrdenacaoGrid() != null)
                .sorted(Comparator.comparingInt(cfg -> cfg.getGridColumnConfig().getOrdenacaoGrid()))
                .map(cfg -> cfg.getGridColumnConfig().getField())
                .collect(Collectors.toList());
            String ordemColunas = String.join("-", orderedKeys);
            logger.debug("Ordem das colunas carregada: {}", ordemColunas);
            return ordemColunas;
        } catch (Exception e) {
            logger.error("Erro ao carregar ordem das colunas para gridId: {} e usuarioId: {}", gridId, usuarioId, e);
            return null;
        }
    }



@Transactional
private void salvarSortConfig() {
    logger.debug("Salvando sort_config para gridId={} e usuarioId={}", gridId, usuarioId);
    try {
        String usuario = getUsuarioId();
        List<ColumnConfigUsuario> allConfigs = columnConfigUsuarioRepository.findByUsuarioAndClassName(usuario, className);
        boolean needsSave = false;

        // Verificar se houve uma mudança explícita na ordenação
        if (sortChanged) {
            if (sortColumn != null && sortDirection != null) {
                ColumnConfigUsuario targetConfig = null;
                for (ColumnConfigUsuario config : allConfigs) {
                    if (config.getFieldName().equals(sortColumn)) {
                        targetConfig = config;
                    } else if (config.getSort() != null && !config.getSort().isEmpty()) {
                        config.setSort(null);
                        needsSave = true;
                    }
                }
                if (targetConfig == null) {
                    targetConfig = new ColumnConfigUsuario();
                    targetConfig.setClassName(className);
                    targetConfig.setFieldName(sortColumn);
                    targetConfig.setUsuario(usuario);
                    targetConfig.setVisible("1");
                    targetConfig.setOrdenacaoGrid(0);
                    targetConfig.setSort(sortDirection);
                    columnConfigUsuarioRepository.save(targetConfig);
                    needsSave = true;
                } else if (!sortDirection.equals(targetConfig.getSort())) {
                    targetConfig.setSort(sortDirection);
                    needsSave = true;
                }
            } else {
                // Apenas limpar se a ordenação foi explicitamente removida
                for (ColumnConfigUsuario config : allConfigs) {
                    if (config.getSort() != null && !config.getSort().isEmpty()) {
                        config.setSort(null);
                        needsSave = true;
                    }
                }
            }
        } else {
            logger.debug("Nenhuma mudança na ordenação detectada, pulando salvamento para gridId={}", gridId);
        }

        if (needsSave) {
            columnConfigUsuarioRepository.saveAll(allConfigs);
            columnConfigUsuarioRepository.flush();
            logger.info("Sort_config salvo com sucesso para gridId={}", gridId);
        } else {
            logger.debug("Nenhuma alteração necessária para sort_config do gridId={}", gridId);
        }

        // Resetar a flag após salvar
        sortChanged = false;
    } catch (Exception e) {
        logger.error("Erro ao salvar sort_config: {}", e.getMessage(), e);
        throw new RuntimeException("Falha ao salvar configuração de ordenação", e);
    }
}



public void carregarSortConfig() {
    logger.debug("Carregando sort_config para gridId={} e usuarioId={}", gridId, usuarioId);
    try {
        String usuario = getUsuarioId();
        List<ColumnConfigUsuario> configs = columnConfigUsuarioRepository.findByUsuarioAndClassName(usuario, className);
        
        // Verificar se há alguma ordenação salva
        boolean foundSort = false;
        
        for (ColumnConfigUsuario config : configs) {
            if (config.getSort() != null && !config.getSort().isEmpty()) {
                String sortFieldName = config.getFieldName();
                String sortDirection = config.getSort();
                logger.info("Sort_config carregado: gridId={}, sortColumn={}, sortDirection={}", 
                    gridId, sortFieldName, sortDirection);

                // Mapear fieldName para columnKey
                Grid.Column<T> column = columnConfigs.stream()
                    .filter(cfg -> cfg.getGridColumnConfig().getField().equals(sortFieldName))
                    .map(cfg -> cfg.column)
                    .findFirst()
                    .orElse(null);

                if (column != null) {
                    SortDirection direction = SortDirection.valueOf(sortDirection.toUpperCase());
                    grid.sort(Collections.singletonList(new GridSortOrder<>(column, direction)));
                    logger.debug("Ordenação aplicada ao grid: coluna={}, direção={}", sortFieldName, sortDirection);
                    foundSort = true;
                    break; // Apenas uma ordenação por grid
                } else {
                    logger.warn("Coluna com fieldName={} não encontrada para aplicar ordenação", sortFieldName);
                }
            }
        }
        
        // Se não encontrou nenhuma ordenação, limpar qualquer ordenação existente
        if (!foundSort) {
            logger.debug("Nenhuma ordenação encontrada, limpando ordenação do grid");
            grid.sort(Collections.emptyList());
        }
    } catch (Exception e) {
        logger.error("Erro ao carregar sort_config: {}", e.getMessage(), e);
    }
}



    private void aplicarOrdenacaoPadrao() {
        logger.debug("Aplicando ordenação padrão para gridId={}", gridId);
        grid.getColumns().stream()
                .filter(Grid.Column::isVisible)
                .findFirst()
                .ifPresentOrElse(column -> {
                    grid.sort(Collections.singletonList(new GridSortOrder<>(column, SortDirection.ASCENDING)));
                    logger.info("Ordenação padrão aplicada: coluna={}, direção=ASC", column.getKey());
                }, () -> {
                    logger.warn("Nenhuma coluna visível encontrada para aplicar ordenação padrão para gridId={}", gridId);
                });
    }

private void saveColumnConfig(ColumnConfigUsuario config) {
    try {
        List<ColumnConfigUsuario> existingConfigs = columnConfigUsuarioRepository.findByUsuarioAndClassName(config.getUsuario(), className);
        ColumnConfigUsuario existingConfig = existingConfigs.stream()
            .filter(c -> c.getFieldName().equals(config.getFieldName()))
            .findFirst()
            .orElse(new ColumnConfigUsuario());

        // Se o registro já existe, preservar o valor de sort antes de atualizar
        String existingSort = existingConfig.getSort();

        // Atualizar os campos fornecidos
        existingConfig.setClassName(className);
        existingConfig.setFieldName(config.getFieldName());
        existingConfig.setUsuario(config.getUsuario());
        existingConfig.setOrdenacaoGrid(config.getOrdenacaoGrid());
        if (config.getVisible() != null) {
            existingConfig.setVisible(config.getVisible());
        } else if (existingConfig.getVisible() == null) {
            existingConfig.setVisible("1");
        }
        if (config.getFiltroAplicado() != null) {
            existingConfig.setFiltroAplicado(config.getFiltroAplicado());
        }
        if (config.getSort() != null) {
            existingConfig.setSort(config.getSort());
        } else {
            // Preservar o valor existente de sort
            existingConfig.setSort(existingSort);
        }

        // Log antes de salvar
        logger.debug("Salvando configuração: id={}, fieldName={}, sort={}", 
            existingConfig.getId(), existingConfig.getFieldName(), existingConfig.getSort());
        columnConfigUsuarioRepository.save(existingConfig);
        logger.debug("Configuração salva com sucesso para fieldName={}", existingConfig.getFieldName());
    } catch (Exception e) {
        logger.error("Erro ao salvar configuração de coluna para fieldName={} e usuario={}: {}", 
            config.getFieldName(), config.getUsuario(), e.getMessage(), e);
    }
}

    public void setFilterChangeListener(Consumer<Map<String, String>> listener) {
        logger.debug("Configurando listener de mudança de filtro para gridId: {}", gridId);
        this.filterChangeListener = listener;
        notifyFilterChange();
    }

public void updateItems(List<T> newItems) {
    logger.info("Atualizando itens no gridId: {}. Novos itens: {}", gridId, newItems != null ? newItems.size() : 0);
    this.items = new ArrayList<>(newItems != null ? newItems : new ArrayList<>());
    grid.setItems(this.items);
    logger.debug("Itens atualizados no grid: {}", this.items.size());
    
    if (aggregationUtil != null) {
        logger.debug("Atualizando itens no GridAggregationUtil para gridId: {}", gridId);
        aggregationUtil.updateItems(this.items);
    }
    
    if (columnConfigs != null) {
        logger.debug("Recarregando filtros com base nos novos itens");
        adicionarFiltrosNoCabecalho(columnConfigs);
        // Reaplicar filtros salvos
        if (!activeFilters.isEmpty()) {
            logger.info("Reaplicando filtros salvos para gridId: {}. Filtros: {}", gridId, activeFilters);
            applyFilters();
            updateFilterRowHighlight();
            grid.getDataProvider().refreshAll();
        } else {
            logger.debug("Nenhum filtro salvo encontrado para reaplicar em gridId: {}", gridId);
        }
    } else {
        logger.warn("columnConfigs é null, não foi possível recarregar filtros para gridId: {}", gridId);
    }
    
    logger.debug("Itens atualizados com sucesso, total: {}", this.items.size());
}

    public void clearAllFilters() {
        logger.info("Limpando todos os filtros para gridId: {}", gridId);
        activeFilters.clear();
        grid.setItems(items);
        filtersChanged = true;
        notifyFilterChange();
        updateFilterRowHighlight();
        logger.debug("Filtros limpos com sucesso");
    }


@SuppressWarnings("unchecked")
public void adicionarFiltrosNoCabecalho(List<ColumnConfig<T>> columnConfigs) {
    logger.info("Adicionando filtros no cabeçalho para gridId: {}. Colunas fornecidas: {}", gridId, columnConfigs.size());
    this.columnConfigs = new ArrayList<>(columnConfigs);
    this.aggregationUtil = new GridAggregationUtil<>(this.items, this.columnConfigs);
    logger.debug("Itens disponíveis: {}", items.size());
    logger.debug("Colunas fornecidas: {}", columnConfigs.stream().map(cfg -> cfg.getHeader()).collect(Collectors.toList()));
    if (items.isEmpty()) {
        logger.warn("Lista de itens vazia. Configurando filtros com base em valores salvos apenas.");
    }

    // Carregar a ordem das colunas da view
    String ordemColunas = carregarOrdemColunas();
    if (ordemColunas != null && !ordemColunas.isEmpty()) {
        List<String> orderedKeys = Arrays.asList(ordemColunas.split("-"));
        List<Grid.Column<T>> columns = grid.getColumns();
        List<Grid.Column<T>> reorderedColumns = new ArrayList<>();
        
        for (String key : orderedKeys) {
            columns.stream()
                .filter(col -> key.equals(col.getKey()))
                .findFirst()
                .ifPresent(reorderedColumns::add);
        }
        columns.stream()
            .filter(col -> !reorderedColumns.contains(col))
            .forEach(reorderedColumns::add);
        
        grid.setColumnOrder(reorderedColumns);
        logger.debug("Ordem das colunas aplicada: {}", ordemColunas);
    } else {
        // Verificar se já existem configurações salvas para evitar sobrescrever
        List<ColumnConfigUsuario> existingConfigs = columnConfigUsuarioRepository.findByUsuarioAndClassName(getUsuarioId(), className);
        if (existingConfigs.isEmpty()) {
            List<Grid.Column<T>> columns = grid.getColumns();
            IntStream.range(0, columns.size()).forEach(i -> {
                Grid.Column<T> column = columns.get(i);
                String columnKey = column.getKey();
                if (columnKey != null) {
                    ColumnConfigUsuario config = new ColumnConfigUsuario();
                    config.setClassName(className);
                    String fieldName = columnConfigs.stream()
                        .filter(cfg -> cfg.column.getKey().equals(columnKey))
                        .findFirst()
                        .map(cfg -> cfg.getGridColumnConfig().getField())
                        .orElse(columnKey);
                    try {
                        config.setUsuario(getUsuarioId());
                        config.setFieldName(fieldName);
                        config.setOrdenacaoGrid(i + 1);
                        saveColumnConfig(config);
                    } catch (IllegalStateException e) {
                        logger.error("Não foi possível salvar configuração de ordem devido à falta de autenticação: {}", e.getMessage());
                    }
                }
            });
            logger.debug("Nenhuma ordem de colunas encontrada, salvando ordem inicial");
        } else {
            logger.debug("Configurações de ordem já existem para gridId: {}, pulando salvamento inicial", gridId);
        }
    }

    initialColumnOrder = grid.getColumns().stream()
            .map(Grid.Column::getKey)
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
    logger.debug("Ordem inicial das colunas: {}", initialColumnOrder);

    List<String> currentColumnKeys = columnConfigs.stream()
            .map(config -> config.column.getKey())
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
    logger.debug("Chaves das colunas: {}", currentColumnKeys);

    activeFilters.keySet().removeIf(columnKey -> {
        if (!currentColumnKeys.contains(columnKey)) {
            logger.warn("Removendo filtro ativo para coluna desconhecida {} em gridId: {}", columnKey, gridId);
            return true;
        }
        return false;
    });

    // Verificar se já existe uma linha de cabeçalho de configuração
    boolean hasConfigHeader = grid.getHeaderRows().stream()
        .anyMatch(row -> row.getCells().stream()
            .anyMatch(cell -> cell.getComponent() != null && cell.getComponent().getClass().getSimpleName().contains("HorizontalLayout")));
    if (!hasConfigHeader) {
        Button configButton = new Button(new Icon(VaadinIcon.COG));
        configButton.getElement().setAttribute("title", "Configurar colunas");
        configButton.addClickListener(event -> {
            logger.debug("Botão de configuração de colunas clicado para gridId: {}", gridId);
            abrirDialogoConfiguracaoColunas(columnConfigs);
        });

        Button saveOrderButton = new Button("Salvar Ordenação", event -> {
            logger.debug("Botão 'Salvar Ordenação' clicado para gridId: {}", gridId);
            columnOrderChanged = true;
            saveChanges();
        });

        Button agruparButton = aggregationUtil.createAggregationButton();

        HorizontalLayout configLayout = new HorizontalLayout(configButton, saveOrderButton, agruparButton);
        configLayout.setDefaultVerticalComponentAlignment(VerticalLayout.Alignment.CENTER);
        configLayout.getStyle().set("flex-shrink", "0");

        HorizontalLayout combinedLayout = new HorizontalLayout(configLayout);
        combinedLayout.setDefaultVerticalComponentAlignment(VerticalLayout.Alignment.CENTER);
        combinedLayout.setWidthFull();
        combinedLayout.getStyle()
                .set("flex-wrap", "nowrap")
                .set("overflow", "visible")
                .set("position", "sticky")
                .set("top", "0")
                .set("z-index", "100")
                .set("background-color", "var(--lumo-base-color)")
                .set("padding", "var(--lumo-space-s)")
                .set("box-shadow", "0 2px 4px rgba(0,0,0,0.1)");
        combinedLayout.addClassName("grid-filter-header-cell");

        HeaderRow configRow = grid.prependHeaderRow();
        configRow.getCells().forEach(cell -> cell.setText(""));
        configRow.join(configRow.getCells().toArray(new HeaderRow.HeaderCell[0])).setComponent(combinedLayout);
        logger.debug("Nova linha de cabeçalho de configuração adicionada para gridId: {}", gridId);
    } else {
        logger.debug("Linha de cabeçalho de configuração já existe para gridId: {}, pulando adição", gridId);
    }

    // Verificar se já existe uma linha de filtros
    HeaderRow filterRow = null;
    for (HeaderRow row : grid.getHeaderRows()) {
        if (row.getCells().stream().anyMatch(cell -> cell.getComponent() instanceof MultiSelectComboBox)) {
            filterRow = row;
            logger.debug("Linha de filtros já existe para gridId: {}, reutilizando", gridId);
            break;
        }
    }
    if (filterRow == null) {
        filterRow = grid.appendHeaderRow();
        logger.debug("Nova linha de filtros criada para gridId: {}", gridId);
    } else {
        // Limpar a linha de filtros existente para evitar duplicatas de componentes
        filterRow.getCells().forEach(cell -> cell.setComponent(null));
        logger.debug("Linha de filtros existente limpa para gridId: {}", gridId);
    }

    Map<String, String> savedFilters = carregarFiltros();
    logger.debug("Filtros salvos carregados para gridId {}: {}", gridId, savedFilters);

    int processedColumns = 0;
    for (ColumnConfig<T> config : columnConfigs) {
        Grid.Column<T> column = config.column;
        String columnKey = column.getKey();
        if (columnKey == null) {
            logger.warn("Coluna sem chave definida, ignorando para evitar erro.");
            continue;
        }

        logger.debug("Processando coluna com chave: {}", columnKey);

        if (!column.isVisible()) {
            logger.debug("Coluna {} está oculta, pulando configuração de filtro", columnKey);
            continue;
        }

        column.setResizable(true);
        column.setAutoWidth(false);
        column.setSortable(true);

        GridColumnConfig gridColumnConfig = gridColumnConfigService.getColumnConfig(gridId, usuarioId, columnKey);
        if (gridColumnConfig == null) {
            gridColumnConfig = config.getGridColumnConfig();
        }
        if (gridColumnConfig != null) {
            logger.debug("Coluna {}: largura definida: {}", columnKey, gridColumnConfig.getWidth());
            logger.debug("Coluna {}: dropdownValueMap: {}", columnKey, gridColumnConfig.getDropdownValueMap());
        }

        String fieldName = columnConfigs.stream()
            .filter(cfg -> cfg.column.getKey().equals(columnKey))
            .findFirst()
            .map(cfg -> cfg.getGridColumnConfig().getField())
            .orElse(columnKey);
        logger.debug("fieldName correspondente a columnKey {}: {}", columnKey, fieldName);

        if (gridColumnConfig != null && gridColumnConfig.getDropdownValueMap() != null && !gridColumnConfig.getDropdownValueMap().isEmpty()) {
            Map<String, String> valueMap = gridColumnConfig.getDropdownValueMap();
            grid.removeColumn(column);
            column = grid.addColumn(item -> {
                Object rawValue = config.getValueExtractor().apply(item);
                return rawValue != null ? valueMap.getOrDefault(rawValue.toString(), rawValue.toString()) : "";
            });
            column.setKey(columnKey);
            column.setResizable(true);
            column.setAutoWidth(false);
            column.setSortable(true);
            column.setHeader(config.getHeader());
            config.setColumn(column);
        }

        final String finalColumnKey = columnKey;

        HorizontalLayout headerLayout = new HorizontalLayout();
        headerLayout.setDefaultVerticalComponentAlignment(VerticalLayout.Alignment.CENTER);
        headerLayout.setWidth(gridColumnConfig != null ? gridColumnConfig.getWidth() : "120px");
        headerLayout.getStyle()
                .set("display", "flex")
                .set("justify-content", "space-between")
                .set("align-items", "center")
                .set("padding", "0 8px")
                .set("margin", "0")
                .set("box-sizing", "border-box")
                .set("overflow", "hidden");

        if (gridColumnConfig != null) {
            StringBuilder tooltip = new StringBuilder("Configurações da Coluna:\n");
            tooltip.append("Field: ").append(gridColumnConfig.getField()).append("\n");
            tooltip.append("Header: ").append(gridColumnConfig.getHeader()).append("\n");
            tooltip.append("Type: ").append(gridColumnConfig.getType()).append("\n");
            tooltip.append("Width: ").append(gridColumnConfig.getWidth()).append("\n");
            tooltip.append("Style: ").append(gridColumnConfig.getStyle()).append("\n");
            tooltip.append("Filter Type: ").append(gridColumnConfig.getFilterType()).append("\n");
            tooltip.append("Visible: ").append(gridColumnConfig.isVisible());

            if (gridColumnConfig instanceof VwColumnConfigEntity) {
                VwColumnConfigEntity vw = (VwColumnConfigEntity) gridColumnConfig;
                tooltip.append("\nAlias: ").append(vw.getAlias());
                tooltip.append("\nUsuário: ").append(vw.getUsuario());
            }

            headerLayout.getElement().setAttribute("title", tooltip.toString());
        }

        Span headerSpan = new Span(config.header);
        headerSpan.getStyle()
                .set("flex-grow", "1")
                .set("overflow", "hidden")
                .set("text-overflow", "ellipsis")
                .set("white-space", "nowrap");
        headerLayout.add(headerSpan);
        column.setHeader(headerLayout);

        final MultiSelectComboBox<String> multiSelectComboBox = new MultiSelectComboBox<>();
        multiSelectComboBox.setClearButtonVisible(true);
        multiSelectComboBox.setPlaceholder("Selecionar...");
        multiSelectComboBox.getStyle().set("font-size", "12px");

        final Map<String, String> valueMap = (gridColumnConfig != null && gridColumnConfig.getDropdownValueMap() != null) 
            ? gridColumnConfig.getDropdownValueMap() 
            : new HashMap<>();

        // Contar valores aplicando o mapeamento
        final Map<String, Long> contagemPorValor = items.stream()
            .map(item -> {
                Object rawValue = config.valueExtractor.apply(item);
                String mappedValue = rawValue != null ? valueMap.getOrDefault(rawValue.toString(), rawValue.toString()) : "N/A";
                logger.debug("Valor extraído para coluna {}: raw={}, mapped={}", finalColumnKey, rawValue, mappedValue);
                return mappedValue;
            })
            .collect(Collectors.groupingBy(v -> v, Collectors.counting()));

        List<String> valoresUnicos = contagemPorValor.keySet().stream()
            .sorted()
            .collect(Collectors.toList());
        logger.debug("Valores únicos para coluna {}: {}", finalColumnKey, valoresUnicos);

        List<String> comboItems = new ArrayList<>(valoresUnicos);
        multiSelectComboBox.setItems(comboItems);
        multiSelectComboBox.setRenderer(new ComponentRenderer<>(item -> {
            String texto = item + " (" + contagemPorValor.getOrDefault(item, 0L) + ")";
            return new Span(texto);
        }));

        int maxLength = valoresUnicos.stream()
            .mapToInt(item -> (item + " (" + contagemPorValor.getOrDefault(item, 0L) + ")").length())
            .max()
            .orElse(10);
        int widthPx = Math.min(Math.max(maxLength * 8, 100), 200);
        multiSelectComboBox.setWidth(widthPx + "px");

        multiSelectComboBox.addValueChangeListener(event -> {
            logger.debug("Filtro alterado para coluna {} em gridId: {}. Valores selecionados: {}", finalColumnKey, gridId, event.getValue());
            Set<String> selectedValues = event.getValue();
            if (selectedValues == null || selectedValues.isEmpty()) {
                activeFilters.remove(finalColumnKey);
                if (columnConfigUsuarioRepository != null) {
                    try {
                        String usuario = getUsuarioId();
                        logger.debug("Limpando filtro para usuario={}, className={}, fieldName={}", usuario, className, fieldName);
                        columnConfigUsuarioRepository.limparFiltro(usuario, className, fieldName);
                    } catch (IllegalStateException e) {
                        logger.error("Não foi possível limpar o filtro devido à falta de autenticação: {}", e.getMessage());
                    }
                } else {
                    logger.warn("columnConfigUsuarioRepository é null, não foi possível limpar o filtro para coluna {}", finalColumnKey);
                }
            } else {
                activeFilters.put(finalColumnKey, selectedValues);
            }
            applyFilters();
            updateFilterRowHighlight();
            saveChanges();
        });

        if (column.isVisible()) {
            HeaderRow.HeaderCell filterCell = filterRow.getCell(column);
            if (filterCell == null) {
                logger.warn("Célula de filtro não encontrada para coluna {} em gridId: {}", finalColumnKey, gridId);
            } else {
                filterCell.setComponent(multiSelectComboBox);
                logger.debug("Filtro configurado para coluna {} em gridId: {}", finalColumnKey, gridId);
            }
        } else {
            logger.debug("Coluna {} não está visível, filtro não será configurado", finalColumnKey);
        }

        // Carregar filtros salvos sem aplicar imediatamente
        if (savedFilters != null && savedFilters.containsKey(fieldName)) {
            String savedFilterValue = savedFilters.get(fieldName);
            logger.info("Carregando filtro salvo para coluna {} (fieldName: {}) em gridId: {}. Valores: {}", finalColumnKey, fieldName, gridId, savedFilterValue);
            Set<String> selectedValues = new HashSet<>(Arrays.asList(savedFilterValue.split("-")));
            if (!selectedValues.isEmpty()) {
                activeFilters.put(finalColumnKey, selectedValues);
                // Adicionar valores salvos à lista de itens do combo box
                comboItems.addAll(selectedValues);
                multiSelectComboBox.setItems(comboItems);
                multiSelectComboBox.setValue(selectedValues);
                updateFilterRowHighlight();
                logger.debug("Filtro salvo aplicado ao combo box para coluna {}: {}", finalColumnKey, selectedValues);
            } else {
                logger.warn("Valores de filtro salvos vazios para coluna {} (fieldName: {}) em gridId: {}. Ignorando.", finalColumnKey, fieldName, gridId);
            }
        } else {
            logger.debug("Nenhum filtro salvo encontrado para coluna {} (fieldName: {}) em gridId: {}", finalColumnKey, fieldName, gridId);
        }

        if (column.isVisible() && activeFilters.containsKey(finalColumnKey) && !activeFilters.get(finalColumnKey).isEmpty()) {
            multiSelectComboBox.getStyle()
                    .set("background-color", "var(--lumo-primary-color-10pct)")
                    .set("border", "1px solid var(--lumo-primary-color)");
        }

        processedColumns++;
    }

    logger.info("Total de colunas processadas para gridId {}: {}", gridId, processedColumns);
    List<String> visibleColumns = grid.getColumns().stream()
            .filter(Grid.Column::isVisible)
            .map(Grid.Column::getKey)
            .collect(Collectors.toList());
    logger.info("Colunas visíveis após configuração para gridId {}: {}", gridId, visibleColumns);
    logger.debug("Filtros adicionados ao cabeçalho com sucesso para gridId: {}", gridId);

    // Reaplicar filtros salvos após configurar todas as colunas
    if (!activeFilters.isEmpty()) {
        logger.info("Reaplicando filtros salvos após configuração do cabeçalho para gridId: {}. Filtros: {}", gridId, activeFilters);
        applyFilters();
        updateFilterRowHighlight();
    }

    // Reaplicar a ordenação após todas as configurações
    carregarSortConfig();

    grid.getDataProvider().refreshAll();
}

private void applyFilters() {
    logger.debug("Aplicando filtros para gridId: {}. Filtros ativos: {}", gridId, activeFilters);
    List<T> filteredItems = items.stream()
            .filter(item -> {
                for (Map.Entry<String, Set<String>> filter : activeFilters.entrySet()) {
                    String filterKey = filter.getKey();
                    Set<String> filterValues = filter.getValue();
                    if (filterValues == null || filterValues.isEmpty()) {
                        logger.debug("Filtro vazio para coluna {} em gridId: {}, ignorando", filterKey, gridId);
                        continue;
                    }
                    ColumnConfig<T> cfg = columnConfigs.stream()
                            .filter(c -> c.column.getKey().equals(filterKey))
                            .findFirst()
                            .orElse(null);
                    if (cfg == null) {
                        logger.warn("Configuração de coluna não encontrada para filterKey {} em gridId: {}", filterKey, gridId);
                        continue;
                    }

                    Object rawValue = cfg.valueExtractor.apply(item);
                    String stringValue = rawValue != null ? String.valueOf(rawValue) : "N/A";

                    GridColumnConfig gridColumnConfig = cfg.getGridColumnConfig();
                    if (gridColumnConfig != null && gridColumnConfig.getDropdownValueMap() != null && !gridColumnConfig.getDropdownValueMap().isEmpty()) {
                        Map<String, String> valueMap = gridColumnConfig.getDropdownValueMap();
                        stringValue = valueMap.getOrDefault(stringValue, stringValue);
                        logger.debug("Mapeando valor bruto {} para {} na coluna {} em gridId: {}", rawValue, stringValue, filterKey, gridId);
                    }

                    logger.debug("Comparando valor mapeado {} com filterValues {} na coluna {} em gridId: {}", stringValue, filterValues, filterKey, gridId);
                    if (!filterValues.contains(stringValue)) {
                        return false;
                    }
                }
                return true;
            })
            .collect(Collectors.toList());

    logger.info("Itens após aplicar filtros para gridId {}: {}", gridId, filteredItems.size());
    if (filteredItems.isEmpty() && !activeFilters.isEmpty()) {
        logger.warn("Nenhum item corresponde aos filtros aplicados para gridId {}. Filtros: {}", gridId, activeFilters);
    }

    grid.setItems(filteredItems);
    filtersChanged = true;
    notifyFilterChange();
}

    private String mapTextAlignToJustifyContent(String textAlign) {
        switch (textAlign.toLowerCase()) {
            case "left":
                return "flex-start";
            case "right":
                return "flex-end";
            case "center":
                return "center";
            default:
                return "flex-start";
        }
    }

    private void updateFilterRowHighlight() {
        logger.debug("Atualizando destaque na linha de filtro para gridId: {}", gridId);
        HeaderRow filterRow = grid.getHeaderRows().stream()
                .filter(row -> row.getCells().stream().anyMatch(cell -> cell.getComponent() instanceof MultiSelectComboBox))
                .findFirst()
                .orElse(null);

        if (filterRow == null) {
            logger.warn("Linha de filtro não encontrada para gridId: {}", gridId);
            return;
        }

        for (ColumnConfig<T> config : columnConfigs) {
            Grid.Column<T> column = config.column;
            String columnKey = column.getKey();
            HeaderRow.HeaderCell filterCell = filterRow.getCell(column);
            if (filterCell != null && filterCell.getComponent() != null) {
                Component component = filterCell.getComponent();
                if (component instanceof MultiSelectComboBox) {
                    @SuppressWarnings("unchecked")
                    MultiSelectComboBox<String> comboBox = (MultiSelectComboBox<String>) component;
                    boolean hasFilter = activeFilters.containsKey(columnKey) && activeFilters.get(columnKey) != null && !activeFilters.get(columnKey).isEmpty();
                    if (hasFilter) {
                        comboBox.getStyle()
                                .set("background-color", "var(--lumo-primary-color-10pct)")
                                .set("border", "1px solid var(--lumo-primary-color)");
                    } else {
                        comboBox.getStyle()
                                .remove("background-color")
                                .remove("border");
                    }
                }
            }
        }
    }

private void abrirDialogoConfiguracaoColunas(List<ColumnConfig<T>> columnConfigs) {
    logger.info("Abrindo diálogo de configuração de colunas para gridId: {}", gridId);
    Dialog dialog = new Dialog();
    dialog.setModal(true);
    dialog.setWidth("300px");

    VerticalLayout layout = new VerticalLayout();
    layout.setPadding(false);
    layout.setSpacing(true);

    List<Checkbox> columnCheckboxes = new ArrayList<>();
    for (ColumnConfig<T> config : columnConfigs) {
        Grid.Column<T> column = config.column;
        // Exibe todas as colunas, visíveis ou não, com o estado atual de visibilidade
        Checkbox checkbox = new Checkbox(config.header, column.isVisible());
        checkbox.addValueChangeListener(event -> {
            logger.debug("Visibilidade da coluna {} alterada para {} em gridId: {}", config.header, event.getValue(), gridId);
            column.setVisible(event.getValue());
            visibilityChanged = true;
            grid.getDataProvider().refreshAll();
            saveChanges(); // Salva a alteração na tabela column_config_usuario
        });
        columnCheckboxes.add(checkbox);
        layout.add(checkbox);
    }

    Button selectAllButton = new Button("Selecionar Todos", event -> {
        logger.debug("Botão 'Selecionar Todos' clicado para gridId: {}", gridId);
        for (Checkbox checkbox : columnCheckboxes) {
            checkbox.setValue(true);
            Grid.Column<T> column = columnConfigs.get(columnCheckboxes.indexOf(checkbox)).column;
            column.setVisible(true);
        }
        visibilityChanged = true;
        grid.getDataProvider().refreshAll();
        saveChanges();
    });

    Button deselectAllButton = new Button("Desmarcar Todos", event -> {
        logger.debug("Botão 'Desmarcar Todos' clicado para gridId: {}", gridId);
        for (Checkbox checkbox : columnCheckboxes) {
            checkbox.setValue(false);
            Grid.Column<T> column = columnConfigs.get(columnCheckboxes.indexOf(checkbox)).column;
            column.setVisible(false);
        }
        visibilityChanged = true;
        grid.getDataProvider().refreshAll();
        saveChanges();
    });

    Button fecharButton = new Button("Fechar", event -> dialog.close());
    layout.add(selectAllButton, deselectAllButton, fecharButton);

    dialog.add(layout);
    dialog.open();
}

    private void notifyFilterChange() {
        logger.debug("Notificando mudança de filtros para gridId: {}", gridId);
        if (filterChangeListener != null) {
            Map<String, String> filterMap = activeFilters.entrySet().stream()
                    .filter(entry -> entry.getValue() != null && !entry.getValue().isEmpty())
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            entry -> String.join("-", entry.getValue())
                    ));
            filterChangeListener.accept(filterMap);
        }
    }

    public Component getLayout() {
        return layoutRaiz;
    }

    public void clearGrid() {
        logger.info("Limpando completamente o grid para gridId: {}", gridId);
        grid.getColumns().forEach(column -> grid.removeColumn(column));
        activeFilters.clear();
        items.clear();
        grid.setItems(new ArrayList<>());
        logger.debug("Grid limpo com sucesso");
    }

    public void addColumns(List<GridColumnConfig> configs) {
        logger.info("🧩 Adicionando {} colunas ao grid {}", configs.size(), gridId);
        Set<String> keysJaAdicionados = new HashSet<>();
        this.columnConfigs = new ArrayList<>();

        for (GridColumnConfig config : configs) {
            String field = config.getField();
            if (keysJaAdicionados.contains(field)) {
                logger.warn("🔁 Ignorando coluna duplicada: {}", field);
                continue;
            }
            keysJaAdicionados.add(field);

            Grid.Column<T> column = grid.addColumn(item -> {
                if (item instanceof RelatorioDinamicoResult) {
                    Object valor = ((RelatorioDinamicoResult) item).getValores().get(field);
                    return valor != null ? valor.toString() : "";
                }
                return "";
            });

            column.setKey(field);
            column.setHeader(config.getHeader() != null ? config.getHeader() : field);
            column.setSortable(true);
            column.setResizable(true);
            column.setAutoWidth(true);
            column.setVisible(config.isVisible());

            ColumnConfig<T> columnConfig = new ColumnConfig<>(
                column,
                config.getHeader() != null ? config.getHeader() : field,
                item -> {
                    if (item instanceof RelatorioDinamicoResult) {
                        return ((RelatorioDinamicoResult) item).getValores().get(field);
                    }
                    return null;
                },
                config
            );

            this.columnConfigs.add(columnConfig);
        }
    }

    public static String serializarFiltros(Map<String, List<String>> filtros) {
        return filtros.entrySet().stream()
            .map(entry -> entry.getKey() + "=" + String.join(",", entry.getValue()))
            .collect(Collectors.joining(";"));
    }

    public static class ColumnConfig<T> {
        private Grid.Column<T> column;
        private final String header;
        private final Function<T, Object> valueExtractor;
        private final GridColumnConfig gridColumnConfig;

        public ColumnConfig(Grid.Column<T> column, String header, Function<T, Object> valueExtractor, GridColumnConfig gridColumnConfig) {
            this.column = column;
            this.header = header;
            this.valueExtractor = valueExtractor;
            this.gridColumnConfig = gridColumnConfig;
        }

        public Grid.Column<T> getColumn() {
            return column;
        }

        public String getHeader() {
            return header;
        }

        public Function<T, Object> getValueExtractor() {
            return valueExtractor;
        }

        public GridColumnConfig getGridColumnConfig() {
            return gridColumnConfig;
        }

        public void setColumn(Grid.Column<T> column) {
            this.column = column;
        }

        public void setProperty(String property) {
        }

        public void setVisible(boolean visible) {
            column.setVisible(visible);
        }
    }

    public static class GridColumnDto<T> {
        private Grid.Column<T> column;
        private Function<T, ?> valueExtractor;
        private GridColumnConfig gridColumnConfig;

        public GridColumnDto(Grid.Column<T> column, Function<T, ?> extractor, GridColumnConfig config) {
            this.column = column;
            this.valueExtractor = extractor;
            this.gridColumnConfig = config;
        }

        public Grid.Column<T> getColumn() {
            return column;
        }

        public void setColumn(Grid.Column<T> column) {
            this.column = column;
        }

        public Function<T, ?> getValueExtractor() {
            return valueExtractor;
        }

        public GridColumnConfig getGridColumnConfig() {
            return gridColumnConfig;
        }
    }
}