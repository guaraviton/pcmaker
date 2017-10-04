package br.com.pcmaker.controller.dto;

import java.io.Serializable;

public class PerfilDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String nome;

	public PerfilDTO(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
