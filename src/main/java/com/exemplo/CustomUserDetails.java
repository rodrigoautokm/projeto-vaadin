package com.exemplo;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class CustomUserDetails extends User {
    private Empresa empresa;

    private final Integer cdEmpresa;

    public CustomUserDetails(String username, String password, Integer cdEmpresa, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.cdEmpresa = cdEmpresa;
    }

    public Integer getCdEmpresa() {
        return cdEmpresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
}
