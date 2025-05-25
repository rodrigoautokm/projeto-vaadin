
package com.exemplo;

import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

@Route("login")
@PageTitle("Login")
@AnonymousAllowed
public class LoginView extends VerticalLayout implements BeforeEnterObserver {

    private static final Logger logger = LoggerFactory.getLogger(LoginView.class);
    private final LoginForm loginForm = new LoginForm();

    public LoginView() {
        logger.info("Inicializando LoginView");
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        loginForm.setAction("login");
        add(loginForm);
        logger.info("LoginView inicializada com sucesso");
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        logger.debug("Verificando parâmetros de erro na URL");
        Map<String, java.util.List<String>> params = event.getLocation().getQueryParameters().getParameters();

        if (params.containsKey("error")) {
            logger.warn("Erro de autenticação detectado");
            loginForm.setError(true);
        } else if (params.containsKey("expired")) {
            logger.info("Sessão expirada detectada");
            Notification.show("Sua sessão expirou. Por favor, faça login novamente.", 3000, Notification.Position.MIDDLE);
        } else if (params.containsKey("logout")) {
            logger.info("Logout bem-sucedido detectado");
            Notification.show("Você saiu com sucesso.", 3000, Notification.Position.MIDDLE);
        }
    }
}
