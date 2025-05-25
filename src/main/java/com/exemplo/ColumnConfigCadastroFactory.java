package com.exemplo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ColumnConfigCadastroFactory {

    @Autowired
    private ColumnConfigCadastroRepository columnConfigRepository;

    public List<GridColumnConfigCadastro> getColumnConfigs(String className, List<String> camposFixos) {
        List<GridColumnConfigCadastro> columnConfigs = new ArrayList<>();

        try {
            List<ColumnConfigCadastroEntity> entidades = columnConfigRepository.findByClassName(className);
            List<ColumnConfigCadastroEntity> defaultEntities = columnConfigRepository.findByClassName("default");

            for (ColumnConfigCadastroEntity entity : entidades) {
                GridColumnConfigCadastro columnConfig = mapEntityToConfig(entity);
                if (columnConfig != null) columnConfigs.add(columnConfig);
            }

            for (String campo : camposFixos) {
                boolean hasConfig = columnConfigs.stream().anyMatch(config -> config.getField().equals(campo));
                if (!hasConfig) {
                    ColumnConfigCadastroEntity defaultEntity = defaultEntities.stream()
                        .filter(entity -> entity.getFieldName().equals(campo)).findFirst().orElse(null);
                    if (defaultEntity != null) {
                        GridColumnConfigCadastro columnConfig = mapEntityToConfig(defaultEntity);
                        if (columnConfig != null) columnConfigs.add(columnConfig);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao carregar colunas: " + e.getMessage());
        }

        return columnConfigs;
    }

    private GridColumnConfigCadastro mapEntityToConfig(ColumnConfigCadastroEntity entity) {
        GridColumnConfigCadastro config = new GridColumnConfigCadastro();
        config.setField(entity.getFieldName());
        config.setHeader(Optional.ofNullable(entity.getHeader()).orElse(entity.getFieldName()));
        config.setType(Optional.ofNullable(entity.getDataType()).orElse("STRING"));
        config.setRequired("1".equals(entity.getRequired()) || "sim".equalsIgnoreCase(entity.getRequired()));
        config.setEditable("1".equals(entity.getEditable()));
        config.setVisible("1".equals(entity.getVisible()));
        config.setDropdownEntity(entity.getDropdownEntity());
        config.setDropdownValues(entity.getDropdownValues());
        config.setWidth(entity.getWidth());
        config.setScale(entity.getScale());
        config.setNestedColumns(entity.getNestedColumns());

        Map<String, String> valueMap = new HashMap<>();
        String dv = entity.getDropdownValues();
        if (dv != null && !dv.isEmpty()) {
            for (String pair : dv.split(";")) {
                String[] parts = pair.split("=");
                valueMap.put(parts[0], parts.length == 2 ? parts[1] : parts[0]);
            }
        }
        config.setDropdownValueMap(valueMap);
        return config;
    }
}