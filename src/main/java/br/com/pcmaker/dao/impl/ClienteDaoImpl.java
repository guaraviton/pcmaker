package br.com.pcmaker.dao.impl;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.pcmaker.common.util.StringUtils;
import br.com.pcmaker.dao.ClienteDAO;
import br.com.pcmaker.entity.Cliente;

@Repository
public class ClienteDaoImpl extends CrudDAOImpl<Cliente> implements ClienteDAO{

	@SuppressWarnings("unchecked") 
	@Override
	public List<Cliente> query(String nome, String cpf, String nomeEmailCpfLike) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Cliente.class);
		if(StringUtils.isNotBlank(nome)){
			criteria.add(Restrictions.like("nome", nome, MatchMode.ANYWHERE));
		}
		if(StringUtils.isNotBlank(cpf)){
			criteria.add(Restrictions.like("cpf", cpf, MatchMode.ANYWHERE));
		}
		if(StringUtils.isNotBlank(nomeEmailCpfLike)){
			Criterion cpfLike= Restrictions.like("cpf", nomeEmailCpfLike, MatchMode.START);
			Criterion nomeLike= Restrictions.like("nome", nomeEmailCpfLike, MatchMode.ANYWHERE);
			Criterion emailLike= Restrictions.like("email", nomeEmailCpfLike, MatchMode.ANYWHERE);
			criteria.add(Restrictions.or(cpfLike, nomeLike, emailLike));
		}
		criteria.addOrder(Order.asc("nome"));
        return (List<Cliente>) template.findByCriteria(criteria);
	}
}