package br.com.pcmaker.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity 
@Table(name = "equipamento")
public class Equipamento extends AutoIncrementIdEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private String nome;

	
	private String corReferencia;
	
	@Column(name = "nome", nullable = false)
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(name = "cor_referencia")
	public String getCorReferencia() {
		return corReferencia;
	}

	public void setCorReferencia(String corReferencia) {
		this.corReferencia = corReferencia;
	}
	
}