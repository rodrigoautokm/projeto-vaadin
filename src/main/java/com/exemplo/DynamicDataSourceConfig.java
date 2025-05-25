
package com.exemplo;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.exemplo",
        excludeFilters = @org.springframework.context.annotation.ComponentScan.Filter(
                type = org.springframework.context.annotation.FilterType.ASSIGNABLE_TYPE,
                classes = {EmpresaRepository.class, UsuarioAcessoRepository.class}
        ),
        entityManagerFactoryRef = "entityManagerFactory",
        transactionManagerRef = "transactionManager"
)
public class DynamicDataSourceConfig {

    private static final Log logger = LogFactory.getLog(DynamicDataSourceConfig.class);

    @Autowired
    private ConfigurableApplicationContext context;

    @Autowired
    private EmpresaConnectionManager empresaConnectionManager;

    private final Map<Object, Object> dataSourceCache = new ConcurrentHashMap<>();

    @Bean
    @Primary
    public DataSource dataSource() {
        logger.info("Configurando dataSource dinâmico...");

        AbstractRoutingDataSource routingDataSource = new AbstractRoutingDataSource() {
            @Override
            protected Object determineCurrentLookupKey() {
                Integer cdEmpresa = 0;
                Object principal = SecurityContextHolder.getContext().getAuthentication() != null ?
                        SecurityContextHolder.getContext().getAuthentication().getPrincipal() : null;

                if (principal instanceof CustomUserDetails customUserDetails) {
                    cdEmpresa = customUserDetails.getCdEmpresa();
                    if (cdEmpresa == null) {
                        logger.warn("cdEmpresa nulo, usando padrão autokm");
                        cdEmpresa = 0;
                    } else {
                        logger.info("Usando DataSource da empresa " + cdEmpresa);
                    }
                } else {
                    logger.warn("Principal não é CustomUserDetails, usando padrão autokm");
                }

                synchronized (dataSourceCache) {
                    if (!dataSourceCache.containsKey(cdEmpresa)) {
                        DataSource ds = empresaConnectionManager.getDataSourceForEmpresa(cdEmpresa);
                        if (ds == null) {
                            logger.error("DataSource não encontrado para cdEmpresa=" + cdEmpresa);
                            throw new IllegalStateException("DataSource não encontrado para a empresa: " + cdEmpresa);
                        }
                        dataSourceCache.put(cdEmpresa, ds);
                        setTargetDataSources(dataSourceCache);
                        afterPropertiesSet();
                    }
                }

                return cdEmpresa;
            }
        };

        // Configuração do DataSource padrão autokm
        Empresa empresaAutokm = new Empresa();
        empresaAutokm.setCd_empresa(0);
        empresaAutokm.setServer_name("autokm");
        empresaAutokm.setNomeBanco("autokm");
        empresaAutokm.setIp_bd("10.0.14.130");
        empresaAutokm.setPorta_bd("2688");
        empresaAutokm.setUsuario_bd("dbo");
        empresaAutokm.setSenha_bd("dircri17");

        EmpresaConnectionManager.configure(empresaAutokm);
        DataSource defaultDataSource = empresaConnectionManager.getDataSourceForEmpresa(0);
        if (defaultDataSource == null) {
            throw new IllegalStateException("DataSource padrão (autokm) não pôde ser configurado.");
        }

        synchronized (dataSourceCache) {
            dataSourceCache.put(0, defaultDataSource);
            routingDataSource.setDefaultTargetDataSource(defaultDataSource);
            routingDataSource.setTargetDataSources(new HashMap<>(dataSourceCache));
            routingDataSource.afterPropertiesSet();
        }

        return routingDataSource;
    }

    @Bean(name = "entityManagerFactory")
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        logger.info("Configurando entityManagerFactory...");
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
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
        properties.put("hibernate.jdbc.batch_versioned_data", "false");
        properties.put("hibernate.physical_naming_strategy", "org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl");
        properties.put("hibernate.implicit_naming_strategy", "org.hibernate.boot.model.naming.ImplicitNamingStrategyComponentPathImpl");

        em.setJpaPropertyMap(properties);
        return em;
    }

    @Bean(name = "transactionManager")
    @Primary
    public PlatformTransactionManager transactionManager() {
        logger.info("Configurando transactionManager...");
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }

    @Bean
    public DynamicDataSourceSwitcher dynamicDataSourceSwitcher() {
        return new DynamicDataSourceSwitcher(context);
    }
}
