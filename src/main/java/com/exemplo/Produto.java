package com.exemplo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import java.sql.Timestamp;
import java.math.BigDecimal;

@Entity
@Table(name = "produto")
public class Produto {

    @Id
    @Column(name = "cd_produto")
    private Integer cdProduto;

    @Column(name = "nr_digito")
    private String nrDigito;

    @Column(name = "ds_produto")
    private String dsProduto;

    @Column(name = "ds_abreviacao")
    private String dsAbreviacao;

    @Column(name = "cd_subgrupo")
    private Short cdSubgrupo;

    @Column(name = "cd_grupo")
    private Short cdGrupo;

    @Column(name = "cd_marca")
    private Short cdMarca;

    @Column(name = "cd_cor")
    private Short cdCor;

    @Column(name = "voltagem")
    private String voltagem;

    @Column(name = "cd_fornecedor")
    private Integer cdFornecedor;

    @Column(name = "situacao")
    private String situacao;

    @Column(name = "reducao_icms")
    private BigDecimal reducaoIcms;

    @Column(name = "icms")
    private BigDecimal icms;

    @Column(name = "ipi")
    private BigDecimal ipi;

    @Column(name = "classificacao_fiscal")
    private String classificacaoFiscal;

    @Column(name = "origem_situacao_tributaria")
    private String origemSituacaoTributaria;

    @Column(name = "situacao_tributaria")
    private String situacaoTributaria;

    @Column(name = "vl_contabil")
    private BigDecimal vlContabil;

    @Column(name = "vl_compra")
    private BigDecimal vlCompra;

    @Column(name = "vl_venda")
    private BigDecimal vlVenda;

    @Column(name = "vl_custo")
    private BigDecimal vlCusto;

    @Column(name = "comissao")
    private BigDecimal comissao;

    @Column(name = "lucro")
    private BigDecimal lucro;

    @Column(name = "estoque_minimo")
    private BigDecimal estoqueMinimo;

    @Column(name = "estoque_maximo")
    private BigDecimal estoqueMaximo;

    @Column(name = "cd_unidade")
    private String cdUnidade;

    @Column(name = "vl_custo_medio")
    private BigDecimal vlCustoMedio;

    @Column(name = "pr_desconto_vendedor")
    private BigDecimal prDescontoVendedor;

    @Column(name = "pr_desconto_gerente")
    private BigDecimal prDescontoGerente;

    @Column(name = "saldo_negativo")
    private String saldoNegativo;

    @Column(name = "cd_barra")
    private String cdBarra;

    @Column(name = "nr_componentes")
    private Short nrComponentes;

    @Column(name = "referencia")
    private String referencia;

    @Column(name = "dt_cadastro")
    private Timestamp dtCadastro;

    @Column(name = "altera_preco")
    private String alteraPreco;

    @Column(name = "prateleira")
    private String prateleira;

    @Column(name = "peso")
    private BigDecimal peso;

    @Column(name = "cubagem")
    private BigDecimal cubagem;

    @Column(name = "cd_fabrica")
    private String cdFabrica;

    @Column(name = "capacidade")
    private Short capacidade;

    @Column(name = "combustivel")
    private String combustivel;

    @Column(name = "renavam")
    private String renavam;

    @Column(name = "chassi")
    private String chassi;

    @Column(name = "motor")
    private String motor;

    @Column(name = "potencia")
    private String potencia;

    @Column(name = "ano_fabricacao")
    private Short anoFabricacao;

    @Column(name = "ano_modelo")
    private Short anoModelo;

    @Column(name = "cilindrada")
    private String cilindrada;

    @Column(name = "cd_perfil")
    private String cdPerfil;

    @Column(name = "cd_acabamento")
    private String cdAcabamento;

    @Column(name = "gravura")
    private String gravura;

    @Column(name = "sombra")
    private String sombra;

    @Column(name = "ncm")
    private String ncm;

    @Column(name = "vl_ipi")
    private BigDecimal vlIpi;

    @Column(name = "vl_substituicao")
    private BigDecimal vlSubstituicao;

    @Column(name = "cd_montadora")
    private String cdMontadora;

    @Column(name = "tamanho")
    private String tamanho;

    @Column(name = "pr_proteina")
    private BigDecimal prProteina;

    @Column(name = "tp_produto")
    private String tpProduto;

    @Column(name = "ds_modelo")
    private String dsModelo;

    @Column(name = "comissao2")
    private BigDecimal comissao2;

    @Column(name = "comissao3")
    private BigDecimal comissao3;

    @Column(name = "numero")
    private String numero;

    @Column(name = "capacidade_volumetrica")
    private BigDecimal capacidadeVolumetrica;

    @Column(name = "cd_produto_dnf")
    private String cdProdutoDnf;

    @Column(name = "kg_milheiro")
    private BigDecimal kgMilheiro;

    @Column(name = "nm_usuario")
    private String nmUsuario;

    @Column(name = "dias_entrega")
    private Short diasEntrega;

    @Column(name = "classificacao")
    private String classificacao;

    @Column(name = "qt_pecas_um_volume")
    private BigDecimal qtPecasUmVolume;

    @Column(name = "obs_orcamento")
    private String obsOrcamento;

    @Column(name = "obs_nf")
    private String obsNf;

    @Column(name = "nr_meses_garantia")
    private Short nrMesesGarantia;

    @Column(name = "pr_desconto_gerente_2")
    private Short prDescontoGerente2;

    @Column(name = "pr_desconto_vendedor_2")
    private Short prDescontoVendedor2;

    @Column(name = "peso_bruto")
    private BigDecimal pesoBruto;

    @Column(name = "caminho_foto")
    private String caminhoFoto;

    @Column(name = "vl_dec")
    private String vlDec;

    @Column(name = "montadora")
    private String montadora;

    @Column(name = "ipi_venda")
    private BigDecimal ipiVenda;

    @Column(name = "prioridade")
    private String prioridade;

    @Column(name = "pis_cofins")
    private String pisCofins;

    @Column(name = "dt_vencimento_nota")
    private Timestamp dtVencimentoNota;

    @Column(name = "cd_receita")
    private String cdReceita;

    @Column(name = "cd_despesa")
    private Short cdDespesa;

    @Column(name = "cd_tipo")
    private Integer cdTipo;

    @Column(name = "vl_mao_obra")
    private BigDecimal vlMaoObra;

    @Column(name = "modalidade_bc_icms")
    private Short modalidadeBcIcms;

    @Column(name = "modalidade_bc_icms_st")
    private Short modalidadeBcIcmsSt;

    @Column(name = "enquadramento_ipi")
    private String enquadramentoIpi;

    @Column(name = "situacao_tributaria_ipi")
    private String situacaoTributariaIpi;

    @Column(name = "situacao_tributaria_pis")
    private String situacaoTributariaPis;

    @Column(name = "pr_aliquota_pis")
    private BigDecimal prAliquotaPis;

    @Column(name = "situacao_tributaria_cofins")
    private String situacaoTributariaCofins;

    @Column(name = "pr_aliquota_cofins")
    private BigDecimal prAliquotaCofins;

    @Column(name = "pr_adicionado_substituicao")
    private BigDecimal prAdicionadoSubstituicao;

    @Column(name = "pr_substituicao")
    private BigDecimal prSubstituicao;

    @Column(name = "cd_tipo_entrada")
    private Integer cdTipoEntrada;

    @Column(name = "ds_aplicacao")
    private String dsAplicacao;

    @Column(name = "conversao")
    private String conversao;

    @Column(name = "pr_icms_compra")
    private BigDecimal prIcmsCompra;

    @Column(name = "somente_cotacao_compra")
    private String somenteCotacaoCompra;

    @Column(name = "cd_grupo_servico_classificacao")
    private Integer cdGrupoServicoClassificacao;

    @Column(name = "cd_servico_classificacao")
    private Integer cdServicoClassificacao;

    @Column(name = "cd_tributacao_iss")
    private String cdTributacaoIss;

    @Column(name = "cd_tipo_item_sped")
    private String cdTipoItemSped;

    @Column(name = "cd_excecao_ncm_sped")
    private String cdExcecaoNcmSped;

    @Column(name = "cd_genero_sped")
    private String cdGeneroSped;

    @Column(name = "pr_reducao_icms_st")
    private BigDecimal prReducaoIcmsSt;

    @Column(name = "pr_fator_reducao_sn_st")
    private BigDecimal prFatorReducaoSnSt;

    @Column(name = "indice_ajuste_mva")
    private BigDecimal indiceAjusteMva;

    @Column(name = "movto_sped")
    private String movtoSped;

    @Column(name = "prioridade_ordem")
    private Integer prioridadeOrdem;

    @Column(name = "altera_descricao_compra")
    private String alteraDescricaoCompra;

    @Column(name = "libera_locacao")
    private String liberaLocacao;

    @Column(name = "criptografia")
    private String criptografia;

    @Column(name = "cd_anp")
    private String cdAnp;

    @Column(name = "largura")
    private BigDecimal largura;

    @Column(name = "profundidade")
    private BigDecimal profundidade;

    @Column(name = "altura")
    private BigDecimal altura;

    @Column(name = "sazonal")
    private String sazonal;

    @Column(name = "nr_especificacao_icms")
    private Integer nrEspecificacaoIcms;

    @Column(name = "nr_especificacao_pis_cofins")
    private Integer nrEspecificacaoPisCofins;

    @Column(name = "nr_especificacao_ipi")
    private Integer nrEspecificacaoIpi;

    @Column(name = "situacao_tributaria_compra")
    private String situacaoTributariaCompra;

    @Column(name = "pr_icms_ajuste_mva")
    private BigDecimal prIcmsAjusteMva;

    @Column(name = "pr_reducao_icms_compra")
    private BigDecimal prReducaoIcmsCompra;

    @Column(name = "nr_especificacao_compras")
    private Integer nrEspecificacaoCompras;

    @Column(name = "nm_usuario_alteracao")
    private String nmUsuarioAlteracao;

    @Column(name = "dt_alteracao")
    private Timestamp dtAlteracao;

    @Column(name = "cest")
    private String cest;

    @Column(name = "pr_desconto_demander")
    private BigDecimal prDescontoDemander;

    @Column(name = "fci")
    private String fci;

    @Column(name = "cd_fabricante")
    private String cdFabricante;

    @Column(name = "pis_cofins_monofasico")
    private Integer pisCofinsMonofasico;

    @Column(name = "vl_bc_icms_st_ret")
    private BigDecimal vlBcIcmsStRet;

    @Column(name = "pr_st_ret")
    private BigDecimal prStRet;

    @Column(name = "vl_icms_ret")
    private BigDecimal vlIcmsRet;

    @Column(name = "vl_icms_st_ret")
    private BigDecimal vlIcmsStRet;

    @Column(name = "cd_similaridade")
    private String cdSimilaridade;

    @Column(name = "verificado_autokm")
    private Integer verificadoAutokm;

    @Column(name = "ds_fabricante_autokm")
    private String dsFabricanteAutokm;

    @Column(name = "qt_fracionada")
    private BigDecimal qtFracionada;

    @Column(name = "cd_unidade_fracionada")
    private String cdUnidadeFracionada;

    @Column(name = "cd_aliquota")
    private Short cdAliquota;

    @Column(name = "cd_produto_agrupador")
    private Integer cdProdutoAgrupador;

    @Column(name = "obs_loja_virtual")
    private String obsLojaVirtual;

    @Column(name = "icms_st_retido_anteriormente")
    private Integer icmsStRetidoAnteriormente;

    // Getters e Setters
    public Integer getCdProduto() {
        return cdProduto;
    }

    public void setCdProduto(Integer cdProduto) {
        this.cdProduto = cdProduto;
    }

    public String getNrDigito() {
        return nrDigito;
    }

    public void setNrDigito(String nrDigito) {
        this.nrDigito = nrDigito;
    }

    public String getDsProduto() {
        return dsProduto;
    }

    public void setDsProduto(String dsProduto) {
        this.dsProduto = dsProduto;
    }

    public String getDsAbreviacao() {
        return dsAbreviacao;
    }

    public void setDsAbreviacao(String dsAbreviacao) {
        this.dsAbreviacao = dsAbreviacao;
    }

    public Short getCdSubgrupo() {
        return cdSubgrupo;
    }

    public void setCdSubgrupo(Short cdSubgrupo) {
        this.cdSubgrupo = cdSubgrupo;
    }

    public Short getCdGrupo() {
        return cdGrupo;
    }

    public void setCdGrupo(Short cdGrupo) {
        this.cdGrupo = cdGrupo;
    }

    public Short getCdMarca() {
        return cdMarca;
    }

    public void setCdMarca(Short cdMarca) {
        this.cdMarca = cdMarca;
    }

    public Short getCdCor() {
        return cdCor;
    }

    public void setCdCor(Short cdCor) {
        this.cdCor = cdCor;
    }

    public String getVoltagem() {
        return voltagem;
    }

    public void setVoltagem(String voltagem) {
        this.voltagem = voltagem;
    }

    public Integer getCdFornecedor() {
        return cdFornecedor;
    }

    public void setCdFornecedor(Integer cdFornecedor) {
        this.cdFornecedor = cdFornecedor;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public BigDecimal getReducaoIcms() {
        return reducaoIcms;
    }

    public void setReducaoIcms(BigDecimal reducaoIcms) {
        this.reducaoIcms = reducaoIcms;
    }

    public BigDecimal getIcms() {
        return icms;
    }

    public void setIcms(BigDecimal icms) {
        this.icms = icms;
    }

    public BigDecimal getIpi() {
        return ipi;
    }

    public void setIpi(BigDecimal ipi) {
        this.ipi = ipi;
    }

    public String getClassificacaoFiscal() {
        return classificacaoFiscal;
    }

    public void setClassificacaoFiscal(String classificacaoFiscal) {
        this.classificacaoFiscal = classificacaoFiscal;
    }

    public String getOrigemSituacaoTributaria() {
        return origemSituacaoTributaria;
    }

    public void setOrigemSituacaoTributaria(String origemSituacaoTributaria) {
        this.origemSituacaoTributaria = origemSituacaoTributaria;
    }

    public String getSituacaoTributaria() {
        return situacaoTributaria;
    }

    public void setSituacaoTributaria(String situacaoTributaria) {
        this.situacaoTributaria = situacaoTributaria;
    }

    public BigDecimal getVlContabil() {
        return vlContabil;
    }

    public void setVlContabil(BigDecimal vlContabil) {
        this.vlContabil = vlContabil;
    }

    public BigDecimal getVlCompra() {
        return vlCompra;
    }

    public void setVlCompra(BigDecimal vlCompra) {
        this.vlCompra = vlCompra;
    }

    public BigDecimal getVlVenda() {
        return vlVenda;
    }

    public void setVlVenda(BigDecimal vlVenda) {
        this.vlVenda = vlVenda;
    }

    public BigDecimal getVlCusto() {
        return vlCusto;
    }

    public void setVlCusto(BigDecimal vlCusto) {
        this.vlCusto = vlCusto;
    }

    public BigDecimal getComissao() {
        return comissao;
    }

    public void setComissao(BigDecimal comissao) {
        this.comissao = comissao;
    }

    public BigDecimal getLucro() {
        return lucro;
    }

    public void setLucro(BigDecimal lucro) {
        this.lucro = lucro;
    }

    public BigDecimal getEstoqueMinimo() {
        return estoqueMinimo;
    }

    public void setEstoqueMinimo(BigDecimal estoqueMinimo) {
        this.estoqueMinimo = estoqueMinimo;
    }

    public BigDecimal getEstoqueMaximo() {
        return estoqueMaximo;
    }

    public void setEstoqueMaximo(BigDecimal estoqueMaximo) {
        this.estoqueMaximo = estoqueMaximo;
    }

    public String getCdUnidade() {
        return cdUnidade;
    }

    public void setCdUnidade(String cdUnidade) {
        this.cdUnidade = cdUnidade;
    }

    public BigDecimal getVlCustoMedio() {
        return vlCustoMedio;
    }

    public void setVlCustoMedio(BigDecimal vlCustoMedio) {
        this.vlCustoMedio = vlCustoMedio;
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

    public String getSaldoNegativo() {
        return saldoNegativo;
    }

    public void setSaldoNegativo(String saldoNegativo) {
        this.saldoNegativo = saldoNegativo;
    }

    public String getCdBarra() {
        return cdBarra;
    }

    public void setCdBarra(String cdBarra) {
        this.cdBarra = cdBarra;
    }

    public Short getNrComponentes() {
        return nrComponentes;
    }

    public void setNrComponentes(Short nrComponentes) {
        this.nrComponentes = nrComponentes;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public Timestamp getDtCadastro() {
        return dtCadastro;
    }

    public void setDtCadastro(Timestamp dtCadastro) {
        this.dtCadastro = dtCadastro;
    }

    public String getAlteraPreco() {
        return alteraPreco;
    }

    public void setAlteraPreco(String alteraPreco) {
        this.alteraPreco = alteraPreco;
    }

    public String getPrateleira() {
        return prateleira;
    }

    public void setPrateleira(String prateleira) {
        this.prateleira = prateleira;
    }

    public BigDecimal getPeso() {
        return peso;
    }

    public void setPeso(BigDecimal peso) {
        this.peso = peso;
    }

    public BigDecimal getCubagem() {
        return cubagem;
    }

    public void setCubagem(BigDecimal cubagem) {
        this.cubagem = cubagem;
    }

    public String getCdFabrica() {
        return cdFabrica;
    }

    public void setCdFabrica(String cdFabrica) {
        this.cdFabrica = cdFabrica;
    }

    public Short getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(Short capacidade) {
        this.capacidade = capacidade;
    }

    public String getCombustivel() {
        return combustivel;
    }

    public void setCombustivel(String combustivel) {
        this.combustivel = combustivel;
    }

    public String getRenavam() {
        return renavam;
    }

    public void setRenavam(String renavam) {
        this.renavam = renavam;
    }

    public String getChassi() {
        return chassi;
    }

    public void setChassi(String chassi) {
        this.chassi = chassi;
    }

    public String getMotor() {
        return motor;
    }

    public void setMotor(String motor) {
        this.motor = motor;
    }

    public String getPotencia() {
        return potencia;
    }

    public void setPotencia(String potencia) {
        this.potencia = potencia;
    }

    public Short getAnoFabricacao() {
        return anoFabricacao;
    }

    public void setAnoFabricacao(Short anoFabricacao) {
        this.anoFabricacao = anoFabricacao;
    }

    public Short getAnoModelo() {
        return anoModelo;
    }

    public void setAnoModelo(Short anoModelo) {
        this.anoModelo = anoModelo;
    }

    public String getCilindrada() {
        return cilindrada;
    }

    public void setCilindrada(String cilindrada) {
        this.cilindrada = cilindrada;
    }

    public String getCdPerfil() {
        return cdPerfil;
    }

    public void setCdPerfil(String cdPerfil) {
        this.cdPerfil = cdPerfil;
    }

    public String getCdAcabamento() {
        return cdAcabamento;
    }

    public void setCdAcabamento(String cdAcabamento) {
        this.cdAcabamento = cdAcabamento;
    }

    public String getGravura() {
        return gravura;
    }

    public void setGravura(String gravura) {
        this.gravura = gravura;
    }

    public String getSombra() {
        return sombra;
    }

    public void setSombra(String sombra) {
        this.sombra = sombra;
    }

    public String getNcm() {
        return ncm;
    }

    public void setNcm(String ncm) {
        this.ncm = ncm;
    }

    public BigDecimal getVlIpi() {
        return vlIpi;
    }

    public void setVlIpi(BigDecimal vlIpi) {
        this.vlIpi = vlIpi;
    }

    public BigDecimal getVlSubstituicao() {
        return vlSubstituicao;
    }

    public void setVlSubstituicao(BigDecimal vlSubstituicao) {
        this.vlSubstituicao = vlSubstituicao;
    }

    public String getCdMontadora() {
        return cdMontadora;
    }

    public void setCdMontadora(String cdMontadora) {
        this.cdMontadora = cdMontadora;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public BigDecimal getPrProteina() {
        return prProteina;
    }

    public void setPrProteina(BigDecimal prProteina) {
        this.prProteina = prProteina;
    }

    public String getTpProduto() {
        return tpProduto;
    }

    public void setTpProduto(String tpProduto) {
        this.tpProduto = tpProduto;
    }

    public String getDsModelo() {
        return dsModelo;
    }

    public void setDsModelo(String dsModelo) {
        this.dsModelo = dsModelo;
    }

    public BigDecimal getComissao2() {
        return comissao2;
    }

    public void setComissao2(BigDecimal comissao2) {
        this.comissao2 = comissao2;
    }

    public BigDecimal getComissao3() {
        return comissao3;
    }

    public void setComissao3(BigDecimal comissao3) {
        this.comissao3 = comissao3;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public BigDecimal getCapacidadeVolumetrica() {
        return capacidadeVolumetrica;
    }

    public void setCapacidadeVolumetrica(BigDecimal capacidadeVolumetrica) {
        this.capacidadeVolumetrica = capacidadeVolumetrica;
    }

    public String getCdProdutoDnf() {
        return cdProdutoDnf;
    }

    public void setCdProdutoDnf(String cdProdutoDnf) {
        this.cdProdutoDnf = cdProdutoDnf;
    }

    public BigDecimal getKgMilheiro() {
        return kgMilheiro;
    }

    public void setKgMilheiro(BigDecimal kgMilheiro) {
        this.kgMilheiro = kgMilheiro;
    }

    public String getNmUsuario() {
        return nmUsuario;
    }

    public void setNmUsuario(String nmUsuario) {
        this.nmUsuario = nmUsuario;
    }

    public Short getDiasEntrega() {
        return diasEntrega;
    }

    public void setDiasEntrega(Short diasEntrega) {
        this.diasEntrega = diasEntrega;
    }

    public String getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(String classificacao) {
        this.classificacao = classificacao;
    }

    public BigDecimal getQtPecasUmVolume() {
        return qtPecasUmVolume;
    }

    public void setQtPecasUmVolume(BigDecimal qtPecasUmVolume) {
        this.qtPecasUmVolume = qtPecasUmVolume;
    }

    public String getObsOrcamento() {
        return obsOrcamento;
    }

    public void setObsOrcamento(String obsOrcamento) {
        this.obsOrcamento = obsOrcamento;
    }

    public String getObsNf() {
        return obsNf;
    }

    public void setObsNf(String obsNf) {
        this.obsNf = obsNf;
    }

    public Short getNrMesesGarantia() {
        return nrMesesGarantia;
    }

    public void setNrMesesGarantia(Short nrMesesGarantia) {
        this.nrMesesGarantia = nrMesesGarantia;
    }

    public Short getPrDescontoGerente2() {
        return prDescontoGerente2;
    }

    public void setPrDescontoGerente2(Short prDescontoGerente2) {
        this.prDescontoGerente2 = prDescontoGerente2;
    }

    public Short getPrDescontoVendedor2() {
        return prDescontoVendedor2;
    }

    public void setPrDescontoVendedor2(Short prDescontoVendedor2) {
        this.prDescontoVendedor2 = prDescontoVendedor2;
    }

    public BigDecimal getPesoBruto() {
        return pesoBruto;
    }

    public void setPesoBruto(BigDecimal pesoBruto) {
        this.pesoBruto = pesoBruto;
    }

    public String getCaminhoFoto() {
        return caminhoFoto;
    }

    public void setCaminhoFoto(String caminhoFoto) {
        this.caminhoFoto = caminhoFoto;
    }

    public String getVlDec() {
        return vlDec;
    }

    public void setVlDec(String vlDec) {
        this.vlDec = vlDec;
    }

    public String getMontadora() {
        return montadora;
    }

    public void setMontadora(String montadora) {
        this.montadora = montadora;
    }

    public BigDecimal getIpiVenda() {
        return ipiVenda;
    }

    public void setIpiVenda(BigDecimal ipiVenda) {
        this.ipiVenda = ipiVenda;
    }

    public String getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }

    public String getPisCofins() {
        return pisCofins;
    }

    public void setPisCofins(String pisCofins) {
        this.pisCofins = pisCofins;
    }

    public Timestamp getDtVencimentoNota() {
        return dtVencimentoNota;
    }

    public void setDtVencimentoNota(Timestamp dtVencimentoNota) {
        this.dtVencimentoNota = dtVencimentoNota;
    }

    public String getCdReceita() {
        return cdReceita;
    }

    public void setCdReceita(String cdReceita) {
        this.cdReceita = cdReceita;
    }

    public Short getCdDespesa() {
        return cdDespesa;
    }

    public void setCdDespesa(Short cdDespesa) {
        this.cdDespesa = cdDespesa;
    }

    public Integer getCdTipo() {
        return cdTipo;
    }

    public void setCdTipo(Integer cdTipo) {
        this.cdTipo = cdTipo;
    }

    public BigDecimal getVlMaoObra() {
        return vlMaoObra;
    }

    public void setVlMaoObra(BigDecimal vlMaoObra) {
        this.vlMaoObra = vlMaoObra;
    }

    public Short getModalidadeBcIcms() {
        return modalidadeBcIcms;
    }

    public void setModalidadeBcIcms(Short modalidadeBcIcms) {
        this.modalidadeBcIcms = modalidadeBcIcms;
    }

    public Short getModalidadeBcIcmsSt() {
        return modalidadeBcIcmsSt;
    }

    public void setModalidadeBcIcmsSt(Short modalidadeBcIcmsSt) {
        this.modalidadeBcIcmsSt = modalidadeBcIcmsSt;
    }

    public String getEnquadramentoIpi() {
        return enquadramentoIpi;
    }

    public void setEnquadramentoIpi(String enquadramentoIpi) {
        this.enquadramentoIpi = enquadramentoIpi;
    }

    public String getSituacaoTributariaIpi() {
        return situacaoTributariaIpi;
    }

    public void setSituacaoTributariaIpi(String situacaoTributariaIpi) {
        this.situacaoTributariaIpi = situacaoTributariaIpi;
    }

    public String getSituacaoTributariaPis() {
        return situacaoTributariaPis;
    }

    public void setSituacaoTributariaPis(String situacaoTributariaPis) {
        this.situacaoTributariaPis = situacaoTributariaPis;
    }

    public BigDecimal getPrAliquotaPis() {
        return prAliquotaPis;
    }

    public void setPrAliquotaPis(BigDecimal prAliquotaPis) {
        this.prAliquotaPis = prAliquotaPis;
    }

    public String getSituacaoTributariaCofins() {
        return situacaoTributariaCofins;
    }

    public void setSituacaoTributariaCofins(String situacaoTributariaCofins) {
        this.situacaoTributariaCofins = situacaoTributariaCofins;
    }

    public BigDecimal getPrAliquotaCofins() {
        return prAliquotaCofins;
    }

    public void setPrAliquotaCofins(BigDecimal prAliquotaCofins) {
        this.prAliquotaCofins = prAliquotaCofins;
    }

    public BigDecimal getPrAdicionadoSubstituicao() {
        return prAdicionadoSubstituicao;
    }

    public void setPrAdicionadoSubstituicao(BigDecimal prAdicionadoSubstituicao) {
        this.prAdicionadoSubstituicao = prAdicionadoSubstituicao;
    }

    public BigDecimal getPrSubstituicao() {
        return prSubstituicao;
    }

    public void setPrSubstituicao(BigDecimal prSubstituicao) {
        this.prSubstituicao = prSubstituicao;
    }

    public Integer getCdTipoEntrada() {
        return cdTipoEntrada;
    }

    public void setCdTipoEntrada(Integer cdTipoEntrada) {
        this.cdTipoEntrada = cdTipoEntrada;
    }

    public String getDsAplicacao() {
        return dsAplicacao;
    }

    public void setDsAplicacao(String dsAplicacao) {
        this.dsAplicacao = dsAplicacao;
    }

    public String getConversao() {
        return conversao;
    }

    public void setConversao(String conversao) {
        this.conversao = conversao;
    }

    public BigDecimal getPrIcmsCompra() {
        return prIcmsCompra;
    }

    public void setPrIcmsCompra(BigDecimal prIcmsCompra) {
        this.prIcmsCompra = prIcmsCompra;
    }

    public String getSomenteCotacaoCompra() {
        return somenteCotacaoCompra;
    }

    public void setSomenteCotacaoCompra(String somenteCotacaoCompra) {
        this.somenteCotacaoCompra = somenteCotacaoCompra;
    }

    public Integer getCdGrupoServicoClassificacao() {
        return cdGrupoServicoClassificacao;
    }

    public void setCdGrupoServicoClassificacao(Integer cdGrupoServicoClassificacao) {
        this.cdGrupoServicoClassificacao = cdGrupoServicoClassificacao;
    }

    public Integer getCdServicoClassificacao() {
        return cdServicoClassificacao;
    }

    public void setCdServicoClassificacao(Integer cdServicoClassificacao) {
        this.cdServicoClassificacao = cdServicoClassificacao;
    }

    public String getCdTributacaoIss() {
        return cdTributacaoIss;
    }

    public void setCdTributacaoIss(String cdTributacaoIss) {
        this.cdTributacaoIss = cdTributacaoIss;
    }

    public String getCdTipoItemSped() {
        return cdTipoItemSped;
    }

    public void setCdTipoItemSped(String cdTipoItemSped) {
        this.cdTipoItemSped = cdTipoItemSped;
    }

    public String getCdExcecaoNcmSped() {
        return cdExcecaoNcmSped;
    }

    public void setCdExcecaoNcmSped(String cdExcecaoNcmSped) {
        this.cdExcecaoNcmSped = cdExcecaoNcmSped;
    }

    public String getCdGeneroSped() {
        return cdGeneroSped;
    }

    public void setCdGeneroSped(String cdGeneroSped) {
        this.cdGeneroSped = cdGeneroSped;
    }

    public BigDecimal getPrReducaoIcmsSt() {
        return prReducaoIcmsSt;
    }

    public void setPrReducaoIcmsSt(BigDecimal prReducaoIcmsSt) {
        this.prReducaoIcmsSt = prReducaoIcmsSt;
    }

    public BigDecimal getPrFatorReducaoSnSt() {
        return prFatorReducaoSnSt;
    }

    public void setPrFatorReducaoSnSt(BigDecimal prFatorReducaoSnSt) {
        this.prFatorReducaoSnSt = prFatorReducaoSnSt;
    }

    public BigDecimal getIndiceAjusteMva() {
        return indiceAjusteMva;
    }

    public void setIndiceAjusteMva(BigDecimal indiceAjusteMva) {
        this.indiceAjusteMva = indiceAjusteMva;
    }

    public String getMovtoSped() {
        return movtoSped;
    }

    public void setMovtoSped(String movtoSped) {
        this.movtoSped = movtoSped;
    }

    public Integer getPrioridadeOrdem() {
        return prioridadeOrdem;
    }

    public void setPrioridadeOrdem(Integer prioridadeOrdem) {
        this.prioridadeOrdem = prioridadeOrdem;
    }

    public String getAlteraDescricaoCompra() {
        return alteraDescricaoCompra;
    }

    public void setAlteraDescricaoCompra(String alteraDescricaoCompra) {
        this.alteraDescricaoCompra = alteraDescricaoCompra;
    }

    public String getLiberaLocacao() {
        return liberaLocacao;
    }

    public void setLiberaLocacao(String liberaLocacao) {
        this.liberaLocacao = liberaLocacao;
    }

    public String getCriptografia() {
        return criptografia;
    }

    public void setCriptografia(String criptografia) {
        this.criptografia = criptografia;
    }

    public String getCdAnp() {
        return cdAnp;
    }

    public void setCdAnp(String cdAnp) {
        this.cdAnp = cdAnp;
    }

    public BigDecimal getLargura() {
        return largura;
    }

    public void setLargura(BigDecimal largura) {
        this.largura = largura;
    }

    public BigDecimal getProfundidade() {
        return profundidade;
    }

    public void setProfundidade(BigDecimal profundidade) {
        this.profundidade = profundidade;
    }

    public BigDecimal getAltura() {
        return altura;
    }

    public void setAltura(BigDecimal altura) {
        this.altura = altura;
    }

    public String getSazonal() {
        return sazonal;
    }

    public void setSazonal(String sazonal) {
        this.sazonal = sazonal;
    }

    public Integer getNrEspecificacaoIcms() {
        return nrEspecificacaoIcms;
    }

    public void setNrEspecificacaoIcms(Integer nrEspecificacaoIcms) {
        this.nrEspecificacaoIcms = nrEspecificacaoIcms;
    }

    public Integer getNrEspecificacaoPisCofins() {
        return nrEspecificacaoPisCofins;
    }

    public void setNrEspecificacaoPisCofins(Integer nrEspecificacaoPisCofins) {
        this.nrEspecificacaoPisCofins = nrEspecificacaoPisCofins;
    }

    public Integer getNrEspecificacaoIpi() {
        return nrEspecificacaoIpi;
    }

    public void setNrEspecificacaoIpi(Integer nrEspecificacaoIpi) {
        this.nrEspecificacaoIpi = nrEspecificacaoIpi;
    }

    public String getSituacaoTributariaCompra() {
        return situacaoTributariaCompra;
    }

    public void setSituacaoTributariaCompra(String situacaoTributariaCompra) {
        this.situacaoTributariaCompra = situacaoTributariaCompra;
    }

    public BigDecimal getPrIcmsAjusteMva() {
        return prIcmsAjusteMva;
    }

    public void setPrIcmsAjusteMva(BigDecimal prIcmsAjusteMva) {
        this.prIcmsAjusteMva = prIcmsAjusteMva;
    }

    public BigDecimal getPrReducaoIcmsCompra() {
        return prReducaoIcmsCompra;
    }

    public void setPrReducaoIcmsCompra(BigDecimal prReducaoIcmsCompra) {
        this.prReducaoIcmsCompra = prReducaoIcmsCompra;
    }

    public Integer getNrEspecificacaoCompras() {
        return nrEspecificacaoCompras;
    }

    public void setNrEspecificacaoCompras(Integer nrEspecificacaoCompras) {
        this.nrEspecificacaoCompras = nrEspecificacaoCompras;
    }

    public String getNmUsuarioAlteracao() {
        return nmUsuarioAlteracao;
    }

    public void setNmUsuarioAlteracao(String nmUsuarioAlteracao) {
        this.nmUsuarioAlteracao = nmUsuarioAlteracao;
    }

    public Timestamp getDtAlteracao() {
        return dtAlteracao;
    }

    public void setDtAlteracao(Timestamp dtAlteracao) {
        this.dtAlteracao = dtAlteracao;
    }

    public String getCest() {
        return cest;
    }

    public void setCest(String cest) {
        this.cest = cest;
    }

    public BigDecimal getPrDescontoDemander() {
        return prDescontoDemander;
    }

    public void setPrDescontoDemander(BigDecimal prDescontoDemander) {
        this.prDescontoDemander = prDescontoDemander;
    }

    public String getFci() {
        return fci;
    }

    public void setFci(String fci) {
        this.fci = fci;
    }

    public String getCdFabricante() {
        return cdFabricante;
    }

    public void setCdFabricante(String cdFabricante) {
        this.cdFabricante = cdFabricante;
    }

    public Integer getPisCofinsMonofasico() {
        return pisCofinsMonofasico;
    }

    public void setPisCofinsMonofasico(Integer pisCofinsMonofasico) {
        this.pisCofinsMonofasico = pisCofinsMonofasico;
    }

    public BigDecimal getVlBcIcmsStRet() {
        return vlBcIcmsStRet;
    }

    public void setVlBcIcmsStRet(BigDecimal vlBcIcmsStRet) {
        this.vlBcIcmsStRet = vlBcIcmsStRet;
    }

    public BigDecimal getPrStRet() {
        return prStRet;
    }

    public void setPrStRet(BigDecimal prStRet) {
        this.prStRet = prStRet;
    }

    public BigDecimal getVlIcmsRet() {
        return vlIcmsRet;
    }

    public void setVlIcmsRet(BigDecimal vlIcmsRet) {
        this.vlIcmsRet = vlIcmsRet;
    }

    public BigDecimal getVlIcmsStRet() {
        return vlIcmsStRet;
    }

    public void setVlIcmsStRet(BigDecimal vlIcmsStRet) {
        this.vlIcmsStRet = vlIcmsStRet;
    }

    public String getCdSimilaridade() {
        return cdSimilaridade;
    }

    public void setCdSimilaridade(String cdSimilaridade) {
        this.cdSimilaridade = cdSimilaridade;
    }

    public Integer getVerificadoAutokm() {
        return verificadoAutokm;
    }

    public void setVerificadoAutokm(Integer verificadoAutokm) {
        this.verificadoAutokm = verificadoAutokm;
    }

    public String getDsFabricanteAutokm() {
        return dsFabricanteAutokm;
    }

    public void setDsFabricanteAutokm(String dsFabricanteAutokm) {
        this.dsFabricanteAutokm = dsFabricanteAutokm;
    }

    public BigDecimal getQtFracionada() {
        return qtFracionada;
    }

    public void setQtFracionada(BigDecimal qtFracionada) {
        this.qtFracionada = qtFracionada;
    }

    public String getCdUnidadeFracionada() {
        return cdUnidadeFracionada;
    }

    public void setCdUnidadeFracionada(String cdUnidadeFracionada) {
        this.cdUnidadeFracionada = cdUnidadeFracionada;
    }

    public Short getCdAliquota() {
        return cdAliquota;
    }

    public void setCdAliquota(Short cdAliquota) {
        this.cdAliquota = cdAliquota;
    }

    public Integer getCdProdutoAgrupador() {
        return cdProdutoAgrupador;
    }

    public void setCdProdutoAgrupador(Integer cdProdutoAgrupador) {
        this.cdProdutoAgrupador = cdProdutoAgrupador;
    }

    public String getObsLojaVirtual() {
        return obsLojaVirtual;
    }

    public void setObsLojaVirtual(String obsLojaVirtual) {
        this.obsLojaVirtual = obsLojaVirtual;
    }

    public Integer getIcmsStRetidoAnteriormente() {
        return icmsStRetidoAnteriormente;
    }

    public void setIcmsStRetidoAnteriormente(Integer icmsStRetidoAnteriormente) {
        this.icmsStRetidoAnteriormente = icmsStRetidoAnteriormente;
    }
}