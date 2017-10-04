package br.com.pcmaker.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pcmaker.dao.RelatorioGraficoDAO;
import br.com.pcmaker.entity.RelatorioGrafico;
import br.com.pcmaker.service.RelatorioGraficoService;

@Service
public class RelatorioGraficoServiceImpl implements RelatorioGraficoService {

	@Autowired
	RelatorioGraficoDAO relatorioGraficoDAO;
	
	@Override
	public List<RelatorioGrafico> queryOrdensServicoPorEquipamento() {
		return relatorioGraficoDAO.queryOrdensServicoPorEquipamento();
	}

	@Override
	public List<RelatorioGrafico> queryOrdensServicoPorMesAno(Integer ano) {
		return relatorioGraficoDAO.queryOrdensServicoPorMesAno(ano);
	}

	@Override
	public List<RelatorioGrafico> queryFaturamentoMesAno(Integer ano) {
		return relatorioGraficoDAO.queryFaturamentoMesAno(ano);
	}
}
