package br.com.pcmaker.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.pcmaker.common.util.StringUtils;
import br.com.pcmaker.dao.UsuarioDAO;
import br.com.pcmaker.entity.Usuario;
import br.com.pcmaker.enums.StatusRegistro;

@Repository
public class UsuarioDaoImpl extends CrudDAOImpl<Usuario> implements UsuarioDAO{

	@SuppressWarnings("unchecked")
	@Override
	public Usuario get(String login) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Usuario.class);
		criteria.add(Restrictions.eq("login", login));
		criteria.add(Restrictions.eq("statusRegistro", StatusRegistro.ATIVO.getCodigo()));
		List<Usuario> usuarios = (List<Usuario>) template.findByCriteria(criteria);
        return !usuarios.isEmpty() ? (Usuario) usuarios.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Usuario> query(String nome, String login) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Usuario.class);
		if(StringUtils.isNotBlank(nome)){
			criteria.add(Restrictions.like("nome", nome, MatchMode.ANYWHERE));
		}
		if(StringUtils.isNotBlank(login)){
			criteria.add(Restrictions.like("login", login, MatchMode.ANYWHERE));
		}
		criteria.addOrder(Order.asc("nome"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return (List<Usuario>) template.findByCriteria(criteria);
	}
	
}
