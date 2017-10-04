package br.com.pcmaker.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.pcmaker.dao.DAO;
import br.com.pcmaker.dao.TimelineOrdemServicoDAO;
import br.com.pcmaker.entity.TimelineOrdemServico;

@Repository
public class TimelineOrdemServicoDaoImpl extends DAO implements TimelineOrdemServicoDAO{

	@SuppressWarnings("unchecked")
	public List<TimelineOrdemServico> query(Integer idOrdemServico) {
		DetachedCriteria criteria = DetachedCriteria.forClass(TimelineOrdemServico.class);
		criteria.add(Restrictions.eq("idOrdemServico", idOrdemServico));
		criteria.addOrder(Order.asc("dataInclusaoEtapa"));
        return (List<TimelineOrdemServico>) template.findByCriteria(criteria);
	}

}