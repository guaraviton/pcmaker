		package br.com.pcmaker.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pcmaker.dao.AcompanhamentoOrdemServicoDAO;
import br.com.pcmaker.dao.CrudDAO;
import br.com.pcmaker.entity.AcompanhamentoOrdemServico;
import br.com.pcmaker.entity.OrdemServico;
import br.com.pcmaker.service.AcompanhamentoOrdemServicoService;
import br.com.pcmaker.service.OrdemServicoService;
import br.com.pcmaker.spring.config.security.authentication.AuthenticationFacade;

@Service
public class AcompanhamentoOrdemServicoServiceImpl extends CrudServiceImpl<AcompanhamentoOrdemServico> implements AcompanhamentoOrdemServicoService {
	
	@Autowired
	AcompanhamentoOrdemServicoDAO acompanhamentoOrdemServicoDAO;

	@Autowired
	OrdemServicoService ordemServicoService;
	
	@Autowired
	AuthenticationFacade authenticationFacade;
	
	@Override
	public CrudDAO<AcompanhamentoOrdemServico> getDAO() {
		return acompanhamentoOrdemServicoDAO;
	}

	@Override
	public AcompanhamentoOrdemServico salvar(Integer idOrdemServico, String texto) {
		OrdemServico ordemServico = ordemServicoService.get(idOrdemServico);
		AcompanhamentoOrdemServico acompanhamentoOrdemServico = new AcompanhamentoOrdemServico(ordemServico, authenticationFacade.getUsuario(), texto);
		return salvar(acompanhamentoOrdemServico);
	}

	@Override
	public List<AcompanhamentoOrdemServico> query(Integer idOrdemServico) {
		return acompanhamentoOrdemServicoDAO.query(idOrdemServico);
	}
}
