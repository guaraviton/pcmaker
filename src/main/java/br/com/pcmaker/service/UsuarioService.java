package br.com.pcmaker.service;

import java.util.List;

import br.com.pcmaker.entity.Usuario;

public interface UsuarioService extends CrudService<Usuario>{

	Usuario get(String login);

	List<Usuario> query(String nome, String login);
	
}
