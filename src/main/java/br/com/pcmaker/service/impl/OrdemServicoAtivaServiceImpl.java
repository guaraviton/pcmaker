package br.com.pcmaker.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pcmaker.dao.OrdemServicoAtivaDAO;
import br.com.pcmaker.entity.OrdemServicoAtiva;
import br.com.pcmaker.service.OrdemServicoAtivaService;

@Service
public class OrdemServicoAtivaServiceImpl implements OrdemServicoAtivaService {
	
	@Autowired
	OrdemServicoAtivaDAO ordemServicoAtivaDAO;

	@Override
	public List<OrdemServicoAtiva> query() {
		return ordemServicoAtivaDAO.query();
	}
}
