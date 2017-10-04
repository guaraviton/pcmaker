package br.com.pcmaker.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pcmaker.dao.CrudDAO;
import br.com.pcmaker.dao.EquipamentoDAO;
import br.com.pcmaker.entity.Equipamento;
import br.com.pcmaker.service.EquipamentoService;

@Service
public class EquipamentoServiceImpl extends CrudServiceImpl<Equipamento> implements EquipamentoService {
	
	@Autowired
	EquipamentoDAO equipamentoDAO;

	@Override
	public CrudDAO<Equipamento> getDAO() {
		return equipamentoDAO;
	}

	@Override
	public List<Equipamento> query(String nome) {
		return equipamentoDAO.query(nome);
	}

}
