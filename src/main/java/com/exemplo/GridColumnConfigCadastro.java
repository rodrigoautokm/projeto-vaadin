package com.exemplo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Transient;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "vw_column_config_cadastro")
public class GridColumnConfigCadastro {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "class_name")
    private String className;

    @Column(name = "field_name")
    private String field;

    @Column(name = "alias")
    private String alias;

    @Column(name = "header")
    private String header;

    @Column(name = "data_type")
    private String type;

    @Column(name = "required")
    private boolean required;

    @Column(name = "editable")
    private boolean editable;

    @Column(name = "visible")
    private boolean visible;

    @Column(name = "dropdown_entity")
    private String dropdownEntity;

    @Column(name = "dropdown_values")
    private String dropdownValues;

    @Transient // Ignora este campo no mapeamento JPA
    private Map<String, String> dropdownValueMap;

    @Column(name = "width")
    private Integer width;

    @Column(name = "scale")
    private Integer scale;

    @Column(name = "colunas_aninhadas")
    private String nestedColumns;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public String getDropdownEntity() {
        return dropdownEntity;
    }

    public void setDropdownEntity(String dropdownEntity) {
        this.dropdownEntity = dropdownEntity;
    }

    public String getDropdownValues() {
        return dropdownValues;
    }

    public void setDropdownValues(String dropdownValues) {
        this.dropdownValues = dropdownValues;
    }

    public Map<String, String> getDropdownValueMap() {
        // Transforma dropdownValues (ex.: "A=Ativo;I=Inativo") em um Map
        Map<String, String> map = new HashMap<>();
        if (dropdownValues != null && !dropdownValues.isEmpty()) {
            String[] pairs = dropdownValues.split(";");
            for (String pair : pairs) {
                String[] keyValue = pair.split("=");
                if (keyValue.length == 2) {
                    map.put(keyValue[0], keyValue[1]);
                }
            }
        }
        this.dropdownValueMap = map;
        return dropdownValueMap;
    }

    public void setDropdownValueMap(Map<String, String> dropdownValueMap) {
        this.dropdownValueMap = dropdownValueMap;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getScale() {
        return scale;
    }

    public void setScale(Integer scale) {
        this.scale = scale;
    }

    public String getNestedColumns() {
        return nestedColumns;
    }

    public void setNestedColumns(String nestedColumns) {
        this.nestedColumns = nestedColumns;
    }
}