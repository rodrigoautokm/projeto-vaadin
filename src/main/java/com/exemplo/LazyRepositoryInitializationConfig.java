package com.exemplo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LazyRepositoryInitializationConfig implements BeanFactoryPostProcessor {

    private static final Logger logger = LoggerFactory.getLogger(LazyRepositoryInitializationConfig.class);

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) {
        logger.info("Aplicando inicialização lazy a beans que dependem de repositórios JPA...");
        for (String beanName : beanFactory.getBeanDefinitionNames()) {
            // Aplicar lazy a todos os beans que contêm "Service", "Repository", ou "View"
            if (beanName.contains("Service") || beanName.contains("Repository") || beanName.contains("View")) {
                logger.debug("Aplicando lazy ao bean: {}", beanName);
                beanFactory.getBeanDefinition(beanName).setLazyInit(true);
            }
        }
        logger.info("Inicialização lazy aplicada com sucesso.");
    }
}