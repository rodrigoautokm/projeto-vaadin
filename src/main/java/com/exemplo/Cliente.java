package com.exemplo;

import javax.persistence.*;

@Entity
@Table(name = "cliente")
public class Cliente {

    @EmbeddedId
    private ClienteId id;

    @Column(name = "nm_cliente")
    private String nome;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "endereco")
    private String endereco;

    @Column(name = "bairro")
    private String bairro;

    @Column(name = "fone")
    private String fone;

    @Column(name = "celular")
    private String celular;

    @Column(name = "cpf")
    private String cpf;

    // Construtores
    public Cliente() {
        this.id = new ClienteId();
    }

    // Getters e Setters
    public ClienteId getId() {
        return id;
    }

    public void setId(ClienteId id) {
        this.id = id;
    }

    public Integer getCdCliente() {
        return id != null ? id.getCdCliente() : null;
    }

    public void setCdCliente(Integer cdCliente) {
        if (id == null) {
            id = new ClienteId();
        }
        id.setCdCliente(cdCliente);
    }

    public Integer getCdEmpresa() {
        return id != null ? id.getCdEmpresa() : null;
    }

    public void setCdEmpresa(Integer cdEmpresa) {
        if (id == null) {
            id = new ClienteId();
        }
        id.setCdEmpresa(cdEmpresa);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}