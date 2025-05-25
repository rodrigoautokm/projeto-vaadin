package com.exemplo;

import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@PageTitle("Fornecedores")
@Route(value = "fornecedores", layout = MainLayout.class)
public class FornecedorView extends AbstractGridView<Fornecedor> implements BeforeEnterObserver {

    private static final Logger logger = LoggerFactory.getLogger(FornecedorView.class);
    private final FornecedorRepository fornecedorRepository;

    @Autowired
    private DataSourceHelper dataSourceHelper;

    @Autowired
    public FornecedorView(FornecedorRepository fornecedorRepository) {
        super("Fornecedores", Fornecedor.class, fornecedorRepository::findAll);
        logger.info("Inicializando FornecedorView com gridId 'fornecedor'");
        this.fornecedorRepository = fornecedorRepository;
    }

    protected Fornecedor createNewItem() {
        return new Fornecedor();
    }

    @Override
    public Class<Fornecedor> getEntityClass() {
        logger.info("Retornando classe de entidade Fornecedor");
        return Fornecedor.class;
    }

    @Override
    protected Object getRepository() {
        logger.info("Retornando repositório de Fornecedor");
        return fornecedorRepository;
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        if (dataSourceHelper != null) {
            dataSourceHelper.configurarDataSourceAtual();
            logger.info("DataSource configurado para FornecedorView");
        } else {
            logger.warn("DataSourceHelper é nulo para FornecedorView. Verifique a configuração do Spring.");
        }

        // A lógica de inicialização do gridUtil e configuração das colunas agora está no AbstractGridView
        super.beforeEnter(event);
    }
}
