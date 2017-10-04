package br.com.pcmaker.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import br.com.pcmaker.dao.CrudDAO;
import br.com.pcmaker.dao.DAO;
import br.com.pcmaker.entity.IdEntity;
import br.com.pcmaker.entity.StatusRegistroEntity;
import br.com.pcmaker.enums.StatusRegistro;

public abstract class CrudDAOImpl<T extends IdEntity> extends DAO implements CrudDAO<T>{

	Class<T> entityClass = null;
	
	@SuppressWarnings("unchecked") 
	public CrudDAOImpl() {
		this.entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
	
	@Override
	public T salvar(T entidade){
		return template.merge(entidade);
	}

	public void excluir(T entidade){
		StatusRegistroEntity entidadeExcluida = (StatusRegistroEntity) entidade;
		entidadeExcluida.setStatusRegistro(StatusRegistro.EXCLUIDO.getCodigo());
		template.update(entidadeExcluida);
	}
	
	public void inativar(T entidade){
		StatusRegistroEntity entidadeExcluida = (StatusRegistroEntity) entidade;
		entidadeExcluida.setStatusRegistro(StatusRegistro.INATIVO.getCodigo());
		template.update(entidadeExcluida);
	}
	
	public void ativar(T entidade){
		StatusRegistroEntity entidadeExcluida = (StatusRegistroEntity) entidade;
		entidadeExcluida.setStatusRegistro(StatusRegistro.ATIVO.getCodigo());
		template.update(entidadeExcluida);
	}
	
	@SuppressWarnings("unchecked") 
	@Override
	public T get(Integer id) {
		DetachedCriteria criteria = DetachedCriteria.forClass(entityClass);
		criteria.add(Restrictions.eq("id", id));
		List<T> retorno = (List<T>) template.findByCriteria(criteria);
	    return retorno.isEmpty() ? null : retorno.get(0);
	}
	
	@Override
	public List<T> query() {
        return (List<T>) template.loadAll(entityClass);
	}
	
}