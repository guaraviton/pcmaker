package br.com.pcmaker.dao;

import java.util.List;

import br.com.pcmaker.entity.TimelineOrdemServico;

public interface TimelineOrdemServicoDAO{
	
	List<TimelineOrdemServico> query(Integer idOrdemServico);
	
}
