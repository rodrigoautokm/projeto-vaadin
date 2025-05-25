package com.exemplo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionExpirationListener implements HttpSessionListener {

    private static final Logger logger = LoggerFactory.getLogger(SessionExpirationListener.class);

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        logger.info("Sessão criada: {}", se.getSession().getId());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        logger.info("Sessão destruída: {}", se.getSession().getId());
        // Opcional: Adicionar lógica adicional, como logging ou notificações
    }
}