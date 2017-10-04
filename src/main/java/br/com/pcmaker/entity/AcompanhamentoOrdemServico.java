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
@Table(name = "acompanhamento_ordem_servico")
public class AcompanhamentoOrdemServico extends AutoIncrementIdEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Date dataInclusao;

	private OrdemServico ordemServico;
	
	private Usuario usuario;
	
	private String texto;
	
	public AcompanhamentoOrdemServico() {
	}
	
	public AcompanhamentoOrdemServico(OrdemServico ordemServico, Usuario usuario, String texto) {
		this.ordemServico = ordemServico;
		this.usuario = usuario;
		this.texto = texto;
		this.dataInclusao = new Date();
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

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}
}