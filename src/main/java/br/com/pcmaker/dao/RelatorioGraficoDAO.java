package br.com.pcmaker.dao;

import java.util.List;

import br.com.pcmaker.entity.RelatorioGrafico;

public interface RelatorioGraficoDAO{
	
	List<RelatorioGrafico> queryOrdensServicoPorEquipamento();
	
	List<RelatorioGrafico> queryOrdensServicoPorMesAno(Integer ano);

	List<RelatorioGrafico> queryFaturamentoMesAno(Integer ano);
	
}
