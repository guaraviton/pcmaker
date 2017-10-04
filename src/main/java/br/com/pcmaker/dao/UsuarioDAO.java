package br.com.pcmaker.dao;

import java.util.List;

import br.com.pcmaker.entity.Usuario;

public interface UsuarioDAO extends CrudDAO<Usuario>{
	
	Usuario get(String login);

	List<Usuario> query(String nome, String login);
}
