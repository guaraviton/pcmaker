package br.com.pcmaker.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.pcmaker.dao.AcompanhamentoOrdemServicoDAO;
import br.com.pcmaker.entity.AcompanhamentoOrdemServico;

@Repository
public class AcompanhamentoOrdemServicoDaoImpl extends CrudDAOImpl<AcompanhamentoOrdemServico> implements AcompanhamentoOrdemServicoDAO{

	@SuppressWarnings("unchecked")
	@Override
	public List<AcompanhamentoOrdemServico> query(Integer idOrdemServico) {
		DetachedCriteria criteria = DetachedCriteria.forClass(AcompanhamentoOrdemServico.class);
		criteria.add(Restrictions.eq("ordemServico.id", idOrdemServico));
		criteria.addOrder(Order.asc("dataInclusao"));
        return (List<AcompanhamentoOrdemServico>) template.findByCriteria(criteria);
	}



}