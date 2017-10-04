package br.com.pcmaker.service;

import java.util.List;

import javax.transaction.Transactional;

import br.com.pcmaker.entity.AcompanhamentoOrdemServico;

public interface AcompanhamentoOrdemServicoService extends CrudService<AcompanhamentoOrdemServico>{

	@Transactional
	AcompanhamentoOrdemServico salvar(Integer idOrdemServico, String texto);
	
	List<AcompanhamentoOrdemServico> query(Integer idOrdemServico);

}
