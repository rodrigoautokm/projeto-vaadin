package com.exemplo;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.renderer.TextRenderer;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

public class GridAggregationUtil<T> {

    private final List<T> items;
    private final List<GridFilterUtil.ColumnConfig<T>> columnConfigs;

    public GridAggregationUtil(List<T> items, List<GridFilterUtil.ColumnConfig<T>> columnConfigs) {
        this.items = items;
        this.columnConfigs = columnConfigs;
    }

    public Button createAggregationButton() {
        Button agruparButton = new Button(new Icon(VaadinIcon.GROUP));
        agruparButton.getElement().setAttribute("title", "Agrupar dados");
        agruparButton.addClickListener(event -> abrirDialogoAgrupamento());
        return agruparButton;
    }

    public void updateItems(List<T> newItems) {
        this.items.clear();
        this.items.addAll(newItems);
    }

    private void abrirDialogoAgrupamento() {
        Dialog dialog = new Dialog();
        dialog.setModal(true);
        dialog.setWidth("900px");
        dialog.setHeight("600px");

        VerticalLayout layout = new VerticalLayout();
        layout.setPadding(false);
        layout.setSpacing(true);

        // Primeiro nível de agrupamento (apenas campos String)
        ComboBox<GridFilterUtil.ColumnConfig<T>> groupByComboBox1 = new ComboBox<>("Agrupar por");
        groupByComboBox1.setItems(columnConfigs.stream()
            .filter(config -> {
                if (items.size() == 0) return false;
                Object value = config.getValueExtractor().apply(items.get(0));
                return value instanceof String; // Apenas campos do tipo String
            })
            .collect(Collectors.toList()));
        groupByComboBox1.setItemLabelGenerator(GridFilterUtil.ColumnConfig::getHeader);
        groupByComboBox1.setWidth("200px");

        // Dropdown para selecionar o campo de valor
        ComboBox<GridFilterUtil.ColumnConfig<T>> valueFieldComboBox = new ComboBox<>("Campo de Valor");
        valueFieldComboBox.setItems(columnConfigs.stream()
            .filter(config -> {
                if (items.size() == 0) return false;
                Object value = config.getValueExtractor().apply(items.get(0));
                return value != null && (value instanceof Number || value instanceof java.math.BigDecimal) &&
                       (config.getHeader().equals("Valor Total") || config.getHeader().equals("Desconto Total"));
            })
            .collect(Collectors.toList()));
        valueFieldComboBox.setItemLabelGenerator(GridFilterUtil.ColumnConfig::getHeader);
        valueFieldComboBox.setWidth("200px");

        // Dropdown para selecionar a operação (Somar ou Contar)
        ComboBox<String> operationComboBox = new ComboBox<>("Operação");
        operationComboBox.setItems("Somar", "Contar");
        operationComboBox.setWidth("150px");
        operationComboBox.setValue("Contar"); // Valor padrão

        // Segundo nível de agrupamento (ajustar rótulo para campos de data)
        ComboBox<GridFilterUtil.ColumnConfig<T>> groupByComboBox2 = new ComboBox<>("Agrupar por (2º nível)");
        groupByComboBox2.setItems(columnConfigs);
        groupByComboBox2.setItemLabelGenerator(config -> {
            if (items.size() > 0) {
                Object value = config.getValueExtractor().apply(items.get(0));
                if (value instanceof Timestamp) {
                    return "Mês de " + config.getHeader(); // Ex.: "Mês de Emissão"
                }
            }
            return config.getHeader();
        });
        groupByComboBox2.setWidth("200px");
        groupByComboBox2.setClearButtonVisible(true); // Permite desmarcar o segundo nível de agrupamento

        // Botão para executar o agrupamento
        Button applyButton = new Button("Aplicar", event -> executeAggregation(groupByComboBox1, groupByComboBox2, operationComboBox, valueFieldComboBox, layout));

        // Botão para fechar o diálogo
        Button closeButton = new Button("Fechar", event -> dialog.close());

        // Adicionar listener para executar a consulta automaticamente ao selecionar o segundo agrupamento
        groupByComboBox2.addValueChangeListener(event -> {
            if (event.getValue() != null) { // Executar apenas se um valor for selecionado
                executeAggregation(groupByComboBox1, groupByComboBox2, operationComboBox, valueFieldComboBox, layout);
            }
        });

        // Layout dos dropdowns e botões
        HorizontalLayout controlsLayout1 = new HorizontalLayout(groupByComboBox1, valueFieldComboBox, operationComboBox);
        controlsLayout1.setAlignItems(Alignment.BASELINE);
        HorizontalLayout controlsLayout2 = new HorizontalLayout(groupByComboBox2, applyButton, closeButton);
        controlsLayout2.setAlignItems(Alignment.BASELINE);
        layout.add(controlsLayout1, controlsLayout2);

        dialog.add(layout);
        dialog.open();
    }

    private void executeAggregation(ComboBox<GridFilterUtil.ColumnConfig<T>> groupByComboBox1,
                                    ComboBox<GridFilterUtil.ColumnConfig<T>> groupByComboBox2,
                                    ComboBox<String> operationComboBox,
                                    ComboBox<GridFilterUtil.ColumnConfig<T>> valueFieldComboBox,
                                    VerticalLayout layout) {
        GridFilterUtil.ColumnConfig<T> groupByConfig1 = groupByComboBox1.getValue();
        GridFilterUtil.ColumnConfig<T> groupByConfig2 = groupByComboBox2.getValue();
        String operation = operationComboBox.getValue();
        GridFilterUtil.ColumnConfig<T> valueFieldConfig = valueFieldComboBox.getValue();

        if (groupByConfig1 == null || operation == null) {
            return;
        }
        if ("Somar".equals(operation) && valueFieldConfig == null) {
            return;
        }

        // Executar o agrupamento
        List<AggregationResult> results = performAggregation(groupByConfig1, groupByConfig2, operation, valueFieldConfig);

        // Exibir o resultado em uma nova grade
        Grid<AggregationResult> resultGrid = new Grid<>(AggregationResult.class, false);
        resultGrid.addColumn(new ComponentRenderer<>(result -> {
            Span span = new Span(result.getGroupValue());
            if (result.isFirstLevel()) {
                span.getStyle().set("font-weight", "bold"); // Negrito para o primeiro nível
                span.getStyle().set("color", "blue"); // Cor azul para destacar
            } else {
                span.getStyle().set("padding-left", "20px"); // Indentação para o segundo nível
            }
            return span;
        }))
            .setHeader(groupByConfig1.getHeader())
            .setWidth("250px");
        resultGrid.addColumn(new TextRenderer<>(result -> {
            DecimalFormat df = new DecimalFormat("#,##0.00"); // Formato com 2 casas decimais
            return df.format(result.getResultValue());
        }))
            .setHeader("Somar".equals(operation) ? "Soma" : "Contagem")
            .setTextAlign(ColumnTextAlign.END);

        // Aplicar estilo de fundo à linha inteira quando houver mais de um agrupamento
        if (groupByConfig2 != null) {
            resultGrid.setClassNameGenerator(result -> {
                if (result.isFirstLevel()) {
                    return "highlight-row"; // Classe CSS para destacar a linha
                }
                return null;
            });
            resultGrid.getElement().getStyle().set("width", "100%");
            resultGrid.getElement().executeJs(
                "var style = document.createElement('style');" +
                "style.innerHTML = '.highlight-row { background-color: #e0e0e0 !important; }';" +
                "this.shadowRoot.appendChild(style);"
            );
        }

        resultGrid.setItems(results);
        resultGrid.setHeight("400px");
        resultGrid.setWidth("100%");

        // Remover qualquer grade anterior e adicionar a nova
        layout.getChildren()
            .filter(component -> component instanceof Grid)
            .forEach(layout::remove);
        layout.add(resultGrid);
    }

    private List<AggregationResult> performAggregation(GridFilterUtil.ColumnConfig<T> groupByConfig1, GridFilterUtil.ColumnConfig<T> groupByConfig2, String operation, GridFilterUtil.ColumnConfig<T> valueFieldConfig) {
        // Primeiro nível de agrupamento
        Map<Object, List<T>> groupedItems1 = items.stream()
            .collect(Collectors.groupingBy(groupByConfig1.getValueExtractor()));

        List<AggregationResult> results = new ArrayList<>();
        SimpleDateFormat monthFormat = new SimpleDateFormat("MM-yyyy"); // Formato MM-yyyy

        // Agrupar e calcular totais do primeiro nível
        List<Map.Entry<Object, List<T>>> firstLevelGroups = new ArrayList<>();
        for (Map.Entry<Object, List<T>> entry1 : groupedItems1.entrySet()) {
            firstLevelGroups.add(entry1);
        }

        // Ordenar grupos do primeiro nível pelo valor total (decrescente)
        firstLevelGroups.sort((entry1, entry2) -> {
            double total1 = calculateResult(entry1.getValue(), operation, valueFieldConfig);
            double total2 = calculateResult(entry2.getValue(), operation, valueFieldConfig);
            return Double.compare(total2, total1); // Ordem decrescente
        });

        for (Map.Entry<Object, List<T>> entry1 : firstLevelGroups) {
            Object groupValue1 = entry1.getKey();
            List<T> groupItems1 = entry1.getValue();

            // Total do primeiro nível
            double totalFirstLevel = calculateResult(groupItems1, operation, valueFieldConfig);
            results.add(new AggregationResult(String.valueOf(groupValue1), totalFirstLevel, true));

            if (groupByConfig2 != null) {
                // Segundo nível de agrupamento
                Map<Object, List<T>> groupedItems2;
                if (groupByConfig2.getValueExtractor().apply(items.get(0)) instanceof Timestamp) {
                    // Agrupar por mês para campos de data
                    groupedItems2 = groupItems1.stream()
                        .collect(Collectors.groupingBy(item -> {
                            Object value = groupByConfig2.getValueExtractor().apply(item);
                            if (value == null) {
                                return "N/A"; // Tratar valores nulos
                            }
                            return monthFormat.format((Timestamp) value); // Formatar como MM-yyyy
                        }));
                } else {
                    groupedItems2 = groupItems1.stream()
                        .collect(Collectors.groupingBy(groupByConfig2.getValueExtractor()));
                }

                // Ordenar subgrupos do segundo nível pelo valor (decrescente)
                List<Map.Entry<Object, List<T>>> secondLevelGroups = new ArrayList<>(groupedItems2.entrySet());
                secondLevelGroups.sort((entry1Sub, entry2Sub) -> {
                    double total1Sub = calculateResult(entry1Sub.getValue(), operation, valueFieldConfig);
                    double total2Sub = calculateResult(entry2Sub.getValue(), operation, valueFieldConfig);
                    return Double.compare(total2Sub, total1Sub); // Ordem decrescente
                });

                for (Map.Entry<Object, List<T>> entry2 : secondLevelGroups) {
                    Object groupValue2 = entry2.getKey();
                    List<T> groupItems2 = entry2.getValue();
                    double resultValue = calculateResult(groupItems2, operation, valueFieldConfig);
                    results.add(new AggregationResult("  " + String.valueOf(groupValue2), resultValue, false)); // Indentação para o segundo nível
                }
            }
        }

        return results;
    }

    private double calculateResult(List<T> groupItems, String operation, GridFilterUtil.ColumnConfig<T> valueFieldConfig) {
        if ("Contar".equals(operation)) {
            return groupItems.size();
        } else {
            // "Somar"
            return groupItems.stream()
                .map(valueFieldConfig.getValueExtractor())
                .filter(value -> value != null && (value instanceof Number || value instanceof java.math.BigDecimal))
                .mapToDouble(value -> {
                    if (value instanceof Number) {
                        return ((Number) value).doubleValue();
                    } else if (value instanceof java.math.BigDecimal) {
                        return ((java.math.BigDecimal) value).doubleValue();
                    }
                    return 0.0;
                })
                .sum();
        }
    }

    private static class AggregationResult {
        private final String groupValue;
        private final double resultValue;
        private final boolean isFirstLevel;

        public AggregationResult(String groupValue, double resultValue, boolean isFirstLevel) {
            this.groupValue = groupValue;
            this.resultValue = resultValue;
            this.isFirstLevel = isFirstLevel;
        }

        public String getGroupValue() {
            return groupValue;
        }

        public double getResultValue() {
            return resultValue;
        }

        public boolean isFirstLevel() {
            return isFirstLevel;
        }
    }
}