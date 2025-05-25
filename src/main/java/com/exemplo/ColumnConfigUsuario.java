package com.exemplo;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "column_config_usuario")
public class ColumnConfigUsuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "class_name", nullable = false, length = 50)
    private String className;

    @Column(name = "field_name", nullable = false, length = 50)
    private String fieldName;

    @Column(name = "usuario", nullable = false, length = 50)
    private String usuario;

    @Column(name = "visible", length = 1)
    private String visible;

    @Column(name = "sort", length = 5)
    private String sort;

    @Column(name = "ordenacao_grid", nullable = false)
    private Integer ordenacaoGrid;

    @Column(name = "filtro_aplicado", length = 255)
    private String filtroAplicado;
    
    @Version
    private Long version;

    public ColumnConfigUsuario() {}

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

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getVisible() {
        return visible;
    }

    public void setVisible(String visible) {
        this.visible = visible;
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

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
