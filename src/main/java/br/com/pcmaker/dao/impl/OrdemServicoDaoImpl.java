package br.com.pcmaker.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Repository;

import br.com.pcmaker.common.util.StringUtils;
import br.com.pcmaker.dao.OrdemServicoDAO;
import br.com.pcmaker.entity.EtapaOrdemServico;
import br.com.pcmaker.entity.OrdemServico;

@Repository
public class OrdemServicoDaoImpl extends CrudDAOImpl<OrdemServico> implements OrdemServicoDAO{

	@SuppressWarnings("unchecked")
	@Override
	public List<OrdemServico> query(String numero, Integer idCliente, Integer idEquipamento, Integer idEtapa) {
		DetachedCriteria criteria = DetachedCriteria.forClass(OrdemServico.class, "q1");
		
		criteria.createAlias("etapaOrdemServico", "etapaOrdemServico", JoinType.LEFT_OUTER_JOIN);
		criteria.createAlias("etapaOrdemServico.etapa", "etapa", JoinType.LEFT_OUTER_JOIN);
		
		if(idEtapa != null){
			DetachedCriteria criteriaMaxData = DetachedCriteria.forClass(EtapaOrdemServico.class, "etapaAtual")
			.add(Restrictions.eqProperty("etapaAtual.ordemServico.id","q1.id"))
			.setProjection(Projections.projectionList().add(Projections.max("etapaAtual.dataInclusao")));
			criteria.add(Subqueries.propertyEq("etapaOrdemServico.dataInclusao", criteriaMaxData));
			criteria.add(Restrictions.eq("etapa.id", idEtapa));
		}
		if(StringUtils.isNotBlank(numero)){
			criteria.add(Restrictions.like("numero", numero));
		}
		if(idCliente != null){
			criteria.add(Restrictions.like("cliente.id", idCliente));
		}
		if(idEquipamento != null){
			criteria.add(Restrictions.like("equipamento.id", idEquipamento));
		}
		criteria.addOrder(Order.asc("numero"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		
        return (List<OrdemServico>) template.findByCriteria(criteria);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public OrdemServico get(Integer id) {
		DetachedCriteria criteria = DetachedCriteria.forClass(OrdemServico.class);
		criteria.createAlias("etapaOrdemServico", "etapaOrdemServico", JoinType.LEFT_OUTER_JOIN);
		criteria.add(Restrictions.eq("id", id));
		List<OrdemServico> retorno = (List<OrdemServico>) template.findByCriteria(criteria);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
	    return retorno.isEmpty() ? null : retorno.get(0);
	}

	@Override
	public Long queryNumeroTotal() {
		DetachedCriteria criteria = DetachedCriteria.forClass(OrdemServico.class);
		criteria.setProjection(Projections.rowCount());
		return (Long) template.findByCriteria(criteria).get(0);
	}

}