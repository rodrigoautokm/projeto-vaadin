package com.exemplo;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.ErrorParameter;
import com.vaadin.flow.router.HasErrorParameter;
import com.vaadin.flow.router.NotFoundException;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Route("erro404")
@PageTitle("Página não encontrada")
@AnonymousAllowed
public class Erro404View extends Div implements HasErrorParameter<NotFoundException> {

    private static final Logger logger = LoggerFactory.getLogger(Erro404View.class);

    public Erro404View() {
        setSizeFull();
        getStyle().set("text-align", "center").set("margin-top", "100px");

        H2 titulo = new H2("Erro 404 - Página não encontrada");
        Paragraph mensagem = new Paragraph("A URL acessada não existe ou não está disponível.");

        Button voltarBtn = new Button("Voltar para a aplicação", event -> {
            getUI().ifPresent(ui -> ui.navigate(""));
        });

        voltarBtn.getStyle()
            .set("margin-top", "20px")
            .set("padding", "10px 20px")
            .set("font-size", "16px");

        add(titulo, mensagem, voltarBtn);
    }

    @Override
    public int setErrorParameter(BeforeEnterEvent event, ErrorParameter<NotFoundException> parameter) {
        logger.warn("Erro 404 capturado: {}", event.getLocation().getPath());
        return 404;
    }
}