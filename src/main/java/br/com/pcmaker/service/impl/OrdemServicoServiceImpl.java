		package br.com.pcmaker.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pcmaker.common.exception.GlobalException;
import br.com.pcmaker.common.util.DateUtils;
import br.com.pcmaker.common.util.MessageUtils;
import br.com.pcmaker.dao.CrudDAO;
import br.com.pcmaker.dao.OrdemServicoDAO;
import br.com.pcmaker.entity.EtapaOrdemServico;
import br.com.pcmaker.entity.OrdemServico;
import br.com.pcmaker.entity.OrdemServicoAcessorio;
import br.com.pcmaker.entity.OrdemServicoAvaria;
import br.com.pcmaker.enums.EtapaEnum;
import br.com.pcmaker.service.ConfiguracaoService;
import br.com.pcmaker.service.EmailService;
import br.com.pcmaker.service.EtapaOrdemServicoService;
import br.com.pcmaker.service.OrdemServicoService;
import br.com.pcmaker.spring.config.security.authentication.AuthenticationFacade;

@Service
public class OrdemServicoServiceImpl extends CrudServiceImpl<OrdemServico> implements OrdemServicoService {
	
	@Autowired
	OrdemServicoDAO ordemServicoDAO;
	
	@Autowired
	EtapaOrdemServicoService etapaOrdemServicoService;
	
	@Autowired
	AuthenticationFacade authenticationFacade;
	
	@Autowired
	EmailService emailService;
	
	@Autowired
	ConfiguracaoService configuracaoService;

	@Override
	public CrudDAO<OrdemServico> getDAO() {
		return ordemServicoDAO;
	}

	@Override
	public List<OrdemServico> query(String numero, Integer idCliente, Integer idEquipamento, Integer idEtapa) {
		return ordemServicoDAO.query(numero, idCliente, idEquipamento, idEtapa);
	}
	
	@Override
	public OrdemServico salvar(OrdemServico entity) {
		tratarRelacionamento(entity);
		OrdemServico ordemServico = super.salvar(entity);
		if(entity.getId() == null){
			ordemServico.setNumero(getNumeroOrdemServico(ordemServico));
			super.salvar(ordemServico);
			ordemServico.getEtapaOrdemServico().add(inserirEtapa(ordemServico.getId(), EtapaEnum.ABERTA));
		}
		return get(ordemServico.getId());
	}
	
	public void cancelar(Integer id) {
		inserirEtapa(id, EtapaEnum.CANCELADA);
	}
	
	public String getNumeroOrdemServico(OrdemServico entity) {
		int ano = DateUtils.getYear(new Date());
		return String.valueOf(ano) + entity.getId();
	}

	private void tratarRelacionamento(OrdemServico ordemServico) {
		for(OrdemServicoAcessorio ordemServicoAcessorio : ordemServico.getOrdemServicoAcessorio()){
			ordemServicoAcessorio.setOrdemServico(ordemServico);
		}
		for(OrdemServicoAvaria ordemServicoAvaria : ordemServico.getOrdemServicoAvaria()){
			ordemServicoAvaria.setOrdemServico(ordemServico);
		}
	}

	public EtapaOrdemServico inserirEtapa(Integer id, EtapaEnum etapa) {
		OrdemServico ordemServico = get(id);
		if(!isEtapasClean(ordemServico, etapa)){
			throw new GlobalException(MessageUtils.get("erro.registro.desatualizado"));
		}
		EtapaOrdemServico etapaOrdemServico = new EtapaOrdemServico(etapa.getEtapa(), ordemServico, authenticationFacade.getUsuario());
		return etapaOrdemServicoService.salvar(etapaOrdemServico);
	}

	private boolean isEtapasClean(OrdemServico ordemServico, EtapaEnum etapa) {
		if(ordemServico.getEtapaOrdemServicoAtual() == null){
			return true;
		}
		EtapaEnum etapaAtual = EtapaEnum.getEtapaOrdemServico(ordemServico.getEtapaOrdemServicoAtual().getEtapa().getId());
		if(etapa == EtapaEnum.CANCELADA){
			return etapaAtual != EtapaEnum.CANCELADA;
		}
		if(etapa == EtapaEnum.ORCAMENTO_APROVADO_CLIENTE || etapa == EtapaEnum.ORCAMENTO_REPROVADO_CLIENTE){
			return etapaAtual == EtapaEnum.PENDENTE_APROVACAO_ORCAMENTO_CLIENTE;
		}
		if(etapa == EtapaEnum.AGUARDANDO_RETIRADA_CLIENTE){
			return etapaAtual == EtapaEnum.SERVICO_FINALIZADO  || etapaAtual == EtapaEnum.ORCAMENTO_REPROVADO_CLIENTE;
		}

		return etapaAtual.getId() + 1 == etapa.getId();
	}

	@Override
	public void finalizarOrcamento(OrdemServico ordemServico) {
		salvar(ordemServico);
		inserirEtapa(ordemServico.getId(), EtapaEnum.PENDENTE_APROVACAO_ORCAMENTO_CLIENTE);
	}

	@Override
	public void iniciarOrcamento(OrdemServico ordemServico) {
		salvar(ordemServico);
		inserirEtapa(ordemServico.getId(), EtapaEnum.ANALISE_ORCAMENTO);
	}

	@Override
	public void aprovarOrcamento(Integer id) {
		OrdemServico ordemServico = get(id);
		ordemServico.setAprovado("S");
		salvar(ordemServico);
		inserirEtapa(id, EtapaEnum.ORCAMENTO_APROVADO_CLIENTE);
	}

	@Override
	public void reprovarOrcamento(Integer id) {
		OrdemServico ordemServico = get(id);
		ordemServico.setAprovado("N");
		salvar(ordemServico);
		inserirEtapa(id, EtapaEnum.ORCAMENTO_REPROVADO_CLIENTE);
	}

	@Override
	public void iniciarExecucaoServico(OrdemServico ordemServico) {
		salvar(ordemServico);
		inserirEtapa(ordemServico.getId(), EtapaEnum.EXECUTANDO_SERVICO);
	}

	@Override
	public void finalizarExecucaoServico(OrdemServico ordemServico) {
		salvar(ordemServico);
		inserirEtapa(ordemServico.getId(), EtapaEnum.SERVICO_FINALIZADO);
	}

	@Override
	public void liberarEquipamentoRetirada(Integer id) {
		inserirEtapa(id, EtapaEnum.AGUARDANDO_RETIRADA_CLIENTE);
	}

	@Override
	public void finalizarOrdemServico(OrdemServico ordemServico) {
		salvar(ordemServico);
		inserirEtapa(ordemServico.getId(), EtapaEnum.FINALIZADO);
	}

	@Override
	public void enviarEmailRetirada(Integer id) {
		OrdemServico ordemServico = get(id);
		String titulo = "PcMaker - Serviço " + ordemServico.getNumero() + " disponível para retirada";
		String conteudoTexto = configuracaoService.query("texto.email.retirada.equipamento").get(0).getValor();
		conteudoTexto = conteudoTexto.replace("#NUMERO_SERVICO#", ordemServico.getNumero());
		conteudoTexto = conteudoTexto.replace("#DATA_ENTRADA#", DateUtils.format(ordemServico.getEtapaOrdemServicoInicial().getDataInclusao(), DateUtils.PATTERN_YYYY_MM_DD_HH_MM));
		emailService.enviar(ordemServico.getCliente().getEmail(), titulo, conteudoTexto);
	}

	@Override
	public void enviarEmailAberturaOrdemServico(Integer id) {
		OrdemServico ordemServico = get(id);
		String titulo = "PcMaker - Serviço " + ordemServico.getNumero() + " solicitado";
		String conteudoTexto = configuracaoService.query("texto.email.abertura.ordem.servico").get(0).getValor();
		conteudoTexto = conteudoTexto.replace("#NUMERO_SERVICO#", ordemServico.getNumero());
		conteudoTexto = conteudoTexto.replace("#DATA_ENTRADA#", DateUtils.format(ordemServico.getEtapaOrdemServicoInicial().getDataInclusao(), DateUtils.PATTERN_YYYY_MM_DD_HH_MM));
		emailService.enviar(ordemServico.getCliente().getEmail(), titulo, conteudoTexto);
	}

	@Override
	public Long queryNumeroTotal() {
		return ordemServicoDAO.queryNumeroTotal();
	}
}
