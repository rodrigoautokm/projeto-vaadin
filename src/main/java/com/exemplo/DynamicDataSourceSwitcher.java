
package com.exemplo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

import javax.sql.DataSource;

public class DynamicDataSourceSwitcher {

    private static final Logger logger = LoggerFactory.getLogger(DynamicDataSourceSwitcher.class);

    private final ConfigurableApplicationContext context;

    public DynamicDataSourceSwitcher(ConfigurableApplicationContext context) {
        this.context = context;
    }

    public void switchTo(Empresa empresa) {
        Integer cdEmpresa = empresa.getCdEmpresa();
        logger.info("🔄 Iniciando troca definitiva para DataSource da empresa {}", cdEmpresa);

        try {
            EmpresaConnectionManager.configure(empresa);
            DataSource novoDataSource = EmpresaConnectionManager.getDataSourceForEmpresa(cdEmpresa);

            if (novoDataSource == null) {
                throw new IllegalStateException("Não foi possível obter o DataSource para a empresa " + cdEmpresa);
            }

            substituirBean("dataSource", novoDataSource);

            LocalContainerEntityManagerFactoryBean entityManagerFactory = context.getBean(LocalContainerEntityManagerFactoryBean.class);
            entityManagerFactory.setDataSource(novoDataSource);
            entityManagerFactory.afterPropertiesSet();

            JpaTransactionManager transactionManager = context.getBean(JpaTransactionManager.class);
            transactionManager.setEntityManagerFactory(entityManagerFactory.getObject());

            logger.info("✅ Troca de DataSource concluída com sucesso para empresa {}", cdEmpresa);

        } catch (Exception e) {
            logger.error("❌ Erro ao alternar DataSource: " + e.getMessage(), e);
        }
    }

    private void substituirBean(String nomeBean, Object novoBean) {
        try {
            ConfigurableListableBeanFactory factory = (ConfigurableListableBeanFactory) context.getBeanFactory();
            if (factory.containsSingleton(nomeBean)) {
                logger.info("⚠️ Substituindo bean '{}' existente no contexto.", nomeBean);
            }
            factory.registerSingleton(nomeBean, novoBean);
            logger.info("✅ Novo bean '{}' registrado no contexto.", nomeBean);
        } catch (BeansException e) {
            logger.error("Erro ao substituir o bean '{}': {}", nomeBean, e.getMessage(), e);
        }
    }
}
