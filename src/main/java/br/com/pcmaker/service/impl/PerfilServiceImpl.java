package br.com.pcmaker.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pcmaker.dao.CrudDAO;
import br.com.pcmaker.dao.PerfilDAO;
import br.com.pcmaker.entity.Perfil;
import br.com.pcmaker.service.PerfilService;

@Service
public class PerfilServiceImpl extends CrudServiceImpl<Perfil> implements PerfilService{

	@Autowired
	private PerfilDAO dao;
	
	@Override
	public CrudDAO<Perfil> getDAO() {
		return dao;
	}

	@Override
	public List<Perfil> query(String nome) {
		return dao.query(nome, null);
	}

	@Override
	public List<Perfil> query(Integer idUsuario) {
		return dao.query(null, idUsuario);
	}
}
