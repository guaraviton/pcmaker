package br.com.pcmaker.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pcmaker.dao.TimelineOrdemServicoDAO;
import br.com.pcmaker.entity.TimelineOrdemServico;
import br.com.pcmaker.service.TimelineOrdemServicoService;

@Service
public class TimelineOrdemServicoServiceImpl implements TimelineOrdemServicoService {
	
	@Autowired
	TimelineOrdemServicoDAO timelineOrdemServicoDAO;

	@Override
	public List<TimelineOrdemServico> query(Integer idOrdemServico) {
		return timelineOrdemServicoDAO.query(idOrdemServico);
	}
}
