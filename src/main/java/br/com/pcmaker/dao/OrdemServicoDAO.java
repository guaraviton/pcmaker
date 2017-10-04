package br.com.pcmaker.dao;

import java.util.List;

import br.com.pcmaker.entity.OrdemServico;

public interface OrdemServicoDAO extends CrudDAO<OrdemServico>{

	List<OrdemServico> query(String numero, Integer idCliente, Integer idEquipamento, Integer idEtapa);

	Long queryNumeroTotal();
	
}
