package br.com.pcmaker.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pcmaker.dao.AcessorioDAO;
import br.com.pcmaker.dao.CrudDAO;
import br.com.pcmaker.entity.Acessorio;
import br.com.pcmaker.service.AcessorioService;

@Service
public class AcessorioServiceImpl extends CrudServiceImpl<Acessorio> implements AcessorioService {
	
	@Autowired
	AcessorioDAO acessorioDAO;

	@Override
	public CrudDAO<Acessorio> getDAO() {
		return acessorioDAO;
	}

	@Override
	public List<Acessorio> query(String nome, Integer idOrdemServico) {
		return acessorioDAO.query(nome, idOrdemServico);
	}

}
