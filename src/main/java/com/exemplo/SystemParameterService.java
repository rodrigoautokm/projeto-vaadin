package com.exemplo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map; // Import adicionado

@Service
public class SystemParameterService {

    private static final Logger logger = LoggerFactory.getLogger(SystemParameterService.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<ParametroRelatorio> getDefaultParameters(String procedureName) {
        logger.info("🔍 Buscando parâmetros padrão para a procedure: {}", procedureName);

        String sql = "SELECT parametro_nome, tipo, header, valor_default, dropdown_values " +
                     "FROM vw_procedure_parametros WHERE procedure_name = ? ORDER BY ordem";

        List<ParametroRelatorio> parameters = new ArrayList<>();
        try {
            List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, procedureName);
            for (Map<String, Object> row : rows) {
                String field = (String) row.get("parametro_nome");
                String tipo = (String) row.get("tipo");
                String header = (String) row.get("header");
                String valorDefault = (String) row.get("valor_default");
                String dropdownValues = (String) row.get("dropdown_values");

                ParametroRelatorio parametro = new ParametroRelatorio(field, tipo, dropdownValues, false);
                parametro.setHeader(header);
                parametro.setValorDefault(valorDefault);
                parameters.add(parametro);

                logger.debug("🧩 Parâmetro padrão: field={}, tipo={}, header={}, valorDefault={}, dropdownValues={}",
                        field, tipo, header, valorDefault, dropdownValues);
            }
            logger.info("✅ Total de parâmetros padrão carregados para {}: {}", procedureName, parameters.size());
        } catch (Exception e) {
            logger.error("❌ Erro ao consultar parâmetros padrão para '{}': {}", procedureName, e.getMessage(), e);
            return List.of();
        }
        return parameters;
    }

    public void saveParameters(String procedureName, List<ParametroRelatorio> parameters) {
        logger.info("💾 Salvando parâmetros para a procedure: {}", procedureName);

        String sql = "INSERT INTO config_parametro_procedure (nm_procedure, nm_parametro, header, valor_default, dropdown_values) " +
                     "VALUES (?, ?, ?, ?, ?)";
        
        int insertedRows = 0;
        try {
            for (ParametroRelatorio p : parameters) {
                jdbcTemplate.update(sql,
                    procedureName,
                    p.getField(),
                    p.getHeader(),
                    p.getValorDefault(),
                    p.getDropdownValues()
                );
                insertedRows++;
            }
            logger.info("✅ Inseridos {} parâmetros na tabela config_parametro_procedure para {}", insertedRows, procedureName);
        } catch (Exception e) {
            logger.error("❌ Erro ao salvar parâmetros para '{}': {}", procedureName, e.getMessage(), e);
            throw new RuntimeException("Erro ao salvar parâmetros: " + e.getMessage(), e);
        }
    }
}