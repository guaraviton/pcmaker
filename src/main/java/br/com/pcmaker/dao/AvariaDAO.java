package br.com.pcmaker.dao;

import java.util.List;

import br.com.pcmaker.entity.Avaria;

public interface AvariaDAO extends CrudDAO<Avaria>{
	
	List<Avaria> query(String nome, Integer idOrdemServico);
}
