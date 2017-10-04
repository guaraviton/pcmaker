package br.com.pcmaker.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity 
@Table(name = "etapa")
public class Etapa extends AutoIncrementIdEntity {
	
	public Etapa() {
	}
	
	public Etapa(Integer id, String nome) {
		setId(id);
		setNome(nome);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "nome", nullable = false)
	private String nome;
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
}