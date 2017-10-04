package br.com.pcmaker.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pcmaker.dao.CrudDAO;
import br.com.pcmaker.dao.EtapaOrdemServicoDAO;
import br.com.pcmaker.entity.EtapaOrdemServico;
import br.com.pcmaker.service.EtapaOrdemServicoService;

@Service
public class EtapaOrdemServicoServiceImpl extends CrudServiceImpl<EtapaOrdemServico> implements EtapaOrdemServicoService {
	
	@Autowired
	EtapaOrdemServicoDAO etapaOrdemServicoDAO;

	@Override
	public CrudDAO<EtapaOrdemServico> getDAO() {
		return etapaOrdemServicoDAO;
	}

}
