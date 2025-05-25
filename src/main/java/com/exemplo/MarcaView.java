package com.exemplo;

import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Route(value = "marcas", layout = MainLayout.class)
@org.springframework.stereotype.Component
public class MarcaView extends AbstractGridView<Marca> implements BeforeEnterObserver {

    private static final Logger logger = LoggerFactory.getLogger(MarcaView.class);
    private final MarcaRepository repository;

    @Autowired
    public MarcaView(MarcaRepository repository) {
        super("Marcas", Marca.class, repository::findAll);
        logger.info("Inicializando MarcaView com gridId 'marca'");
        this.repository = repository;
    }

    protected Marca createNewItem() {
        return new Marca();
    }

    @Override
    public Class<Marca> getEntityClass() {
        logger.info("Retornando classe de entidade Marca");
        return Marca.class;
    }

    @Override
    protected Object getRepository() {
        logger.info("Retornando repositório de Marca");
        return repository;
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        logger.info("Executando beforeEnter em MarcaView");
        if (!isUserAuthenticated()) {
            logger.warn("Usuário não autenticado, redirecionando para login");
            event.rerouteTo("login");
            Notification.show("Por favor, faça login para acessar esta página.", 3000, Notification.Position.TOP_CENTER);
            return;
        }

        // A lógica de inicialização do gridUtil e configuração das colunas agora está no AbstractGridView
        super.beforeEnter(event);
    }

    private boolean isUserAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isAuthenticated = authentication != null && authentication.isAuthenticated() && !"anonymousUser".equals(authentication.getPrincipal());
        logger.info("Verificação de autenticação: {}", isAuthenticated);
        return isAuthenticated;
    }
}
