package br.com.pcmaker.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity 
@Table(name = "ordem_servico")
public class OrdemServico extends AutoIncrementIdEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotEmpty
	private String marca;
	
	@NotEmpty
	private String modelo;
	
	@NotEmpty
	private String serial;
	
	@NotEmpty
	private String defeito;
	
	private String numero;
	
	private String referenciaRetorno;

	@JoinColumn(name = "cliente_id", nullable = false)
	private Cliente cliente;

	@JoinColumn(name = "equipamento_id", nullable = false)
	private Equipamento equipamento;
	
    private Integer versao;
	
	private String descricaoOrcamento;
	
	private Double valorOrcamento;

	private Date dataPagamentoSinal;

	private Double valorSinal;
	
	private String tipoPagamentoSinal;
	
	private Date dataPagamentoRestante;

	private Double valorPagamentoRestante;
	
	private String tipoPagamentoRestante;
	
	private String observacao;
	
	private String aprovado;
	
	private List<EtapaOrdemServico> etapaOrdemServico = new ArrayList<EtapaOrdemServico>(0);
	
	private List<OrdemServicoAcessorio> ordemServicoAcessorio = new ArrayList<OrdemServicoAcessorio>(0);
	
	private List<OrdemServicoAvaria> ordemServicoAvaria = new ArrayList<OrdemServicoAvaria>(0);
	
	@ManyToOne(fetch = FetchType.EAGER)
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	public Equipamento getEquipamento() {
		return equipamento;
	}

	public void setEquipamento(Equipamento equipamento) {
		this.equipamento = equipamento;
	}

	@JsonIgnore
	@OneToMany(fetch=FetchType.LAZY, mappedBy="ordemServico", orphanRemoval = true, cascade = {CascadeType.ALL})
	public List<OrdemServicoAcessorio> getOrdemServicoAcessorio() {
		return ordemServicoAcessorio;
	}

	@JsonProperty
	public void setOrdemServicoAcessorio(List<OrdemServicoAcessorio> ordemServicoAcessorio) {
		this.ordemServicoAcessorio = ordemServicoAcessorio;
	}

	@JsonIgnore
	@OneToMany(fetch=FetchType.LAZY, mappedBy="ordemServico", orphanRemoval = true, cascade = {CascadeType.ALL})
	public List<OrdemServicoAvaria> getOrdemServicoAvaria() {
		return ordemServicoAvaria;
	}
	
	@JsonProperty
	public void setOrdemServicoAvaria(List<OrdemServicoAvaria> ordemServicoAvaria) {
		this.ordemServicoAvaria = ordemServicoAvaria;
	}
	
	@JsonIgnore
	@OneToMany(fetch=FetchType.LAZY, mappedBy="ordemServico")
	@OrderBy("dataInclusao")
	public List<EtapaOrdemServico> getEtapaOrdemServico() {
		return etapaOrdemServico;
	}

	public void setEtapaOrdemServico(List<EtapaOrdemServico> etapaOrdemServico) {
		this.etapaOrdemServico = etapaOrdemServico;
	}
	
	@Transient
	public EtapaOrdemServico getEtapaOrdemServicoAtual() {
		if(etapaOrdemServico == null || etapaOrdemServico.size() == 0){
			return null;
		}
		return etapaOrdemServico.get(etapaOrdemServico.size() - 1);
	}
	
	@Transient
	public EtapaOrdemServico getEtapaOrdemServicoInicial() {
		if(etapaOrdemServico == null || etapaOrdemServico.size() == 0){
			return null;
		}
		return etapaOrdemServico.get(0);
	}
	
	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public String getDefeito() {
		return defeito;
	}

	public void setDefeito(String defeito) {
		this.defeito = defeito;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	@Column(name = "descricao_orcamento")
	public String getDescricaoOrcamento() {
		return descricaoOrcamento;
	}

	public void setDescricaoOrcamento(String descricaoOrcamento) {
		this.descricaoOrcamento = descricaoOrcamento;
	}

	@Column(name = "valor_orcamento")
	public Double getValorOrcamento() {
		return valorOrcamento;
	}

	public void setValorOrcamento(Double valorOrcamento) {
		this.valorOrcamento = valorOrcamento;
	}

	@Column(name = "data_pagamento_sinal")
	public Date getDataPagamentoSinal() {
		return dataPagamentoSinal;
	}

	public void setDataPagamentoSinal(Date dataPagamentoSinal) {
		this.dataPagamentoSinal = dataPagamentoSinal;
	}

	@Column(name = "valor_sinal")
	public Double getValorSinal() {
		return valorSinal;
	}

	public void setValorSinal(Double valorSinal) {
		this.valorSinal = valorSinal;
	}

	@Column(name = "tipo_pagamento_sinal")
	public String getTipoPagamentoSinal() {
		return tipoPagamentoSinal;
	}

	public void setTipoPagamentoSinal(String tipoPagamentoSinal) {
		this.tipoPagamentoSinal = tipoPagamentoSinal;
	}

	@Column(name = "data_pagamento_restante")
	public Date getDataPagamentoRestante() {
		return dataPagamentoRestante;
	}

	public void setDataPagamentoRestante(Date dataPagamentoRestante) {
		this.dataPagamentoRestante = dataPagamentoRestante;
	}

	@Column(name = "valor_pagamento_restante")
	public Double getValorPagamentoRestante() {
		return valorPagamentoRestante;
	}

	public void setValorPagamentoRestante(Double valorPagamentoRestante) {
		this.valorPagamentoRestante = valorPagamentoRestante;
	}

	@Column(name = "tipo_pagamento_restante")
	public String getTipoPagamentoRestante() {
		return tipoPagamentoRestante;
	}

	public void setTipoPagamentoRestante(String tipoPagamentoRestante) {
		this.tipoPagamentoRestante = tipoPagamentoRestante;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getAprovado() {
		return aprovado;
	}

	public void setAprovado(String aprovado) {
		this.aprovado = aprovado;
	}

	@Version
	public Integer getVersao() {
		return versao;
	}

	public void setVersao(Integer versao) {
		this.versao = versao;
	}

	@Column(name = "referencia_retorno")
	public String getReferenciaRetorno() {
		return referenciaRetorno;
	}

	public void setReferenciaRetorno(String referenciaRetorno) {
		this.referenciaRetorno = referenciaRetorno;
	}
	
}