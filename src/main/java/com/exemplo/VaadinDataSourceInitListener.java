package com.exemplo;

import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.VaadinServiceInitListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Listener para integração com Vaadin que garante que o DataSource correto
 * seja configurado antes de cada operação do Vaadin.
 * Esta versão simplificada usa o DataSourceHelper para centralizar a lógica de alternância.
 */
@Component
public class VaadinDataSourceInitListener implements VaadinServiceInitListener {

    private static final Logger logger = LoggerFactory.getLogger(VaadinDataSourceInitListener.class);

    @Autowired
    private DataSourceHelper dataSourceHelper;

    @Override
    public void serviceInit(ServiceInitEvent event) {
        logger.info("Inicializando serviço Vaadin");
        
        // Adiciona um listener para cada UI inicializada
        event.getSource().addUIInitListener(uiEvent -> {
            // Adiciona um listener para cada navegação
            uiEvent.getUI().addBeforeEnterListener(enterEvent -> {
                logger.debug("Navegação Vaadin para: {}", enterEvent.getLocation().getPath());
                // Configura o DataSource antes de cada navegação
                dataSourceHelper.configurarDataSourceAtual();
            });
        });
    }
}
