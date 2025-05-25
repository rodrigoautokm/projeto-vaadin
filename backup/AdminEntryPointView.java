package com.exemplo;

import com.exemplo.admin.CadastroView;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.beans.factory.annotation.Autowired;

@Route("admin/cadastro")
public class AdminEntryPointView extends VerticalLayout implements BeforeEnterObserver {

    private final CadastroView cadastroView;

    @Autowired
    public AdminEntryPointView(CadastroView cadastroView) {
        this.cadastroView = cadastroView;
        setSizeFull();
        add(cadastroView);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        // Verificar se o usuário está autenticado
        Boolean isAuthenticated = (Boolean) VaadinSession.getCurrent().getAttribute("isAuthenticated");
        if (isAuthenticated == null || !isAuthenticated) {
            // Redirecionar para a tela de login se não estiver autenticado
            event.forwardTo(AdminLoginView.class);
        }
    }
}