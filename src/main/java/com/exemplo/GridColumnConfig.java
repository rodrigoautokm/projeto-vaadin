package com.exemplo;

import java.util.HashMap;
import java.util.Map;
import java.util.Collections;

public class GridColumnConfig {

    private String gridId;
    private String field;
    private String header;
    private boolean visible;
    private Integer numericWidth;
    private String width;
    private String className;
    private String type;
    private String style;
    private String filterType;
    private String dropdownValues;
    private String alias;
    private String sort;
    private Integer ordenacaoGrid;
    private String filtroAplicado;

    private Map<String, String> dropdownValueMap;

    public String getGridId() {
        return gridId;
    }

    public void setGridId(String gridId) {
        this.gridId = gridId;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public Integer getNumericWidth() {
        return numericWidth;
    }

    public void setNumericWidth(Integer numericWidth) {
        this.numericWidth = numericWidth;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getFilterType() {
        return filterType;
    }

    public void setFilterType(String filterType) {
        this.filterType = filterType;
    }

    public String getDropdownValues() {
        return dropdownValues;
    }

    public void setDropdownValues(String dropdownValues) {
        this.dropdownValues = dropdownValues;
        if (dropdownValues != null && !dropdownValues.isEmpty()) {
            this.dropdownValueMap = parseDropdownValues(dropdownValues);
        } else {
            this.dropdownValueMap = Collections.emptyMap();
        }
    }

    public Map<String, String> getDropdownValueMap() {
        return dropdownValueMap != null ? dropdownValueMap : Collections.emptyMap();
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public Integer getOrdenacaoGrid() {
        return ordenacaoGrid;
    }

    public void setOrdenacaoGrid(Integer ordenacaoGrid) {
        this.ordenacaoGrid = ordenacaoGrid;
    }

    public String getFiltroAplicado() {
        return filtroAplicado;
    }

    public void setFiltroAplicado(String filtroAplicado) {
        this.filtroAplicado = filtroAplicado;
    }

    private Map<String, String> parseDropdownValues(String dropdownValues) {
        Map<String, String> valueMap = new HashMap<>();
        for (String pair : dropdownValues.split(";")) {
            String[] parts = pair.split("=");
            if (parts.length == 2) {
                valueMap.put(parts[0], parts[1]);
            }
        }
        return valueMap;
    }
}