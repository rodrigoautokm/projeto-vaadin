package com.exemplo;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClienteView extends VerticalLayout {

    private final ClienteService clienteService;
    private Grid<Cliente> grid;
    private GridFilterUtil<Cliente> gridUtil;
    private HorizontalLayout activeFiltersLayout;
    private Map<String, String> currentFilters = new HashMap<>();
    private List<GridFilterUtil.ColumnConfig<Cliente>> columnConfigs;

    @Autowired
    public ClienteView(ClienteService clienteService) {
        this.clienteService = clienteService;

        setSizeFull();
        getElement().getClassList().add("main-content");

        add(new H1("Clientes"));

        // Layout para filtros ativos
        activeFiltersLayout = new HorizontalLayout();
        activeFiltersLayout.setAlignItems(Alignment.CENTER);
        activeFiltersLayout.setSpacing(true);
        add(activeFiltersLayout);

        // Configurar o grid
        grid = new Grid<>(Cliente.class, false);
        grid.getElement().getClassList().add("grid-container");

        // Carregar os dados
        List<Cliente> clientes = clienteService.listar();
        if (clientes == null) {
            clientes = new ArrayList<>();
        }

        // Definir as colunas
        Grid.Column<Cliente> cdClienteColumn = grid.addColumn(Cliente::getCdCliente)
            .setHeader("Código Cliente")
            .setKey("cdCliente");
        Grid.Column<Cliente> cdEmpresaColumn = grid.addColumn(Cliente::getCdEmpresa)
            .setHeader("Código Empresa")
            .setKey("cdEmpresa");
        Grid.Column<Cliente> nmClienteColumn = grid.addColumn(Cliente::getNome)
            .setHeader("Nome Cliente")
            .setKey("nmCliente");
        Grid.Column<Cliente> tipoColumn = grid.addColumn(Cliente::getTipo)
            .setHeader("Tipo")
            .setKey("tipo");
        Grid.Column<Cliente> enderecoColumn = grid.addColumn(Cliente::getEndereco)
            .setHeader("Endereço")
            .setKey("endereco");
        Grid.Column<Cliente> bairroColumn = grid.addColumn(Cliente::getBairro)
            .setHeader("Bairro")
            .setKey("bairro");
        Grid.Column<Cliente> foneColumn = grid.addColumn(Cliente::getFone)
            .setHeader("Telefone")
            .setKey("fone");
        Grid.Column<Cliente> celularColumn = grid.addColumn(Cliente::getCelular)
            .setHeader("Celular")
            .setKey("celular");
        Grid.Column<Cliente> cpfColumn = grid.addColumn(Cliente::getCpf)
            .setHeader("CPF")
            .setKey("cpf");

        // Configurar os ColumnConfig para o GridFilterUtil
        columnConfigs = new ArrayList<>();
        columnConfigs.add(new GridFilterUtil.ColumnConfig<>(
            cdClienteColumn,
            "Código Cliente",
            item -> String.valueOf(item.getCdCliente())
        ));
        columnConfigs.add(new GridFilterUtil.ColumnConfig<>(
            cdEmpresaColumn,
            "Código Empresa",
            item -> String.valueOf(item.getCdEmpresa())
        ));
        columnConfigs.add(new GridFilterUtil.ColumnConfig<>(
            nmClienteColumn,
            "Nome Cliente",
            Cliente::getNome
        ));
        columnConfigs.add(new GridFilterUtil.ColumnConfig<>(
            tipoColumn,
            "Tipo",
            Cliente::getTipo
        ));
        columnConfigs.add(new GridFilterUtil.ColumnConfig<>(
            enderecoColumn,
            "Endereço",
            Cliente::getEndereco
        ));
        columnConfigs.add(new GridFilterUtil.ColumnConfig<>(
            bairroColumn,
            "Bairro",
            Cliente::getBairro
        ));
        columnConfigs.add(new GridFilterUtil.ColumnConfig<>(
            foneColumn,
            "Telefone",
            Cliente::getFone
        ));
        columnConfigs.add(new GridFilterUtil.ColumnConfig<>(
            celularColumn,
            "Celular",
            Cliente::getCelular
        ));
        columnConfigs.add(new GridFilterUtil.ColumnConfig<>(
            cpfColumn,
            "CPF",
            Cliente::getCpf
        ));

        // Inicializar o GridFilterUtil
        gridUtil = new GridFilterUtil<>(grid, clientes, "clientes");
        gridUtil.setFilterChangeListener(this::updateActiveFilters);
        gridUtil.adicionarFiltrosNoCabecalho(columnConfigs);

        // Configurar tamanho do grid
        grid.setHeight("100%");
        grid.setWidth("100%");
        add(grid);

        setFlexGrow(1, grid);
    }

    private void updateActiveFilters(Map<String, String> activeFilters) {
        activeFiltersLayout.removeAll();
        currentFilters.clear();
        currentFilters.putAll(activeFilters);

        if (currentFilters.isEmpty()) {
            return;
        }

        Span label = new Span("Filtros Ativos:");
        label.getStyle().set("margin-right", "10px");
        activeFiltersLayout.add(label);

        currentFilters.forEach((columnKey, filterValue) -> {
            String displayText = columnKey + ": " + filterValue;
            Button filterChip = new Button(displayText, new Icon(VaadinIcon.CLOSE_SMALL));
            filterChip.getStyle().set("background-color", "var(--lumo-primary-color-10pct)");
            filterChip.getStyle().set("color", "var(--lumo-primary-text-color)");
            filterChip.getStyle().set("border-radius", "var(--lumo-border-radius-m)");
            filterChip.getStyle().set("margin", "2px");
            filterChip.addClickListener(event -> {
                gridUtil.clearAllFilters();
                updateActiveFilters(new HashMap<>());
            });
            activeFiltersLayout.add(filterChip);
        });

        Button clearAllButton = new Button("Limpar Filtros", new Icon(VaadinIcon.TRASH));
        clearAllButton.getStyle().set("background-color", "var(--lumo-error-color-10pct)");
        clearAllButton.getStyle().set("color", "var(--lumo-error-text-color)");
        clearAllButton.addClickListener(event -> {
            gridUtil.clearAllFilters();
            updateActiveFilters(new HashMap<>());
        });
        activeFiltersLayout.add(clearAllButton);
    }
}