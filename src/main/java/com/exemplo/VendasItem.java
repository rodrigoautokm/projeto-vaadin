package com.exemplo;

public class VendasItem {
    private String nrPedido;
    private String nmCliente;
    private String dsProduto;
    private Double qtProduto;
    private Double vlUnitario;
    private Double vlDesconto;
    private Double vlCusto;
    private String dtEmissao;
    private String situacao;
    private String dsGrupo;
    private Double vlTotal; // Adicionado

    public VendasItem(String nrPedido, String nmCliente, String dsProduto, Double qtProduto, 
                     Double vlUnitario, Double vlDesconto, Double vlCusto, String dtEmissao, 
                     String situacao, String dsGrupo) {
        this.nrPedido = nrPedido;
        this.nmCliente = nmCliente;
        this.dsProduto = dsProduto;
        this.qtProduto = qtProduto;
        this.vlUnitario = vlUnitario;
        this.vlDesconto = vlDesconto;
        this.vlCusto = vlCusto;
        this.dtEmissao = dtEmissao;
        this.situacao = situacao;
        this.dsGrupo = dsGrupo;
        this.vlTotal = (qtProduto * vlUnitario) - (vlDesconto != null ? vlDesconto : 0); // Cálculo seguro
    }

    // Getters
    public String getNrPedido() { return nrPedido; }
    public String getNmCliente() { return nmCliente; }
    public String getDsProduto() { return dsProduto; }
    public Double getQtProduto() { return qtProduto; }
    public Double getVlUnitario() { return vlUnitario; }
    public Double getVlDesconto() { return vlDesconto; }
    public Double getVlCusto() { return vlCusto; }
    public String getDtEmissao() { return dtEmissao; }
    public String getSituacao() { return situacao; }
    public String getDsGrupo() { return dsGrupo; }
    public Double getVlTotal() { return vlTotal; }

    // Setters
    public void setNrPedido(String nrPedido) { this.nrPedido = nrPedido; }
    public void setNmCliente(String nmCliente) { this.nmCliente = nmCliente; }
    public void setDsProduto(String dsProduto) { this.dsProduto = dsProduto; }
    public void setQtProduto(Double qtProduto) { this.qtProduto = qtProduto; }
    public void setVlUnitario(Double vlUnitario) { this.vlUnitario = vlUnitario; }
    public void setVlDesconto(Double vlDesconto) { this.vlDesconto = vlDesconto; }
    public void setVlCusto(Double vlCusto) { this.vlCusto = vlCusto; }
    public void setDtEmissao(String dtEmissao) { this.dtEmissao = dtEmissao; }
    public void setSituacao(String situacao) { this.situacao = situacao; }
    public void setDsGrupo(String dsGrupo) { this.dsGrupo = dsGrupo; }
    public void setVlTotal(Double vlTotal) { this.vlTotal = vlTotal; }
}