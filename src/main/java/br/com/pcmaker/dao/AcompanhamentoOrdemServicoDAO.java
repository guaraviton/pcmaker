package br.com.pcmaker.dao;

import java.util.List;

import br.com.pcmaker.entity.AcompanhamentoOrdemServico;

public interface AcompanhamentoOrdemServicoDAO extends CrudDAO<AcompanhamentoOrdemServico>{

	List<AcompanhamentoOrdemServico> query(Integer idOrdemServico);

}
