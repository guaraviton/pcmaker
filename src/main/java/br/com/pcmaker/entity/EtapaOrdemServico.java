package br.com.pcmaker.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity 
@Table(name = "etapa_ordem_servico")
public class EtapaOrdemServico extends AutoIncrementIdEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Date dataInclusao;

	private Etapa etapa;
	
	private OrdemServico ordemServico;
	
	private Usuario usuario;
	
	public EtapaOrdemServico() {
	}
	
	public EtapaOrdemServico(Etapa etapa, OrdemServico ordemServico, Usuario usuario) {
		this.etapa = etapa;
		this.ordemServico = ordemServico;
		this.usuario = usuario;
		this.dataInclusao = new Date();
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "etapa_id", nullable = false)
	public Etapa getEtapa() {
		return etapa;
	}
	public void setEtapa(Etapa etapa) {
		this.etapa = etapa;
	}
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ordem_servico_id", nullable = false)
	public OrdemServico getOrdemServico() {
		return ordemServico;
	}
	public void setOrdemServico(OrdemServico ordemServico) {
		this.ordemServico = ordemServico;
	}
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "usuario_id", nullable = false)
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	@Column(name = "data_inclusao", nullable = false)
	public Date getDataInclusao() {
		return dataInclusao;
	}
	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}
}