package com.exemplo;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.server.DefaultErrorHandler;
import com.vaadin.flow.server.ErrorEvent;

public class CustomErrorHandler extends DefaultErrorHandler {

    @Override
    public void error(ErrorEvent event) {
        UI.getCurrent().navigate("fallback");
    }
}