package com.exemplo;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "vw_column_config_cadastro", schema = "dbo")
@org.hibernate.annotations.Immutable
public class ColumnConfigCadastroEntity implements Serializable {

    @Id
    private Integer id;

    @Column(name = "class_name")
    private String className;

    @Column(name = "field_name")
    private String fieldName;

    @Column(name = "header")
    private String header;

    @Column(name = "data_type")
    private String dataType;

    @Column(name = "required")
    private String required;

    @Column(name = "editable")
    private String editable;

    @Column(name = "visible")
    private String visible;

    @Column(name = "dropdown_entity")
    private String dropdownEntity;

    @Column(name = "dropdown_values")
    private String dropdownValues;

    @Column(name = "width")
    private Integer width;

    @Column(name = "scale")
    private Integer scale;

    @Column(name = "pkey")
    private String pkey;

    @Column(name = "colunas_aninhadas")
    private String nestedColumns;

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

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getRequired() {
        return required;
    }

    public void setRequired(String required) {
        this.required = required;
    }

    public String getEditable() {
        return editable;
    }

    public void setEditable(String editable) {
        this.editable = editable;
    }

    public String getVisible() {
        return visible;
    }

    public void setVisible(String visible) {
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

    public String getPkey() {
        return pkey;
    }

    public void setPkey(String pkey) {
        this.pkey = pkey;
    }

    public String getNestedColumns() {
        return nestedColumns;
    }

    public void setNestedColumns(String nestedColumns) {
        this.nestedColumns = nestedColumns;
    }
}