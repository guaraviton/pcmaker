package br.com.pcmaker.service;

import java.util.List;

import javax.transaction.Transactional;

import br.com.pcmaker.entity.OrdemServico;

public interface OrdemServicoService extends CrudService<OrdemServico>{

	List<OrdemServico> query(String numero, Integer idCliente, Integer idEquipamento, Integer idEtapa);

	@Transactional
	void cancelar(Integer id);
	
	@Transactional
	void finalizarOrcamento(OrdemServico ordemServico);

	@Transactional
	void iniciarOrcamento(OrdemServico ordemServico);

	@Transactional
	void aprovarOrcamento(Integer id);

	@Transactional
	void reprovarOrcamento(Integer id);

	@Transactional
	void iniciarExecucaoServico(OrdemServico ordemServico);

	@Transactional
	void finalizarExecucaoServico(OrdemServico ordemServico);

	void liberarEquipamentoRetirada(Integer id);

	@Transactional
	void finalizarOrdemServico(OrdemServico ordemServico);

	void enviarEmailRetirada(Integer id);

	void enviarEmailAberturaOrdemServico(Integer id);

	Long queryNumeroTotal();

}
