
package com.exemplo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class GridColumnConfigService {

    private static final Logger logger = LoggerFactory.getLogger(GridColumnConfigService.class);

    @Autowired
    private VwColumnConfigRepository vwColumnConfigRepository;

    @Autowired
    private ColumnConfigUsuarioRepository columnConfigUsuarioRepository;

    public List<GridColumnConfig> listar(String className) {
        logger.info("🔍 Buscando configurações de colunas para className: {}", className);

        List<VwColumnConfigEntity> entities;
        try {
            entities = vwColumnConfigRepository.findByClassName(className);
            logger.debug("Configurações retornadas para className {}: {}", className,
                entities.stream().map(entity -> String.format("id=%s, field=%s, header=%s, visible=%s",
                    entity.getId(), entity.getFieldName(), entity.getHeader(), entity.getVisible())).collect(Collectors.toList()));
        } catch (Exception e) {
            logger.error("❌ Erro ao consultar vw_column_config para className '{}': {}", className, e.getMessage(), e);
            return Collections.emptyList();
        }

        Map<String, GridColumnConfig> deduplicatedConfigs = new HashMap<>();
        for (VwColumnConfigEntity entity : entities) {
            String field = entity.getFieldName();
            if (field == null || field.isBlank()) {
                logger.warn("⚠️ Ignorando configuração com field nulo ou vazio para className={}.", className);
                continue;
            }

            if (deduplicatedConfigs.containsKey(field)) {
                logger.warn("⚠️ Configuração duplicada para className={}, field={}. Mantendo a primeira ocorrência.", className, field);
                continue;
            }

            GridColumnConfig config = new GridColumnConfig();
            config.setGridId(entity.getClassName());
            config.setField(field);
            config.setHeader(entity.getHeader() != null ? entity.getHeader() : field);
            config.setVisible("1".equals(entity.getVisible()) || "S".equalsIgnoreCase(entity.getVisible()));
            config.setNumericWidth(entity.getNumericWidth() != null ? entity.getNumericWidth() : 0);
            config.setWidth(entity.getWidth());
            config.setClassName(entity.getClassName());
            config.setType(entity.getColumnType());
            config.setStyle(entity.getStyle());
            config.setFilterType(entity.getFilterType());
            config.setDropdownValues(entity.getDropdownValues());
            config.setAlias(entity.getAlias());

            logger.debug("🧩 Configuração carregada: className={}, field={}, header={}, visible={}, width={}",
                config.getGridId(), config.getField(), config.getHeader(), config.isVisible(), config.getWidth());

            deduplicatedConfigs.put(field, config);
        }

        List<GridColumnConfig> configs = new ArrayList<>(deduplicatedConfigs.values());
        logger.info("✅ Total de configurações carregadas para className {}: {}", className, configs.size());
        return configs;
    }

    public List<GridColumnConfig> listar() {
        logger.info("🔍 Buscando todas as configurações de colunas de vw_column_config");

        List<VwColumnConfigEntity> entities;
        try {
            entities = vwColumnConfigRepository.findAll();
            logger.debug("Configurações retornadas: {}",
                entities.stream().map(entity -> String.format("id=%s, class_name=%s, field=%s, header=%s, visible=%s",
                    entity.getId(), entity.getClassName(), entity.getFieldName(), entity.getHeader(), entity.getVisible()))
                    .collect(Collectors.toList()));
        } catch (Exception e) {
            logger.error("❌ Erro ao consultar vw_column_config: {}", e.getMessage(), e);
            return Collections.emptyList();
        }

        Map<String, GridColumnConfig> deduplicatedConfigs = new HashMap<>();
        for (VwColumnConfigEntity entity : entities) {
            String field = entity.getFieldName();
            if (field == null || field.isBlank()) {
                logger.warn("⚠️ Ignorando configuração com field nulo ou vazio.");
                continue;
            }

            String key = entity.getClassName() + "|" + field;
            if (deduplicatedConfigs.containsKey(key)) {
                logger.warn("⚠️ Configuração duplicada para class_name={}, field={}. Mantendo a primeira ocorrência.",
                    entity.getClassName(), field);
                continue;
            }

            GridColumnConfig config = new GridColumnConfig();
            config.setGridId(entity.getClassName());
            config.setField(field);
            config.setHeader(entity.getHeader() != null ? entity.getHeader() : field);
            config.setVisible("1".equals(entity.getVisible()) || "S".equalsIgnoreCase(entity.getVisible()));
            config.setNumericWidth(entity.getNumericWidth() != null ? entity.getNumericWidth() : 0);
            config.setWidth(entity.getWidth());
            config.setClassName(entity.getClassName());
            config.setType(entity.getColumnType());
            config.setStyle(entity.getStyle());
            config.setFilterType(entity.getFilterType());
            config.setDropdownValues(entity.getDropdownValues());
            config.setAlias(entity.getAlias());

            logger.debug("🧩 Configuração carregada: className={}, field={}, header={}, visible={}, width={}",
                config.getGridId(), config.getField(), config.getHeader(), config.isVisible(), config.getWidth());

            deduplicatedConfigs.put(key, config);
        }

        List<GridColumnConfig> configs = new ArrayList<>(deduplicatedConfigs.values());
        logger.info("✅ Total de configurações carregadas: {}", configs.size());
        return configs;
    }

    public GridColumnConfig getColumnConfig(String className, String usuarioId, String field) {
        logger.debug("🔍 Buscando configuração de coluna para className={}, usuarioId={}, field={}",
            className, usuarioId, field);

        List<VwColumnConfigEntity> entities;
        try {
            entities = vwColumnConfigRepository.findByClassName(className);
        } catch (Exception e) {
            logger.error("❌ Erro ao consultar configuração de coluna para className={}, field={}: {}",
                className, field, e.getMessage(), e);
            return null;
        }

        VwColumnConfigEntity entity = entities.stream()
            .filter(e -> field.equals(e.getFieldName()))
            .findFirst()
            .orElse(null);

        if (entity == null) {
            logger.debug("⚠️ Nenhuma configuração encontrada para className={}, field={}", className, field);
            return null;
        }

        GridColumnConfig config = new GridColumnConfig();
        config.setGridId(entity.getClassName());
        config.setField(entity.getFieldName());
        config.setHeader(entity.getHeader() != null ? entity.getHeader() : entity.getFieldName());
        config.setVisible("1".equals(entity.getVisible()) || "S".equalsIgnoreCase(entity.getVisible()));
        config.setNumericWidth(entity.getNumericWidth() != null ? entity.getNumericWidth() : 0);
        config.setWidth(entity.getWidth());
        config.setClassName(entity.getClassName());
        config.setType(entity.getColumnType());
        config.setStyle(entity.getStyle());
        config.setFilterType(entity.getFilterType());
        config.setDropdownValues(entity.getDropdownValues());
        config.setAlias(entity.getAlias());

        logger.debug("✅ Configuração encontrada: className={}, field={}, header={}, visible={}, width={}",
            config.getGridId(), config.getField(), config.getHeader(), config.isVisible(), config.getWidth());

        return config;
    }

    public void atualizarOrdenacaoUsuario(String className, String fieldName, String usuario, String sortDirection) {
        logger.info("📝 Salvando ordenação: className={}, fieldName={}, usuario={}, sort={}", className, fieldName, usuario, sortDirection);
        List<ColumnConfigUsuario> configs = columnConfigUsuarioRepository.findByUsuarioAndClassName(usuario, className);
        for (ColumnConfigUsuario config : configs) {
            config.setSort(null);
        }

        List<ColumnConfigUsuario> existentes = columnConfigUsuarioRepository
            .findByUsuarioAndClassNameAndFieldName(usuario, className, fieldName);
        ColumnConfigUsuario atual;
        if (existentes.isEmpty()) {
            logger.info("➕ Nenhuma configuração existente encontrada. Criando nova.");
            atual = new ColumnConfigUsuario();
        } else {
            logger.info("✏️ Configuração existente encontrada. Atualizando.");
            atual = existentes.get(0);
        }

        atual.setClassName(className);
        atual.setFieldName(fieldName);
        atual.setUsuario(usuario);
        atual.setSort(sortDirection);

        columnConfigUsuarioRepository.saveAll(configs);
        columnConfigUsuarioRepository.save(atual);
    }

    public void salvarFiltroAplicado(String className, String fieldName, String usuario, String filtroAplicado) {
        logger.info("🧪 Salvando filtro aplicado: className={}, fieldName={}, usuario={}, filtro={}", 
            className, fieldName, usuario, filtroAplicado);

        List<ColumnConfigUsuario> existentes = columnConfigUsuarioRepository
            .findByUsuarioAndClassNameAndFieldName(usuario, className, fieldName);

        ColumnConfigUsuario atual;
        if (existentes.isEmpty()) {
            logger.info("➕ Nenhuma configuração existente encontrada para filtro. Criando nova.");
            atual = new ColumnConfigUsuario();
        } else {
            logger.info("✏️ Configuração existente encontrada para filtro. Atualizando.");
            atual = existentes.get(0);
        }

        atual.setClassName(className);
        atual.setFieldName(fieldName);
        atual.setUsuario(usuario);
        atual.setFiltroAplicado(filtroAplicado);

        columnConfigUsuarioRepository.save(atual);
    }

}
