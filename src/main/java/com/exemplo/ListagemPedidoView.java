package com.exemplo;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ListagemPedidoView extends VerticalLayout {

    private final ListagemPedidoService listagemPedidoService;
    private Grid<ListagemPedido> grid;
    private List<ListagemPedido> pedidos;
    private DatePicker dataInicioPicker;
    private DatePicker dataFimPicker;
    private GridFilterUtil<ListagemPedido> gridUtil;
    private List<GridFilterUtil.ColumnConfig<ListagemPedido>> columnConfigs;

    public ListagemPedidoView(ListagemPedidoService listagemPedidoService) {
        this.listagemPedidoService = listagemPedidoService;

        LocalDateTime inicio = LocalDateTime.now().minusMonths(1);
        LocalDateTime fim = LocalDateTime.now();
        this.pedidos = listagemPedidoService.listarPorPeriodo(
            Timestamp.valueOf(inicio), Timestamp.valueOf(fim)
        );
        if (this.pedidos == null) {
            this.pedidos = new ArrayList<>();
        }

        setSizeFull();
        getElement().getClassList().add("main-content");

        // Título "Listagem de Pedidos" com fonte menor
        H1 title = new H1("Listagem de Pedidos");
        title.getStyle().set("font-size", "1.5em");

        // Filtros de data
        dataInicioPicker = new DatePicker("Data Início");
        dataInicioPicker.setValue(inicio.toLocalDate());
        dataFimPicker = new DatePicker("Data Fim");
        dataFimPicker.setValue(fim.toLocalDate());

        Button gerarButton = new Button("Gerar", event -> atualizarGrid());

        // Layout do título e filtros na mesma linha
        HorizontalLayout headerLayout = new HorizontalLayout(title, dataInicioPicker, dataFimPicker, gerarButton);
        headerLayout.setAlignItems(Alignment.BASELINE);
        headerLayout.setSpacing(true);
        headerLayout.getStyle().set("margin-bottom", "5px");

        add(headerLayout);

        // Configuração do grid
        grid = new Grid<>(ListagemPedido.class, false);
        grid.getElement().getClassList().add("grid-container");

        Grid.Column<ListagemPedido> cdEmpresaColumn = grid.addColumn(ListagemPedido::getCdEmpresa)
            .setHeader("Código Empresa")
            .setKey("cdEmpresa")
            .setTextAlign(ColumnTextAlign.END);
        Grid.Column<ListagemPedido> nrPedidoColumn = grid.addColumn(ListagemPedido::getNrPedido)
            .setHeader("Número Pedido")
            .setKey("nrPedido")
            .setTextAlign(ColumnTextAlign.END);
        Grid.Column<ListagemPedido> serieColumn = grid.addColumn(ListagemPedido::getSerie)
            .setHeader("Série")
            .setKey("serie");
        Grid.Column<ListagemPedido> cdEmpresaVendaColumn = grid.addColumn(ListagemPedido::getCdEmpresaVenda)
            .setHeader("Código Empresa Venda")
            .setKey("cdEmpresaVenda")
            .setTextAlign(ColumnTextAlign.END);
        Grid.Column<ListagemPedido> dtEmissaoColumn = grid.addColumn(ListagemPedido::getDtEmissao)
            .setHeader("Data Emissão")
            .setKey("dtEmissao");
        Grid.Column<ListagemPedido> cdClienteColumn = grid.addColumn(ListagemPedido::getCdCliente)
            .setHeader("Código Cliente")
            .setKey("cdCliente")
            .setTextAlign(ColumnTextAlign.END);
        Grid.Column<ListagemPedido> nmClienteColumn = grid.addColumn(ListagemPedido::getNmCliente)
            .setHeader("Nome Cliente")
            .setKey("nmCliente");
        Grid.Column<ListagemPedido> vlTotalColumn = grid.addColumn(ListagemPedido::getVlTotal)
            .setHeader("Valor Total")
            .setKey("vlTotal")
            .setTextAlign(ColumnTextAlign.END);
        Grid.Column<ListagemPedido> vlDescontoTotalColumn = grid.addColumn(ListagemPedido::getVlDescontoTotal)
            .setHeader("Desconto Total")
            .setKey("vlDescontoTotal")
            .setTextAlign(ColumnTextAlign.END);
        Grid.Column<ListagemPedido> situacaoColumn = grid.addColumn(ListagemPedido::getSituacao)
            .setHeader("Situação")
            .setKey("situacao");
        Grid.Column<ListagemPedido> cdFuncionarioColumn = grid.addColumn(ListagemPedido::getCdFuncionario)
            .setHeader("Código Funcionário")
            .setKey("cdFuncionario")
            .setTextAlign(ColumnTextAlign.END);
        Grid.Column<ListagemPedido> nmFuncionarioColumn = grid.addColumn(ListagemPedido::getNmFuncionario)
            .setHeader("Nome Funcionário")
            .setKey("nmFuncionario");

        columnConfigs = new ArrayList<>();
        columnConfigs.add(new GridFilterUtil.ColumnConfig<>(cdEmpresaColumn, "Código Empresa", item -> String.valueOf(item.getCdEmpresa())));
        columnConfigs.add(new GridFilterUtil.ColumnConfig<>(nrPedidoColumn, "Número Pedido", item -> String.valueOf(item.getNrPedido())));
        columnConfigs.add(new GridFilterUtil.ColumnConfig<>(serieColumn, "Série", ListagemPedido::getSerie));
        columnConfigs.add(new GridFilterUtil.ColumnConfig<>(cdEmpresaVendaColumn, "Código Empresa Venda", item -> String.valueOf(item.getCdEmpresaVenda())));
        columnConfigs.add(new GridFilterUtil.ColumnConfig<>(dtEmissaoColumn, "Data Emissão", item -> String.valueOf(item.getDtEmissao())));
        columnConfigs.add(new GridFilterUtil.ColumnConfig<>(cdClienteColumn, "Código Cliente", item -> String.valueOf(item.getCdCliente())));
        columnConfigs.add(new GridFilterUtil.ColumnConfig<>(nmClienteColumn, "Nome Cliente", ListagemPedido::getNmCliente));
        columnConfigs.add(new GridFilterUtil.ColumnConfig<>(vlTotalColumn, "Valor Total", ListagemPedido::getVlTotal));
        columnConfigs.add(new GridFilterUtil.ColumnConfig<>(vlDescontoTotalColumn, "Desconto Total", ListagemPedido::getVlDescontoTotal));
        columnConfigs.add(new GridFilterUtil.ColumnConfig<>(situacaoColumn, "Situação", ListagemPedido::getSituacao));
        columnConfigs.add(new GridFilterUtil.ColumnConfig<>(cdFuncionarioColumn, "Código Funcionário", item -> String.valueOf(item.getCdFuncionario())));
        columnConfigs.add(new GridFilterUtil.ColumnConfig<>(nmFuncionarioColumn, "Nome Funcionário", ListagemPedido::getNmFuncionario));

        gridUtil = new GridFilterUtil<>(grid, pedidos, "listagem-pedidos");
        gridUtil.setFilterChangeListener(filterMap -> {});
        gridUtil.adicionarFiltrosNoCabecalho(columnConfigs);

        grid.setHeight("100%");
        grid.setWidth("100%");
        grid.getStyle().set("margin-top", "0px");
        add(grid);

        setFlexGrow(1, grid);
        setSizeFull();
        setMargin(true);
        setPadding(true);
    }

    private void atualizarGrid() {
        LocalDate dataInicio = dataInicioPicker.getValue();
        LocalDate dataFim = dataFimPicker.getValue();

        if (dataInicio != null && dataFim != null) {
            LocalDateTime inicio = dataInicio.atStartOfDay();
            LocalDateTime fim = dataFim.atTime(23, 59, 59);
            this.pedidos = listagemPedidoService.listarPorPeriodo(
                Timestamp.valueOf(inicio), Timestamp.valueOf(fim)
            );
            if (this.pedidos == null) {
                this.pedidos = new ArrayList<>();
            }
            gridUtil.updateItems(pedidos);
        }
    }
}