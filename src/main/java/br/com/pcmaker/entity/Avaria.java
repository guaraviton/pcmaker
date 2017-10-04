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
@Table(name = "avaria")
public class Avaria extends AutoIncrementIdEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "nome", nullable = false)
	private String nome;
	
	private List<OrdemServicoAvaria> ordemServicoAvaria = new ArrayList<OrdemServicoAvaria>(0);

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@JsonIgnore
	@OneToMany(fetch=FetchType.LAZY, mappedBy="avaria")
	public List<OrdemServicoAvaria> getOrdemServicoAvaria() {
		return ordemServicoAvaria;
	}

	public void setOrdemServicoAvaria(List<OrdemServicoAvaria> ordemServicoAvaria) {
		this.ordemServicoAvaria = ordemServicoAvaria;
	}

	@Override
	public String toString() {
		return nome;
	}
	
}