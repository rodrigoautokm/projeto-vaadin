package com.exemplo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Service
public class ParametroRelatorioService {

    private static final Logger logger = LoggerFactory.getLogger(ParametroRelatorioService.class);

    @PersistenceContext
    private EntityManager entityManager;

    // ✅ Usado no banco principal (fixo)
    public List<ParametroRelatorio> getParametros(String procedureName) {
        String sql = "SELECT parametro_nome, tipo, header, valor_default, dropdown_values " +
                     "FROM vw_procedure_parametros WHERE procedure_name = ? ORDER BY ordem";

        try {
            Query query = entityManager.createNativeQuery(sql);
            query.setParameter(1, procedureName);

            List<Object[]> linhas = query.getResultList();
            List<ParametroRelatorio> parametros = new ArrayList<>();

            for (Object[] linha : linhas) {
                Map<String, Object> map = mapRow(linha);
                ParametroRelatorio p = new ParametroRelatorio();
                p.setField(asString(map.get("parametro_nome")));
                p.setTipo(asString(map.get("tipo")));
                p.setHeader(asString(map.get("header")));
                p.setValorDefault(asString(map.get("valor_default")));
                p.setDropdownValues(asString(map.get("dropdown_values")));
                parametros.add(p);
            }

            logger.info("📌 {} parâmetros encontrados para {}", parametros.size(), procedureName);
            return parametros;

        } catch (Exception e) {
            logger.error("❌ Erro ao buscar parâmetros para {}: {}", procedureName, e.getMessage(), e);
            return List.of();
        }
    }

    // ✅ Usado com DataSource dinâmico
    public List<ParametroRelatorio> getParametros(String procedureName, DataSource ds) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);
        String sql = "SELECT parametro_nome, tipo, header, valor_default, dropdown_values " +
                     "FROM vw_procedure_parametros WHERE procedure_name = ? ORDER BY ordem";

        logger.info("🔍 Buscando parâmetros de {} no DataSource dinâmico", procedureName);

        return jdbcTemplate.query(sql, new Object[]{procedureName}, (ResultSet rs, int rowNum) -> {
            ParametroRelatorio param = new ParametroRelatorio();
            param.setField(rs.getString("parametro_nome"));
            param.setTipo(rs.getString("tipo"));
            param.setHeader(rs.getString("header"));
            param.setValorDefault(rs.getString("valor_default"));
            param.setDropdownValues(rs.getString("dropdown_values"));
            return param;
        });
    }

    private Map<String, Object> mapRow(Object[] row) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("parametro_nome", row[0]);
        map.put("tipo", row[1]);
        map.put("header", row[2]);
        map.put("valor_default", row[3]);
        map.put("dropdown_values", row[4]);
        return map;
    }

    private String asString(Object obj) {
        return obj != null ? obj.toString() : null;
    }
}
