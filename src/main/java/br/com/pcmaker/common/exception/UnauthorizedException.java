package br.com.pcmaker.common.exception;

/**
 * Excecao generica para identificar sessao inexistente do usuario
 * 
 * @version 1.0
 * @author y2jm - FABIO PAIVA
 * 
 */
public class UnauthorizedException extends GlobalException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UnauthorizedException(String mensagem) {
		super(mensagem);
	}
	
}
