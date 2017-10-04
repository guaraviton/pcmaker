package br.com.pcmaker.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ordem_servico_acessorio")
public class OrdemServicoAcessorio extends AutoIncrementIdEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private OrdemServico ordemServico;
	
	private Acessorio acessorio;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ordem_servico_id", nullable = false)
	public OrdemServico getOrdemServico() {
		return ordemServico;
	}

	public void setOrdemServico(OrdemServico ordemServico) {
		this.ordemServico = ordemServico;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "acessorio_id", nullable = false)
	public Acessorio getAcessorio() {
		return acessorio;
	}

	public void setAcessorio(Acessorio acessorio) {
		this.acessorio = acessorio;
	}
}