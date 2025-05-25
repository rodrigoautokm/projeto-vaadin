package com.exemplo;

import org.springframework.stereotype.Component;

@Component
public class EmpresaAwareService {

    private Integer cdEmpresa;

    public Integer getCdEmpresa() {
        return cdEmpresa;
    }

    public void setCdEmpresa(Integer cdEmpresa) {
        this.cdEmpresa = cdEmpresa;
    }
}
