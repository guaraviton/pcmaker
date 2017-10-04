package br.com.pcmaker.controller.dto;

public class LoginDTO {
	
	private String chave;
	private String senha;
	
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getChave() {
		return chave;
	}
	public void setChave(String chave) {
		this.chave = chave;
	}

}
