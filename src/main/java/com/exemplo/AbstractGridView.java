package com.exemplo;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public abstract class AbstractGridView<T> extends VerticalLayout implements BeforeEnterObserver {

    protected final String title;
    protected final String gridId;
    protected final Supplier<List<T>> dataSupplier;
    protected Grid<T> grid;
    protected GridFilterUtil<T> gridUtil;
    protected List<T> items;
    protected HorizontalLayout activeFiltersLayout;
    protected Map<String, String> currentFilters = new HashMap<>();
    protected List<GridFilterUtil.ColumnConfig<T>> columnConfigs;

    public AbstractGridView(String title, String gridId, Supplier<List<T>> dataSupplier) {
        this.title = title;
        this.gridId = gridId;
        this.dataSupplier = dataSupplier;

        setSizeFull();
        getElement().getClassList().add("main-content");

        add(new H1(title));

        activeFiltersLayout = new HorizontalLayout();
        activeFiltersLayout.setAlignItems(Alignment.CENTER);
        activeFiltersLayout.setSpacing(true);
        add(activeFiltersLayout);

        grid = new Grid<>((Class<T>) getEntityClass(), false);
        grid.getElement().getClassList().add("grid-container");

        grid.setHeight("100%");
        grid.setWidth("100%");
        add(grid);

        setFlexGrow(1, grid);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        this.items = dataSupplier.get();
        if (this.items == null) {
            this.items = new ArrayList<>();
        }

        grid.removeAllColumns();

        columnConfigs = configureColumns();
        if (columnConfigs == null) {
            columnConfigs = new ArrayList<>();
        }

        gridUtil = new GridFilterUtil<>(grid, items, gridId);
        gridUtil.setFilterChangeListener(this::updateActiveFilters);
        gridUtil.adicionarFiltrosNoCabecalho(columnConfigs);

        gridUtil.loadColumnVisibility(columnConfigs);

        grid.setItems(items);
    }

    protected abstract Class<T> getEntityClass();

    protected abstract List<GridFilterUtil.ColumnConfig<T>> configureColumns();

    protected void updateActiveFilters(Map<String, String> activeFilters) {
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

    protected void updateData(List<T> newItems) {
        this.items = newItems != null ? newItems : new ArrayList<>();
        grid.setItems(items);
        gridUtil.adicionarFiltrosNoCabecalho(columnConfigs);
    }
}