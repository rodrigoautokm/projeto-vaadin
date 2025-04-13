package com.exemplo;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ClienteId implements Serializable {

    @Column(name = "cd_cliente")
    private Integer cdCliente;

    @Column(name = "cd_empresa")
    private Integer cdEmpresa;

    // Construtores
    public ClienteId() {}

    public ClienteId(Integer cdCliente, Integer cdEmpresa) {
        this.cdCliente = cdCliente;
        this.cdEmpresa = cdEmpresa;
    }

    // Getters e Setters
    public Integer getCdCliente() {
        return cdCliente;
    }

    public void setCdCliente(Integer cdCliente) {
        this.cdCliente = cdCliente;
    }

    public Integer getCdEmpresa() {
        return cdEmpresa;
    }

    public void setCdEmpresa(Integer cdEmpresa) {
        this.cdEmpresa = cdEmpresa;
    }

    // Equals e HashCode (necessário para chaves compostas)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClienteId that = (ClienteId) o;
        return Objects.equals(cdCliente, that.cdCliente) &&
               Objects.equals(cdEmpresa, that.cdEmpresa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cdCliente, cdEmpresa);
    }
}