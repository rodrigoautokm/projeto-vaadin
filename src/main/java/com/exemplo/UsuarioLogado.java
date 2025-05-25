package com.exemplo;

import java.io.Serializable;

public class UsuarioLogado implements Serializable {
    private String username;
    private Integer cdEmpresa;

    public UsuarioLogado(String username, Integer cdEmpresa) {
        this.username = username;
        this.cdEmpresa = cdEmpresa;
    }

    public String getUsername() {
        return username;
    }

    public Integer getCdEmpresa() {
        return cdEmpresa;
    }
}
