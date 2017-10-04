package br.com.pcmaker.service;

import java.util.List;

import br.com.pcmaker.entity.Avaria;

public interface AvariaService extends CrudService<Avaria>{

	List<Avaria> query(String nome, Integer idOrdemServico);
	
}
