package br.com.pcmaker.controller.dto;

import java.util.List;

public class EmailMarketingDTO {

	private String texto;
	private String assunto;
	private Boolean enviarTodosClientes;
	private List<String> emailDestinatarios;
	
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public String getAssunto() {
		return assunto;
	}
	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}
	public Boolean getEnviarTodosClientes() {
		return enviarTodosClientes;
	}
	public void setEnviarTodosClientes(Boolean enviarTodosClientes) {
		this.enviarTodosClientes = enviarTodosClientes;
	}
	public List<String> getEmailDestinatarios() {
		return emailDestinatarios;
	}
	public void setEmailDestinatarios(List<String> emailDestinatarios) {
		this.emailDestinatarios = emailDestinatarios;
	}
}
