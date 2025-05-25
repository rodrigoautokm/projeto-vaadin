package com.exemplo;

import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@PageTitle("Clientes")
@Route(value = "clientes", layout = MainLayout.class)
public class ClienteView extends AbstractGridView<Cliente> implements BeforeEnterObserver {

    private static final Logger logger = LoggerFactory.getLogger(ClienteView.class);
    private final ClienteRepository clienteRepository;

    @Autowired
    private DataSourceHelper dataSourceHelper;

    @Autowired
    public ClienteView(ClienteRepository clienteRepository) {
        super("Clientes", Cliente.class, clienteRepository::findAll);
        logger.info("Inicializando ClienteView com gridId 'cliente'");
        this.clienteRepository = clienteRepository;
    }

    protected Cliente createNewItem() {
        return new Cliente();
    }

    @Override
    public Class<Cliente> getEntityClass() {
        logger.info("Retornando classe de entidade Cliente");
        return Cliente.class;
    }

    @Override
    protected Object getRepository() {
        logger.info("Retornando repositório de Cliente");
        return clienteRepository;
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        if (dataSourceHelper != null) {
            dataSourceHelper.configurarDataSourceAtual();
            logger.info("DataSource configurado para ClienteView");
        } else {
            logger.warn("DataSourceHelper é nulo para ClienteView. Verifique a configuração do Spring.");
        }

        // A lógica de inicialização do gridUtil e configuração das colunas agora está no AbstractGridView
        super.beforeEnter(event);
    }
}