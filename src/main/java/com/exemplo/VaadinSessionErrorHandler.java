package com.exemplo;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.VaadinServiceInitListener;
import com.vaadin.flow.server.VaadinSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class VaadinSessionErrorHandler implements VaadinServiceInitListener {

    private static final Logger logger = LoggerFactory.getLogger(VaadinSessionErrorHandler.class);

    @Override
    public void serviceInit(ServiceInitEvent event) {
        event.getSource().addSessionInitListener(sessionInitEvent -> {
            VaadinSession session = sessionInitEvent.getSession();
            session.setErrorHandler(errorEvent -> {
                Throwable throwable = errorEvent.getThrowable();
                logger.error("Erro na sessão Vaadin: {}", throwable.getMessage(), throwable);
                if (throwable instanceof IllegalStateException && throwable.getMessage().contains("Session has been closed")) {
                    logger.info("Sessão expirada ou fechada. Redirecionando para login.");
                    UI.getCurrent().access(() -> UI.getCurrent().navigate("login"));
                }
            });
        });
    }
}