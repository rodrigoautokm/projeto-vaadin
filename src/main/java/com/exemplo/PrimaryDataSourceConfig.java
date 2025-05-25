package com.exemplo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableJpaRepositories(
    basePackages = "com.exemplo",
    includeFilters = {
        @org.springframework.context.annotation.ComponentScan.Filter(
            type = org.springframework.context.annotation.FilterType.ASSIGNABLE_TYPE,
            classes = {UsuarioAcessoRepository.class, EmpresaRepository.class}
        )
    },
    entityManagerFactoryRef = "primaryEntityManagerFactory",
    transactionManagerRef = "primaryTransactionManager"
)
public class PrimaryDataSourceConfig {

    private static final Logger logger = LoggerFactory.getLogger(PrimaryDataSourceConfig.class);

    
    @Bean(name = "primaryDataSource")
    public DataSource primaryDataSource() {
        logger.info("Configurando primaryDataSource para o banco autokm...");
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:sqlanywhere:uid=dbo;pwd=dircri17;Host=10.0.14.130:2688;ServerName=autokm;DatabaseName=autokm");
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
        try (Connection conn = dataSource.getConnection()) {
            logger.info("Conexão com o banco autokm estabelecida com sucesso: {}", conn.getMetaData().getURL());
        } catch (SQLException e) {
            logger.error("Falha ao conectar ao banco autokm: {}", e.getMessage(), e);
            throw new RuntimeException("Não foi possível conectar ao banco autokm: " + e.getMessage(), e);
        }
        return dataSource;
    }

    @Bean(name = "primaryEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean primaryEntityManagerFactory(
            @Qualifier("primaryDataSource") DataSource primaryDataSource) {
        logger.info("Configurando primaryEntityManagerFactory...");
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(primaryDataSource);
        em.setPackagesToScan("com.exemplo");
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.dialect", "com.exemplo.SQLAnywhere10Dialect");
        properties.put("hibernate.hbm2ddl.auto", "none");
        properties.put("hibernate.show_sql", true);
        properties.put("hibernate.format_sql", true);
        properties.put("hibernate.connection.charSet", "ISO-8859-1");
        properties.put("hibernate.connection.characterEncoding", "ISO-8859-1");
        properties.put("hibernate.connection.useUnicode", "false");
        properties.put("hibernate.connection.catalog", "autokm");
        properties.put("hibernate.jdbc.batch_versioned_data", "false");
        properties.put("hibernate.physical_naming_strategy", "org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl");
        properties.put("hibernate.implicit_naming_strategy", "org.hibernate.boot.model.naming.ImplicitNamingStrategyComponentPathImpl");
        em.setJpaPropertyMap(properties);

        return em;
    }

    @Bean(name = "primaryTransactionManager")
    public JpaTransactionManager primaryTransactionManager(
            @Qualifier("primaryEntityManagerFactory") LocalContainerEntityManagerFactoryBean primaryEntityManagerFactory) {
        logger.info("Configurando primaryTransactionManager...");
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(primaryEntityManagerFactory.getObject());
        return transactionManager;
    }
}