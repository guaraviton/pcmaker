package br.com.pcmaker.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import br.com.pcmaker.dao.DAO;
import br.com.pcmaker.dao.OrdemServicoAtivaDAO;
import br.com.pcmaker.entity.OrdemServicoAtiva;

@Repository
public class OrdemServicoAtivaDaoImpl extends DAO implements OrdemServicoAtivaDAO{

	@SuppressWarnings("unchecked")
	public List<OrdemServicoAtiva> query() {
        DetachedCriteria criteria = DetachedCriteria.forClass(OrdemServicoAtiva.class);
		criteria.addOrder(Order.asc("numeroOrdemServico"));
        return (List<OrdemServicoAtiva>) template.findByCriteria(criteria);
	}

}