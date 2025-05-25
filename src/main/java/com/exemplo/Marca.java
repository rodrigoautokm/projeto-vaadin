package com.exemplo;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "marca")
public class Marca {

    @Id
    @Column(name = "cd_marca")
    private Short cdMarca;

    @Column(name = "ds_marca")
    private String dsMarca;

    @Column(name = "comissao")
    private BigDecimal comissao;

    @Column(name = "pr_desconto_vendedor")
    private BigDecimal prDescontoVendedor;

    @Column(name = "pr_desconto_gerente")
    private BigDecimal prDescontoGerente;

    @Column(name = "altera_preco")
    private String alteraPreco;

    @Column(name = "situacao")
    private String situacao;

    @Column(name = "gtin_unico")
    private Long gtinUnico;

    // Getters e Setters
    public Short getCdMarca() { return cdMarca; }
    public void setCdMarca(Short cdMarca) { this.cdMarca = cdMarca; }

    public String getDsMarca() { return dsMarca; }
    public void setDsMarca(String dsMarca) { this.dsMarca = dsMarca; }

    public BigDecimal getComissao() { return comissao; }
    public void setComissao(BigDecimal comissao) { this.comissao = comissao; }

    public BigDecimal getPrDescontoVendedor() { return prDescontoVendedor; }
    public void setPrDescontoVendedor(BigDecimal prDescontoVendedor) { this.prDescontoVendedor = prDescontoVendedor; }

    public BigDecimal getPrDescontoGerente() { return prDescontoGerente; }
    public void setPrDescontoGerente(BigDecimal prDescontoGerente) { this.prDescontoGerente = prDescontoGerente; }

    public String getAlteraPreco() { return alteraPreco; }
    public void setAlteraPreco(String alteraPreco) { this.alteraPreco = alteraPreco; }

    public String getSituacao() { return situacao; }
    public void setSituacao(String situacao) { this.situacao = situacao; }

    public Long getGtinUnico() { return gtinUnico; }
    public void setGtinUnico(Long gtinUnico) { this.gtinUnico = gtinUnico; }
}