package br.com.pcmaker.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

@Immutable
@Entity 
@Table(name = "vw_ordem_servico_ativa")
public class OrdemServicoAtiva extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_ordem_servico", unique = true)
	private Integer idOrdemServico;
	
	@Column(name = "defeito")
	private String defeito;
	
	@Column(name = "nome_equipamento")
	private String nomeEquipamento;
	
	@Column(name = "cor_referencia_equipamento")
	private String corReferenciaEquipamento;
	
	@Column(name = "numero_ordem_servico")
	private Integer numeroOrdemServico;
	
	@Column(name = "referencia_retorno")
	private String referenciaRetorno;
	
	@Column(name = "aprovado")
	private String aprovado;
	
	@Column(name = "nome_cliente")
	private String nomeCliente;
	
	@Column(name = "email_cliente")
	private String emailCliente;
	
	@Column(name = "telefone_cliente")
	private String telefoneCliente;
	
	@Column(name = "id_etapa", unique = true, nullable = false)
	private Integer idEtapa;
	
	@Column(name = "nome_etapa")
	private String nomeEtapa;
	
	@Column(name = "data_inclusao_etapa")
	private Date dataInclusaoEtapa;
	
	@Column(name = "login_usuario_etapa")
	private String loginUsuarioEtapa;
	
	@Column(name = "numero_dias_etapa")
	private Integer numeroDiasEtapa;
	
	public Integer getIdOrdemServico() {
		return idOrdemServico;
	}
	public void setIdOrdemServico(Integer idOrdemServico) {
		this.idOrdemServico = idOrdemServico;
	}
	public String getNomeCliente() {
		return nomeCliente;
	}
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	public String getNomeEtapa() {
		return nomeEtapa;
	}
	public void setNomeEtapa(String nomeEtapa) {
		this.nomeEtapa = nomeEtapa;
	}
	public Date getDataInclusaoEtapa() {
		return dataInclusaoEtapa;
	}
	public void setDataInclusaoEtapa(Date dataInclusaoEtapa) {
		this.dataInclusaoEtapa = dataInclusaoEtapa;
	}
	public String getLoginUsuarioEtapa() {
		return loginUsuarioEtapa;
	}
	public void setLoginUsuarioEtapa(String loginUsuarioEtapa) {
		this.loginUsuarioEtapa = loginUsuarioEtapa;
	}
	public Integer getIdEtapa() {
		return idEtapa;
	}
	public void setIdEtapa(Integer idEtapa) {
		this.idEtapa = idEtapa;
	}
	public Integer getNumeroOrdemServico() {
		return numeroOrdemServico;
	}
	public void setNumeroOrdemServico(Integer numeroOrdemServico) {
		this.numeroOrdemServico = numeroOrdemServico;
	}
	public String getEmailCliente() {
		return emailCliente;
	}
	public void setEmailCliente(String emailCliente) {
		this.emailCliente = emailCliente;
	}
	public String getTelefoneCliente() {
		return telefoneCliente;
	}
	public void setTelefoneCliente(String telefoneCliente) {
		this.telefoneCliente = telefoneCliente;
	}
	public String getNomeEquipamento() {
		return nomeEquipamento;
	}
	public void setNomeEquipamento(String nomeEquipamento) {
		this.nomeEquipamento = nomeEquipamento;
	}
	public String getCorReferenciaEquipamento() {
		return corReferenciaEquipamento;
	}
	public void setCorReferenciaEquipamento(String corReferenciaEquipamento) {
		this.corReferenciaEquipamento = corReferenciaEquipamento;
	}
	public String getDefeito() {
		return defeito;
	}
	public void setDefeito(String defeito) {
		this.defeito = defeito;
	}
	public Integer getNumeroDiasEtapa() {
		return numeroDiasEtapa;
	}
	public void setNumeroDiasEtapa(Integer numeroDiasEtapa) {
		this.numeroDiasEtapa = numeroDiasEtapa;
	}
	public String getAprovado() {
		return aprovado;
	}
	public void setAprovado(String aprovado) {
		this.aprovado = aprovado;
	}
	public String getReferenciaRetorno() {
		return referenciaRetorno;
	}
	public void setReferenciaRetorno(String referenciaRetorno) {
		this.referenciaRetorno = referenciaRetorno;
	}
}
