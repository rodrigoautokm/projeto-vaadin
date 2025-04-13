package com.exemplo;

public class DREItem {
    private String agrupa;
    private String descricao;
    private Double valor;
    private String fornecedor;
    private String situacao;
    private String dataEmissao;
    private String dataVencimento;
    private String dataPagamento;
    private String observacao;
    private String despesa1; // Renomeado de formaPagamento para despesa1 para alinhar com o retorno

    public DREItem(String agrupa, String descricao, Double valor, String fornecedor, String situacao,
                   String dataEmissao, String dataVencimento, String dataPagamento, String observacao, String despesa1) {
        this.agrupa = agrupa;
        this.descricao = descricao;
        this.valor = valor;
        this.fornecedor = fornecedor;
        this.situacao = situacao;
        this.dataEmissao = dataEmissao;
        this.dataVencimento = dataVencimento;
        this.dataPagamento = dataPagamento;
        this.observacao = observacao;
        this.despesa1 = despesa1;
    }

    public String getAgrupa() { return agrupa; }
    public void setAgrupa(String agrupa) { this.agrupa = agrupa; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public Double getValor() { return valor; }
    public void setValor(Double valor) { this.valor = valor; }

    public String getFornecedor() { return fornecedor; }
    public void setFornecedor(String fornecedor) { this.fornecedor = fornecedor; }

    public String getSituacao() { return situacao; }
    public void setSituacao(String situacao) { this.situacao = situacao; }

    public String getDataEmissao() { return dataEmissao; }
    public void setDataEmissao(String dataEmissao) { this.dataEmissao = dataEmissao; }

    public String getDataVencimento() { return dataVencimento; }
    public void setDataVencimento(String dataVencimento) { this.dataVencimento = dataVencimento; }

    public String getDataPagamento() { return dataPagamento; }
    public void setDataPagamento(String dataPagamento) { this.dataPagamento = dataPagamento; }

    public String getObservacao() { return observacao; }
    public void setObservacao(String observacao) { this.observacao = observacao; }

    public String getDespesa1() { return despesa1; }
    public void setDespesa1(String despesa1) { this.despesa1 = despesa1; }
}