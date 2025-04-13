package com.exemplo;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "pedido_venda")
public class ListagemPedido {

    @EmbeddedId
    private ListagemPedidoId id;

    @Column(name = "cd_empresa_venda")
    private Integer cdEmpresaVenda;

    @Column(name = "dt_emissao")
    private Timestamp dtEmissao;

    @Column(name = "cd_cliente")
    private Integer cdCliente;

    @Column(name = "nm_cliente")
    private String nmCliente;

    @Column(name = "vl_total")
    private BigDecimal vlTotal;

    @Column(name = "vl_desconto_total")
    private BigDecimal vlDescontoTotal;

    @Column(name = "situacao")
    private String situacao;

    @Column(name = "cd_funcionario")
    private Integer cdFuncionario;

    @Column(name = "nm_funcionario")
    private String nmFuncionario;

    // Construtores
    public ListagemPedido() {
        this.id = new ListagemPedidoId();
    }

    // Getters e Setters
    public ListagemPedidoId getId() { return id; }
    public void setId(ListagemPedidoId id) { this.id = id; }

    public Integer getCdEmpresa() { return id != null ? id.getCdEmpresa() : null; }
    public void setCdEmpresa(Integer cdEmpresa) { if (id == null) id = new ListagemPedidoId(); id.setCdEmpresa(cdEmpresa); }

    public Integer getNrPedido() { return id != null ? id.getNrPedido() : null; }
    public void setNrPedido(Integer nrPedido) { if (id == null) id = new ListagemPedidoId(); id.setNrPedido(nrPedido); }

    public String getSerie() { return id != null ? id.getSerie() : null; }
    public void setSerie(String serie) { if (id == null) id = new ListagemPedidoId(); id.setSerie(serie); }

    public Integer getCdEmpresaVenda() { return cdEmpresaVenda; }
    public void setCdEmpresaVenda(Integer cdEmpresaVenda) { this.cdEmpresaVenda = cdEmpresaVenda; }

    public Timestamp getDtEmissao() { return dtEmissao; }
    public void setDtEmissao(Timestamp dtEmissao) { this.dtEmissao = dtEmissao; }

    public Integer getCdCliente() { return cdCliente; }
    public void setCdCliente(Integer cdCliente) { this.cdCliente = cdCliente; }

    public String getNmCliente() { return nmCliente; }
    public void setNmCliente(String nmCliente) { this.nmCliente = nmCliente; }

    public BigDecimal getVlTotal() { return vlTotal; }
    public void setVlTotal(BigDecimal vlTotal) { this.vlTotal = vlTotal; }

    public BigDecimal getVlDescontoTotal() { return vlDescontoTotal; }
    public void setVlDescontoTotal(BigDecimal vlDescontoTotal) { this.vlDescontoTotal = vlDescontoTotal; }

    public String getSituacao() { return situacao; }
    public void setSituacao(String situacao) { this.situacao = situacao; }

    public Integer getCdFuncionario() { return cdFuncionario; }
    public void setCdFuncionario(Integer cdFuncionario) { this.cdFuncionario = cdFuncionario; }

    public String getNmFuncionario() { return nmFuncionario; }
    public void setNmFuncionario(String nmFuncionario) { this.nmFuncionario = nmFuncionario; }
}