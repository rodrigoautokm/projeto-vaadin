package com.exemplo;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.server.VaadinService;
import com.vaadin.flow.server.RouteRegistry;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

@CssImport("./styles/styles.css")
public class MainLayout extends AppLayout implements RouterLayout, BeforeEnterObserver {

    private static final Logger logger = LoggerFactory.getLogger(MainLayout.class);

    private Tabs menuTabs;
    private Tab clientesTab;
    private Tab dreTab;
    private Tab relVendasTab;
    private Tab pedidosTab;
    private Tab ordensServicoTab;
    private Tab marcasTab;
    private Tab fornecedoresTab;
    private Tab produtosTab;
    private Tab currentTab;

    private AbstractGridView<?> currentView;

    public MainLayout() {
        logger.info("Inicializando MainLayout");

        // Depuração de rotas registradas
        RouteRegistry registry = VaadinService.getCurrent().getRouter().getRegistry();
        logger.info("Rotas registradas pelo Vaadin:");
        registry.getRegisteredRoutes().forEach(route -> logger.info("Rota: {}", route.getNavigationTarget().getName()));

        // Registro do handler de erro
        getUI().ifPresent(ui -> {
            if (VaadinSession.getCurrent() != null) {
                VaadinSession.getCurrent().setErrorHandler(new CustomErrorHandler());
            }
        });

        // Barra de navegação superior
        H1 title = new H1("Minha Aplicação");
        title.getStyle().set("font-size", "20px");
        title.getStyle().set("margin", "0 20px");

        DrawerToggle toggle = new DrawerToggle();

        Button logoutButton = new Button("Sair", event -> {
            logger.info("Usuário solicitou logout");
            Dialog confirmDialog = new Dialog();
            confirmDialog.setHeaderTitle("Confirmação de Logout");
            confirmDialog.add(new Span("Deseja realmente sair da aplicação?"));

            Button confirmButton = new Button("Sair", e -> {
                getUI().ifPresent(ui -> {
                    // Fecha a sessão do Vaadin e redireciona para a página de login
                    ui.getSession().close();
                    ui.getPage().executeJs("window.location.href='/login?logout'");
                });
                confirmDialog.close();
            });
            confirmButton.addThemeVariants(com.vaadin.flow.component.button.ButtonVariant.LUMO_ERROR);

            Button cancelButton = new Button("Cancelar", e -> confirmDialog.close());
            cancelButton.addThemeVariants(com.vaadin.flow.component.button.ButtonVariant.LUMO_TERTIARY);

            confirmDialog.getFooter().add(cancelButton, confirmButton);
            confirmDialog.open();
        });
        logoutButton.getStyle().set("margin-left", "auto");

        HorizontalLayout navbarLayout = new HorizontalLayout(toggle, title, logoutButton);
        navbarLayout.setWidthFull();
        navbarLayout.setAlignItems(Alignment.CENTER);
        addToNavbar(true, navbarLayout);

        // Menu lateral (drawer)
        VerticalLayout menu = new VerticalLayout();
        menu.setWidth("300px");
        menu.setHeightFull();
        menu.setMargin(false);
        menu.setPadding(false);
        menu.setSpacing(false);

        clientesTab = new Tab("Clientes");
        dreTab = new Tab("Relatório DRE");
        relVendasTab = new Tab("Relatório de Vendas");
        pedidosTab = new Tab("Listagem de Pedidos");
        ordensServicoTab = new Tab("Relatório Ordem de Serviço");
        marcasTab = new Tab("Marcas");
        fornecedoresTab = new Tab("Fornecedores");
        produtosTab = new Tab("Produtos");

        menuTabs = new Tabs(clientesTab, dreTab, relVendasTab, pedidosTab, ordensServicoTab, marcasTab, fornecedoresTab, produtosTab);
        menuTabs.setOrientation(Tabs.Orientation.VERTICAL);

        menuTabs.addSelectedChangeListener(event -> {
            Tab selectedTab = event.getSelectedTab();
            logger.info("Aba selecionada: {}", selectedTab.getLabel());

            currentTab = selectedTab;

            getUI().ifPresent(ui -> {
                if (selectedTab == clientesTab) {
                    ui.navigate("clientes");
                } else if (selectedTab == dreTab) {
                    ui.navigate("rel_dre");
                } else if (selectedTab == relVendasTab) {
                    ui.navigate("rel_vendas");
                } else if (selectedTab == pedidosTab) {
                    ui.navigate("rel_listagem");
                } else if (selectedTab == ordensServicoTab) {
                    ui.navigate("rel_ordens_servico");
                } else if (selectedTab == marcasTab) {
                    ui.navigate("marcas");
                } else if (selectedTab == fornecedoresTab) {
                    ui.navigate("fornecedores");
                } else if (selectedTab == produtosTab) {
                    ui.navigate("produtos");
                }
            });
        });

        menu.add(menuTabs);
        addToDrawer(menu);

        setDrawerOpened(true);
        setPrimarySection(Section.DRAWER);

        logger.info("MainLayout inicializado com sucesso. Drawer width: 300px");
    }

    @Override
    public void showRouterLayoutContent(com.vaadin.flow.component.HasElement content) {
        super.showRouterLayoutContent(content);
        if (content instanceof AbstractGridView) {
            currentView = (AbstractGridView<?>) content;
            logger.debug("Nova view ativa: {}", currentView.getClass().getSimpleName());
        } else {
            currentView = null;
            logger.debug("Nenhuma view ativa do tipo AbstractGridView");
        }
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        if (!isUserLoggedIn()) {
            logger.info("Usuário não autenticado ou sessão inválida. Redirecionando para a página de login.");
            event.getUI().getPage().executeJs("window.location.href='/login?expired=true'");
            return;
        }

        String path = event.getLocation().getPath();
        Tab selectedTab = null;
        switch (path) {
            case "":
                return;
            case "clientes":
            case "cliente":
                selectedTab = clientesTab;
                if (path.equals("cliente")) {
                    event.forwardTo("clientes");
                }
                break;
            case "rel_dre":
                selectedTab = dreTab;
                break;
            case "rel_vendas":
                selectedTab = relVendasTab;
                break;
            case "rel_listagem":
                selectedTab = pedidosTab;
                break;
            case "rel_ordens_servico":
                selectedTab = ordensServicoTab;
                break;
            case "marcas":
                selectedTab = marcasTab;
                break;
            case "fornecedores":
                selectedTab = fornecedoresTab;
                break;
            case "produtos":
                selectedTab = produtosTab;
                break;
            default:
                logger.warn("Rota não reconhecida: {}", path);
                break;
        }

        if (selectedTab != null) {
            menuTabs.setSelectedTab(selectedTab);
            currentTab = selectedTab;
        }
    }

    public void selectTab(String tabName) {
        logger.info("Selecionando aba via chamada direta: {}", tabName);
        getUI().ifPresent(ui -> {
            switch (tabName) {
                case "clientes":
                    ui.navigate("clientes");
                    menuTabs.setSelectedTab(clientesTab);
                    break;
                case "dre":
                    ui.navigate("rel_dre");
                    menuTabs.setSelectedTab(dreTab);
                    break;
                case "vendas":
                    ui.navigate("rel_vendas");
                    menuTabs.setSelectedTab(relVendasTab);
                    break;
                case "pedidos":
                    ui.navigate("rel_listagem");
                    menuTabs.setSelectedTab(pedidosTab);
                    break;
                case "ordens_servico":
                    ui.navigate("rel_ordens_servico");
                    menuTabs.setSelectedTab(ordensServicoTab);
                    break;
                case "marcas":
                    ui.navigate("marcas");
                    menuTabs.setSelectedTab(marcasTab);
                    break;
                case "fornecedores":
                    ui.navigate("fornecedores");
                    menuTabs.setSelectedTab(fornecedoresTab);
                    break;
                case "produtos":
                    ui.navigate("produtos");
                    menuTabs.setSelectedTab(produtosTab);
                    break;
                default:
                    logger.warn("Aba não encontrada: {}", tabName);
            }
        });
    }

    private boolean isUserLoggedIn() {
        return SecurityContextHolder.getContext().getAuthentication() != null &&
               SecurityContextHolder.getContext().getAuthentication().isAuthenticated() &&
               !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken);
    }
}