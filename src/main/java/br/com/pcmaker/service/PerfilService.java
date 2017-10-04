package br.com.pcmaker.service;

import java.util.List;

import br.com.pcmaker.entity.Perfil;

public interface PerfilService extends CrudService<Perfil>{
	
	static final String ADMIN = "ADMIN";

	List<Perfil> query(String nome);
	
	List<Perfil> query(Integer idUsuario);

}
