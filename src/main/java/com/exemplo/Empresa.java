package com.exemplo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import java.sql.Timestamp;

@Entity
@Table(name = "empresa")
public class Empresa {
    @Id
    @Column(name = "cd_empresa")
    private Integer cd_empresa;

    @Column(name = "nm_empresa")
    private String nmEmpresa;

    @Column(name = "chave_acesso")
    private String chaveAcesso;

    @Column(name = "cnpj")
    private String cnpj;

    @Column(name = "ip_bd")
    private String ip_bd;

    @Column(name = "porta_bd")
    private String porta_bd;

    @Column(name = "usuario_bd")
    private String usuario_bd;

    @Column(name = "senha_bd")
    private String senha_bd;

    @Column(name = "data_criacao")
    private Timestamp dataCriacao;

    @Column(name = "data_atualizacao")
    private Timestamp dataAtualizacao;

    @Column(name = "servername") // Ajustado para corresponder à coluna real na tabela
    private String server_name;

    @Column(name = "nome_banco")
    private String nomeBanco;

    // Getters e Setters
    public Integer getCd_empresa() { return cd_empresa; }
    public void setCd_empresa(Integer cd_empresa) { this.cd_empresa = cd_empresa; }

    public String getNmEmpresa() { return nmEmpresa; }
    public void setNmEmpresa(String nmEmpresa) { this.nmEmpresa = nmEmpresa; }

    public String getChaveAcesso() { return chaveAcesso; }
    public void setChaveAcesso(String chaveAcesso) { this.chaveAcesso = chaveAcesso; }

    public String getCnpj() { return cnpj; }
    public void setCnpj(String cnpj) { this.cnpj = cnpj; }

    public String getIp_bd() { return ip_bd; }
    public void setIp_bd(String ip_bd) { this.ip_bd = ip_bd; }

    public String getPorta_bd() { return porta_bd; }
    public void setPorta_bd(String porta_bd) { this.porta_bd = porta_bd; }

    public String getUsuario_bd() { return usuario_bd; }
    public void setUsuario_bd(String usuario_bd) { this.usuario_bd = usuario_bd; }

    public String getSenha_bd() { return senha_bd; }
    public void setSenha_bd(String senha_bd) { this.senha_bd = senha_bd; }

    public Timestamp getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(Timestamp dataCriacao) { this.dataCriacao = dataCriacao; }

    public Timestamp getDataAtualizacao() { return dataAtualizacao; }
    public void setDataAtualizacao(Timestamp dataAtualizacao) { this.dataAtualizacao = dataAtualizacao; }

    public String getServer_name() { return server_name; }
    public void setServer_name(String server_name) { this.server_name = server_name; }

    public String getNomeBanco() { return nomeBanco; }
    public void setNomeBanco(String nomeBanco) { this.nomeBanco = nomeBanco; }

    // Métodos usados no DynamicDataSourceSwitcher
    public String getServerName() { return server_name; }
    public String getIpBd() { return ip_bd; }
    public String getPortaBd() { return porta_bd; }
    public String getUsuarioBd() { return usuario_bd; }
    public String getSenhaBd() { return senha_bd; }
    public Integer getCdEmpresa() { return cd_empresa; }
}