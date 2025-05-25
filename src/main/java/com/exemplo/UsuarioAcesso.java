package com.exemplo;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "usuario_acesso")
public class UsuarioAcesso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "usuario", nullable = false)
    private String usuario;

    @Column(name = "senha", nullable = false)
    private String senha;

    @Column(name = "cd_empresa", nullable = false)
    private Integer cdEmpresa;

    @Column(name = "admin")
    private String admin;

    @Column(name = "data_criacao")
    private Timestamp dataCriacao;

    @Column(name = "data_atualizacao")
    private Timestamp dataAtualizacao;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public Integer getCdEmpresa() { return cdEmpresa; }
    public void setCdEmpresa(Integer cdEmpresa) { this.cdEmpresa = cdEmpresa; }

    public String getAdmin() { return admin; }
    public void setAdmin(String admin) { this.admin = admin; }

    public Timestamp getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(Timestamp dataCriacao) { this.dataCriacao = dataCriacao; }

    public Timestamp getDataAtualizacao() { return dataAtualizacao; }
    public void setDataAtualizacao(Timestamp dataAtualizacao) { this.dataAtualizacao = dataAtualizacao; }
}
