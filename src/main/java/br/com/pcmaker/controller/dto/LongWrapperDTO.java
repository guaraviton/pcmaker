package br.com.pcmaker.controller.dto;

public class LongWrapperDTO {
	
	private Long valor;

	public LongWrapperDTO(Long valor) {
		this.valor = valor;
	}

	public Long getValor() {
		return valor;
	}

	public void setValor(Long valor) {
		this.valor = valor;
	}
}
