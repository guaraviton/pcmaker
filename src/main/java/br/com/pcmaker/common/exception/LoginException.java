package br.com.pcmaker.common.exception;

/**
 * Excecao generica para representar problema na autenticacao do usuario
 * 
 * @version 1.0
 * @author y2jm - FABIO PAIVA
 * 
 */
public class LoginException extends DAOException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param message
	 */
	public LoginException(String chaveUsuario) {
		super("Usuário " + chaveUsuario + " ou senha inválido(s).");
	}

	/**
	 * 
	 * @param cause
	 */
	public LoginException(Throwable cause) {
		super(cause);
	}

}
