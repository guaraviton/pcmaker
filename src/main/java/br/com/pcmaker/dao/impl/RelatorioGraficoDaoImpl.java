package br.com.pcmaker.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.pcmaker.dao.DAO;
import br.com.pcmaker.dao.RelatorioGraficoDAO;
import br.com.pcmaker.entity.RelatorioGrafico;
import br.com.pcmaker.enums.EtapaEnum;

@Repository
public class RelatorioGraficoDaoImpl extends DAO implements RelatorioGraficoDAO{

	@SuppressWarnings("unchecked")
	@Override
	public List<RelatorioGrafico> queryOrdensServicoPorEquipamento() {
		List<Object> parametros = new ArrayList<Object>();
		parametros.add(EtapaEnum.CANCELADA.getId());
		parametros.add("S");
		return (List<RelatorioGrafico>) template.findByNamedQuery("queryServicosPorEquipamento", parametros.toArray());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RelatorioGrafico> queryOrdensServicoPorMesAno(Integer ano) {
		List<Object> parametros = new ArrayList<Object>();
		parametros.add(EtapaEnum.ABERTA.getId());
		parametros.add(String.valueOf(ano));
		return (List<RelatorioGrafico>) template.findByNamedQuery("queryOrdensServicoMesAno", parametros.toArray());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RelatorioGrafico> queryFaturamentoMesAno(Integer ano) {
		List<Object> parametros = new ArrayList<Object>();
		parametros.add(EtapaEnum.FINALIZADO.getId());
		parametros.add("S");
		parametros.add(String.valueOf(ano));
		return (List<RelatorioGrafico>) template.findByNamedQuery("queryFaturamentoMesAno", parametros.toArray());
	}
}
