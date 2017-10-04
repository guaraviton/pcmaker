package br.com.pcmaker.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity 
@Table(name = "configuracao")
public class Configuracao extends AutoIncrementIdEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "chave", nullable = false)
	private String chave;
	
	@Column(name = "valor", nullable = false)
	private String valor;
	
	@Column(name = "descricao", nullable = false)
	private String descricao;
	
	@Column(name = "parametro", nullable = false)
	private String parametro = "S";

	public String getChave() {
		return chave;
	}

	public void setChave(String chave) {
		this.chave = chave;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getParametro() {
		return parametro;
	}

	public void setParametro(String parametro) {
		this.parametro = parametro;
	}


	
}