package br.com.pcmaker.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Repository;

import br.com.pcmaker.common.util.StringUtils;
import br.com.pcmaker.dao.AvariaDAO;
import br.com.pcmaker.entity.Avaria;

@Repository
public class AvariaDaoImpl extends CrudDAOImpl<Avaria> implements AvariaDAO{

	@SuppressWarnings("unchecked")
	@Override
	public List<Avaria> query(String nome, Integer idOrdemServico) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Avaria.class);
		if(StringUtils.isNotBlank(nome)){
			criteria.add(Restrictions.like("nome", nome, MatchMode.ANYWHERE));
		}
		if(idOrdemServico != null){
			criteria.createAlias("ordemServicoAvaria", "ordemServicoAvaria", JoinType.LEFT_OUTER_JOIN);
			criteria.createAlias("ordemServicoAvaria.ordemServico", "ordemServico", JoinType.LEFT_OUTER_JOIN);
			criteria.add(Restrictions.eq("ordemServico.id", idOrdemServico));	
		}
		criteria.addOrder(Order.asc("nome"));
        return (List<Avaria>) template.findByCriteria(criteria);
	}
	
}
