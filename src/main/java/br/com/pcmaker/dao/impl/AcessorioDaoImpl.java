package br.com.pcmaker.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Repository;

import br.com.pcmaker.common.util.StringUtils;
import br.com.pcmaker.dao.AcessorioDAO;
import br.com.pcmaker.entity.Acessorio;

@Repository
public class AcessorioDaoImpl extends CrudDAOImpl<Acessorio> implements AcessorioDAO{

	@SuppressWarnings("unchecked")
	@Override
	public List<Acessorio> query(String nome, Integer idOrdemServico) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Acessorio.class);
		if(StringUtils.isNotBlank(nome)){
			criteria.add(Restrictions.like("nome", nome, MatchMode.ANYWHERE));
		}
		if(idOrdemServico != null){
			criteria.createAlias("ordemServicoAcessorio", "ordemServicoAcessorio", JoinType.LEFT_OUTER_JOIN);
			criteria.createAlias("ordemServicoAcessorio.ordemServico", "ordemServico", JoinType.LEFT_OUTER_JOIN);
			criteria.add(Restrictions.eq("ordemServico.id", idOrdemServico));	
		}
        return (List<Acessorio>) template.findByCriteria(criteria);
	}
	
}
