package br.com.pcmaker.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pcmaker.dao.AvariaDAO;
import br.com.pcmaker.dao.CrudDAO;
import br.com.pcmaker.entity.Avaria;
import br.com.pcmaker.service.AvariaService;

@Service
public class AvariaServiceImpl extends CrudServiceImpl<Avaria> implements AvariaService {
	
	@Autowired
	AvariaDAO avariaDAO;

	@Override
	public CrudDAO<Avaria> getDAO() {
		return avariaDAO;
	}

	@Override
	public List<Avaria> query(String nome, Integer idOrdemServico) {
		return avariaDAO.query(nome, idOrdemServico);
	}

}
