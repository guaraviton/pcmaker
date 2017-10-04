package br.com.pcmaker.service;

import java.util.List;

import br.com.pcmaker.entity.Configuracao;

public interface ConfiguracaoService extends CrudService<Configuracao>{

	List<Configuracao> query(String chave);
	
}
