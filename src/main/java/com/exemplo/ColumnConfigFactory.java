package com.exemplo;

import com.vaadin.flow.component.grid.Grid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Component
public class ColumnConfigFactory {

    private static final Logger logger = LoggerFactory.getLogger(ColumnConfigFactory.class);

    @Autowired
    private VwColumnConfigRepository columnConfigRepository;

    public List<GridColumnConfig> loadColumnConfigs() {
        List<GridColumnConfig> columnConfigs = new ArrayList<>();
        try {
            List<VwColumnConfigEntity> entities = columnConfigRepository.findAll();
            for (VwColumnConfigEntity entity : entities) {
                GridColumnConfig columnConfig = mapEntityToConfig(entity);
                if (columnConfig != null) {
                    columnConfigs.add(columnConfig);
                }
            }
            logger.info("Configurações de colunas carregadas com sucesso: {} entradas", columnConfigs.size());
        } catch (Exception e) {
            logger.error("❌ Erro ao carregar configurações de colunas do banco de dados: {}", e.getMessage(), e);
        }
        return columnConfigs;
    }

    public List<GridColumnConfig> getColumnConfigs(String viewName, Grid<?> grid, Map<String, Function<String, Object>> propertyMappers, List<String> camposFixos) {
        return getColumnConfigs(viewName, null, grid, propertyMappers, camposFixos);
    }

    public List<GridColumnConfig> getColumnConfigs(String viewName, String usuario, Grid<?> grid, Map<String, Function<String, Object>> propertyMappers, List<String> camposFixos) {
        List<GridColumnConfig> columnConfigs = new ArrayList<>();
        try {
            // Carregar todas as configurações para a viewName
            List<VwColumnConfigEntity> entities = columnConfigRepository.findByClassName(viewName);

            // Carregar configurações padrão (class_name = 'default')
            List<VwColumnConfigEntity> defaultEntities = columnConfigRepository.findByClassNameDefault();

            // Mapear as configurações específicas da view
            for (VwColumnConfigEntity entity : entities) {
                GridColumnConfig columnConfig = mapEntityToConfig(entity);
                if (columnConfig != null) {
                    columnConfigs.add(columnConfig);
                }
            }

            // Garantir que todos os campos fixos estejam presentes
            for (String campo : camposFixos) {
                boolean hasConfig = columnConfigs.stream()
                        .anyMatch(config -> config.getField().equals(campo));

                if (!hasConfig) {
                    VwColumnConfigEntity defaultEntity = defaultEntities.stream()
                            .filter(entity -> entity.getFieldName().equals(campo))
                            .findFirst()
                            .orElse(null);

                    if (defaultEntity != null) {
                        GridColumnConfig columnConfig = mapEntityToConfig(defaultEntity);
                        if (columnConfig != null) {
                            columnConfigs.add(columnConfig);
                        }
                    } else {
                        // Criar uma configuração padrão mínima para o campo fixo
                        GridColumnConfig columnConfig = new GridColumnConfig();
                        columnConfig.setGridId(viewName);
                        columnConfig.setField(campo);
                        columnConfig.setHeader(campo);
                        columnConfig.setVisible(true);
                        columnConfig.setWidth("100px");
                        columnConfig.setClassName(viewName);
                        columnConfig.setType("STRING");
                        columnConfig.setStyle("");
                        columnConfig.setFilterType("EQUALS");
                        columnConfigs.add(columnConfig);
                        logger.warn("Campo fixo {} não encontrado nas configurações. Usando configuração padrão.", campo);
                    }
                }
            }

            logger.info("Configurações de colunas carregadas para view {}: {} colunas", viewName, columnConfigs.size());
        } catch (Exception e) {
            logger.error("❌ Erro ao carregar configurações de colunas para view {}: {}", viewName, e.getMessage(), e);
        }
        return columnConfigs;
    }

    private GridColumnConfig mapEntityToConfig(VwColumnConfigEntity entity) {
        String field = entity.getFieldName();
        String header = entity.getHeader();
        String visible = entity.getVisible();
        String width = entity.getWidth();
        String style = entity.getStyle();
        String type = entity.getColumnType(); // Ajustado para usar getColumnType()
        String filterType = entity.getFilterType();
        String dropdownValues = entity.getDropdownValues();
        Integer numericWidth = entity.getNumericWidth();
        String sort = entity.getSort();
        Integer ordenacaoGrid = entity.getOrdenacaoGrid();
        String filtroAplicado = entity.getFiltroAplicado();

        if (field == null || field.isEmpty()) {
            logger.warn("⚠️ Campo field_name vazio ou nulo na configuração. Ignorando essa coluna.");
            return null;
        }

        GridColumnConfig columnConfig = new GridColumnConfig();
        columnConfig.setGridId(entity.getClassName());
        columnConfig.setField(field);
        columnConfig.setHeader(header != null ? header : field);
        columnConfig.setVisible("S".equalsIgnoreCase(visible) || "1".equalsIgnoreCase(visible));
        columnConfig.setNumericWidth(numericWidth);
        columnConfig.setWidth(width != null ? width : "100px");
        columnConfig.setClassName(entity.getClassName());
        columnConfig.setType(type != null ? type : "STRING");
        columnConfig.setStyle(style != null ? style : "");
        columnConfig.setFilterType(filterType != null ? filterType : "EQUALS");
        columnConfig.setDropdownValues(dropdownValues);
        columnConfig.setAlias(entity.getAlias());
        columnConfig.setSort(sort);
        columnConfig.setOrdenacaoGrid(ordenacaoGrid != null ? ordenacaoGrid : 0);
        columnConfig.setFiltroAplicado(filtroAplicado);

        return columnConfig;
    }
}