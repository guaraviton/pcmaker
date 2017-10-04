package br.com.pcmaker.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pcmaker.dao.ConfiguracaoDAO;
import br.com.pcmaker.dao.CrudDAO;
import br.com.pcmaker.entity.Configuracao;
import br.com.pcmaker.service.ConfiguracaoService;

@Service
public class ConfiguracaoServiceImpl extends CrudServiceImpl<Configuracao> implements ConfiguracaoService {
	
	@Autowired
	ConfiguracaoDAO configuracaoDAO;

	@Override
	public CrudDAO<Configuracao> getDAO() {
		return configuracaoDAO;
	}

	@Override
	public List<Configuracao> query(String chave) {
		return configuracaoDAO.query(chave);
	}

}
