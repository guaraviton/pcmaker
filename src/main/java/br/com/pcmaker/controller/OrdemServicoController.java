package br.com.pcmaker.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsPdfView;

import br.com.pcmaker.common.util.StringUtils;
import br.com.pcmaker.entity.Acessorio;
import br.com.pcmaker.entity.AcompanhamentoOrdemServico;
import br.com.pcmaker.entity.Avaria;
import br.com.pcmaker.entity.OrdemServico;
import br.com.pcmaker.entity.OrdemServicoAtiva;
import br.com.pcmaker.entity.TimelineOrdemServico;
import br.com.pcmaker.service.AcessorioService;
import br.com.pcmaker.service.AcompanhamentoOrdemServicoService;
import br.com.pcmaker.service.AvariaService;
import br.com.pcmaker.service.CrudService;
import br.com.pcmaker.service.EtapaOrdemServicoService;
import br.com.pcmaker.service.OrdemServicoAtivaService;
import br.com.pcmaker.service.OrdemServicoService;
import br.com.pcmaker.service.TimelineOrdemServicoService;

@RestController
@RequestMapping("/ordemServico")
public class OrdemServicoController extends CrudController<OrdemServico>{
	
	@Autowired
	OrdemServicoService service;
	
	@Autowired
	AcompanhamentoOrdemServicoService acompanhamentoOrdemServicoService;
	
	@Autowired
	AcessorioService acessorioService;
	
	@Autowired
	AvariaService avariaService;
	
	@Autowired
	OrdemServicoAtivaService ordemServicoAtivaService;
	
	@Autowired 
	ApplicationContext appContext;
	
	@Autowired
	EtapaOrdemServicoService etapaOrdemServicoService;
	
	@Autowired
	TimelineOrdemServicoService timelineOrdemServicoService;

	@Override
	public CrudService<OrdemServico> getService() {
		return service;
	}
	
	@RequestMapping(value="/{id}/cancelar", method = RequestMethod.DELETE)
    public OrdemServico cancelar(@PathVariable Integer id) {
		service.cancelar(id);
		return get(id);
    }
	
	@RequestMapping(method = RequestMethod.GET)
    public List<OrdemServico> query(@RequestParam(required=false, defaultValue="") String numero, @RequestParam(required=false, defaultValue="") Integer idCliente, @RequestParam(required=false, defaultValue="") Integer idEquipamento, @RequestParam(required=false, defaultValue="") Integer idEtapa) {
        return service.query(numero, idCliente, idEquipamento, idEtapa);
    }
	
	@RequestMapping(value="/{id}/acessorios", method = RequestMethod.GET)
    public List<Acessorio> getAcessorios(@PathVariable Integer id) {
        return acessorioService.query(null, id);
    }
	
	@RequestMapping(value="/{id}/avarias", method = RequestMethod.GET)
    public List<Avaria> getAvarias(@PathVariable Integer id) {				
        return avariaService.query(null, id);
    }
	
	@RequestMapping(value="/ativas", method = RequestMethod.GET)
    public List<OrdemServicoAtiva> getAtivas() {
        return ordemServicoAtivaService.query();
    }
	
	@RequestMapping(value="/{id}/enviarEmailAberturaOrdemServico", method = RequestMethod.PUT)
    public void enviarEmailAberturaOrdemServico(@PathVariable Integer id) {				
		service.enviarEmailAberturaOrdemServico(id);
    }
	
	@RequestMapping(value="/{id}/orcamento/iniciar", method = RequestMethod.PUT)
    public OrdemServico iniciarOrcamento(@PathVariable Integer id, @Valid @RequestBody OrdemServico ordemServico) {				
		service.iniciarOrcamento(ordemServico);
		return get(id);
    }
	
	@RequestMapping(value="/{id}/orcamento/finalizar", method = RequestMethod.PUT)
    public OrdemServico finalizarOrcamento(@PathVariable Integer id, @Valid @RequestBody OrdemServico ordemServico) {				
		service.finalizarOrcamento(ordemServico);
		return get(id);
    }
	
	@RequestMapping(value="/{id}/orcamento/aprovar", method = RequestMethod.PUT)
    public OrdemServico aprovarOrcamento(@PathVariable Integer id) {				
		service.aprovarOrcamento(id);
		return get(id);
    }
	
	@RequestMapping(value="/{id}/orcamento/reprovar", method = RequestMethod.PUT)
    public OrdemServico reprovarOrcamento(@PathVariable Integer id) {				
		service.reprovarOrcamento(id);
		return get(id);
    }
	
	@RequestMapping(value="/{id}/iniciarExecucaoServico", method = RequestMethod.PUT)
    public OrdemServico iniciarExecucaoServico(@PathVariable Integer id, @Valid @RequestBody OrdemServico ordemServico) {				
		service.iniciarExecucaoServico(ordemServico);
		return get(id);
    }
	
	@RequestMapping(value="/{id}/finalizarExecucaoServico", method = RequestMethod.PUT)
    public OrdemServico finalizarExecucaoServico(@PathVariable Integer id, @Valid @RequestBody OrdemServico ordemServico) {
		service.finalizarExecucaoServico(ordemServico);
		return get(id);
    }
	
	@RequestMapping(value="/{id}/liberarEquipamentoRetirada", method = RequestMethod.PUT)
    public OrdemServico liberarEquipamentoRetirada(@PathVariable Integer id) {				
		service.liberarEquipamentoRetirada(id);
		return get(id);
    }
	
	@RequestMapping(value="/{id}/enviarEmailRetirada", method = RequestMethod.PUT)
    public void enviarEmailRetirada(@PathVariable Integer id) {				
		service.enviarEmailRetirada(id);
    }
	
	@RequestMapping(value="/{id}/finalizarOrdemServico", method = RequestMethod.PUT)
    public OrdemServico finalizarOrdemServico(@PathVariable Integer id, @Valid @RequestBody OrdemServico ordemServico) {				
		service.finalizarOrdemServico(ordemServico);
		return get(id);
    }
	
	@RequestMapping(value="/{id}/timeline", method = RequestMethod.GET)
    public List<TimelineOrdemServico> timeline(@PathVariable Integer id) {				
		return timelineOrdemServicoService.query(id);
    }
	
	@RequestMapping(value="/{id}/acompanhamento", method = RequestMethod.POST)
    public AcompanhamentoOrdemServico acompanhamento(@PathVariable Integer id, @RequestBody Map<String, String> request) {				
		return acompanhamentoOrdemServicoService.salvar(id, request.get("texto"));
    }
	
	@RequestMapping(value="/{id}/acompanhamento", method = RequestMethod.GET)
    public List<AcompanhamentoOrdemServico> getAcompanhamento(@PathVariable Integer id) {				
		return acompanhamentoOrdemServicoService.query(id);
    }
	
	@RequestMapping(value="/{id}/exportar/entradaEquipamento/pdf", method=RequestMethod.GET)
	public ModelAndView entradaEquipamento(@PathVariable Integer id){
		JasperReportsPdfView view = new JasperReportsPdfView();
		view.setUrl("classpath:jasper\\ordemServico.jasper");
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("ordemServico", service.get(id));
		parametros.put("isEntrada", true);
		parametros.put("acessorios", StringUtils.toString(acessorioService.query(null, id)));
		parametros.put("avarias", StringUtils.toString(avariaService.query(null, id)));
	    view.setApplicationContext(appContext);
	    return new ModelAndView(view, parametros);
	}
	
	@RequestMapping(value="/{id}/exportar/saidaEquipamento/pdf", method=RequestMethod.GET)
	public ModelAndView saidaEquipamento(@PathVariable Integer id){
		JasperReportsPdfView view = new JasperReportsPdfView();
		view.setUrl("classpath:jasper\\ordemServico.jasper");
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("ordemServico", service.get(id));
		parametros.put("isSaida", true);
		parametros.put("acessorios", StringUtils.toString(acessorioService.query(null, id)));
		parametros.put("avarias", StringUtils.toString(avariaService.query(null, id)));
	    view.setApplicationContext(appContext);
	    return new ModelAndView(view, parametros);
	}
}