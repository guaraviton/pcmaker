package br.com.pcmaker.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Repository;

import br.com.pcmaker.common.util.StringUtils;
import br.com.pcmaker.dao.PerfilDAO;
import br.com.pcmaker.entity.Perfil;

@Repository
public class PerfilDAOImpl extends CrudDAOImpl<Perfil> implements PerfilDAO{

	@SuppressWarnings("unchecked")
	@Override
	public List<Perfil> query(String nome, Integer idUsuario) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Perfil.class);
		if(idUsuario != null){
			criteria.createAlias("usuarioPerfil", "usuarioPerfil", JoinType.LEFT_OUTER_JOIN);
			criteria.createAlias("usuarioPerfil.usuario", "usuario", JoinType.LEFT_OUTER_JOIN);
			criteria.add(Restrictions.eq("usuario.id", idUsuario));	
		}
		if(StringUtils.isNotBlank(nome)){
			criteria.add(Restrictions.like("nome", nome, MatchMode.ANYWHERE));
		}
		criteria.addOrder(Order.asc("nome"));
        return (List<Perfil>) template.findByCriteria(criteria);
	}
}
