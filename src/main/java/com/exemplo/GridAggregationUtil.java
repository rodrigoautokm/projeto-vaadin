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

    private List<T> items; // Tornar mutável
    private final List<GridFilterUtil.ColumnConfig<T>> columnConfigs;
    private ComboBox<GridFilterUtil.ColumnConfig<T>> valueFieldComboBox;

    public GridAggregationUtil(List<T> items, List<GridFilterUtil.ColumnConfig<T>> columnConfigs) {
        this.items = new ArrayList<>(items); // Garantir que seja mutável
        this.columnConfigs = columnConfigs;
    }

    public Button createAggregationButton() {
        Button agruparButton = new Button(new Icon(VaadinIcon.GROUP));
        agruparButton.getElement().setAttribute("title", "Agrupar dados");
        agruparButton.addClickListener(event -> abrirDialogoAgrupamento());
        return agruparButton;
    }

    public void updateItems(List<T> newItems) {
        this.items = new ArrayList<>(newItems != null ? newItems : new ArrayList<>()); // Substituir por uma nova lista mutável
        if (valueFieldComboBox != null) {
            loadValueFieldOptions();
        }
    }

    private void loadValueFieldOptions() {
        if (valueFieldComboBox == null) return;
        List<GridFilterUtil.ColumnConfig<T>> numericColumns = columnConfigs.stream()
            .filter(config -> {
                GridColumnConfig gridColumnConfig = config.getGridColumnConfig();
                if (gridColumnConfig == null) {
                    System.out.println("GridColumnConfig não disponível para a coluna: " + config.getHeader());
                    return false;
                }
                String type = gridColumnConfig.getType();
                boolean isNumeric = "NUMBER".equalsIgnoreCase(type);
                System.out.println("Coluna: " + config.getHeader() + ", Tipo: " + type + ", É numérico? " + isNumeric);
                return isNumeric;
            })
            .collect(Collectors.toList());
        System.out.println("Opções encontradas para Campo de Valor: " + numericColumns.stream().map(GridFilterUtil.ColumnConfig::getHeader).collect(Collectors.toList()));
        valueFieldComboBox.setItems(numericColumns);
    }

    private void abrirDialogoAgrupamento() {
        Dialog dialog = new Dialog();
        dialog.setModal(true);
        dialog.setWidth("900px");
        dialog.setHeight("600px");

        VerticalLayout layout = new VerticalLayout();
        layout.setPadding(false);
        layout.setSpacing(true);

        ComboBox<GridFilterUtil.ColumnConfig<T>> groupByComboBox1 = new ComboBox<>("Agrupar por");
        groupByComboBox1.setItems(columnConfigs.stream()
            .filter(config -> {
                GridColumnConfig gridColumnConfig = config.getGridColumnConfig();
                if (gridColumnConfig == null) return false;
                return "STRING".equalsIgnoreCase(gridColumnConfig.getType());
            })
            .collect(Collectors.toList()));
        groupByComboBox1.setItemLabelGenerator(GridFilterUtil.ColumnConfig::getHeader);
        groupByComboBox1.setWidth("200px");

        valueFieldComboBox = new ComboBox<>("Campo de Valor");
        loadValueFieldOptions();
        valueFieldComboBox.setItemLabelGenerator(GridFilterUtil.ColumnConfig::getHeader);
        valueFieldComboBox.setWidth("200px");

        ComboBox<String> operationComboBox = new ComboBox<>("Operação");
        operationComboBox.setItems("Somar", "Contar");
        operationComboBox.setWidth("150px");
        operationComboBox.setValue("Contar");

        ComboBox<GridFilterUtil.ColumnConfig<T>> groupByComboBox2 = new ComboBox<>("Agrupar por (2º nível)");
        groupByComboBox2.setItems(columnConfigs);
        groupByComboBox2.setItemLabelGenerator(config -> {
            GridColumnConfig gridColumnConfig = config.getGridColumnConfig();
            if (gridColumnConfig != null && "DATE".equalsIgnoreCase(gridColumnConfig.getType())) {
                return "Mês de " + config.getHeader();
            }
            return config.getHeader();
        });
        groupByComboBox2.setWidth("200px");
        groupByComboBox2.setClearButtonVisible(true);

        Button applyButton = new Button("Aplicar", event -> executeAggregation(groupByComboBox1, groupByComboBox2, operationComboBox, valueFieldComboBox, layout));
        Button closeButton = new Button("Fechar", event -> dialog.close());

        groupByComboBox2.addValueChangeListener(event -> {
            if (event.getValue() != null) {
                executeAggregation(groupByComboBox1, groupByComboBox2, operationComboBox, valueFieldComboBox, layout);
            }
        });

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

        List<AggregationResult> results = performAggregation(groupByConfig1, groupByConfig2, operation, valueFieldConfig);

        Grid<AggregationResult> resultGrid = new Grid<>(AggregationResult.class, false);
        resultGrid.addColumn(new ComponentRenderer<>(result -> {
            Span span = new Span(result.getGroupValue());
            if (result.isFirstLevel()) {
                span.getStyle().set("font-weight", "bold");
                span.getStyle().set("color", "blue");
            } else {
                span.getStyle().set("padding-left", "20px");
            }
            return span;
        }))
            .setHeader(groupByConfig1.getHeader())
            .setWidth("250px");
        resultGrid.addColumn(new TextRenderer<>(result -> {
            DecimalFormat df = new DecimalFormat("#,##0.00");
            return df.format(result.getResultValue());
        }))
            .setHeader("Somar".equals(operation) ? "Soma" : "Contagem")
            .setTextAlign(ColumnTextAlign.END);

        if (groupByConfig2 != null) {
            resultGrid.setClassNameGenerator(result -> result.isFirstLevel() ? "highlight-row" : null);
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

        layout.getChildren()
            .filter(component -> component instanceof Grid)
            .forEach(layout::remove);
        layout.add(resultGrid);
    }

    private List<AggregationResult> performAggregation(GridFilterUtil.ColumnConfig<T> groupByConfig1, GridFilterUtil.ColumnConfig<T> groupByConfig2, String operation, GridFilterUtil.ColumnConfig<T> valueFieldConfig) {
        Map<Object, List<T>> groupedItems1 = items.stream()
            .collect(Collectors.groupingBy(groupByConfig1.getValueExtractor()));

        List<AggregationResult> results = new ArrayList<>();
        SimpleDateFormat monthFormat = new SimpleDateFormat("MM-yyyy");

        List<Map.Entry<Object, List<T>>> firstLevelGroups = new ArrayList<>();
        for (Map.Entry<Object, List<T>> entry1 : groupedItems1.entrySet()) {
            firstLevelGroups.add(entry1);
        }

        firstLevelGroups.sort((entry1, entry2) -> {
            double total1 = calculateResult(entry1.getValue(), operation, valueFieldConfig);
            double total2 = calculateResult(entry2.getValue(), operation, valueFieldConfig);
            return Double.compare(total2, total1);
        });

        for (Map.Entry<Object, List<T>> entry1 : firstLevelGroups) {
            Object groupValue1 = entry1.getKey();
            List<T> groupItems1 = entry1.getValue();

            double totalFirstLevel = calculateResult(groupItems1, operation, valueFieldConfig);
            results.add(new AggregationResult(String.valueOf(groupValue1), totalFirstLevel, true));

            if (groupByConfig2 != null) {
                Map<Object, List<T>> groupedItems2;
                GridColumnConfig gridColumnConfig = groupByConfig2.getGridColumnConfig();
                if (gridColumnConfig != null && "DATE".equalsIgnoreCase(gridColumnConfig.getType())) {
                    groupedItems2 = groupItems1.stream()
                        .collect(Collectors.groupingBy(item -> {
                            Object value = groupByConfig2.getValueExtractor().apply(item);
                            if (value == null) {
                                return "N/A";
                            }
                            return monthFormat.format((Timestamp) value);
                        }));
                } else {
                    groupedItems2 = groupItems1.stream()
                        .collect(Collectors.groupingBy(groupByConfig2.getValueExtractor()));
                }

                List<Map.Entry<Object, List<T>>> secondLevelGroups = new ArrayList<>(groupedItems2.entrySet());
                secondLevelGroups.sort((entry1Sub, entry2Sub) -> {
                    double total1Sub = calculateResult(entry1Sub.getValue(), operation, valueFieldConfig);
                    double total2Sub = calculateResult(entry2Sub.getValue(), operation, valueFieldConfig);
                    return Double.compare(total2Sub, total1Sub);
                });

                for (Map.Entry<Object, List<T>> entry2 : secondLevelGroups) {
                    Object groupValue2 = entry2.getKey();
                    List<T> groupItems2 = entry2.getValue();
                    double resultValue = calculateResult(groupItems2, operation, valueFieldConfig);
                    results.add(new AggregationResult("  " + String.valueOf(groupValue2), resultValue, false));
                }
            }
        }

        return results;
    }

    private double calculateResult(List<T> groupItems, String operation, GridFilterUtil.ColumnConfig<T> valueFieldConfig) {
        if ("Contar".equals(operation)) {
            return groupItems.size();
        } else {
            return groupItems.stream()
                .map(valueFieldConfig.getValueExtractor())
                .filter(value -> value != null)
                .mapToDouble(value -> {
                    if (value instanceof Number) {
                        return ((Number) value).doubleValue();
                    } else if (value instanceof java.math.BigDecimal) {
                        return ((java.math.BigDecimal) value).doubleValue();
                    } else if (value instanceof String) {
                        try {
                            return Double.parseDouble((String) value);
                        } catch (NumberFormatException e) {
                            return 0.0;
                        }
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