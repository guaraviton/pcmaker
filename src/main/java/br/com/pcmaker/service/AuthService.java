package br.com.pcmaker.service;

import br.com.pcmaker.controller.dto.UsuarioDTO;

public interface AuthService{
	String login(String chaveUsuario, String senhaUsuario);
	UsuarioDTO get();
	String refresh();
}
