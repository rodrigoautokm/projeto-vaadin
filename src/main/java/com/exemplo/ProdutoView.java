package com.exemplo;

import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@PageTitle("Produtos")
@Route(value = "produtos", layout = MainLayout.class)
public class ProdutoView extends AbstractGridView<Produto> implements BeforeEnterObserver {

    private static final Logger logger = LoggerFactory.getLogger(ProdutoView.class);

    private final ProdutoRepository produtoRepository;

    @Autowired
    private DataSourceHelper dataSourceHelper;

    @Autowired
    public ProdutoView(ProdutoRepository produtoRepository) {
        super("Produtos", Produto.class, produtoRepository::findAll);
        this.produtoRepository = produtoRepository;
        logger.info("Inicializando ProdutoView com gridId 'produto'");
    }

    protected Produto createNewItem() {
        return new Produto();
    }

    @Override
    public Class<Produto> getEntityClass() {
        return Produto.class;
    }

    @Override
    protected Object getRepository() {
        return produtoRepository;
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        if (dataSourceHelper != null) {
            dataSourceHelper.configurarDataSourceAtual();
            logger.info("DataSource configurado para ProdutoView");
        } else {
            logger.warn("DataSourceHelper é nulo para ProdutoView. Verifique a configuração do Spring.");
        }

        // A lógica de inicialização do gridUtil e configuração das colunas agora está no AbstractGridView
        super.beforeEnter(event);
    }
}
