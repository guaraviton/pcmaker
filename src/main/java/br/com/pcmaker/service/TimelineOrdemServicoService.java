package br.com.pcmaker.service;

import java.util.List;

import br.com.pcmaker.entity.TimelineOrdemServico;

public interface TimelineOrdemServicoService{

	List<TimelineOrdemServico> query(Integer idOrdemServico);
	
}
