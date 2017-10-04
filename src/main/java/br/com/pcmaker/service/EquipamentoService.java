package br.com.pcmaker.service;

import java.util.List;

import br.com.pcmaker.entity.Equipamento;

public interface EquipamentoService extends CrudService<Equipamento>{

	List<Equipamento> query(String nome);
	
}
