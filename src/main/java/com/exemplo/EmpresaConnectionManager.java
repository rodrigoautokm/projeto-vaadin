package com.exemplo;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class EmpresaConnectionManager {

    private static final Logger logger = LoggerFactory.getLogger(EmpresaConnectionManager.class);
    private static final Map<Integer, DataSource> cache = new ConcurrentHashMap<>();

    public static void configure(Empresa empresa) {
        logger.info("🔌 .. EmpresaConnectionManager ... Configurando conexão única para empresa {} ", empresa.getCdEmpresa());

        try {
            // Log completo com detalhes da conexão
            logger.info("➡️ Conectando para empresa {} no banco '{}', IP: {}, porta: {}, server: {}",
                    empresa.getCdEmpresa(),
                    empresa.getNomeBanco(),
                    empresa.getIpBd(),
                    empresa.getPortaBd(),
                    empresa.getServerName());

            DataSource dataSource = criarDataSource(empresa);
            cache.clear(); // Limpa conexões anteriores
            cache.put(empresa.getCdEmpresa(), dataSource);
            logger.info("✅ DataSource configurado com sucesso para empresa {}: {}", empresa.getCdEmpresa(), dataSource);
        } catch (Exception e) {
            logger.error("❌ Erro ao configurar conexão para a empresa {}: {}", empresa.getCdEmpresa(), e.getMessage(), e);
            throw new RuntimeException("Falha ao configurar DataSource", e);
        }
    }

    public static DataSource getDataSourceForEmpresa(Integer cdEmpresa) {
        logger.info("Buscando DataSource para empresa: {}", cdEmpresa);
        DataSource ds = cache.get(cdEmpresa);
        if (ds == null) {
            logger.error("DataSource não configurado para a empresa: {}", cdEmpresa);
            throw new IllegalStateException("DataSource não configurado para a empresa: " + cdEmpresa);
        }
        logger.info("DataSource encontrado para empresa {}: {}", cdEmpresa, ds);
        return ds;
    }

    private static DataSource criarDataSource(Empresa empresa) {
        logger.info("Criando DataSource para empresa: {}", empresa.getCdEmpresa());
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:sqlanywhere:UserID=" + empresa.getUsuarioBd()
                + ";Password=" + empresa.getSenhaBd()
                + ";ServerName=" + empresa.getServerName()
                + ";Host=" + empresa.getIpBd() + ":" + empresa.getPortaBd());
        config.setDriverClassName("sap.jdbc4.sqlanywhere.IDriver");
        config.addDataSourceProperty("charset", "iso_1");

        config.setConnectionTestQuery("SELECT 1");
        config.setMinimumIdle(1);
        config.setMaximumPoolSize(5);
        config.setConnectionTimeout(30000);
        config.setIdleTimeout(600000);
        config.setMaxLifetime(1800000);
        config.setLeakDetectionThreshold(2000);

        HikariDataSource dataSource = new HikariDataSource(config);
        logger.info("DataSource criado: {}", dataSource);
        return dataSource;
    }
}
