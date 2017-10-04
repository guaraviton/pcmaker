package br.com.pcmaker.dao;

import java.util.List;

import br.com.pcmaker.entity.Equipamento;

public interface EquipamentoDAO extends CrudDAO<Equipamento>{
	
	List<Equipamento> query(String nome);
}
