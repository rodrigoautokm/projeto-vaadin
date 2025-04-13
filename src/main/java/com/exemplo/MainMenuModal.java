package com.exemplo;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent;

public class MainMenuModal extends Dialog {

    private final MainLayout mainLayout;

    public MainMenuModal(MainLayout mainLayout) {
        this.mainLayout = mainLayout;

        setModal(true);
        setWidth("300px");
        setHeight("400px");
        addClassName("main-menu-modal"); // Adicionar classe CSS para estilização

        VerticalLayout layout = new VerticalLayout();
        layout.setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
        layout.setPadding(true);
        layout.setSpacing(true);

        // Título do menu
        H3 title = new H3("Menu Principal");
        layout.add(title);

        // Botões de navegação
        Button clientesButton = new Button("Listagem de Clientes", event -> {
            mainLayout.selectTab("clientes");
            close();
        });
        layout.add(clientesButton);

        Button dreButton = new Button("Relatório DRE", event -> {
            mainLayout.selectTab("dre");
            close();
        });
        layout.add(dreButton);

        // Botão para fechar o modal
        Button fecharButton = new Button("Fechar", event -> close());
        layout.add(fecharButton);

        add(layout);
    }
}