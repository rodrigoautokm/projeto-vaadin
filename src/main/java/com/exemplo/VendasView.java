package com.exemplo;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class VendasView extends VerticalLayout {

    private final VendasService vendasService;
    private Grid<VendasItem> grid;
    private GridFilterUtil<VendasItem> gridUtil;
    private DatePicker dataInicioPicker;
    private DatePicker dataFimPicker;
    private List<VendasItem> itens;

    @Autowired
    public VendasView(VendasService vendasService) {
        this.vendasService = vendasService;

        setWidthFull(); // Garantir que o layout ocupe toda a largura disponível
        setHeightFull(); // Garantir que o layout ocupe toda a altura disponível

        add(new H1("Vendas"));

        // Configurar filtro por período
        LocalDateTime inicio = LocalDateTime.of(2025, 3, 1, 0, 0);
        LocalDateTime fim = LocalDateTime.of(2025, 4, 10, 23, 59, 59, 999000000);
        dataInicioPicker = new DatePicker("Data Início");
        dataInicioPicker.setValue(inicio.toLocalDate());
        dataFimPicker = new DatePicker("Data Fim");
        dataFimPicker.setValue(fim.toLocalDate());

        Button gerarButton = new Button("Gerar", event -> atualizarGrid());

        HorizontalLayout filtroLayout = new HorizontalLayout(dataInicioPicker, dataFimPicker, gerarButton);
        filtroLayout.setAlignItems(Alignment.BASELINE);
        add(filtroLayout);

        // Configurar o grid
        grid = new Grid<>(VendasItem.class, false);
        grid.setWidthFull(); // Garantir que o grid ocupe toda a largura disponível

        // Carregar os dados iniciais
        itens = vendasService.executarRelVendas(
            1, "2025-03-01 00:00:00.000", "2025-04-10 23:59:59.999", 0, 0, 0, 0, 0, 0, 0, 0, 0, null, 0, "N", "", 0, "S", "E", 0
        );
        if (itens == null || itens.isEmpty()) {
            itens = new ArrayList<>();
            Notification.show("Nenhum dado encontrado para o período selecionado.", 3000, Notification.Position.MIDDLE);
        }

        // Definir todas as colunas
        Grid.Column<VendasItem> nrPedidoColumn = grid.addColumn(VendasItem::getNrPedido)
            .setHeader("Número Pedido")
            .setKey("nrPedido");
        Grid.Column<VendasItem> nmClienteColumn = grid.addColumn(VendasItem::getNmCliente)
            .setHeader("Nome Cliente")
            .setKey("nmCliente");
        Grid.Column<VendasItem> dsProdutoColumn = grid.addColumn(VendasItem::getDsProduto)
            .setHeader("Produto")
            .setKey("dsProduto");
        Grid.Column<VendasItem> qtProdutoColumn = grid.addColumn(VendasItem::getQtProduto)
            .setHeader("Quantidade")
            .setKey("qtProduto");
        Grid.Column<VendasItem> vlUnitarioColumn = grid.addColumn(VendasItem::getVlUnitario)
            .setHeader("Valor Unitário")
            .setKey("vlUnitario");
        Grid.Column<VendasItem> vlDescontoColumn = grid.addColumn(VendasItem::getVlDesconto)
            .setHeader("Desconto")
            .setKey("vlDesconto");
        Grid.Column<VendasItem> vlCustoColumn = grid.addColumn(VendasItem::getVlCusto)
            .setHeader("Custo")
            .setKey("vlCusto");
        Grid.Column<VendasItem> dtEmissaoColumn = grid.addColumn(VendasItem::getDtEmissao)
            .setHeader("Data Emissão")
            .setKey("dtEmissao");
        Grid.Column<VendasItem> situacaoColumn = grid.addColumn(VendasItem::getSituacao)
            .setHeader("Situação")
            .setKey("situacao");
        Grid.Column<VendasItem> dsGrupoColumn = grid.addColumn(VendasItem::getDsGrupo)
            .setHeader("Grupo")
            .setKey("dsGrupo");
        Grid.Column<VendasItem> vlTotalColumn = grid.addColumn(VendasItem::getVlTotal)
            .setHeader("Valor Total")
            .setKey("vlTotal");

        // Configurar GridFilterUtil
        List<GridFilterUtil.ColumnConfig<VendasItem>> columnConfigs = new ArrayList<>();
        columnConfigs.add(new GridFilterUtil.ColumnConfig<>(
            nrPedidoColumn,
            "Número Pedido",
            VendasItem::getNrPedido
        ));
        columnConfigs.add(new GridFilterUtil.ColumnConfig<>(
            nmClienteColumn,
            "Nome Cliente",
            VendasItem::getNmCliente
        ));
        columnConfigs.add(new GridFilterUtil.ColumnConfig<>(
            dsProdutoColumn,
            "Produto",
            VendasItem::getDsProduto
        ));
        columnConfigs.add(new GridFilterUtil.ColumnConfig<>(
            qtProdutoColumn,
            "Quantidade",
            item -> String.valueOf(item.getQtProduto())
        ));
        columnConfigs.add(new GridFilterUtil.ColumnConfig<>(
            vlUnitarioColumn,
            "Valor Unitário",
            item -> String.valueOf(item.getVlUnitario())
        ));
        columnConfigs.add(new GridFilterUtil.ColumnConfig<>(
            vlDescontoColumn,
            "Desconto",
            item -> String.valueOf(item.getVlDesconto())
        ));
        columnConfigs.add(new GridFilterUtil.ColumnConfig<>(
            vlCustoColumn,
            "Custo",
            item -> String.valueOf(item.getVlCusto())
        ));
        columnConfigs.add(new GridFilterUtil.ColumnConfig<>(
            dtEmissaoColumn,
            "Data Emissão",
            VendasItem::getDtEmissao
        ));
        columnConfigs.add(new GridFilterUtil.ColumnConfig<>(
            situacaoColumn,
            "Situação",
            VendasItem::getSituacao
        ));
        columnConfigs.add(new GridFilterUtil.ColumnConfig<>(
            dsGrupoColumn,
            "Grupo",
            VendasItem::getDsGrupo
        ));
        columnConfigs.add(new GridFilterUtil.ColumnConfig<>(
            vlTotalColumn,
            "Valor Total",
            item -> String.valueOf(item.getVlTotal())
        ));

        gridUtil = new GridFilterUtil<>(grid, itens, "vendas");
        gridUtil.adicionarFiltrosNoCabecalho(columnConfigs);

        add(grid);
    }

    private void atualizarGrid() {
        LocalDate dataInicio = dataInicioPicker.getValue();
        LocalDate dataFim = dataFimPicker.getValue();

        if (dataInicio == null || dataFim == null) {
            Notification.show("Selecione as datas de início e fim.", 3000, Notification.Position.MIDDLE);
            return;
        }

        LocalDateTime inicio = dataInicio.atStartOfDay();
        LocalDateTime fim = dataFim.atTime(23, 59, 59, 999000000);

        this.itens = vendasService.executarRelVendas(
            1,
            formatarData(inicio),
            formatarData(fim),
            0, 0, 0, 0, 0, 0, 0, 0, 0, null, 0, "N", "", 0, "S", "E", 0
        );

        if (itens == null || itens.isEmpty()) {
            this.itens = new ArrayList<>();
            Notification.show("Nenhum dado encontrado para o período selecionado.", 3000, Notification.Position.MIDDLE);
        }

        gridUtil.updateItems(itens);
    }

    private String formatarData(LocalDateTime data) {
        return data.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
    }
}