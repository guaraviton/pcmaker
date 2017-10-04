package br.com.pcmaker.service;

import java.util.List;

import br.com.pcmaker.entity.RelatorioGrafico;

public interface RelatorioGraficoService{
	
	List<RelatorioGrafico> queryOrdensServicoPorEquipamento();

	List<RelatorioGrafico> queryOrdensServicoPorMesAno(Integer ano);

	List<RelatorioGrafico> queryFaturamentoMesAno(Integer ano);

}
