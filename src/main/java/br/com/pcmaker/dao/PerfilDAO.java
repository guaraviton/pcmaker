package br.com.pcmaker.dao;

import java.util.List;

import br.com.pcmaker.entity.Perfil;

public interface PerfilDAO extends CrudDAO<Perfil>{

	List<Perfil> query(String nome, Integer idUsuario);

}
