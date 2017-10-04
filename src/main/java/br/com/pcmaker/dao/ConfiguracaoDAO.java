package br.com.pcmaker.dao;

import java.util.List;

import br.com.pcmaker.entity.Configuracao;

public interface ConfiguracaoDAO extends CrudDAO<Configuracao>{
	
	List<Configuracao> query(String chave);
}
