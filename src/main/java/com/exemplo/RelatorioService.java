package com.exemplo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.sql.DataSource;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

@Service
public class RelatorioService {

    private static final Logger logger = LoggerFactory.getLogger(RelatorioService.class);

    private final ParametroRelatorioService parametroService;
    @Autowired
    private DataSourceHelper dataSourceHelper;
    @Autowired
    private DataSource dataSource;

    public RelatorioService(ParametroRelatorioService parametroService) {
        this.parametroService = parametroService;
    }

    public List<RelatorioDinamicoResult> executarProcedure(String procedureName, Map<String, Object> parametros) {
        logger.info("📊 Iniciando execução do relatório: {}", procedureName);
        dataSourceHelper.configurarDataSourceAtual();

        List<RelatorioDinamicoResult> resultados = new ArrayList<>();

        Integer cdEmpresa = ((CustomUserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal()).getCdEmpresa();
        logger.info("👤 Usuário logado: {}, cdEmpresa: {}", 
            ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername(), 
            cdEmpresa);

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<ParametroRelatorio> parametroDefs = parametroService.getParametros(procedureName, dataSource);
        logger.info("🔧 Parâmetros esperados para {}: {}", procedureName, parametroDefs.size());

        String sql = construirSqlChamada(procedureName, parametroDefs.size());
        logger.debug("🧪 SQL Gerado: {}", sql);

        try {
            Object[] args = new Object[parametroDefs.size()];
            for (int i = 0; i < parametroDefs.size(); i++) {
                ParametroRelatorio def = parametroDefs.get(i);
                Object valor = parametros.get(def.getField());
                if (valor == null) {
                    args[i] = null;
                } else if ("NUMBER".equalsIgnoreCase(def.getTipo())) {
                    args[i] = toInteger(valor);
                } else if ("STRING".equalsIgnoreCase(def.getTipo())) {
                    args[i] = valor.toString();
                } else if ("DATE".equalsIgnoreCase(def.getTipo())) {
                    args[i] = toSqlDate(valor);
                } else {
                    args[i] = valor;
                }
            }

            jdbcTemplate.query(sql, args, rs -> {
                do {
                    Map<String, Object> row = new LinkedHashMap<>();
                    for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                        row.put(rs.getMetaData().getColumnName(i), rs.getObject(i));
                    }
                    resultados.add(new RelatorioDinamicoResult(row));
                } while (rs.next());
            });

            logger.info("✅ Relatório {} retornou {} registros", procedureName, resultados.size());
            return resultados;

        } catch (Exception e) {
            logger.error("❌ Erro ao executar procedure {}: {}", procedureName, e.getMessage(), e);
            throw new RuntimeException("Erro ao executar relatório: " + e.getMessage(), e);
        }
    }

    private String construirSqlChamada(String procedureName, int qtdParametros) {
        StringJoiner joiner = new StringJoiner(",", "CALL " + procedureName + "(", ")");
        for (int i = 0; i < qtdParametros; i++) {
            joiner.add("?");
        }
        return joiner.toString();
    }

    private Integer toInteger(Object valor) {
        try {
            if (valor instanceof Number) return ((Number) valor).intValue();
            if (valor instanceof String) return Integer.parseInt((String) valor);
        } catch (NumberFormatException e) {
            logger.warn("⚠️ Não foi possível converter para Integer: {}", valor);
        }
        return 0;
    }

    private Date toSqlDate(Object valor) {
        if (valor instanceof LocalDate) {
            return Date.valueOf((LocalDate) valor);
        } else if (valor instanceof String && !((String) valor).isEmpty()) {
            try {
                return Date.valueOf(LocalDate.parse((String) valor));
            } catch (Exception e) {
                logger.warn("⚠️ Erro ao converter String para Date: {}", valor);
            }
        }
        return null;
    }
}