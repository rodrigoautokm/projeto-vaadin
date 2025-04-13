package com.exemplo;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.combobox.MultiSelectComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.data.renderer.TextRenderer;
import com.vaadin.flow.server.VaadinService;

import javax.servlet.http.Cookie;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GridFilterUtil<T> {

    private final Grid<T> grid;
    private List<T> items;
    private final String gridId;
    private static final int COOKIE_MAX_AGE = 30 * 24 * 60 * 60; // 30 dias em segundos
    private Consumer<Map<String, String>> filterChangeListener;
    private Map<String, Set<String>> activeFilters = new HashMap<>();
    private Map<String, Icon> filterIcons = new HashMap<>();
    private List<ColumnConfig<T>> columnConfigs;
    private GridAggregationUtil<T> aggregationUtil;
    private final HorizontalLayout activeFiltersLayout;

    public GridFilterUtil(Grid<T> grid, List<T> items, String gridId) {
        this.grid = grid;
        this.items = items;
        this.gridId = gridId;
        this.grid.setItems(items);
        this.activeFiltersLayout = new HorizontalLayout();
        this.activeFiltersLayout.setAlignItems(VerticalLayout.Alignment.CENTER);
        this.activeFiltersLayout.setSpacing(true);
        this.activeFiltersLayout.setMinWidth("300px");
        this.activeFiltersLayout.getStyle()
            .set("margin-left", "10px")
            .set("white-space", "nowrap")
            .set("flex-shrink", "0");
    }

    public void setFilterChangeListener(Consumer<Map<String, String>> listener) {
        this.filterChangeListener = listener;
        notifyFilterChange();
    }

    public void updateItems(List<T> newItems) {
        this.items = newItems;
        grid.setItems(newItems);
        clearAllFilters();
        if (aggregationUtil != null) {
            aggregationUtil.updateItems(newItems);
        }
    }

    public void clearAllFilters() {
        activeFilters.clear();
        grid.setItems(items);

        for (ColumnConfig<T> config : columnConfigs) {
            String columnKey = config.column.getKey();
            if (columnKey != null) {
                setCookie(gridId + "_filter_" + columnKey, "");
            }
        }

        notifyFilterChange();
        updateFilterIcons();
    }

    @SuppressWarnings("unchecked")
    public void adicionarFiltrosNoCabecalho(List<ColumnConfig<T>> columnConfigs) {
        this.columnConfigs = columnConfigs;
        this.aggregationUtil = new GridAggregationUtil<>(items, columnConfigs);
        grid.setColumnReorderingAllowed(true);

        // Carregar configurações de visibilidade e ordem
        loadColumnVisibility(columnConfigs);
        loadColumnOrder(columnConfigs);

        grid.addColumnReorderListener(event -> {
            saveColumnOrder(columnConfigs);
        });

        // Botão de configuração (engrenagem)
        Button configButton = new Button(new Icon(VaadinIcon.COG));
        configButton.getElement().setAttribute("title", "Configurar colunas");
        configButton.addClickListener(event -> abrirDialogoConfiguracaoColunas(columnConfigs));

        // Botão de agrupamento (delegado ao GridAggregationUtil)
        Button agruparButton = aggregationUtil.createAggregationButton();

        // Layout do cabeçalho com a engrenagem e o botão de agrupamento
        HorizontalLayout configLayout = new HorizontalLayout(configButton, agruparButton);
        configLayout.setDefaultVerticalComponentAlignment(VerticalLayout.Alignment.CENTER);
        configLayout.getStyle().set("flex-shrink", "0");

        // Combinar o configLayout e o activeFiltersLayout
        HorizontalLayout combinedLayout = new HorizontalLayout(configLayout, activeFiltersLayout);
        combinedLayout.setDefaultVerticalComponentAlignment(VerticalLayout.Alignment.CENTER);
        combinedLayout.setWidthFull();
        combinedLayout.getStyle()
            .set("flex-wrap", "nowrap")
            .set("overflow", "visible");
        // Adicionar a classe CSS para aplicar os estilos
        combinedLayout.addClassName("grid-filter-header-cell");

        // Adicionar a engrenagem, botão de agrupamento e filtros ativos a uma célula fixa
        HeaderRow configRow = grid.prependHeaderRow();
        configRow.getCells().forEach(cell -> cell.setText(""));
        configRow.getCells().get(0).setComponent(combinedLayout);

        for (ColumnConfig<T> config : columnConfigs) {
            Grid.Column<T> column = config.column;
            String columnKey = column.getKey();
            if (columnKey == null) {
                columnKey = "column-" + UUID.randomUUID().toString();
                column.setKey(columnKey);
            }

            final String finalColumnKey = columnKey;
            final Grid.Column<T> finalColumn = column;
            final MultiSelectComboBox<String> multiSelectComboBox = new MultiSelectComboBox<>();

            Icon filterIcon = new Icon(VaadinIcon.FILTER);
            filterIcon.getStyle().set("cursor", "pointer");
            filterIcon.setSize("16px");
            filterIcons.put(finalColumnKey, filterIcon);

            Icon hideIcon = new Icon(VaadinIcon.CLOSE);
            hideIcon.addClassName("hide-column-icon");
            hideIcon.getStyle().set("cursor", "pointer");
            hideIcon.setSize("16px");
            hideIcon.getStyle().set("margin-left", "5px");
            hideIcon.addClickListener(event -> {
                finalColumn.setVisible(false);
                saveColumnVisibility(columnConfigs);
                grid.getDataProvider().refreshAll();
            });

            // Atualizar o cabeçalho
            HorizontalLayout headerLayout = new HorizontalLayout();
            headerLayout.setDefaultVerticalComponentAlignment(VerticalLayout.Alignment.CENTER);
            headerLayout.add(new Span(config.header), filterIcon, hideIcon);
            column.setHeader(headerLayout);

            multiSelectComboBox.setClearButtonVisible(true);
            multiSelectComboBox.setLabel("Filtrar " + config.header);

            Map<String, Long> contagemPorValor = items.stream()
                .map(item -> {
                    Object value = config.valueExtractor.apply(item);
                    return value != null ? String.valueOf(value) : "";
                })
                .filter(v -> !v.isEmpty())
                .collect(Collectors.groupingBy(v -> v, Collectors.counting()));

            List<String> valoresUnicos = contagemPorValor.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue(Comparator.reverseOrder())
                    .thenComparing(Map.Entry.comparingByKey()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
            multiSelectComboBox.setItems(valoresUnicos);

            multiSelectComboBox.setRenderer(new TextRenderer<>(item ->
                item + " (" + contagemPorValor.getOrDefault(item, 0L) + ")"
            ));

            int maxLength = valoresUnicos.stream()
                .mapToInt(item -> (item + " (" + contagemPorValor.get(item) + ")").length())
                .max()
                .orElse(10);
            int widthPx = Math.min(Math.max(maxLength * 8, 200), 600);
            multiSelectComboBox.setWidth(widthPx + "px");

            Dialog filterDialog = new Dialog();
            filterDialog.setModal(false);
            filterDialog.setWidth((widthPx + 50) + "px");

            Button fecharButton = new Button("Fechar", event -> filterDialog.close());
            VerticalLayout dialogLayout = new VerticalLayout(fecharButton, multiSelectComboBox);
            dialogLayout.setPadding(false);
            dialogLayout.setSpacing(false);
            filterDialog.add(dialogLayout);

            filterIcon.addClickListener(event -> {
                if (!filterDialog.isOpened()) {
                    filterDialog.open();
                    multiSelectComboBox.setVisible(true);
                    multiSelectComboBox.focus();
                }
            });

            multiSelectComboBox.addValueChangeListener(event -> {
                Set<String> selectedValues = event.getValue();
                if (selectedValues == null || selectedValues.isEmpty()) {
                    activeFilters.remove(finalColumnKey);
                } else {
                    activeFilters.put(finalColumnKey, selectedValues);
                }

                List<T> filteredItems = items.stream()
                    .filter(item -> {
                        for (Map.Entry<String, Set<String>> filter : activeFilters.entrySet()) {
                            String filterKey = filter.getKey();
                            Set<String> filterValues = filter.getValue();
                            if (filterValues == null || filterValues.isEmpty()) {
                                continue;
                            }
                            ColumnConfig<T> cfg = columnConfigs.stream()
                                .filter(c -> c.column.getKey().equals(filterKey))
                                .findFirst()
                                .orElse(null);
                            if (cfg == null) continue;
                            Object value = cfg.valueExtractor.apply(item);
                            if (value == null || !filterValues.contains(String.valueOf(value))) {
                                return false;
                            }
                        }
                        return true;
                    })
                    .collect(Collectors.toList());

                grid.setItems(filteredItems);

                saveFilterValue(finalColumnKey, selectedValues != null ? String.join("-", selectedValues) : "");
                notifyFilterChange();
            });

            String savedFilterValue = getFilterValue(finalColumnKey);
            if (savedFilterValue != null && !savedFilterValue.isEmpty()) {
                Set<String> selectedValues = Set.of(savedFilterValue.split("-"));
                activeFilters.put(finalColumnKey, selectedValues);
                multiSelectComboBox.setValue(selectedValues);
            }

            column.setSortable(true);
            column.setResizable(true);
        }

        updateFilterIcons();
    }

    private void updateFilterIcons() {
        filterIcons.forEach((columnKey, icon) -> {
            boolean hasFilter = activeFilters.containsKey(columnKey) && activeFilters.get(columnKey) != null && !activeFilters.get(columnKey).isEmpty();
            if (hasFilter) {
                icon.setColor("var(--lumo-primary-color)");
            } else {
                icon.setColor("var(--lumo-contrast-50pct)");
            }
        });
    }

    private void abrirDialogoConfiguracaoColunas(List<ColumnConfig<T>> columnConfigs) {
        Dialog dialog = new Dialog();
        dialog.setModal(true);
        dialog.setWidth("300px");

        VerticalLayout layout = new VerticalLayout();
        layout.setPadding(false);
        layout.setSpacing(true);

        // Lista de checkboxes para as colunas
        List<Checkbox> columnCheckboxes = new ArrayList<>();
        for (ColumnConfig<T> config : columnConfigs) {
            Grid.Column<T> column = config.column;
            Checkbox checkbox = new Checkbox(config.header, column.isVisible());
            checkbox.addValueChangeListener(event -> {
                column.setVisible(event.getValue());
                saveColumnVisibility(columnConfigs);
                grid.getDataProvider().refreshAll();
            });
            columnCheckboxes.add(checkbox);
            layout.add(checkbox);
        }

        // Botão "Selecionar Todos"
        Button selectAllButton = new Button("Selecionar Todos", event -> {
            for (Checkbox checkbox : columnCheckboxes) {
                checkbox.setValue(true);
                Grid.Column<T> column = columnConfigs.get(columnCheckboxes.indexOf(checkbox)).column;
                column.setVisible(true);
            }
            saveColumnVisibility(columnConfigs);
            grid.getDataProvider().refreshAll();
        });
        layout.add(selectAllButton);

        // Botão "Fechar"
        Button fecharButton = new Button("Fechar", event -> dialog.close());
        layout.add(fecharButton);

        dialog.add(layout);
        dialog.open();
    }

    private void notifyFilterChange() {
        activeFiltersLayout.removeAll();

        if (activeFilters.isEmpty()) {
            if (filterChangeListener != null) {
                filterChangeListener.accept(new HashMap<>());
            }
            updateFilterIcons();
            return;
        }

        Span label = new Span("Filtros Ativos:");
        label.getStyle()
            .set("margin-right", "10px")
            .set("white-space", "nowrap")
            .set("flex-shrink", "0");
        activeFiltersLayout.add(label);

        activeFilters.forEach((columnKey, filterValues) -> {
            String displayText = columnKey + ": " + String.join("-", filterValues);
            Button filterChip = new Button(displayText, new Icon(VaadinIcon.CLOSE_SMALL));
            filterChip.getStyle()
                .set("background-color", "var(--lumo-primary-color-10pct)")
                .set("color", "var(--lumo-primary-text-color)")
                .set("border-radius", "var(--lumo-border-radius-m)")
                .set("margin", "2px")
                .set("white-space", "nowrap")
                .set("flex-shrink", "0");
            filterChip.addClickListener(event -> {
                activeFilters.remove(columnKey);
                List<T> filteredItems = items.stream()
                    .filter(item -> {
                        for (Map.Entry<String, Set<String>> filter : activeFilters.entrySet()) {
                            String filterKey = filter.getKey();
                            Set<String> filterValuesInner = filter.getValue();
                            if (filterValuesInner == null || filterValuesInner.isEmpty()) {
                                continue;
                            }
                            ColumnConfig<T> cfg = columnConfigs.stream()
                                .filter(c -> c.column.getKey().equals(filterKey))
                                .findFirst()
                                .orElse(null);
                            if (cfg == null) continue;
                            Object value = cfg.valueExtractor.apply(item);
                            if (value == null || !filterValuesInner.contains(String.valueOf(value))) {
                                return false;
                            }
                        }
                        return true;
                    })
                    .collect(Collectors.toList());
                grid.setItems(filteredItems);
                saveFilterValue(columnKey, "");
                notifyFilterChange();
            });
            activeFiltersLayout.add(filterChip);
        });

        Button clearAllButton = new Button("Limpar Filtros", new Icon(VaadinIcon.TRASH));
        clearAllButton.getStyle()
            .set("background-color", "var(--lumo-error-color-10pct)")
            .set("color", "var(--lumo-error-text-color)")
            .set("white-space", "nowrap")
            .set("flex-shrink", "0");
        clearAllButton.addClickListener(event -> clearAllFilters());
        activeFiltersLayout.add(clearAllButton);

        if (filterChangeListener != null) {
            Map<String, String> filterMap = new HashMap<>();
            for (Map.Entry<String, Set<String>> entry : activeFilters.entrySet()) {
                String columnKey = entry.getKey();
                Set<String> values = entry.getValue();
                if (values != null && !values.isEmpty()) {
                    filterMap.put(columnKey, String.join("-", values));
                }
            }
            filterChangeListener.accept(filterMap);
        }
        updateFilterIcons();
    }

    private Cookie getCookieByName(String name) {
        if (VaadinService.getCurrentRequest() == null) {
            return null;
        }
        Cookie[] cookies = VaadinService.getCurrentRequest().getCookies();
        if (cookies == null) {
            return null;
        }
        for (Cookie cookie : cookies) {
            if (name.equals(cookie.getName())) {
                return cookie;
            }
        }
        return null;
    }

    private void setCookie(String name, String value) {
        if (VaadinService.getCurrentResponse() == null) {
            return;
        }
        try {
            Cookie cookie = new Cookie(name, value);
            cookie.setMaxAge(value.isEmpty() ? 0 : COOKIE_MAX_AGE);
            cookie.setPath("/");
            cookie.setHttpOnly(true);
            cookie.setSecure(false);
            VaadinService.getCurrentResponse().addCookie(cookie);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveColumnVisibility(List<ColumnConfig<T>> columnConfigs) {
        String visibleColumns = columnConfigs.stream()
            .map(config -> config.column)
            .filter(Grid.Column::isVisible)
            .map(Grid.Column::getKey)
            .filter(key -> key != null)
            .collect(Collectors.joining("-"));
        if (visibleColumns.isEmpty()) {
            visibleColumns = "none";
        }
        setCookie(gridId + "_visibleColumns", visibleColumns);
    }

    public void loadColumnVisibility(List<ColumnConfig<T>> columnConfigs) {
        Cookie cookie = getCookieByName(gridId + "_visibleColumns");
        if (cookie != null && !cookie.getValue().isEmpty()) {
            String cookieValue = cookie.getValue();
            List<String> visibleKeys = cookieValue.equals("none") ? List.of() : Arrays.asList(cookieValue.split("-"));
            for (ColumnConfig<T> config : columnConfigs) {
                Grid.Column<T> column = config.column;
                String columnKey = column.getKey();
                if (columnKey != null) {
                    column.setVisible(visibleKeys.contains(columnKey));
                }
            }
        } else {
            for (ColumnConfig<T> config : columnConfigs) {
                config.column.setVisible(true);
            }
        }
    }

    private void saveColumnOrder(List<ColumnConfig<T>> columnConfigs) {
        List<String> columnOrder = grid.getColumns().stream()
            .map(Grid.Column::getKey)
            .filter(key -> key != null)
            .collect(Collectors.toList());
        String columnOrderString = String.join("-", columnOrder);
        setCookie(gridId + "_columnOrder", columnOrderString);
    }

    private void loadColumnOrder(List<ColumnConfig<T>> columnConfigs) {
        Cookie cookie = getCookieByName(gridId + "_columnOrder");
        if (cookie != null) {
            String cookieValue = cookie.getValue();
            List<String> orderedKeys = Arrays.asList(cookieValue.split("-"));
            List<Grid.Column<T>> columns = grid.getColumns();
            List<Grid.Column<T>> reorderedColumns = orderedKeys.stream()
                .map(key -> columns.stream().filter(col -> key.equals(col.getKey())).findFirst().orElse(null))
                .filter(col -> col != null)
                .collect(Collectors.toList());
            columns.stream()
                .filter(col -> !reorderedColumns.contains(col))
                .forEach(reorderedColumns::add);
            grid.setColumnOrder(reorderedColumns);
        }
    }

    private void saveFilterValue(String columnKey, String filterValue) {
        setCookie(gridId + "_filter_" + columnKey, filterValue);
    }

    private String getFilterValue(String columnKey) {
        Cookie cookie = getCookieByName(gridId + "_filter_" + columnKey);
        return cookie != null ? cookie.getValue() : null;
    }

    public static class ColumnConfig<T> {
        private final Grid.Column<T> column;
        private final String header;
        private final Function<T, Object> valueExtractor;

        public ColumnConfig(Grid.Column<T> column, String header, Function<T, Object> valueExtractor) {
            this.column = column;
            this.header = header;
            this.valueExtractor = valueExtractor;
        }

        public Grid.Column<T> getColumn() {
            return column;
        }

        public String getHeader() {
            return header;
        }

        public Function<T, Object> getValueExtractor() {
            return valueExtractor;
        }
    }
}