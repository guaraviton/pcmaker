package br.com.pcmaker.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

@Immutable
@Entity 
@Table(name = "vw_timeline_ordem_servico")
public class TimelineOrdemServico extends IdEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer idOrdemServico;
	
	private String nomeEtapa;
	
	private Date dataInclusaoEtapa;
	
	private String loginUsuarioEtapa;

	@Column(name = "id_ordem_servico")
	public Integer getIdOrdemServico() {
		return idOrdemServico;
	}

	public void setIdOrdemServico(Integer idOrdemServico) {
		this.idOrdemServico = idOrdemServico;
	}

	@Column(name = "nome_etapa")
	public String getNomeEtapa() {
		return nomeEtapa;
	}

	public void setNomeEtapa(String nomeEtapa) {
		this.nomeEtapa = nomeEtapa;
	}

	@Column(name = "data_inclusao_etapa")
	public Date getDataInclusaoEtapa() {
		return dataInclusaoEtapa;
	}

	public void setDataInclusaoEtapa(Date dataInclusaoEtapa) {
		this.dataInclusaoEtapa = dataInclusaoEtapa;
	}

	@Column(name = "login_usuario_etapa")
	public String getLoginUsuarioEtapa() {
		return loginUsuarioEtapa;
	}

	public void setLoginUsuarioEtapa(String loginUsuarioEtapa) {
		this.loginUsuarioEtapa = loginUsuarioEtapa;
	}

	@Id
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}
}
