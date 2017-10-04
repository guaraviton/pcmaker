package br.com.pcmaker.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.pcmaker.common.util.StringUtils;
import br.com.pcmaker.dao.ConfiguracaoDAO;
import br.com.pcmaker.entity.Configuracao;

@Repository
public class ConfiguracaoDaoImpl extends CrudDAOImpl<Configuracao> implements ConfiguracaoDAO{

	@SuppressWarnings("unchecked")
	@Override
	public List<Configuracao> query(String chave) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Configuracao.class);
		if(StringUtils.isNotBlank(chave)){
			criteria.add(Restrictions.like("chave", chave, MatchMode.ANYWHERE));
		}
		criteria.add(Restrictions.eq("parametro", "S"));
		criteria.addOrder(Order.asc("chave"));
        return (List<Configuracao>) template.findByCriteria(criteria);
	}
}
