package com.exemplo;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ClienteId implements Serializable {

    private Long cdEmpresa;
    private Integer cdCliente;

    public ClienteId() {
    }

    public ClienteId(Long cdEmpresa, Integer cdCliente) {
        this.cdEmpresa = cdEmpresa;
        this.cdCliente = cdCliente;
    }

    // Getters e setters
    public Long getCdEmpresa() {
        return cdEmpresa;
    }

    public void setCdEmpresa(Long cdEmpresa) {
        this.cdEmpresa = cdEmpresa;
    }

    public Integer getCdCliente() {
        return cdCliente;
    }

    public void setCdCliente(Integer cdCliente) {
        this.cdCliente = cdCliente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClienteId that = (ClienteId) o;
        return Objects.equals(cdEmpresa, that.cdEmpresa) &&
               Objects.equals(cdCliente, that.cdCliente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cdEmpresa, cdCliente);
    }
}