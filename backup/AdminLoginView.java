package com.exemplo.admin;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Route("admin/login") // Mudança aqui
public class AdminLoginView extends VerticalLayout implements BeforeEnterObserver {
    private static final Logger logger = LoggerFactory.getLogger(AdminLoginView.class);

    private final AdminUserService adminUserService;

    public AdminLoginView(AdminUserService adminUserService) {
        this.adminUserService = adminUserService;

        TextField usernameField = new TextField("Usuário");
        PasswordField passwordField = new PasswordField("Senha");

        Button loginButton = new Button("Entrar", event -> {
            String username = usernameField.getValue();
            String password = passwordField.getValue();

            logger.info("Tentativa de login - Usuário: '{}'", username);

            if (username == null || username.trim().isEmpty()) {
                Notification.show("Por favor, informe o usuário!");
                return;
            }
            if (password == null || password.trim().isEmpty()) {
                Notification.show("Por favor, informe a senha!");
                return;
            }

            if (adminUserService.authenticate(username.trim(), password.trim())) {
                VaadinSession.getCurrent().setAttribute("isAuthenticated", true);
                getUI().ifPresent(ui -> ui.navigate(AdminEntryPointView.class));
            } else {
                Notification.show("Usuário ou senha inválidos!");
            }
        });

        FormLayout loginForm = new FormLayout(usernameField, passwordField, loginButton);
        add(loginForm);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        Boolean isAuthenticated = (Boolean) VaadinSession.getCurrent().getAttribute("isAuthenticated");
        if (isAuthenticated != null && isAuthenticated) {
            event.forwardTo(AdminEntryPointView.class);
        }
    }
}