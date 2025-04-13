package com.exemplo;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ListagemPedidoId implements Serializable {

    @Column(name = "cd_empresa")
    private Integer cdEmpresa;

    @Column(name = "nr_pedido")
    private Integer nrPedido;

    @Column(name = "serie")
    private String serie;

    // Construtores
    public ListagemPedidoId() {}

    public ListagemPedidoId(Integer cdEmpresa, Integer nrPedido, String serie) {
        this.cdEmpresa = cdEmpresa;
        this.nrPedido = nrPedido;
        this.serie = serie;
    }

    // Getters e Setters
    public Integer getCdEmpresa() { return cdEmpresa; }
    public void setCdEmpresa(Integer cdEmpresa) { this.cdEmpresa = cdEmpresa; }
    public Integer getNrPedido() { return nrPedido; }
    public void setNrPedido(Integer nrPedido) { this.nrPedido = nrPedido; }
    public String getSerie() { return serie; }
    public void setSerie(String serie) { this.serie = serie; }

    // Equals e HashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListagemPedidoId that = (ListagemPedidoId) o;
        return Objects.equals(cdEmpresa, that.cdEmpresa) &&
               Objects.equals(nrPedido, that.nrPedido) &&
               Objects.equals(serie, that.serie);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cdEmpresa, nrPedido, serie);
    }
}