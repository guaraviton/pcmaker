package br.com.pcmaker.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ordem_servico_avaria")
public class OrdemServicoAvaria extends AutoIncrementIdEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private OrdemServico ordemServico;
	
	@JoinColumn(name = "avaria_id", nullable = false)
	private Avaria avaria;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ordem_servico_id", nullable = false)
	public OrdemServico getOrdemServico() {
		return ordemServico;
	}

	public void setOrdemServico(OrdemServico ordemServico) {
		this.ordemServico = ordemServico;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	public Avaria getAvaria() {
		return avaria;
	}

	public void setAvaria(Avaria avaria) {
		this.avaria = avaria;
	}

}