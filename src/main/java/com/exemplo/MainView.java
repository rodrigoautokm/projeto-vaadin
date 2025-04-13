package com.exemplo;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class MainView extends VerticalLayout {
    public MainView() {
        setAlignItems(Alignment.CENTER);
        add(new H1("Bem-vindo à Aplicação"));
    }
}