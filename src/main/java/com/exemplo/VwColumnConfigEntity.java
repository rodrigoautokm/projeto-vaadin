package com.exemplo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vw_column_config")
public class VwColumnConfigEntity extends GridColumnConfig {


    @Id
    private Integer id;

    @Column(name = "class_name")
    private String className;

    @Column(name = "field_name")
    private String fieldName;

    private String header;

    @Column(name = "type") // Ajustado para corresponder à coluna 'type' na view
    private String columnType;

    private String visible;

    @Column(name = "numeric_width")
    private Integer numericWidth;

    private String width;

    private String style;

    @Column(name = "filter_type")
    private String filterType;

    @Column(name = "dropdown_values")
    private String dropdownValues;

    private String alias;

    private String sort;

    @Column(name = "ordenacao_grid")
    private Integer ordenacaoGrid;

    @Column(name = "filtro_aplicado")
    private String filtroAplicado;

    private String usuario; // Adicionado para mapear a coluna 'usuario'

    // Getters e Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public String getVisible() {
        return visible;
    }

    public void setVisible(String visible) {
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

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}