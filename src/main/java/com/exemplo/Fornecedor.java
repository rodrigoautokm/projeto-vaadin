package com.exemplo;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "fornecedor")
public class Fornecedor implements Serializable {

    @Id
    @Column(name = "cd_fornecedor")
    private Integer cdFornecedor;

    @Column(name = "nm_fornecedor")
    private String nmFornecedor;

    @Column(name = "nm_fantasia")
    private String nmFantasia;

    @Column(name = "nm_representante")
    private String nmRepresentante;

    @Column(name = "cd_cidade")
    private Integer cdCidade;

    @Column(name = "endereco")
    private String endereco;

    @Column(name = "bairro")
    private String bairro;

    @Column(name = "fone")
    private String fone;

    @Column(name = "email")
    private String email;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "situacao")
    private String situacao;

    // Campos adicionais da visão
    @Column(name = "agencia")
    private String agencia;

    @Column(name = "altera_preco")
    private String alteraPreco;

    @Column(name = "bairro_garantia")
    private String bairroGarantia;

    @Column(name = "cd_banco")
    private Integer cdBanco;

    @Column(name = "cd_cidade_garantia")
    private Integer cdCidadeGarantia;

    @Column(name = "cd_grupo")
    private Integer cdGrupo;

    @Column(name = "cd_pais")
    private Integer cdPais;

    @Column(name = "celular")
    private String celular;

    @Column(name = "celular_comercial")
    private String celularComercial;

    @Column(name = "celular_financeiro")
    private String celularFinanceiro;

    @Column(name = "celular_representante")
    private String celularRepresentante;

    @Column(name = "cep")
    private String cep;

    @Column(name = "cep_garantia")
    private String cepGarantia;

    @Column(name = "cgc")
    private String cgc;

    @Column(name = "conta_contabil")
    private String contaContabil;

    @Column(name = "conta_corrente")
    private String contaCorrente;

    @Column(name = "contato_comercial")
    private String contatoComercial;

    @Column(name = "contato_email_nfe")
    private String contatoEmailNfe;

    @Column(name = "contato_financeiro")
    private String contatoFinanceiro;

    @Column(name = "contato_garantia")
    private String contatoGarantia;

    @Column(name = "cooperado")
    private String cooperado;

    @Column(name = "dias_entrega")
    private Integer diasEntrega;

    @Column(name = "email_comercial")
    private String emailComercial;

    @Column(name = "email_cotacao")
    private String emailCotacao;

    @Column(name = "email_financeiro")
    private String emailFinanceiro;

    @Column(name = "email_garantia")
    private String emailGarantia;

    @Column(name = "email_nfe")
    private String emailNfe;

    @Column(name = "email_representante")
    private String emailRepresentante;

    @Column(name = "endereco_garantia")
    private String enderecoGarantia;

    @Column(name = "fax")
    private String fax;

    @Column(name = "fax_comercial")
    private String faxComercial;

    @Column(name = "fax_financeiro")
    private String faxFinanceiro;

    @Column(name = "fax_garantia")
    private String faxGarantia;

    @Column(name = "fax_representante")
    private String faxRepresentante;

    @Column(name = "fone_comercial")
    private String foneComercial;

    @Column(name = "fone_financeiro")
    private String foneFinanceiro;

    @Column(name = "fone_garantia")
    private String foneGarantia;

    @Column(name = "fone_representante")
    private String foneRepresentante;

    @Column(name = "frete_no_ipi")
    private String freteNoIpi;

    @Column(name = "icms_no_desconto")
    private String icmsNoDesconto;

    @Column(name = "inf_negociacao", length = 32767)
    private String infNegociacao;

    @Column(name = "inscricao_estadual")
    private String inscricaoEstadual;

    @Column(name = "isento_pis_cofins")
    private String isentoPisCofins;

    @Column(name = "msn_garantia")
    private String msnGarantia;

    @Column(name = "nro_endereco")
    private String nroEndereco;

    @Column(name = "obs", length = 32767)
    private String obs;

    @Column(name = "obs_como_historico_contabil")
    private String obsComoHistoricoContabil;

    @Column(name = "obs_garantia", length = 32767)
    private String obsGarantia;

    @Column(name = "ramal_comercial")
    private String ramalComercial;

    @Column(name = "ramal_financeiro")
    private String ramalFinanceiro;

    @Column(name = "ramal_representante")
    private String ramalRepresentante;

    @Column(name = "representante")
    private String representante;

    @Column(name = "tipo_contribuinte")
    private Integer tipoContribuinte;

    @Column(name = "tipo_fornecedor")
    private String tipoFornecedor;

    @Column(name = "tp_pessoa")
    private String tpPessoa;

    // Getters e Setters
    public Integer getCdFornecedor() {
        return cdFornecedor;
    }

    public void setCdFornecedor(Integer cdFornecedor) {
        this.cdFornecedor = cdFornecedor;
    }

    public String getNmFornecedor() {
        return nmFornecedor;
    }

    public void setNmFornecedor(String nmFornecedor) {
        this.nmFornecedor = nmFornecedor;
    }

    public String getNmFantasia() {
        return nmFantasia;
    }

    public void setNmFantasia(String nmFantasia) {
        this.nmFantasia = nmFantasia;
    }

    public String getNmRepresentante() {
        return nmRepresentante;
    }

    public void setNmRepresentante(String nmRepresentante) {
        this.nmRepresentante = nmRepresentante;
    }

    public Integer getCdCidade() {
        return cdCidade;
    }

    public void setCdCidade(Integer cdCidade) {
        this.cdCidade = cdCidade;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getAlteraPreco() {
        return alteraPreco;
    }

    public void setAlteraPreco(String alteraPreco) {
        this.alteraPreco = alteraPreco;
    }

    public String getBairroGarantia() {
        return bairroGarantia;
    }

    public void setBairroGarantia(String bairroGarantia) {
        this.bairroGarantia = bairroGarantia;
    }

    public Integer getCdBanco() {
        return cdBanco;
    }

    public void setCdBanco(Integer cdBanco) {
        this.cdBanco = cdBanco;
    }

    public Integer getCdCidadeGarantia() {
        return cdCidadeGarantia;
    }

    public void setCdCidadeGarantia(Integer cdCidadeGarantia) {
        this.cdCidadeGarantia = cdCidadeGarantia;
    }

    public Integer getCdGrupo() {
        return cdGrupo;
    }

    public void setCdGrupo(Integer cdGrupo) {
        this.cdGrupo = cdGrupo;
    }

    public Integer getCdPais() {
        return cdPais;
    }

    public void setCdPais(Integer cdPais) {
        this.cdPais = cdPais;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getCelularComercial() {
        return celularComercial;
    }

    public void setCelularComercial(String celularComercial) {
        this.celularComercial = celularComercial;
    }

    public String getCelularFinanceiro() {
        return celularFinanceiro;
    }

    public void setCelularFinanceiro(String celularFinanceiro) {
        this.celularFinanceiro = celularFinanceiro;
    }

    public String getCelularRepresentante() {
        return celularRepresentante;
    }

    public void setCelularRepresentante(String celularRepresentante) {
        this.celularRepresentante = celularRepresentante;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCepGarantia() {
        return cepGarantia;
    }

    public void setCepGarantia(String cepGarantia) {
        this.cepGarantia = cepGarantia;
    }

    public String getCgc() {
        return cgc;
    }

    public void setCgc(String cgc) {
        this.cgc = cgc;
    }

    public String getContaContabil() {
        return contaContabil;
    }

    public void setContaContabil(String contaContabil) {
        this.contaContabil = contaContabil;
    }

    public String getContaCorrente() {
        return contaCorrente;
    }

    public void setContaCorrente(String contaCorrente) {
        this.contaCorrente = contaCorrente;
    }

    public String getContatoComercial() {
        return contatoComercial;
    }

    public void setContatoComercial(String contatoComercial) {
        this.contatoComercial = contatoComercial;
    }

    public String getContatoEmailNfe() {
        return contatoEmailNfe;
    }

    public void setContatoEmailNfe(String contatoEmailNfe) {
        this.contatoEmailNfe = contatoEmailNfe;
    }

    public String getContatoFinanceiro() {
        return contatoFinanceiro;
    }

    public void setContatoFinanceiro(String contatoFinanceiro) {
        this.contatoFinanceiro = contatoFinanceiro;
    }

    public String getContatoGarantia() {
        return contatoGarantia;
    }

    public void setContatoGarantia(String contatoGarantia) {
        this.contatoGarantia = contatoGarantia;
    }

    public String getCooperado() {
        return cooperado;
    }

    public void setCooperado(String cooperado) {
        this.cooperado = cooperado;
    }

    public Integer getDiasEntrega() {
        return diasEntrega;
    }

    public void setDiasEntrega(Integer diasEntrega) {
        this.diasEntrega = diasEntrega;
    }

    public String getEmailComercial() {
        return emailComercial;
    }

    public void setEmailComercial(String emailComercial) {
        this.emailComercial = emailComercial;
    }

    public String getEmailCotacao() {
        return emailCotacao;
    }

    public void setEmailCotacao(String emailCotacao) {
        this.emailCotacao = emailCotacao;
    }

    public String getEmailFinanceiro() {
        return emailFinanceiro;
    }

    public void setEmailFinanceiro(String emailFinanceiro) {
        this.emailFinanceiro = emailFinanceiro;
    }

    public String getEmailGarantia() {
        return emailGarantia;
    }

    public void setEmailGarantia(String emailGarantia) {
        this.emailGarantia = emailGarantia;
    }

    public String getEmailNfe() {
        return emailNfe;
    }

    public void setEmailNfe(String emailNfe) {
        this.emailNfe = emailNfe;
    }

    public String getEmailRepresentante() {
        return emailRepresentante;
    }

    public void setEmailRepresentante(String emailRepresentante) {
        this.emailRepresentante = emailRepresentante;
    }

    public String getEnderecoGarantia() {
        return enderecoGarantia;
    }

    public void setEnderecoGarantia(String enderecoGarantia) {
        this.enderecoGarantia = enderecoGarantia;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getFaxComercial() {
        return faxComercial;
    }

    public void setFaxComercial(String faxComercial) {
        this.faxComercial = faxComercial;
    }

    public String getFaxFinanceiro() {
        return faxFinanceiro;
    }

    public void setFaxFinanceiro(String faxFinanceiro) {
        this.faxFinanceiro = faxFinanceiro;
    }

    public String getFaxGarantia() {
        return faxGarantia;
    }

    public void setFaxGarantia(String faxGarantia) {
        this.faxGarantia = faxGarantia;
    }

    public String getFaxRepresentante() {
        return faxRepresentante;
    }

    public void setFaxRepresentante(String faxRepresentante) {
        this.faxRepresentante = faxRepresentante;
    }

    public String getFoneComercial() {
        return foneComercial;
    }

    public void setFoneComercial(String foneComercial) {
        this.foneComercial = foneComercial;
    }

    public String getFoneFinanceiro() {
        return foneFinanceiro;
    }

    public void setFoneFinanceiro(String foneFinanceiro) {
        this.foneFinanceiro = foneFinanceiro;
    }

    public String getFoneGarantia() {
        return foneGarantia;
    }

    public void setFoneGarantia(String foneGarantia) {
        this.foneGarantia = foneGarantia;
    }

    public String getFoneRepresentante() {
        return foneRepresentante;
    }

    public void setFoneRepresentante(String foneRepresentante) {
        this.foneRepresentante = foneRepresentante;
    }

    public String getFreteNoIpi() {
        return freteNoIpi;
    }

    public void setFreteNoIpi(String freteNoIpi) {
        this.freteNoIpi = freteNoIpi;
    }

    public String getIcmsNoDesconto() {
        return icmsNoDesconto;
    }

    public void setIcmsNoDesconto(String icmsNoDesconto) {
        this.icmsNoDesconto = icmsNoDesconto;
    }

    public String getInfNegociacao() {
        return infNegociacao;
    }

    public void setInfNegociacao(String infNegociacao) {
        this.infNegociacao = infNegociacao;
    }

    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    public void setInscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }

    public String getIsentoPisCofins() {
        return isentoPisCofins;
    }

    public void setIsentoPisCofins(String isentoPisCofins) {
        this.isentoPisCofins = isentoPisCofins;
    }

    public String getMsnGarantia() {
        return msnGarantia;
    }

    public void setMsnGarantia(String msnGarantia) {
        this.msnGarantia = msnGarantia;
    }

    public String getNroEndereco() {
        return nroEndereco;
    }

    public void setNroEndereco(String nroEndereco) {
        this.nroEndereco = nroEndereco;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public String getObsComoHistoricoContabil() {
        return obsComoHistoricoContabil;
    }

    public void setObsComoHistoricoContabil(String obsComoHistoricoContabil) {
        this.obsComoHistoricoContabil = obsComoHistoricoContabil;
    }

    public String getObsGarantia() {
        return obsGarantia;
    }

    public void setObsGarantia(String obsGarantia) {
        this.obsGarantia = obsGarantia;
    }

    public String getRamalComercial() {
        return ramalComercial;
    }

    public void setRamalComercial(String ramalComercial) {
        this.ramalComercial = ramalComercial;
    }

    public String getRamalFinanceiro() {
        return ramalFinanceiro;
    }

    public void setRamalFinanceiro(String ramalFinanceiro) {
        this.ramalFinanceiro = ramalFinanceiro;
    }

    public String getRamalRepresentante() {
        return ramalRepresentante;
    }

    public void setRamalRepresentante(String ramalRepresentante) {
        this.ramalRepresentante = ramalRepresentante;
    }

    public String getRepresentante() {
        return representante;
    }

    public void setRepresentante(String representante) {
        this.representante = representante;
    }

    public Integer getTipoContribuinte() {
        return tipoContribuinte;
    }

    public void setTipoContribuinte(Integer tipoContribuinte) {
        this.tipoContribuinte = tipoContribuinte;
    }

    public String getTipoFornecedor() {
        return tipoFornecedor;
    }

    public void setTipoFornecedor(String tipoFornecedor) {
        this.tipoFornecedor = tipoFornecedor;
    }

    public String getTpPessoa() {
        return tpPessoa;
    }

    public void setTpPessoa(String tpPessoa) {
        this.tpPessoa = tpPessoa;
    }
}