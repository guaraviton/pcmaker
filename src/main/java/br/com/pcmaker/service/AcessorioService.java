package br.com.pcmaker.service;

import java.util.List;

import br.com.pcmaker.entity.Acessorio;

public interface AcessorioService extends CrudService<Acessorio>{

	List<Acessorio> query(String nome, Integer idOrdemServico);
	
}
