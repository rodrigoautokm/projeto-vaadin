package com.exemplo;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * MainLayout é o layout principal da aplicação, utilizando o componente AppLayout do Vaadin.
 * O AppLayout fornece um menu lateral (drawer) à esquerda e uma área principal de conteúdo à direita.
 *
 * Regras importantes para manutenção e evolução:
 * 1. **Uso do Menu do Vaadin (AppLayout)**:
 *    - O AppLayout é usado para criar o layout com um drawer (menu lateral) e uma área principal.
 *    - O drawer contém apenas as abas de navegação (menuTabs).
 *    - O conteúdo das views (ClienteView, DREView, VendasView, ListagemPedidoView) é exibido na área principal (contentArea).
 *
 * 2. **Navegação Manual**:
 *    - A navegação é gerenciada manualmente por este MainLayout.
 *    - Não usamos rotas do Vaadin (@Route) para navegar entre as views.
 *    - O conteúdo é atualizado no contentArea com base na aba selecionada (menuTabs.addSelectedChangeListener).
 *
 * 3. **Restrições sobre @Route**:
 *    - As views (ClienteView, DREView, VendasView, ListagemPedidoView, etc.) NÃO DEVEM ter anotações @Route.
 *    - Se uma view tiver @Route, o Vaadin tentará navegar automaticamente para ela, exibindo o conteúdo dentro do drawer, o que quebrará o layout.
 *
 * 4. **Adição de Novas Abas**:
 *    - Para adicionar uma nova aba, crie uma nova Tab (ex.: Tab novaTab = new Tab("Nova Aba");).
 *    - Adicione a nova aba ao menuTabs (ex.: menuTabs = new Tabs(clientesTab, dreTab, vendasTab, pedidosTab, novaTab);).
 *    - Atualize o listener (menuTabs.addSelectedChangeListener) para lidar com a nova aba:
 *      if (selectedTab == novaTab) {
 *          contentArea.add(new NovaView(servicoCorrespondente));
 *      }
 *
 * 5. **Adição de Novas Opções no MainMenuModal**:
 *    - Adicione um novo botão no MainMenuModal (ex.: Button novaOpcaoButton = new Button("Nova Opção", event -> { mainLayout.selectTab("nova-opcao"); close(); });).
 *    - Atualize o método selectTab para reconhecer a nova opção:
 *      case "nova-opcao":
 *          menuTabs.setSelectedTab(novaTab);
 *          break;
 *
 * 6. **Manutenção do contentArea**:
 *    - O contentArea é a área principal onde o conteúdo é exibido.
 *    - Qualquer nova view deve ser adicionada ao contentArea usando contentArea.add().
 *    - Não adicione conteúdo diretamente ao drawer, pois isso quebrará o layout.
 */
// O arquivo styles.css está localizado em frontend/styles/styles.css
// Ele contém as estilizações para o layout, drawer, e componentes da aplicação
@CssImport("./styles/styles.css")
@Route("")
public class MainLayout extends AppLayout {

    private VerticalLayout contentArea; // Área para exibir o conteúdo na área principal
    private final ClienteService clienteService;
    private final DREService dreService;
    private final VendasService vendasService;
    private final ListagemPedidoService listagemPedidoService;
    private Tabs menuTabs; // Tornar menuTabs acessível para o MainMenuModal
    private Tab clientesTab;
    private Tab dreTab;
    private Tab vendasTab;
    private Tab pedidosTab;

    @Autowired
    public MainLayout(ClienteService clienteService, DREService dreService, VendasService vendasService, ListagemPedidoService listagemPedidoService) {
        this.clienteService = clienteService;
        this.dreService = dreService;
        this.vendasService = vendasService;
        this.listagemPedidoService = listagemPedidoService;

        // Configurar a barra de navegação superior
        H1 title = new H1("Minha Aplicação");
        title.getStyle().set("font-size", "20px");
        title.getStyle().set("margin", "0 20px");

        DrawerToggle toggle = new DrawerToggle();

        // Botão para abrir o MainMenuModal
        Button menuButton = new Button(new Icon(VaadinIcon.MENU), event -> {
            MainMenuModal menuModal = new MainMenuModal(this);
            menuModal.open();
        });
        menuButton.getElement().setAttribute("title", "Abrir Menu");

        HorizontalLayout navbarLayout = new HorizontalLayout(toggle, title, menuButton);
        navbarLayout.setWidthFull();
        navbarLayout.setAlignItems(Alignment.CENTER);
        addToNavbar(true, navbarLayout);

        // Configurar o menu lateral (drawer) com apenas as abas
        VerticalLayout menu = new VerticalLayout();
        menu.setWidth("300px"); // Ajustar a largura do drawer
        menu.setHeightFull();
        menu.setMargin(false);
        menu.setPadding(false);
        menu.setSpacing(false);

        // Menu com Tabs
        clientesTab = new Tab("Clientes");
        dreTab = new Tab("Relatório DRE");
        vendasTab = new Tab("Relatório de Vendas");
        pedidosTab = new Tab("Listagem de Pedidos");

        menuTabs = new Tabs(clientesTab, dreTab, vendasTab, pedidosTab);
        menuTabs.setOrientation(Tabs.Orientation.VERTICAL);

        menu.add(menuTabs);
        addToDrawer(menu);

        // Área de conteúdo na área principal
        contentArea = new VerticalLayout();
        contentArea.setWidthFull();
        contentArea.setHeightFull();
        contentArea.setMargin(true);
        contentArea.setPadding(true);
        contentArea.setSpacing(true);
        contentArea.getStyle().set("margin-left", "300px"); // Adicionar margem à esquerda para corresponder à largura do drawer

        // Definir o conteúdo inicial na área principal
        contentArea.add(new MainView());
        setContent(contentArea); // Definir o contentArea como o conteúdo principal do AppLayout

        // Listener para atualizar o conteúdo ao selecionar uma aba
        menuTabs.addSelectedChangeListener(event -> {
            System.out.println("Atualizando conteúdo para a aba: " + event.getSelectedTab().getLabel());
            contentArea.removeAll(); // Limpar o conteúdo atual
            Tab selectedTab = event.getSelectedTab();
            if (selectedTab == clientesTab) {
                System.out.println("Exibindo ClienteView na área principal");
                contentArea.add(new ClienteView(clienteService));
            } else if (selectedTab == dreTab) {
                System.out.println("Exibindo DREView na área principal");
                DREView dreView = new DREView(dreService);
                dreView.setWidthFull(); // Garantir que a view ocupe toda a largura disponível
                contentArea.add(dreView);
            } else if (selectedTab == vendasTab) {
                System.out.println("Exibindo VendasView na área principal");
                VendasView vendasView = new VendasView(vendasService);
                vendasView.setWidthFull(); // Garantir que a view ocupe toda a largura disponível
                contentArea.add(vendasView);
            } else if (selectedTab == pedidosTab) {
                System.out.println("Exibindo ListagemPedidoView na área principal");
                contentArea.add(new ListagemPedidoView(listagemPedidoService));
            }
        });

        // Tornar o drawer fixo (não retrátil)
        setDrawerOpened(true);
        setPrimarySection(Section.DRAWER); // Garante que o drawer seja a seção principal

        System.out.println("MainLayout inicializado com AppLayout. Drawer width: 300px");
    }

    // Método para permitir que o MainMenuModal selecione uma aba
    public void selectTab(String tabName) {
        System.out.println("Selecionando aba via MainMenuModal: " + tabName);
        switch (tabName) {
            case "clientes":
                menuTabs.setSelectedTab(clientesTab);
                break;
            case "dre":
                menuTabs.setSelectedTab(dreTab);
                break;
            case "vendas":
                menuTabs.setSelectedTab(vendasTab);
                break;
            case "pedidos":
                menuTabs.setSelectedTab(pedidosTab);
                break;
            default:
                System.out.println("Tab not found: " + tabName);
        }
    }
}