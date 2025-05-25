package com.exemplo;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "cliente")
public class Cliente {

    @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "cdEmpresa", column = @Column(name = "cd_empresa")),
        @AttributeOverride(name = "cdCliente", column = @Column(name = "cd_cliente"))
    })
    private ClienteId id;

    @Column(name = "nm_cliente")
    private String nmCliente;

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

    @Column(name = "cd_cidade")
    private Integer cdCidade;

    @Column(name = "uf")
    private String uf;

    @Column(name = "cep")
    private String cep;

    @Column(name = "dt_nasc")
    private LocalDate dtNasc;

    @Column(name = "rg")
    private String rg;

    @Column(name = "filiacao_pai")
    private String filiacaoPai;

    @Column(name = "filiacao_mae")
    private String filiacaoMae;

    @Column(name = "naturalidade")
    private String naturalidade;

    @Column(name = "resi_ante")
    private String resiAnte;

    @Column(name = "data_resi")
    private LocalDate dataResi;

    @Column(name = "tipo_resi")
    private String tipoResi;

    @Column(name = "valor_aluguel")
    private BigDecimal valorAluguel;

    @Column(name = "data_digitacao")
    private LocalDate dataDigitacao;

    @Column(name = "responsavel")
    private String responsavel;

    @Column(name = "estado_civil")
    private String estadoCivil;

    @Column(name = "conjuge")
    private String conjuge;

    @Column(name = "dt_conjuge")
    private LocalDate dtConjuge;

    @Column(name = "cpf_conjuge")
    private String cpfConjuge;

    @Column(name = "rg_conjuge")
    private String rgConjuge;

    @Column(name = "atividade_conj")
    private String atividadeConj;

    @Column(name = "dep")
    private Integer dep;

    @Column(name = "renda_conjuge")
    private BigDecimal rendaConjuge;

    @Column(name = "empresa")
    private String empresa;

    @Column(name = "end_come")
    private String endCome;

    @Column(name = "bairro_come")
    private String bairroCome;

    @Column(name = "cd_cidade_come")
    private Integer cdCidadeCome;

    @Column(name = "uf_come")
    private String ufCome;

    @Column(name = "cepcome")
    private String cepcome;

    @Column(name = "fone_come")
    private String foneCome;

    @Column(name = "fax_come")
    private String faxCome;

    @Column(name = "profissao")
    private String profissao;

    @Column(name = "cargo")
    private String cargo;

    @Column(name = "dt_admissao")
    private LocalDate dtAdmissao;

    @Column(name = "renda")
    private BigDecimal renda;

    @Column(name = "outras_rendas")
    private BigDecimal outrasRendas;

    @Column(name = "nome_avalista")
    private String nomeAvalista;

    @Column(name = "data_nasc_avali")
    private LocalDate dataNascAvali;

    @Column(name = "cpf_avalista")
    private String cpfAvalista;

    @Column(name = "rg_avalista")
    private String rgAvalista;

    @Column(name = "fone_avalista")
    private String foneAvalista;

    @Column(name = "celular_avalista")
    private String celularAvalista;

    @Column(name = "empresa_avalista")
    private String empresaAvalista;

    @Column(name = "fone_co_avalista")
    private String foneCoAvalista;

    @Column(name = "cd_categoria")
    private Integer cdCategoria;

    @Column(name = "obs1")
    private String obs1;

    @Column(name = "spc")
    private String spc;

    @Column(name = "razao")
    private String razao;

    @Column(name = "cgc")
    private String cgc;

    @Column(name = "inscricao")
    private String inscricao;

    @Column(name = "limite")
    private BigDecimal limite;

    @Column(name = "limite1")
    private BigDecimal limite1;

    @Column(name = "cd_ramo")
    private Integer cdRamo;

    @Column(name = "fone_conjuge")
    private String foneConjuge;

    @Column(name = "nr_tabela")
    private Integer nrTabela;

    @Column(name = "suframa")
    private String suframa;

    @Column(name = "endereco_cobranca")
    private String enderecoCobranca;

    @Column(name = "cd_cidade_cobranca")
    private Integer cdCidadeCobranca;

    @Column(name = "bairro_cobranca")
    private String bairroCobranca;

    @Column(name = "proximidade_cobranca")
    private String proximidadeCobranca;

    @Column(name = "cep_cobranca")
    private String cepCobranca;

    @Column(name = "fone_cobranca")
    private String foneCobranca;

    @Column(name = "nro_endereco_cobranca")
    private Integer nroEnderecoCobranca;

    @Column(name = "complemento_cobranca")
    private String complementoCobranca;

    @Column(name = "endereco_entrega")
    private String enderecoEntrega;

    @Column(name = "cd_cidade_entrega")
    private Integer cdCidadeEntrega;

    @Column(name = "bairro_entrega")
    private String bairroEntrega;

    @Column(name = "proximidade_entrega")
    private String proximidadeEntrega;

    @Column(name = "cep_entrega")
    private String cepEntrega;

    @Column(name = "fone_entrega")
    private String foneEntrega;

    @Column(name = "nro_endereco_entrega")
    private Integer nroEnderecoEntrega;

    @Column(name = "complemento_entrega")
    private String complementoEntrega;

    @Column(name = "cd_funcionario")
    private Integer cdFuncionario;

    @Column(name = "email")
    private String email;

    @Column(name = "contato")
    private String contato;

    @Column(name = "pr_desconto_vendedor")
    private BigDecimal prDescontoVendedor;

    @Column(name = "pr_desconto_gerente")
    private BigDecimal prDescontoGerente;

    @Column(name = "dt_nasc_socio_1")
    private LocalDate dtNascSocio1;

    @Column(name = "dt_nasc_socio_2")
    private LocalDate dtNascSocio2;

    @Column(name = "nm_usuario")
    private String nmUsuario;

    @Column(name = "nome_ref_pessoal1")
    private String nomeRefPessoal1;

    @Column(name = "endereco_ref_pessoal1")
    private String enderecoRefPessoal1;

    @Column(name = "nro_endereco_ref_pessoal1")
    private Integer nroEnderecoRefPessoal1;

    @Column(name = "complemento_ref_pessoal1")
    private String complementoRefPessoal1;

    @Column(name = "bairro_ref_pessoal1")
    private String bairroRefPessoal1;

    @Column(name = "cd_cidade_ref_pessoal1")
    private Integer cdCidadeRefPessoal1;

    @Column(name = "cep_ref_pessoal1")
    private String cepRefPessoal1;

    @Column(name = "fone_ref_pessoal1")
    private String foneRefPessoal1;

    @Column(name = "nome_ref_pessoal2")
    private String nomeRefPessoal2;

    @Column(name = "endereco_ref_pessoal2")
    private String enderecoRefPessoal2;

    @Column(name = "nro_endereco_ref_pessoal2")
    private Integer nroEnderecoRefPessoal2;

    @Column(name = "complemento_ref_pessoal2")
    private String complementoRefPessoal2;

    @Column(name = "bairro_ref_pessoal2")
    private String bairroRefPessoal2;

    @Column(name = "cd_cidade_ref_pessoal2")
    private Integer cdCidadeRefPessoal2;

    @Column(name = "cep_ref_pessoal2")
    private String cepRefPessoal2;

    @Column(name = "fone_ref_pessoal2")
    private String foneRefPessoal2;

    // Campos novos adicionais
    @Column(name = "agencia_cidade_banco_financiamento01")
    private String agenciaCidadeBancoFinanciamento01;

    @Column(name = "agencia_cidade_banco_financiamento02")
    private String agenciaCidadeBancoFinanciamento02;

    @Column(name = "apelido")
    private String apelido;

    @Column(name = "area_plantada")
    private String areaPlantada;

    @Column(name = "asaas_id")
    private String asaasId;

    @Column(name = "assistencia_tecnica01")
    private String assistenciaTecnica01;

    @Column(name = "assistencia_tecnica02")
    private String assistenciaTecnica02;

    @Column(name = "banco_financiamento_agencia01")
    private String bancoFinanciamentoAgencia01;

    @Column(name = "banco_financiamento_agencia02")
    private String bancoFinanciamentoAgencia02;

    @Column(name = "banco_financiamento_agencia03")
    private String bancoFinanciamentoAgencia03;

    @Column(name = "banco_financiamento01")
    private String bancoFinanciamento01;

    @Column(name = "banco_financiamento02")
    private String bancoFinanciamento02;

    @Column(name = "banco_financiamento03")
    private String bancoFinanciamento03;

    @Column(name = "banco_tipo_financiamento01")
    private String bancoTipoFinanciamento01;

    @Column(name = "banco_tipo_financiamento02")
    private String bancoTipoFinanciamento02;

    @Column(name = "banco_tipo_financiamento03")
    private String bancoTipoFinanciamento03;

    @Column(name = "bloco_produtor")
    private String blocoProdutor;

    @Column(name = "caminho_foto")
    private String caminhoFoto;

    @Column(name = "cd_condicao_padrao")
    private Integer cdCondicaoPadrao;

    @Column(name = "cd_contabil")
    private Integer cdContabil;

    @Column(name = "cd_contrato")
    private String cdContrato;

    @Column(name = "cd_forma_pagamento")
    private Integer cdFormaPagamento;

    @Column(name = "cd_regiao")
    private Integer cdRegiao;

    @Column(name = "cd_transportadora_padrao")
    private Integer cdTransportadoraPadrao;

    @Column(name = "cep_ref_pessoal3")
    private String cepRefPessoal3;

    @Column(name = "cgc_cobranca")
    private String cgcCobranca;

    @Column(name = "cidasc_localidade")
    private Integer cidascLocalidade;

    @Column(name = "cloud_id")
    private Integer cloudId;

    @Column(name = "cobrar_taxa_no_boleto")
    private String cobrarTaxaNoBoleto;

    @Column(name = "compex")
    private String compex;

    @Column(name = "compex_icms")
    private BigDecimal compexIcms;

    @Column(name = "compex_outros")
    private BigDecimal compexOutros;

    @Column(name = "compex_texto", length = 32767)
    private String compexTexto;

    @Column(name = "compex_texto02", length = 32767)
    private String compexTexto02;

    @Column(name = "contato_cargo1")
    private String contatoCargo1;

    @Column(name = "contato_cargo2")
    private String contatoCargo2;

    @Column(name = "contato_cargo3")
    private String contatoCargo3;

    @Column(name = "contato_email1")
    private String contatoEmail1;

    @Column(name = "contato_email2")
    private String contatoEmail2;

    @Column(name = "contato_email3")
    private String contatoEmail3;

    @Column(name = "contato_fone1")
    private String contatoFone1;

    @Column(name = "contato_fone2")
    private String contatoFone2;

    @Column(name = "contato_fone3")
    private String contatoFone3;

    @Column(name = "contato_nome1")
    private String contatoNome1;

    @Column(name = "contato_nome2")
    private String contatoNome2;

    @Column(name = "contato_nome3")
    private String contatoNome3;

    @Column(name = "contrato_arrendatario")
    private String contratoArrendatario;

    @Column(name = "contrato_parceria")
    private String contratoParceria;

    @Column(name = "cooperado")
    private String cooperado;

    @Column(name = "copia_rg_avalista")
    private String copiaRgAvalista;

    @Column(name = "copia_rg_cliente")
    private String copiaRgCliente;

    @Column(name = "copia_rg_conjuge")
    private String copiaRgConjuge;

    @Column(name = "cpf_regularizados")
    private String cpfRegularizados;

    @Column(name = "data_nasci_avali02")
    private LocalDate dataNasciAvali02;

    @Column(name = "debito_conta_agencia")
    private String debitoContaAgencia;

    @Column(name = "debito_conta_banco")
    private String debitoContaBanco;

    @Column(name = "debito_conta_conta_corrente")
    private String debitoContaContaCorrente;

    @Column(name = "debito_conta_razao")
    private String debitoContaRazao;

    @Column(name = "destaca_icms")
    private String destacaIcms;

    @Column(name = "dia_vencimento_todo_mes")
    private Integer diaVencimentoTodoMes;

    @Column(name = "dt_alteracao")
    private LocalDate dtAlteracao;

    @Column(name = "dt_cadastro")
    private LocalDate dtCadastro;

    @Column(name = "dt_consulta_inscricao_estadual_autokm")
    private LocalDate dtConsultaInscricaoEstadualAutokm;

    @Column(name = "dt_contrato")
    private LocalDate dtContrato;

    @Column(name = "dt_ultima_atualizacao")
    private LocalDate dtUltimaAtualizacao;

    @Column(name = "dt_vencimento_fixo")
    private LocalDate dtVencimentoFixo;

    @Column(name = "dt_vencimento_limite")
    private LocalDate dtVencimentoLimite;

    @Column(name = "dt_vencimento_limite_novo")
    private LocalDate dtVencimentoLimiteNovo;

    @Column(name = "email_nfe", length = 1020)
    private String emailNfe;

    @Column(name = "fax_cobranca")
    private String faxCobranca;

    @Column(name = "forma_pagamento_faturamento", length = 32767)
    private String formaPagamentoFaturamento;

    @Column(name = "gera_boleto")
    private Integer geraBoleto;

    @Column(name = "gera_nf")
    private Integer geraNf;

    @Column(name = "higia_pr_desconto")
    private String higiaPrDesconto;

    @Column(name = "higia_tipo_vendedor")
    private String higiaTipoVendedor;

    @Column(name = "id_canal_venda_padrao")
    private Integer idCanalVendaPadrao;

    @Column(name = "imposto_renda")
    private String impostoRenda;

    @Column(name = "imprime_boleto_registrado")
    private String imprimeBoletoRegistrado;

    @Column(name = "imprime_endereco_cobranca_no_boleto")
    private String imprimeEnderecoCobrancaNoBoleto;

    @Column(name = "imprime_endereco_entrega_na_nf")
    private String imprimeEnderecoEntregaNaNf;

    @Column(name = "insc_municipal")
    private String inscMunicipal;

    @Column(name = "inscricao_cobranca")
    private String inscricaoCobranca;

    @Column(name = "inscricao_produtor")
    private String inscricaoProdutor;

    @Column(name = "lat")
    private BigDecimal lat;

    @Column(name = "lng")
    private BigDecimal lng;

    @Column(name = "mae_conjuge")
    private String maeConjuge;

    @Column(name = "maior_compra_ref_comercial1")
    private BigDecimal maiorCompraRefComercial1;

    @Column(name = "maior_compra_ref_comercial2")
    private BigDecimal maiorCompraRefComercial2;

    @Column(name = "maior_compra_ref_comercial3")
    private BigDecimal maiorCompraRefComercial3;

    @Column(name = "matricula_imovel")
    private String matriculaImovel;

    @Column(name = "nacionalidade")
    private String nacionalidade;

    @Column(name = "nm_banco_financiamento01")
    private String nmBancoFinanciamento01;

    @Column(name = "nm_banco_financiamento02")
    private String nmBancoFinanciamento02;

    @Column(name = "nome_ref_comercial1")
    private String nomeRefComercial1;

    @Column(name = "nome_ref_comercial2")
    private String nomeRefComercial2;

    @Column(name = "nome_ref_comercial3")
    private String nomeRefComercial3;

    @Column(name = "nome_ref_pessoal3")
    private String nomeRefPessoal3;

    @Column(name = "nr_cartao_fidelidade")
    private String nrCartaoFidelidade;

    @Column(name = "nr_dependentes")
    private Integer nrDependentes;

    @Column(name = "nro_endereco")
    private String nroEndereco;

    @Column(name = "obs2", length = 32767)
    private String obs2;

    @Column(name = "orgao_expedidor")
    private String orgaoExpedidor;

    @Column(name = "orgao_expedidor_conjuge")
    private String orgaoExpedidorConjuge;

    @Column(name = "outra_fonte_renda01")
    private String outraFonteRenda01;

    @Column(name = "outra_fonte_renda02")
    private String outraFonteRenda02;

    @Column(name = "padrao_venda_presencial")
    private Integer padraoVendaPresencial;

    @Column(name = "pai_conjuge")
    private String paiConjuge;

    @Column(name = "pr_desconto")
    private BigDecimal prDesconto;

    @Column(name = "pr_desconto_automatico")
    private BigDecimal prDescontoAutomatico;

    @Column(name = "pr_juro_diario")
    private BigDecimal prJuroDiario;

    @Column(name = "primeira_compra_ref_comercial1")
    private LocalDate primeiraCompraRefComercial1;

    @Column(name = "primeira_compra_ref_comercial2")
    private LocalDate primeiraCompraRefComercial2;

    @Column(name = "primeira_compra_ref_comercial3")
    private LocalDate primeiraCompraRefComercial3;

    @Column(name = "qt_compras_ref_comercial1")
    private Integer qtComprasRefComercial1;

    @Column(name = "qt_compras_ref_comercial2")
    private Integer qtComprasRefComercial2;

    @Column(name = "qt_compras_ref_comercial3")
    private Integer qtComprasRefComercial3;

    @Column(name = "razao_cobranca")
    private String razaoCobranca;

    @Column(name = "regime_casamento")
    private String regimeCasamento;

    @Column(name = "regime_tributacao")
    private String regimeTributacao;

    @Column(name = "retem_iss")
    private String retemIss;

    @Column(name = "revenda")
    private String revenda;

    @Column(name = "rg_cliente")
    private String rgCliente;

    @Column(name = "sexo")
    private String sexo;

    @Column(name = "status_transmissao")
    private String statusTransmissao;

    @Column(name = "suframa_icms")
    private String suframaIcms;

    @Column(name = "suframa_ipi")
    private String suframaIpi;

    @Column(name = "sync_asaas")
    private Integer syncAsaas;

    @Column(name = "tel_assistencia_tecnica01")
    private String telAssistenciaTecnica01;

    @Column(name = "tel_assistencia_tecnica02")
    private String telAssistenciaTecnica02;

    @Column(name = "tempo_residencial_atual")
    private String tempoResidencialAtual;

    @Column(name = "tipo_cobranca")
    private String tipoCobranca;

    @Column(name = "tipo_contribuinte")
    private Integer tipoContribuinte;

    @Column(name = "tipo_financiamento01")
    private String tipoFinanciamento01;

    @Column(name = "tipo_financiamento02")
    private String tipoFinanciamento02;

    @Column(name = "ultima_compra_ref_comercial1")
    private LocalDate ultimaCompraRefComercial1;

    @Column(name = "ultima_compra_ref_comercial2")
    private LocalDate ultimaCompraRefComercial2;

    @Column(name = "ultima_compra_ref_comercial3")
    private LocalDate ultimaCompraRefComercial3;

    @Column(name = "validade_contrato")
    private String validadeContrato;

    @Column(name = "vencimento_contrato")
    private String vencimentoContrato;

    @Column(name = "vinculo_ref_pessoa1")
    private String vinculoRefPessoa1;

    @Column(name = "vinculo_ref_pessoa2")
    private String vinculoRefPessoa2;

    @Column(name = "vinculo_ref_pessoa3")
    private String vinculoRefPessoa3;

    @Column(name = "visualiza_ultimo_preco")
    private String visualizaUltimoPreco;

    @Column(name = "vl_limite_fixo")
    private BigDecimal vlLimiteFixo;

    @Column(name = "vl_mensal_contrato")
    private BigDecimal vlMensalContrato;

    @Column(name = "vl_outra_fonte_renda01")
    private BigDecimal vlOutraFonteRenda01;

    @Column(name = "vl_outra_fonte_renda02")
    private BigDecimal vlOutraFonteRenda02;

@Column(name = "cd_cidade_avalista")
private Integer cdCidadeAvalista;

@Column(name = "cd_cidade_avalista02")
private Integer cdCidadeAvalista02;

@Column(name = "celular_avalista02")
private String celularAvalista02;

@Column(name = "conta_contabil")
private String contaContabil;

@Column(name = "cpf_avalista02")
private String cpfAvalista02;

@Column(name = "empresa_avalista02")
private String empresaAvalista02;

@Column(name = "endereco_avalista")
private String enderecoAvalista;

@Column(name = "endereco_avalista02")
private String enderecoAvalista02;

@Column(name = "fone_avalista02")
private String foneAvalista02;

@Column(name = "fone_ref_comercial1")
private String foneRefComercial1;

@Column(name = "fone_ref_comercial2")
private String foneRefComercial2;

@Column(name = "fone_ref_comercial3")
private String foneRefComercial3;

@Column(name = "nome_avalista02")
private String nomeAvalista02;

@Column(name = "nro_endereco_ref_pessoal3")
private String nroEnderecoRefPessoal3;

@Column(name = "rg_avalista02")
private String rgAvalista02;


@Column(name = "endereco_ref_pessoal3")
private String enderecoRefPessoal3;

@Column(name = "cd_cidade_ref_pessoal3")
private Integer cdCidadeRefPessoal3;

@Column(name = "bairro_ref_pessoal3")
private String bairroRefPessoal3;

@Column(name = "fone_ref_pessoal3")
private String foneRefPessoal3;

@Column(name = "complemento_ref_pessoal3")
private String complementoRefPessoal3;


@Column(name = "proximo")
private String proximo;

// Getter e Setter
public String getProximo() {
    return proximo;
}

public void setProximo(String proximo) {
    this.proximo = proximo;
}

// Getters e Setters
public String getEnderecoRefPessoal3() {
    return enderecoRefPessoal3;
}

public void setEnderecoRefPessoal3(String enderecoRefPessoal3) {
    this.enderecoRefPessoal3 = enderecoRefPessoal3;
}

public Integer getCdCidadeRefPessoal3() {
    return cdCidadeRefPessoal3;
}

public void setCdCidadeRefPessoal3(Integer cdCidadeRefPessoal3) {
    this.cdCidadeRefPessoal3 = cdCidadeRefPessoal3;
}

public String getBairroRefPessoal3() {
    return bairroRefPessoal3;
}

public void setBairroRefPessoal3(String bairroRefPessoal3) {
    this.bairroRefPessoal3 = bairroRefPessoal3;
}

public String getFoneRefPessoal3() {
    return foneRefPessoal3;
}

public void setFoneRefPessoal3(String foneRefPessoal3) {
    this.foneRefPessoal3 = foneRefPessoal3;
}

public String getComplementoRefPessoal3() {
    return complementoRefPessoal3;
}

public void setComplementoRefPessoal3(String complementoRefPessoal3) {
    this.complementoRefPessoal3 = complementoRefPessoal3;
}



    // Getters delegados do ID
    public Integer getCdCliente() {
        return id != null ? id.getCdCliente() : null;
    }

    public void setCdCliente(Integer cdCliente) {
        if (id == null) id = new ClienteId();
        id.setCdCliente(cdCliente);
    }

    public Long getCdEmpresa() {
        return id != null ? id.getCdEmpresa() : null;
    }

    public void setCdEmpresa(Long cdEmpresa) {
        if (id == null) id = new ClienteId();
        id.setCdEmpresa(cdEmpresa);
    }

    // Getters e Setters padrão
    public ClienteId getId() {
        return id;
    }

    public void setId(ClienteId id) {
        this.id = id;
    }

    public String getNmCliente() {
        return nmCliente;
    }

    public void setNmCliente(String nmCliente) {
        this.nmCliente = nmCliente;
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

    public Integer getCdCidade() {
        return cdCidade;
    }

    public void setCdCidade(Integer cdCidade) {
        this.cdCidade = cdCidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public LocalDate getDtNasc() {
        return dtNasc;
    }

    public void setDtNasc(LocalDate dtNasc) {
        this.dtNasc = dtNasc;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getFiliacaoPai() {
        return filiacaoPai;
    }

    public void setFiliacaoPai(String filiacaoPai) {
        this.filiacaoPai = filiacaoPai;
    }

    public String getFiliacaoMae() {
        return filiacaoMae;
    }

    public void setFiliacaoMae(String filiacaoMae) {
        this.filiacaoMae = filiacaoMae;
    }

    public String getNaturalidade() {
        return naturalidade;
    }

    public void setNaturalidade(String naturalidade) {
        this.naturalidade = naturalidade;
    }

    public String getResiAnte() {
        return resiAnte;
    }

    public void setResiAnte(String resiAnte) {
        this.resiAnte = resiAnte;
    }

    public LocalDate getDataResi() {
        return dataResi;
    }

    public void setDataResi(LocalDate dataResi) {
        this.dataResi = dataResi;
    }

    public String getTipoResi() {
        return tipoResi;
    }

    public void setTipoResi(String tipoResi) {
        this.tipoResi = tipoResi;
    }

    public BigDecimal getValorAluguel() {
        return valorAluguel;
    }

    public void setValorAluguel(BigDecimal valorAluguel) {
        this.valorAluguel = valorAluguel;
    }

    public LocalDate getDataDigitacao() {
        return dataDigitacao;
    }

    public void setDataDigitacao(LocalDate dataDigitacao) {
        this.dataDigitacao = dataDigitacao;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getConjuge() {
        return conjuge;
    }

    public void setConjuge(String conjuge) {
        this.conjuge = conjuge;
    }

    public LocalDate getDtConjuge() {
        return dtConjuge;
    }

    public void setDtConjuge(LocalDate dtConjuge) {
        this.dtConjuge = dtConjuge;
    }

    public String getCpfConjuge() {
        return cpfConjuge;
    }

    public void setCpfConjuge(String cpfConjuge) {
        this.cpfConjuge = cpfConjuge;
    }

    public String getRgConjuge() {
        return rgConjuge;
    }

    public void setRgConjuge(String rgConjuge) {
        this.rgConjuge = rgConjuge;
    }

    public String getAtividadeConj() {
        return atividadeConj;
    }

    public void setAtividadeConj(String atividadeConj) {
        this.atividadeConj = atividadeConj;
    }

    public Integer getDep() {
        return dep;
    }

    public void setDep(Integer dep) {
        this.dep = dep;
    }

    public BigDecimal getRendaConjuge() {
        return rendaConjuge;
    }

    public void setRendaConjuge(BigDecimal rendaConjuge) {
        this.rendaConjuge = rendaConjuge;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getEndCome() {
        return endCome;
    }

    public void setEndCome(String endCome) {
        this.endCome = endCome;
    }

    public String getBairroCome() {
        return bairroCome;
    }

    public void setBairroCome(String bairroCome) {
        this.bairroCome = bairroCome;
    }

    public Integer getCdCidadeCome() {
        return cdCidadeCome;
    }

    public void setCdCidadeCome(Integer cdCidadeCome) {
        this.cdCidadeCome = cdCidadeCome;
    }

    public String getUfCome() {
        return ufCome;
    }

    public void setUfCome(String ufCome) {
        this.ufCome = ufCome;
    }

    public String getCepcome() {
        return cepcome;
    }

    public void setCepcome(String cepcome) {
        this.cepcome = cepcome;
    }

    public String getFoneCome() {
        return foneCome;
    }

    public void setFoneCome(String foneCome) {
        this.foneCome = foneCome;
    }

    public String getFaxCome() {
        return faxCome;
    }

    public void setFaxCome(String faxCome) {
        this.faxCome = faxCome;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public LocalDate getDtAdmissao() {
        return dtAdmissao;
    }

    public void setDtAdmissao(LocalDate dtAdmissao) {
        this.dtAdmissao = dtAdmissao;
    }

    public BigDecimal getRenda() {
        return renda;
    }

    public void setRenda(BigDecimal renda) {
        this.renda = renda;
    }

    public BigDecimal getOutrasRendas() {
        return outrasRendas;
    }

    public void setOutrasRendas(BigDecimal outrasRendas) {
        this.outrasRendas = outrasRendas;
    }

    public String getNomeAvalista() {
        return nomeAvalista;
    }

    public void setNomeAvalista(String nomeAvalista) {
        this.nomeAvalista = nomeAvalista;
    }

    public LocalDate getDataNascAvali() {
        return dataNascAvali;
    }

    public void setDataNascAvali(LocalDate dataNascAvali) {
        this.dataNascAvali = dataNascAvali;
    }

    public String getCpfAvalista() {
        return cpfAvalista;
    }

    public void setCpfAvalista(String cpfAvalista) {
        this.cpfAvalista = cpfAvalista;
    }

    public String getRgAvalista() {
        return rgAvalista;
    }

    public void setRgAvalista(String rgAvalista) {
        this.rgAvalista = rgAvalista;
    }

    public String getFoneAvalista() {
        return foneAvalista;
    }

    public void setFoneAvalista(String foneAvalista) {
        this.foneAvalista = foneAvalista;
    }

    public String getCelularAvalista() {
        return celularAvalista;
    }

    public void setCelularAvalista(String celularAvalista) {
        this.celularAvalista = celularAvalista;
    }

    public String getEmpresaAvalista() {
        return empresaAvalista;
    }

    public void setEmpresaAvalista(String empresaAvalista) {
        this.empresaAvalista = empresaAvalista;
    }

    public String getFoneCoAvalista() {
        return foneCoAvalista;
    }

    public void setFoneCoAvalista(String foneCoAvalista) {
        this.foneCoAvalista = foneCoAvalista;
    }

    public Integer getCdCategoria() {
        return cdCategoria;
    }

    public void setCdCategoria(Integer cdCategoria) {
        this.cdCategoria = cdCategoria;
    }

    public String getObs1() {
        return obs1;
    }

    public void setObs1(String obs1) {
        this.obs1 = obs1;
    }

    public String getSpc() {
        return spc;
    }

    public void setSpc(String spc) {
        this.spc = spc;
    }

    public String getRazao() {
        return razao;
    }

    public void setRazao(String razao) {
        this.razao = razao;
    }

    public String getCgc() {
        return cgc;
    }

    public void setCgc(String cgc) {
        this.cgc = cgc;
    }

    public String getInscricao() {
        return inscricao;
    }

    public void setInscricao(String inscricao) {
        this.inscricao = inscricao;
    }

    public BigDecimal getLimite() {
        return limite;
    }

    public void setLimite(BigDecimal limite) {
        this.limite = limite;
    }

    public BigDecimal getLimite1() {
        return limite1;
    }

    public void setLimite1(BigDecimal limite1) {
        this.limite1 = limite1;
    }

    public Integer getCdRamo() {
        return cdRamo;
    }

    public void setCdRamo(Integer cdRamo) {
        this.cdRamo = cdRamo;
    }

    public String getFoneConjuge() {
        return foneConjuge;
    }

    public void setFoneConjuge(String foneConjuge) {
        this.foneConjuge = foneConjuge;
    }

    public Integer getNrTabela() {
        return nrTabela;
    }

    public void setNrTabela(Integer nrTabela) {
        this.nrTabela = nrTabela;
    }

    public String getSuframa() {
        return suframa;
    }

    public void setSuframa(String suframa) {
        this.suframa = suframa;
    }

    public String getEnderecoCobranca() {
        return enderecoCobranca;
    }

    public void setEnderecoCobranca(String enderecoCobranca) {
        this.enderecoCobranca = enderecoCobranca;
    }

    public Integer getCdCidadeCobranca() {
        return cdCidadeCobranca;
    }

    public void setCdCidadeCobranca(Integer cdCidadeCobranca) {
        this.cdCidadeCobranca = cdCidadeCobranca;
    }

    public String getBairroCobranca() {
        return bairroCobranca;
    }

    public void setBairroCobranca(String bairroCobranca) {
        this.bairroCobranca = bairroCobranca;
    }

    public String getProximidadeCobranca() {
        return proximidadeCobranca;
    }

    public void setProximidadeCobranca(String proximidadeCobranca) {
        this.proximidadeCobranca = proximidadeCobranca;
    }

    public String getCepCobranca() {
        return cepCobranca;
    }

    public void setCepCobranca(String cepCobranca) {
        this.cepCobranca = cepCobranca;
    }

    public String getFoneCobranca() {
        return foneCobranca;
    }

    public void setFoneCobranca(String foneCobranca) {
        this.foneCobranca = foneCobranca;
    }

    public Integer getNroEnderecoCobranca() {
        return nroEnderecoCobranca;
    }

    public void setNroEnderecoCobranca(Integer nroEnderecoCobranca) {
        this.nroEnderecoCobranca = nroEnderecoCobranca;
    }

    public String getComplementoCobranca() {
        return complementoCobranca;
    }

    public void setComplementoCobranca(String complementoCobranca) {
        this.complementoCobranca = complementoCobranca;
    }

    public String getEnderecoEntrega() {
        return enderecoEntrega;
    }

    public void setEnderecoEntrega(String enderecoEntrega) {
        this.enderecoEntrega = enderecoEntrega;
    }

    public Integer getCdCidadeEntrega() {
        return cdCidadeEntrega;
    }

    public void setCdCidadeEntrega(Integer cdCidadeEntrega) {
        this.cdCidadeEntrega = cdCidadeEntrega;
    }

    public String getBairroEntrega() {
        return bairroEntrega;
    }

    public void setBairroEntrega(String bairroEntrega) {
        this.bairroEntrega = bairroEntrega;
    }

    public String getProximidadeEntrega() {
        return proximidadeEntrega;
    }

    public void setProximidadeEntrega(String proximidadeEntrega) {
        this.proximidadeEntrega = proximidadeEntrega;
    }

    public String getCepEntrega() {
        return cepEntrega;
    }

    public void setCepEntrega(String cepEntrega) {
        this.cepEntrega = cepEntrega;
    }

    public String getFoneEntrega() {
        return foneEntrega;
    }

    public void setFoneEntrega(String foneEntrega) {
        this.foneEntrega = foneEntrega;
    }

    public Integer getNroEnderecoEntrega() {
        return nroEnderecoEntrega;
    }

    public void setNroEnderecoEntrega(Integer nroEnderecoEntrega) {
        this.nroEnderecoEntrega = nroEnderecoEntrega;
    }

    public String getComplementoEntrega() {
        return complementoEntrega;
    }

    public void setComplementoEntrega(String complementoEntrega) {
        this.complementoEntrega = complementoEntrega;
    }

    public Integer getCdFuncionario() {
        return cdFuncionario;
    }

    public void setCdFuncionario(Integer cdFuncionario) {
        this.cdFuncionario = cdFuncionario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public BigDecimal getPrDescontoVendedor() {
        return prDescontoVendedor;
    }

    public void setPrDescontoVendedor(BigDecimal prDescontoVendedor) {
        this.prDescontoVendedor = prDescontoVendedor;
    }

    public BigDecimal getPrDescontoGerente() {
        return prDescontoGerente;
    }

    public void setPrDescontoGerente(BigDecimal prDescontoGerente) {
        this.prDescontoGerente = prDescontoGerente;
    }

    public LocalDate getDtNascSocio1() {
        return dtNascSocio1;
    }

    public void setDtNascSocio1(LocalDate dtNascSocio1) {
        this.dtNascSocio1 = dtNascSocio1;
    }

    public LocalDate getDtNascSocio2() {
        return dtNascSocio2;
    }

    public void setDtNascSocio2(LocalDate dtNascSocio2) {
        this.dtNascSocio2 = dtNascSocio2;
    }

    public String getNmUsuario() {
        return nmUsuario;
    }

    public void setNmUsuario(String nmUsuario) {
        this.nmUsuario = nmUsuario;
    }

    public String getNomeRefPessoal1() {
        return nomeRefPessoal1;
    }

    public void setNomeRefPessoal1(String nomeRefPessoal1) {
        this.nomeRefPessoal1 = nomeRefPessoal1;
    }

    public String getEnderecoRefPessoal1() {
        return enderecoRefPessoal1;
    }

    public void setEnderecoRefPessoal1(String enderecoRefPessoal1) {
        this.enderecoRefPessoal1 = enderecoRefPessoal1;
    }

    public Integer getNroEnderecoRefPessoal1() {
        return nroEnderecoRefPessoal1;
    }

    public void setNroEnderecoRefPessoal1(Integer nroEnderecoRefPessoal1) {
        this.nroEnderecoRefPessoal1 = nroEnderecoRefPessoal1;
    }

    public String getComplementoRefPessoal1() {
        return complementoRefPessoal1;
    }

    public void setComplementoRefPessoal1(String complementoRefPessoal1) {
        this.complementoRefPessoal1 = complementoRefPessoal1;
    }

    public String getBairroRefPessoal1() {
        return bairroRefPessoal1;
    }

    public void setBairroRefPessoal1(String bairroRefPessoal1) {
        this.bairroRefPessoal1 = bairroRefPessoal1;
    }

    public Integer getCdCidadeRefPessoal1() {
        return cdCidadeRefPessoal1;
    }

    public void setCdCidadeRefPessoal1(Integer cdCidadeRefPessoal1) {
        this.cdCidadeRefPessoal1 = cdCidadeRefPessoal1;
    }

    public String getCepRefPessoal1() {
        return cepRefPessoal1;
    }

    public void setCepRefPessoal1(String cepRefPessoal1) {
        this.cepRefPessoal1 = cepRefPessoal1;
    }

    public String getFoneRefPessoal1() {
        return foneRefPessoal1;
    }

    public void setFoneRefPessoal1(String foneRefPessoal1) {
        this.foneRefPessoal1 = foneRefPessoal1;
    }

    public String getNomeRefPessoal2() {
        return nomeRefPessoal2;
    }

    public void setNomeRefPessoal2(String nomeRefPessoal2) {
        this.nomeRefPessoal2 = nomeRefPessoal2;
    }

    public String getEnderecoRefPessoal2() {
        return enderecoRefPessoal2;
    }

    public void setEnderecoRefPessoal2(String enderecoRefPessoal2) {
        this.enderecoRefPessoal2 = enderecoRefPessoal2;
    }

    public Integer getNroEnderecoRefPessoal2() {
        return nroEnderecoRefPessoal2;
    }

    public void setNroEnderecoRefPessoal2(Integer nroEnderecoRefPessoal2) {
        this.nroEnderecoRefPessoal2 = nroEnderecoRefPessoal2;
    }

    public String getComplementoRefPessoal2() {
        return complementoRefPessoal2;
    }

    public void setComplementoRefPessoal2(String complementoRefPessoal2) {
        this.complementoRefPessoal2 = complementoRefPessoal2;
    }

    public String getBairroRefPessoal2() {
        return bairroRefPessoal2;
    }

    public void setBairroRefPessoal2(String bairroRefPessoal2) {
        this.bairroRefPessoal2 = bairroRefPessoal2;
    }

    public Integer getCdCidadeRefPessoal2() {
        return cdCidadeRefPessoal2;
    }

    public void setCdCidadeRefPessoal2(Integer cdCidadeRefPessoal2) {
        this.cdCidadeRefPessoal2 = cdCidadeRefPessoal2;
    }

    public String getCepRefPessoal2() {
        return cepRefPessoal2;
    }

    public void setCepRefPessoal2(String cepRefPessoal2) {
        this.cepRefPessoal2 = cepRefPessoal2;
    }

    public String getFoneRefPessoal2() {
        return foneRefPessoal2;
    }

    public void setFoneRefPessoal2(String foneRefPessoal2) {
        this.foneRefPessoal2 = foneRefPessoal2;
    }

    public String getAgenciaCidadeBancoFinanciamento01() {
        return agenciaCidadeBancoFinanciamento01;
    }

    public void setAgenciaCidadeBancoFinanciamento01(String agenciaCidadeBancoFinanciamento01) {
        this.agenciaCidadeBancoFinanciamento01 = agenciaCidadeBancoFinanciamento01;
    }

    public String getAgenciaCidadeBancoFinanciamento02() {
        return agenciaCidadeBancoFinanciamento02;
    }

    public void setAgenciaCidadeBancoFinanciamento02(String agenciaCidadeBancoFinanciamento02) {
        this.agenciaCidadeBancoFinanciamento02 = agenciaCidadeBancoFinanciamento02;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getAreaPlantada() {
        return areaPlantada;
    }

    public void setAreaPlantada(String areaPlantada) {
        this.areaPlantada = areaPlantada;
    }

    public String getAsaasId() {
        return asaasId;
    }

    public void setAsaasId(String asaasId) {
        this.asaasId = asaasId;
    }

    public String getAssistenciaTecnica01() {
        return assistenciaTecnica01;
    }

    public void setAssistenciaTecnica01(String assistenciaTecnica01) {
        this.assistenciaTecnica01 = assistenciaTecnica01;
    }

    public String getAssistenciaTecnica02() {
        return assistenciaTecnica02;
    }

    public void setAssistenciaTecnica02(String assistenciaTecnica02) {
        this.assistenciaTecnica02 = assistenciaTecnica02;
    }

    public String getBancoFinanciamentoAgencia01() {
        return bancoFinanciamentoAgencia01;
    }

    public void setBancoFinanciamentoAgencia01(String bancoFinanciamentoAgencia01) {
        this.bancoFinanciamentoAgencia01 = bancoFinanciamentoAgencia01;
    }

    public String getBancoFinanciamentoAgencia02() {
        return bancoFinanciamentoAgencia02;
    }

    public void setBancoFinanciamentoAgencia02(String bancoFinanciamentoAgencia02) {
        this.bancoFinanciamentoAgencia02 = bancoFinanciamentoAgencia02;
    }

    public String getBancoFinanciamentoAgencia03() {
        return bancoFinanciamentoAgencia03;
    }

    public void setBancoFinanciamentoAgencia03(String bancoFinanciamentoAgencia03) {
        this.bancoFinanciamentoAgencia03 = bancoFinanciamentoAgencia03;
    }

    public String getBancoFinanciamento01() {
        return bancoFinanciamento01;
    }

    public void setBancoFinanciamento01(String bancoFinanciamento01) {
        this.bancoFinanciamento01 = bancoFinanciamento01;
    }

    public String getBancoFinanciamento02() {
        return bancoFinanciamento02;
    }

    public void setBancoFinanciamento02(String bancoFinanciamento02) {
        this.bancoFinanciamento02 = bancoFinanciamento02;
    }

    public String getBancoFinanciamento03() {
        return bancoFinanciamento03;
    }

    public void setBancoFinanciamento03(String bancoFinanciamento03) {
        this.bancoFinanciamento03 = bancoFinanciamento03;
    }

    public String getBancoTipoFinanciamento01() {
        return bancoTipoFinanciamento01;
    }

    public void setBancoTipoFinanciamento01(String bancoTipoFinanciamento01) {
        this.bancoTipoFinanciamento01 = bancoTipoFinanciamento01;
    }

    public String getBancoTipoFinanciamento02() {
        return bancoTipoFinanciamento02;
    }

    public void setBancoTipoFinanciamento02(String bancoTipoFinanciamento02) {
        this.bancoTipoFinanciamento02 = bancoTipoFinanciamento02;
    }

    public String getBancoTipoFinanciamento03() {
        return bancoTipoFinanciamento03;
    }

    public void setBancoTipoFinanciamento03(String bancoTipoFinanciamento03) {
        this.bancoTipoFinanciamento03 = bancoTipoFinanciamento03;
    }

    public String getBlocoProdutor() {
        return blocoProdutor;
    }

    public void setBlocoProdutor(String blocoProdutor) {
        this.blocoProdutor = blocoProdutor;
    }

    public String getCaminhoFoto() {
        return caminhoFoto;
    }

    public void setCaminhoFoto(String caminhoFoto) {
        this.caminhoFoto = caminhoFoto;
    }

    public Integer getCdCondicaoPadrao() {
        return cdCondicaoPadrao;
    }

    public void setCdCondicaoPadrao(Integer cdCondicaoPadrao) {
        this.cdCondicaoPadrao = cdCondicaoPadrao;
    }

    public Integer getCdContabil() {
        return cdContabil;
    }

    public void setCdContabil(Integer cdContabil) {
        this.cdContabil = cdContabil;
    }

    public String getCdContrato() {
        return cdContrato;
    }

    public void setCdContrato(String cdContrato) {
        this.cdContrato = cdContrato;
    }

    public Integer getCdFormaPagamento() {
        return cdFormaPagamento;
    }

    public void setCdFormaPagamento(Integer cdFormaPagamento) {
        this.cdFormaPagamento = cdFormaPagamento;
    }

    public Integer getCdRegiao() {
        return cdRegiao;
    }

    public void setCdRegiao(Integer cdRegiao) {
        this.cdRegiao = cdRegiao;
    }

    public Integer getCdTransportadoraPadrao() {
        return cdTransportadoraPadrao;
    }

    public void setCdTransportadoraPadrao(Integer cdTransportadoraPadrao) {
        this.cdTransportadoraPadrao = cdTransportadoraPadrao;
    }

    public String getCepRefPessoal3() {
        return cepRefPessoal3;
    }

    public void setCepRefPessoal3(String cepRefPessoal3) {
        this.cepRefPessoal3 = cepRefPessoal3;
    }

    public String getCgcCobranca() {
        return cgcCobranca;
    }

    public void setCgcCobranca(String cgcCobranca) {
        this.cgcCobranca = cgcCobranca;
    }

    public Integer getCidascLocalidade() {
        return cidascLocalidade;
    }

    public void setCidascLocalidade(Integer cidascLocalidade) {
        this.cidascLocalidade = cidascLocalidade;
    }

    public Integer getCloudId() {
        return cloudId;
    }

    public void setCloudId(Integer cloudId) {
        this.cloudId = cloudId;
    }

    public String getCobrarTaxaNoBoleto() {
        return cobrarTaxaNoBoleto;
    }

    public void setCobrarTaxaNoBoleto(String cobrarTaxaNoBoleto) {
        this.cobrarTaxaNoBoleto = cobrarTaxaNoBoleto;
    }

    public String getCompex() {
        return compex;
    }

    public void setCompex(String compex) {
        this.compex = compex;
    }

    public BigDecimal getCompexIcms() {
        return compexIcms;
    }

    public void setCompexIcms(BigDecimal compexIcms) {
        this.compexIcms = compexIcms;
    }

    public BigDecimal getCompexOutros() {
        return compexOutros;
    }

    public void setCompexOutros(BigDecimal compexOutros) {
        this.compexOutros = compexOutros;
    }

    public String getCompexTexto() {
        return compexTexto;
    }

    public void setCompexTexto(String compexTexto) {
        this.compexTexto = compexTexto;
    }

    public String getCompexTexto02() {
        return compexTexto02;
    }

    public void setCompexTexto02(String compexTexto02) {
        this.compexTexto02 = compexTexto02;
    }

    public String getContatoCargo1() {
        return contatoCargo1;
    }

    public void setContatoCargo1(String contatoCargo1) {
        this.contatoCargo1 = contatoCargo1;
    }

    public String getContatoCargo2() {
        return contatoCargo2;
    }

    public void setContatoCargo2(String contatoCargo2) {
        this.contatoCargo2 = contatoCargo2;
    }

    public String getContatoCargo3() {
        return contatoCargo3;
    }

    public void setContatoCargo3(String contatoCargo3) {
        this.contatoCargo3 = contatoCargo3;
    }

    public String getContatoEmail1() {
        return contatoEmail1;
    }

    public void setContatoEmail1(String contatoEmail1) {
        this.contatoEmail1 = contatoEmail1;
    }

    public String getContatoEmail2() {
        return contatoEmail2;
    }

    public void setContatoEmail2(String contatoEmail2) {
        this.contatoEmail2 = contatoEmail2;
    }

    public String getContatoEmail3() {
        return contatoEmail3;
    }

    public void setContatoEmail3(String contatoEmail3) {
        this.contatoEmail3 = contatoEmail3;
    }

    public String getContatoFone1() {
        return contatoFone1;
    }

    public void setContatoFone1(String contatoFone1) {
        this.contatoFone1 = contatoFone1;
    }

    public String getContatoFone2() {
        return contatoFone2;
    }

    public void setContatoFone2(String contatoFone2) {
        this.contatoFone2 = contatoFone2;
    }

    public String getContatoFone3() {
        return contatoFone3;
    }

    public void setContatoFone3(String contatoFone3) {
        this.contatoFone3 = contatoFone3;
    }

    public String getContatoNome1() {
        return contatoNome1;
    }

    public void setContatoNome1(String contatoNome1) {
        this.contatoNome1 = contatoNome1;
    }

    public String getContatoNome2() {
        return contatoNome2;
    }

    public void setContatoNome2(String contatoNome2) {
        this.contatoNome2 = contatoNome2;
    }

    public String getContatoNome3() {
        return contatoNome3;
    }

    public void setContatoNome3(String contatoNome3) {
        this.contatoNome3 = contatoNome3;
    }

    public String getContratoArrendatario() {
        return contratoArrendatario;
    }

    public void setContratoArrendatario(String contratoArrendatario) {
        this.contratoArrendatario = contratoArrendatario;
    }

    public String getContratoParceria() {
        return contratoParceria;
    }

    public void setContratoParceria(String contratoParceria) {
        this.contratoParceria = contratoParceria;
    }

    public String getCooperado() {
        return cooperado;
    }

    public void setCooperado(String cooperado) {
        this.cooperado = cooperado;
    }

    public String getCopiaRgAvalista() {
        return copiaRgAvalista;
    }

    public void setCopiaRgAvalista(String copiaRgAvalista) {
        this.copiaRgAvalista = copiaRgAvalista;
    }

    public String getCopiaRgCliente() {
        return copiaRgCliente;
    }

    public void setCopiaRgCliente(String copiaRgCliente){
        this.copiaRgCliente = copiaRgCliente;
    }	
	
 
    // Getters e Setters para os campos continuados
    public String getCopiaRgConjuge() {
        return copiaRgConjuge;
    }

    public void setCopiaRgConjuge(String copiaRgConjuge) {
        this.copiaRgConjuge = copiaRgConjuge;
    }

    public String getCpfRegularizados() {
        return cpfRegularizados;
    }

    public void setCpfRegularizados(String cpfRegularizados) {
        this.cpfRegularizados = cpfRegularizados;
    }

    public LocalDate getDataNasciAvali02() {
        return dataNasciAvali02;
    }

    public void setDataNasciAvali02(LocalDate dataNasciAvali02) {
        this.dataNasciAvali02 = dataNasciAvali02;
    }

    public String getDebitoContaAgencia() {
        return debitoContaAgencia;
    }

    public void setDebitoContaAgencia(String debitoContaAgencia) {
        this.debitoContaAgencia = debitoContaAgencia;
    }

    public String getDebitoContaBanco() {
        return debitoContaBanco;
    }

    public void setDebitoContaBanco(String debitoContaBanco) {
        this.debitoContaBanco = debitoContaBanco;
    }

    public String getDebitoContaContaCorrente() {
        return debitoContaContaCorrente;
    }

    public void setDebitoContaContaCorrente(String debitoContaContaCorrente) {
        this.debitoContaContaCorrente = debitoContaContaCorrente;
    }

    public String getDebitoContaRazao() {
        return debitoContaRazao;
    }

    public void setDebitoContaRazao(String debitoContaRazao) {
        this.debitoContaRazao = debitoContaRazao;
    }

    public String getDestacaIcms() {
        return destacaIcms;
    }

    public void setDestacaIcms(String destacaIcms) {
        this.destacaIcms = destacaIcms;
    }

    public Integer getDiaVencimentoTodoMes() {
        return diaVencimentoTodoMes;
    }

    public void setDiaVencimentoTodoMes(Integer diaVencimentoTodoMes) {
        this.diaVencimentoTodoMes = diaVencimentoTodoMes;
    }

    public LocalDate getDtAlteracao() {
        return dtAlteracao;
    }

    public void setDtAlteracao(LocalDate dtAlteracao) {
        this.dtAlteracao = dtAlteracao;
    }

    public LocalDate getDtCadastro() {
        return dtCadastro;
    }

    public void setDtCadastro(LocalDate dtCadastro) {
        this.dtCadastro = dtCadastro;
    }

    public LocalDate getDtConsultaInscricaoEstadualAutokm() {
        return dtConsultaInscricaoEstadualAutokm;
    }

    public void setDtConsultaInscricaoEstadualAutokm(LocalDate dtConsultaInscricaoEstadualAutokm) {
        this.dtConsultaInscricaoEstadualAutokm = dtConsultaInscricaoEstadualAutokm;
    }

    public LocalDate getDtContrato() {
        return dtContrato;
    }

    public void setDtContrato(LocalDate dtContrato) {
        this.dtContrato = dtContrato;
    }

    public LocalDate getDtUltimaAtualizacao() {
        return dtUltimaAtualizacao;
    }

    public void setDtUltimaAtualizacao(LocalDate dtUltimaAtualizacao) {
        this.dtUltimaAtualizacao = dtUltimaAtualizacao;
    }

    public LocalDate getDtVencimentoFixo() {
        return dtVencimentoFixo;
    }

    public void setDtVencimentoFixo(LocalDate dtVencimentoFixo) {
        this.dtVencimentoFixo = dtVencimentoFixo;
    }

    public LocalDate getDtVencimentoLimite() {
        return dtVencimentoLimite;
    }

    public void setDtVencimentoLimite(LocalDate dtVencimentoLimite) {
        this.dtVencimentoLimite = dtVencimentoLimite;
    }

    public LocalDate getDtVencimentoLimiteNovo() {
        return dtVencimentoLimiteNovo;
    }

    public void setDtVencimentoLimiteNovo(LocalDate dtVencimentoLimiteNovo) {
        this.dtVencimentoLimiteNovo = dtVencimentoLimiteNovo;
    }

    public String getEmailNfe() {
        return emailNfe;
    }

    public void setEmailNfe(String emailNfe) {
        this.emailNfe = emailNfe;
    }

    public String getFaxCobranca() {
        return faxCobranca;
    }

    public void setFaxCobranca(String faxCobranca) {
        this.faxCobranca = faxCobranca;
    }

    public String getFormaPagamentoFaturamento() {
        return formaPagamentoFaturamento;
    }

    public void setFormaPagamentoFaturamento(String formaPagamentoFaturamento) {
        this.formaPagamentoFaturamento = formaPagamentoFaturamento;
    }

    public Integer getGeraBoleto() {
        return geraBoleto;
    }

    public void setGeraBoleto(Integer geraBoleto) {
        this.geraBoleto = geraBoleto;
    }

    public Integer getGeraNf() {
        return geraNf;
    }

    public void setGeraNf(Integer geraNf) {
        this.geraNf = geraNf;
    }

    public String getHigiaPrDesconto() {
        return higiaPrDesconto;
    }

    public void setHigiaPrDesconto(String higiaPrDesconto) {
        this.higiaPrDesconto = higiaPrDesconto;
    }

    public String getHigiaTipoVendedor() {
        return higiaTipoVendedor;
    }

    public void setHigiaTipoVendedor(String higiaTipoVendedor) {
        this.higiaTipoVendedor = higiaTipoVendedor;
    }

    public Integer getIdCanalVendaPadrao() {
        return idCanalVendaPadrao;
    }

    public void setIdCanalVendaPadrao(Integer idCanalVendaPadrao) {
        this.idCanalVendaPadrao = idCanalVendaPadrao;
    }

    public String getImpostoRenda() {
        return impostoRenda;
    }

    public void setImpostoRenda(String impostoRenda) {
        this.impostoRenda = impostoRenda;
    }

    public String getImprimeBoletoRegistrado() {
        return imprimeBoletoRegistrado;
    }

    public void setImprimeBoletoRegistrado(String imprimeBoletoRegistrado) {
        this.imprimeBoletoRegistrado = imprimeBoletoRegistrado;
    }

    public String getImprimeEnderecoCobrancaNoBoleto() {
        return imprimeEnderecoCobrancaNoBoleto;
    }

    public void setImprimeEnderecoCobrancaNoBoleto(String imprimeEnderecoCobrancaNoBoleto) {
        this.imprimeEnderecoCobrancaNoBoleto = imprimeEnderecoCobrancaNoBoleto;
    }

    public String getImprimeEnderecoEntregaNaNf() {
        return imprimeEnderecoEntregaNaNf;
    }

    public void setImprimeEnderecoEntregaNaNf(String imprimeEnderecoEntregaNaNf) {
        this.imprimeEnderecoEntregaNaNf = imprimeEnderecoEntregaNaNf;
    }

    public String getInscMunicipal() {
        return inscMunicipal;
    }

    public void setInscMunicipal(String inscMunicipal) {
        this.inscMunicipal = inscMunicipal;
    }

    public String getInscricaoCobranca() {
        return inscricaoCobranca;
    }

    public void setInscricaoCobranca(String inscricaoCobranca) {
        this.inscricaoCobranca = inscricaoCobranca;
    }

    public String getInscricaoProdutor() {
        return inscricaoProdutor;
    }

    public void setInscricaoProdutor(String inscricaoProdutor) {
        this.inscricaoProdutor = inscricaoProdutor;
    }

    public BigDecimal getLat() {
        return lat;
    }

    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }

    public BigDecimal getLng() {
        return lng;
    }

    public void setLng(BigDecimal lng) {
        this.lng = lng;
    }

    public String getMaeConjuge() {
        return maeConjuge;
    }

    public void setMaeConjuge(String maeConjuge) {
        this.maeConjuge = maeConjuge;
    }

    public BigDecimal getMaiorCompraRefComercial1() {
        return maiorCompraRefComercial1;
    }

    public void setMaiorCompraRefComercial1(BigDecimal maiorCompraRefComercial1) {
        this.maiorCompraRefComercial1 = maiorCompraRefComercial1;
    }

    public BigDecimal getMaiorCompraRefComercial2() {
        return maiorCompraRefComercial2;
    }

    public void setMaiorCompraRefComercial2(BigDecimal maiorCompraRefComercial2) {
        this.maiorCompraRefComercial2 = maiorCompraRefComercial2;
    }

    public BigDecimal getMaiorCompraRefComercial3() {
        return maiorCompraRefComercial3;
    }

    public void setMaiorCompraRefComercial3(BigDecimal maiorCompraRefComercial3) {
        this.maiorCompraRefComercial3 = maiorCompraRefComercial3;
    }

    public String getMatriculaImovel() {
        return matriculaImovel;
    }

    public void setMatriculaImovel(String matriculaImovel) {
        this.matriculaImovel = matriculaImovel;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public String getNmBancoFinanciamento01() {
        return nmBancoFinanciamento01;
    }

    public void setNmBancoFinanciamento01(String nmBancoFinanciamento01) {
        this.nmBancoFinanciamento01 = nmBancoFinanciamento01;
    }

    public String getNmBancoFinanciamento02() {
        return nmBancoFinanciamento02;
    }

    public void setNmBancoFinanciamento02(String nmBancoFinanciamento02) {
        this.nmBancoFinanciamento02 = nmBancoFinanciamento02;
    }

    public String getNomeRefComercial1() {
        return nomeRefComercial1;
    }

    public void setNomeRefComercial1(String nomeRefComercial1) {
        this.nomeRefComercial1 = nomeRefComercial1;
    }

    public String getNomeRefComercial2() {
        return nomeRefComercial2;
    }

    public void setNomeRefComercial2(String nomeRefComercial2) {
        this.nomeRefComercial2 = nomeRefComercial2;
    }

    public String getNomeRefComercial3() {
        return nomeRefComercial3;
    }

    public void setNomeRefComercial3(String nomeRefComercial3) {
        this.nomeRefComercial3 = nomeRefComercial3;
    }

    public String getNomeRefPessoal3() {
        return nomeRefPessoal3;
    }

    public void setNomeRefPessoal3(String nomeRefPessoal3) {
        this.nomeRefPessoal3 = nomeRefPessoal3;
    }

    public String getNrCartaoFidelidade() {
        return nrCartaoFidelidade;
    }

    public void setNrCartaoFidelidade(String nrCartaoFidelidade) {
        this.nrCartaoFidelidade = nrCartaoFidelidade;
    }

    public Integer getNrDependentes() {
        return nrDependentes;
    }

    public void setNrDependentes(Integer nrDependentes) {
        this.nrDependentes = nrDependentes;
    }

    public String getNroEndereco() {
        return nroEndereco;
    }

    public void setNroEndereco(String nroEndereco) {
        this.nroEndereco = nroEndereco;
    }

    public String getObs2() {
        return obs2;
    }

    public void setObs2(String obs2) {
        this.obs2 = obs2;
    }

    public String getOrgaoExpedidor() {
        return orgaoExpedidor;
    }

    public void setOrgaoExpedidor(String orgaoExpedidor) {
        this.orgaoExpedidor = orgaoExpedidor;
    }

    public String getOrgaoExpedidorConjuge() {
        return orgaoExpedidorConjuge;
    }

    public void setOrgaoExpedidorConjuge(String orgaoExpedidorConjuge) {
        this.orgaoExpedidorConjuge = orgaoExpedidorConjuge;
    }

    public String getOutraFonteRenda01() {
        return outraFonteRenda01;
    }

    public void setOutraFonteRenda01(String outraFonteRenda01) {
        this.outraFonteRenda01 = outraFonteRenda01;
    }

    public String getOutraFonteRenda02() {
        return outraFonteRenda02;
    }

    public void setOutraFonteRenda02(String outraFonteRenda02) {
        this.outraFonteRenda02 = outraFonteRenda02;
    }

    public Integer getPadraoVendaPresencial() {
        return padraoVendaPresencial;
    }

    public void setPadraoVendaPresencial(Integer padraoVendaPresencial) {
        this.padraoVendaPresencial = padraoVendaPresencial;
    }

    public String getPaiConjuge() {
        return paiConjuge;
    }

    public void setPaiConjuge(String paiConjuge) {
        this.paiConjuge = paiConjuge;
    }

    public BigDecimal getPrDesconto() {
        return prDesconto;
    }

    public void setPrDesconto(BigDecimal prDesconto) {
        this.prDesconto = prDesconto;
    }

    public BigDecimal getPrDescontoAutomatico() {
        return prDescontoAutomatico;
    }

    public void setPrDescontoAutomatico(BigDecimal prDescontoAutomatico) {
        this.prDescontoAutomatico = prDescontoAutomatico;
    }

    public BigDecimal getPrJuroDiario() {
        return prJuroDiario;
    }

    public void setPrJuroDiario(BigDecimal prJuroDiario) {
        this.prJuroDiario = prJuroDiario;
    }

    public LocalDate getPrimeiraCompraRefComercial1() {
        return primeiraCompraRefComercial1;
    }

    public void setPrimeiraCompraRefComercial1(LocalDate primeiraCompraRefComercial1) {
        this.primeiraCompraRefComercial1 = primeiraCompraRefComercial1;
    }

    public LocalDate getPrimeiraCompraRefComercial2() {
        return primeiraCompraRefComercial2;
    }

    public void setPrimeiraCompraRefComercial2(LocalDate primeiraCompraRefComercial2) {
        this.primeiraCompraRefComercial2 = primeiraCompraRefComercial2;
    }

    public LocalDate getPrimeiraCompraRefComercial3() {
        return primeiraCompraRefComercial3;
    }

    public void setPrimeiraCompraRefComercial3(LocalDate primeiraCompraRefComercial3) {
        this.primeiraCompraRefComercial3 = primeiraCompraRefComercial3;
    }

    public Integer getQtComprasRefComercial1() {
        return qtComprasRefComercial1;
    }

    public void setQtComprasRefComercial1(Integer qtComprasRefComercial1) {
        this.qtComprasRefComercial1 = qtComprasRefComercial1;
    }

    public Integer getQtComprasRefComercial2() {
        return qtComprasRefComercial2;
    }

    public void setQtComprasRefComercial2(Integer qtComprasRefComercial2) {
        this.qtComprasRefComercial2 = qtComprasRefComercial2;
    }

    public Integer getQtComprasRefComercial3() {
        return qtComprasRefComercial3;
    }

    public void setQtComprasRefComercial3(Integer qtComprasRefComercial3) {
        this.qtComprasRefComercial3 = qtComprasRefComercial3;
    }

    public String getRazaoCobranca() {
        return razaoCobranca;
    }

    public void setRazaoCobranca(String razaoCobranca) {
        this.razaoCobranca = razaoCobranca;
    }

    public String getRegimeCasamento() {
        return regimeCasamento;
    }

    public void setRegimeCasamento(String regimeCasamento) {
        this.regimeCasamento = regimeCasamento;
    }

    public String getRegimeTributacao() {
        return regimeTributacao;
    }

    public void setRegimeTributacao(String regimeTributacao) {
        this.regimeTributacao = regimeTributacao;
    }

    public String getRetemIss() {
        return retemIss;
    }

    public void setRetemIss(String retemIss) {
        this.retemIss = retemIss;
    }

    public String getRevenda() {
        return revenda;
    }

    public void setRevenda(String revenda) {
        this.revenda = revenda;
    }

    public String getRgCliente() {
        return rgCliente;
    }

    public void setRgCliente(String rgCliente) {
        this.rgCliente = rgCliente;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getStatusTransmissao() {
        return statusTransmissao;
    }

    public void setStatusTransmissao(String statusTransmissao) {
        this.statusTransmissao = statusTransmissao;
    }

    public String getSuframaIcms() {
        return suframaIcms;
    }

    public void setSuframaIcms(String suframaIcms) {
        this.suframaIcms = suframaIcms;
    }

    public String getSuframaIpi() {
        return suframaIpi;
    }

    public void setSuframaIpi(String suframaIpi) {
        this.suframaIpi = suframaIpi;
    }

    public Integer getSyncAsaas() {
        return syncAsaas;
    }

    public void setSyncAsaas(Integer syncAsaas) {
        this.syncAsaas = syncAsaas;
    }

    public String getTelAssistenciaTecnica01() {
        return telAssistenciaTecnica01;
    }

    public void setTelAssistenciaTecnica01(String telAssistenciaTecnica01) {
        this.telAssistenciaTecnica01 = telAssistenciaTecnica01;
    }

    public String getTelAssistenciaTecnica02() {
        return telAssistenciaTecnica02;
    }

    public void setTelAssistenciaTecnica02(String telAssistenciaTecnica02) {
        this.telAssistenciaTecnica02 = telAssistenciaTecnica02;
    }

    public String getTempoResidencialAtual() {
        return tempoResidencialAtual;
    }

    public void setTempoResidencialAtual(String tempoResidencialAtual) {
        this.tempoResidencialAtual = tempoResidencialAtual;
    }

    public String getTipoCobranca() {
        return tipoCobranca;
    }

    public void setTipoCobranca(String tipoCobranca) {
        this.tipoCobranca = tipoCobranca;
    }

    public Integer getTipoContribuinte() {
        return tipoContribuinte;
    }

    public void setTipoContribuinte(Integer tipoContribuinte) {
        this.tipoContribuinte = tipoContribuinte;
    }

    public String getTipoFinanciamento01() {
        return tipoFinanciamento01;
    }

    public void setTipoFinanciamento01(String tipoFinanciamento01) {
        this.tipoFinanciamento01 = tipoFinanciamento01;
    }

    public String getTipoFinanciamento02() {
        return tipoFinanciamento02;
    }

    public void setTipoFinanciamento02(String tipoFinanciamento02) {
        this.tipoFinanciamento02 = tipoFinanciamento02;
    }

    public LocalDate getUltimaCompraRefComercial1() {
        return ultimaCompraRefComercial1;
    }

    public void setUltimaCompraRefComercial1(LocalDate ultimaCompraRefComercial1) {
        this.ultimaCompraRefComercial1 = ultimaCompraRefComercial1;
    }

    public LocalDate getUltimaCompraRefComercial2() {
        return ultimaCompraRefComercial2;
    }

    public void setUltimaCompraRefComercial2(LocalDate ultimaCompraRefComercial2) {
        this.ultimaCompraRefComercial2 = ultimaCompraRefComercial2;
    }

    public LocalDate getUltimaCompraRefComercial3() {
        return ultimaCompraRefComercial3;
    }

    public void setUltimaCompraRefComercial3(LocalDate ultimaCompraRefComercial3) {
        this.ultimaCompraRefComercial3 = ultimaCompraRefComercial3;
    }

    public String getValidadeContrato() {
        return validadeContrato;
    }

    public void setValidadeContrato(String validadeContrato) {
        this.validadeContrato = validadeContrato;
    }

    public String getVencimentoContrato() {
        return vencimentoContrato;
    }

    public void setVencimentoContrato(String vencimentoContrato) {
        this.vencimentoContrato = vencimentoContrato;
    }

    public String getVinculoRefPessoa1() {
        return vinculoRefPessoa1;
    }

    public void setVinculoRefPessoa1(String vinculoRefPessoa1) {
        this.vinculoRefPessoa1 = vinculoRefPessoa1;
    }

    public String getVinculoRefPessoa2() {
        return vinculoRefPessoa2;
    }

    public void setVinculoRefPessoa2(String vinculoRefPessoa2) {
        this.vinculoRefPessoa2 = vinculoRefPessoa2;
    }

    public String getVinculoRefPessoa3() {
        return vinculoRefPessoa3;
    }

    public void setVinculoRefPessoa3(String vinculoRefPessoa3) {
        this.vinculoRefPessoa3 = vinculoRefPessoa3;
    }

    public String getVisualizaUltimoPreco() {
        return visualizaUltimoPreco;
    }

    public void setVisualizaUltimoPreco(String visualizaUltimoPreco) {
        this.visualizaUltimoPreco = visualizaUltimoPreco;
    }

    public BigDecimal getVlLimiteFixo() {
        return vlLimiteFixo;
    }

    public void setVlLimiteFixo(BigDecimal vlLimiteFixo) {
        this.vlLimiteFixo = vlLimiteFixo;
    }

    public BigDecimal getVlMensalContrato() {
        return vlMensalContrato;
    }

    public void setVlMensalContrato(BigDecimal vlMensalContrato) {
        this.vlMensalContrato = vlMensalContrato;
    }

    public BigDecimal getVlOutraFonteRenda01() {
        return vlOutraFonteRenda01;
    }

    public void setVlOutraFonteRenda01(BigDecimal vlOutraFonteRenda01) {
        this.vlOutraFonteRenda01 = vlOutraFonteRenda01;
    }

    public BigDecimal getVlOutraFonteRenda02() {
        return vlOutraFonteRenda02;
    }

    public void setVlOutraFonteRenda02(BigDecimal vlOutraFonteRenda02) {
        this.vlOutraFonteRenda02 = vlOutraFonteRenda02;
    }

public Integer getCdCidadeAvalista() {
    return cdCidadeAvalista;
}

public void setCdCidadeAvalista(Integer cdCidadeAvalista) {
    this.cdCidadeAvalista = cdCidadeAvalista;
}

public Integer getCdCidadeAvalista02() {
    return cdCidadeAvalista02;
}

public void setCdCidadeAvalista02(Integer cdCidadeAvalista02) {
    this.cdCidadeAvalista02 = cdCidadeAvalista02;
}

public String getCelularAvalista02() {
    return celularAvalista02;
}

public void setCelularAvalista02(String celularAvalista02) {
    this.celularAvalista02 = celularAvalista02;
}

public String getContaContabil() {
    return contaContabil;
}

public void setContaContabil(String contaContabil) {
    this.contaContabil = contaContabil;
}

public String getCpfAvalista02() {
    return cpfAvalista02;
}

public void setCpfAvalista02(String cpfAvalista02) {
    this.cpfAvalista02 = cpfAvalista02;
}

public String getEmpresaAvalista02() {
    return empresaAvalista02;
}

public void setEmpresaAvalista02(String empresaAvalista02) {
    this.empresaAvalista02 = empresaAvalista02;
}

public String getEnderecoAvalista() {
    return enderecoAvalista;
}

public void setEnderecoAvalista(String enderecoAvalista) {
    this.enderecoAvalista = enderecoAvalista;
}

public String getEnderecoAvalista02() {
    return enderecoAvalista02;
}

public void setEnderecoAvalista02(String enderecoAvalista02) {
    this.enderecoAvalista02 = enderecoAvalista02;
}

public String getFoneAvalista02() {
    return foneAvalista02;
}

public void setFoneAvalista02(String foneAvalista02) {
    this.foneAvalista02 = foneAvalista02;
}

public String getFoneRefComercial1() {
    return foneRefComercial1;
}

public void setFoneRefComercial1(String foneRefComercial1) {
    this.foneRefComercial1 = foneRefComercial1;
}

public String getFoneRefComercial2() {
    return foneRefComercial2;
}

public void setFoneRefComercial2(String foneRefComercial2) {
    this.foneRefComercial2 = foneRefComercial2;
}

public String getFoneRefComercial3() {
    return foneRefComercial3;
}

public void setFoneRefComercial3(String foneRefComercial3) {
    this.foneRefComercial3 = foneRefComercial3;
}

public String getNomeAvalista02() {
    return nomeAvalista02;
}

public void setNomeAvalista02(String nomeAvalista02) {
    this.nomeAvalista02 = nomeAvalista02;
}

public String getNroEnderecoRefPessoal3() {
    return nroEnderecoRefPessoal3;
}

public void setNroEnderecoRefPessoal3(String nroEnderecoRefPessoal3) {
    this.nroEnderecoRefPessoal3 = nroEnderecoRefPessoal3;
}

public String getRgAvalista02() {
    return rgAvalista02;
}

public void setRgAvalista02(String rgAvalista02) {
    this.rgAvalista02 = rgAvalista02;
}

}