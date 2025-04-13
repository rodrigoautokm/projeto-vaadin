package com.exemplo.admin;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import java.time.LocalDateTime;

@Entity
@Table(name = "empresa", schema = "dbo")
public class Empresa {
    @Id
    private Short cdEmpresa;
    private String nmEmpresa;
    private String chaveAcesso;
    private String cnpj;
    private String ipBd;
    private String portaBd;
    private String usuarioBd;
    private String senhaBd;

    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;

    // Getters e Setters
    public Short getCdEmpresa() {
        return cdEmpresa;
    }

    public void setCdEmpresa(Short cdEmpresa) {
        this.cdEmpresa = cdEmpresa;
    }

    public String getNmEmpresa() {
        return nmEmpresa;
    }

    public void setNmEmpresa(String nmEmpresa) {
        this.nmEmpresa = nmEmpresa;
    }

    public String getChaveAcesso() {
        return chaveAcesso;
    }

    public void setChaveAcesso(String chaveAcesso) {
        this.chaveAcesso = chaveAcesso;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getIpBd() {
        return ipBd;
    }

    public void setIpBd(String ipBd) {
        this.ipBd = ipBd;
    }

    public String getPortaBd() {
        return portaBd;
    }

    public void setPortaBd(String portaBd) {
        this.portaBd = portaBd;
    }

    public String getUsuarioBd() {
        return usuarioBd;
    }

    public void setUsuarioBd(String usuarioBd) {
        this.usuarioBd = usuarioBd;
    }

    public String getSenhaBd() {
        return senhaBd;
    }

    public void setSenhaBd(String senhaBd) {
        this.senhaBd = senhaBd;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }
}