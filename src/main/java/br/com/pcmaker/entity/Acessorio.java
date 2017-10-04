package br.com.pcmaker.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity 
@Table(name = "acessorio")
public class Acessorio extends AutoIncrementIdEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "nome", nullable = false)
	private String nome;
	
	private List<OrdemServicoAcessorio> ordemServicoAcessorio = new ArrayList<OrdemServicoAcessorio>(0);

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@JsonIgnore
	@OneToMany(fetch=FetchType.LAZY, mappedBy="acessorio")
	public List<OrdemServicoAcessorio> getOrdemServicoAcessorio() {
		return ordemServicoAcessorio;
	}

	public void setOrdemServicoAcessorio(List<OrdemServicoAcessorio> ordemServicoAcessorio) {
		this.ordemServicoAcessorio = ordemServicoAcessorio;
	}

	@Override
	public String toString() {
		return nome;
	}
	
}