package br.com.pcmaker.dao;

import java.util.List;

import br.com.pcmaker.entity.Acessorio;

public interface AcessorioDAO extends CrudDAO<Acessorio>{
	
	List<Acessorio> query(String nome, Integer idOrdemServico);
}
