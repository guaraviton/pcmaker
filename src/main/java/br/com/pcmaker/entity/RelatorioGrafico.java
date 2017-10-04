package br.com.pcmaker.entity;

import javax.persistence.MappedSuperclass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@NamedQueries({
	@NamedQuery(
			name = "queryFaturamentoMesAno",
			query = "select new br.com.pcmaker.entity.RelatorioGrafico(date_format(etapaOrdemServico.dataInclusao, '%m/%Y'), sum(ordemServico.valorOrcamento))"
					+ " from OrdemServico as ordemServico"
					+ " join ordemServico.etapaOrdemServico as etapaOrdemServico"
					+ " where etapaOrdemServico.etapa.id = ?"
					+ " and ordemServico.aprovado = ?"
					+ " and etapaOrdemServico.dataInclusao = ("
					+ " 	select max(dataInclusao) from EtapaOrdemServico as maxEtapaOrdemServico"
					+ " 	where maxEtapaOrdemServico.ordemServico.id = ordemServico.id"
					+ " ) "
					+ " and date_format(etapaOrdemServico.dataInclusao, '%Y') = ?"
					+ " group by date_format(etapaOrdemServico.dataInclusao, '%m/%Y')"
					+ " order by date_format(etapaOrdemServico.dataInclusao, '%m')"
	),
	@NamedQuery(
			name = "queryServicosPorEquipamento",
			query = "select new br.com.pcmaker.entity.RelatorioGrafico(ordemServico.equipamento.nome, count(1))"
					+ " from OrdemServico as ordemServico"
					+ " join ordemServico.etapaOrdemServico as etapaOrdemServico"
					+ " where etapaOrdemServico.etapa.id <> ?"
					+ " and ordemServico.aprovado = ?"
					+ " and etapaOrdemServico.dataInclusao = ("
					+ " 	select max(dataInclusao) from EtapaOrdemServico as maxEtapaOrdemServico"
					+ " 	where maxEtapaOrdemServico.ordemServico.id = ordemServico.id"
					+ " ) "
					+ " group by ordemServico.equipamento.nome"
					+ " order by ordemServico.equipamento.nome"
	),
	@NamedQuery(
			name = "queryOrdensServicoMesAno",
			query = "select new br.com.pcmaker.entity.RelatorioGrafico(date_format(etapaOrdemServico.dataInclusao, '%m/%Y'), count(1))"
					+ " from OrdemServico as ordemServico"
					+ " join ordemServico.etapaOrdemServico as etapaOrdemServico"
					+ " where etapaOrdemServico.id = ("
					+ " 	select innerEtapaOrdemServico.id from EtapaOrdemServico as innerEtapaOrdemServico"
					+ " 	where innerEtapaOrdemServico.ordemServico.id = ordemServico.id"
					+ " 	and innerEtapaOrdemServico.etapa.id = ?"
					+ " ) "
					+ " and date_format(etapaOrdemServico.dataInclusao, '%Y') = ?"
					+ " group by date_format(etapaOrdemServico.dataInclusao, '%m/%Y')"
					+ " order by date_format(etapaOrdemServico.dataInclusao, '%m')"
	)
})

@MappedSuperclass
public class RelatorioGrafico extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String label;
	
	private double value;
	
	public RelatorioGrafico() {
	}
	
	public RelatorioGrafico(String label, double value) {
		this.label = label;
		this.value = value;
	}
	
	public RelatorioGrafico(String label, long value) {
		this.label = label;
		this.value = value;
	}

	public String getLabel() {
		return label;
	}


	public void setLabel(String label) {
		this.label = label;
	}


	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}
	
}