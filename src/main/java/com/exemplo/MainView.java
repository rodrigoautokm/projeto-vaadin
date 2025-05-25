package com.exemplo;

import com.exemplo.EmpresaAwareService;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "", layout = MainLayout.class)
@PageTitle("Página Inicial")
public class MainView extends VerticalLayout {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private EmpresaAwareService empresaAwareService;

    public MainView() {
        setSpacing(true);
        setPadding(true);
        add(new H1("Bem-vindo à Minha Aplicação Vaadin!"));
    }

    private Integer getCdEmpresa() {
        try {
            return empresaAwareService.getCdEmpresa();
        } catch (Exception e) {
            Notification.show("Erro ao identificar usuário logado.");
            return null;
        }
    }
}
