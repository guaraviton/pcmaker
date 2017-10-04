package br.com.pcmaker.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.pcmaker.common.util.StringUtils;
import br.com.pcmaker.dao.EquipamentoDAO;
import br.com.pcmaker.entity.Equipamento;

@Repository
public class EquipamentoDaoImpl extends CrudDAOImpl<Equipamento> implements EquipamentoDAO{

	@SuppressWarnings("unchecked")
	@Override
	public List<Equipamento> query(String nome) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Equipamento.class);
		if(StringUtils.isNotBlank(nome)){
			criteria.add(Restrictions.like("nome", nome, MatchMode.ANYWHERE));
		}
		criteria.addOrder(Order.asc("nome"));
        return (List<Equipamento>) template.findByCriteria(criteria);
	}
	
}
