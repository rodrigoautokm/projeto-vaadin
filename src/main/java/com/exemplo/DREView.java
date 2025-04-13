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

public class DREView extends VerticalLayout {

    private final DREService dreService;
    private Grid<DREItem> grid;
    private GridFilterUtil<DREItem> gridUtil;
    private DatePicker dataInicioPicker;
    private DatePicker dataFimPicker;
    private List<DREItem> itens;

    @Autowired
    public DREView(DREService dreService) {
        this.dreService = dreService;

        setWidthFull(); // Garantir que o layout ocupe toda a largura disponível
        setHeightFull(); // Garantir que o layout ocupe toda a altura disponível

        add(new H1("DRE - Demonstrativo Resultado Arvore"));

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
        grid = new Grid<>(DREItem.class, false);
        grid.setWidthFull(); // Garantir que o grid ocupe toda a largura disponível

        // Carregar os dados iniciais
        itens = dreService.executarDRE(
            1, "2025-03-01 00:00:00.000", "2025-04-10 23:59:59.999", 0, "DC", "N", "S", "N", "1506875,98", "V", "N"
        );
        if (itens == null || itens.isEmpty()) {
            itens = new ArrayList<>();
            Notification.show("Nenhum dado encontrado para o período selecionado.", 3000, Notification.Position.MIDDLE);
        }

        // Definir todas as colunas
        Grid.Column<DREItem> agrupaColumn = grid.addColumn(DREItem::getAgrupa)
            .setHeader("Grupo")
            .setKey("agrupa");
        Grid.Column<DREItem> descricaoColumn = grid.addColumn(DREItem::getDescricao)
            .setHeader("Descrição")
            .setKey("descricao");
        Grid.Column<DREItem> valorColumn = grid.addColumn(DREItem::getValor)
            .setHeader("Valor")
            .setKey("valor");
        Grid.Column<DREItem> fornecedorColumn = grid.addColumn(DREItem::getFornecedor)
            .setHeader("Fornecedor")
            .setKey("fornecedor");
        Grid.Column<DREItem> situacaoColumn = grid.addColumn(DREItem::getSituacao)
            .setHeader("Situação")
            .setKey("situacao");
        Grid.Column<DREItem> dataEmissaoColumn = grid.addColumn(DREItem::getDataEmissao)
            .setHeader("Emissão")
            .setKey("dataEmissao");
        Grid.Column<DREItem> dataVencimentoColumn = grid.addColumn(DREItem::getDataVencimento)
            .setHeader("Vencimento")
            .setKey("dataVencimento");
        Grid.Column<DREItem> dataPagamentoColumn = grid.addColumn(DREItem::getDataPagamento)
            .setHeader("Pagamento")
            .setKey("dataPagamento");
        Grid.Column<DREItem> observacaoColumn = grid.addColumn(DREItem::getObservacao)
            .setHeader("Observação")
            .setKey("observacao");
        Grid.Column<DREItem> despesaColumn = grid.addColumn(DREItem::getDespesa1)
            .setHeader("Despesa")
            .setKey("despesa");

        // Configurar GridFilterUtil
        List<GridFilterUtil.ColumnConfig<DREItem>> columnConfigs = new ArrayList<>();
        columnConfigs.add(new GridFilterUtil.ColumnConfig<>(agrupaColumn, "Grupo", DREItem::getAgrupa));
        columnConfigs.add(new GridFilterUtil.ColumnConfig<>(descricaoColumn, "Descrição", DREItem::getDescricao));
        columnConfigs.add(new GridFilterUtil.ColumnConfig<>(valorColumn, "Valor", item -> String.valueOf(item.getValor())));
        columnConfigs.add(new GridFilterUtil.ColumnConfig<>(fornecedorColumn, "Fornecedor", DREItem::getFornecedor));
        columnConfigs.add(new GridFilterUtil.ColumnConfig<>(situacaoColumn, "Situação", DREItem::getSituacao));
        columnConfigs.add(new GridFilterUtil.ColumnConfig<>(dataEmissaoColumn, "Emissão", DREItem::getDataEmissao));
        columnConfigs.add(new GridFilterUtil.ColumnConfig<>(dataVencimentoColumn, "Vencimento", DREItem::getDataVencimento));
        columnConfigs.add(new GridFilterUtil.ColumnConfig<>(dataPagamentoColumn, "Pagamento", DREItem::getDataPagamento));
        columnConfigs.add(new GridFilterUtil.ColumnConfig<>(observacaoColumn, "Observação", DREItem::getObservacao));
        columnConfigs.add(new GridFilterUtil.ColumnConfig<>(despesaColumn, "Despesa", DREItem::getDespesa1));

        gridUtil = new GridFilterUtil<>(grid, itens, "dre");
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

        this.itens = dreService.executarDRE(
            1,
            formatarData(inicio),
            formatarData(fim),
            0,
            "DC",
            "N",
            "S",
            "N",
            "1506875,98",
            "V",
            "N"
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