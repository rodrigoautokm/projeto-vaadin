package com.exemplo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.*;

@Service
public class GridColumnConfigRelatorioService {

    private static final Logger logger = LoggerFactory.getLogger(GridColumnConfigRelatorioService.class);

    private final EmpresaConnectionManager empresaConnectionManager;

    public GridColumnConfigRelatorioService(EmpresaConnectionManager empresaConnectionManager) {
        this.empresaConnectionManager = empresaConnectionManager;
    }

    private JdbcTemplate getJdbcTemplate() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer cdEmpresa;

        if (principal instanceof CustomUserDetails) {
            cdEmpresa = ((CustomUserDetails) principal).getCdEmpresa();
        } else {
            logger.error("❌ Erro: principal não é CustomUserDetails, é: {}", principal.getClass().getName());
            throw new IllegalStateException("Usuário não autenticado corretamente. Tipo: " + principal.getClass().getName());
        }

        DataSource ds = empresaConnectionManager.getDataSourceForEmpresa(cdEmpresa);
        return new JdbcTemplate(ds);
    }

    public List<GridColumnConfig> getColumnConfigs(String procedureName) {
        logger.info("🔍 Buscando configurações de colunas para a procedure: {}", procedureName);

        String sql = "SELECT field_name, header, type, visible, width, style, filter_type, dropdown_values, alias " +
                     "FROM vw_procedure_campos_retorno WHERE procedure_name = ?";

        JdbcTemplate jdbcTemplate = getJdbcTemplate();

        List<Map<String, Object>> rows;
        try {
            rows = jdbcTemplate.queryForList(sql, procedureName);
        } catch (Exception e) {
            logger.error("❌ Erro ao consultar vw_procedure_campos_retorno para '{}': {}", procedureName, e.getMessage(), e);
            return Collections.emptyList();
        }

        List<GridColumnConfig> configs = new ArrayList<>();
        for (Map<String, Object> row : rows) {
            GridColumnConfig config = new GridColumnConfig();

            String field = getString(row, "field_name");
            if (field == null || field.isBlank()) {
                logger.warn("⚠️ Ignorando configuração com field_name nulo ou vazio.");
                continue;
            }

            config.setField(field);
            config.setHeader(getString(row, "header"));
            config.setType(getString(row, "type"));
            config.setVisible(getBoolean(row.get("visible")));
            config.setWidth(getString(row, "width"));
            config.setStyle(getString(row, "style"));
            config.setFilterType(getString(row, "filter_type"));
            config.setDropdownValues(getString(row, "dropdown_values"));
            config.setAlias(getString(row, "alias"));

            logger.debug("🧩 Campo: {}, Header: {}, Tipo: {}, Visível: {}, Estilo: {}", 
                config.getField(), config.getHeader(), config.getType(), config.isVisible(), config.getStyle());

            configs.add(config);
        }

        logger.info("✅ Total de colunas carregadas para {}: {}", procedureName, configs.size());
        return configs;
    }

    public void saveColumnConfigs(String procedureName, List<GridColumnConfig> configs) {
        logger.info("💾 Salvando configurações de colunas para a procedure: {}", procedureName);

        String sql = "INSERT INTO config_procedure_campos_retorno (procedure_name, field_name, header, tipo, style, filter_type, dropdown_values, visible) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        JdbcTemplate jdbcTemplate = getJdbcTemplate();
        int insertedRows = 0;
        try {
            for (GridColumnConfig config : configs) {
                String visibleValue = config.isVisible() ? "S" : "N";
                logger.debug("Inserindo campo: procedure_name={}, field_name={}, header={}, tipo={}, visible={}", 
                    procedureName, config.getField(), config.getHeader(), config.getType(), visibleValue);
                jdbcTemplate.update(sql,
                    procedureName,
                    config.getField(),
                    config.getHeader(),
                    config.getType(),
                    config.getStyle(),
                    config.getFilterType(),
                    config.getDropdownValues(),
                    visibleValue
                );
                insertedRows++;
            }
            logger.info("✅ Inseridos {} campos na tabela config_procedure_campos_retorno para {}", insertedRows, procedureName);
        } catch (Exception e) {
            logger.error("❌ Erro ao salvar configurações de colunas para '{}': {}", procedureName, e.getMessage(), e);
            throw new RuntimeException("Erro ao salvar configurações de colunas: " + e.getMessage(), e);
        }
    }

    private String getString(Map<String, Object> row, String key) {
        Object value = row.get(key);
        return value != null ? value.toString().trim() : null;
    }

    private boolean getBoolean(Object obj) {
        if (obj instanceof Boolean) return (Boolean) obj;
        if (obj instanceof Number) return ((Number) obj).intValue() == 1;
        if (obj instanceof String) return "S".equalsIgnoreCase((String) obj);
        return false;
    }
}
