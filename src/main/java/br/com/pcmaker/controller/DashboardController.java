package br.com.pcmaker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.pcmaker.controller.dto.LongWrapperDTO;
import br.com.pcmaker.entity.RelatorioGrafico;
import br.com.pcmaker.service.OrdemServicoService;
import br.com.pcmaker.service.RelatorioGraficoService;

@RestController
@RequestMapping("/dashBoard")
public class DashboardController{
	
	@Autowired
	OrdemServicoService ordemServicoService;
	
	@Autowired
	RelatorioGraficoService relatorioGraficoService;

	@RequestMapping(value="numeroOrdensServicoAtendidas", method = RequestMethod.GET)
    public LongWrapperDTO numeroOrdensServicoAtendidas() {
        return new LongWrapperDTO(ordemServicoService.queryNumeroTotal());
    }
	
	@RequestMapping(value="ordensServicoPorEquipamento", method = RequestMethod.GET)
    public List<RelatorioGrafico> ordensServicoPorEquipamento() {
        return relatorioGraficoService.queryOrdensServicoPorEquipamento();
    }
	
	@RequestMapping(value="ordensServicoPorMesAno", method = RequestMethod.GET)
    public List<RelatorioGrafico> queryOrdensServicoPorMesAno(@RequestParam Integer ano) {
        return relatorioGraficoService.queryOrdensServicoPorMesAno(ano);
    }
	
	@RequestMapping(value="faturamentoMesAno", method = RequestMethod.GET)
    public List<RelatorioGrafico> queryFaturamentoMesAno(@RequestParam Integer ano) {
        return relatorioGraficoService.queryFaturamentoMesAno(ano);
    }
}