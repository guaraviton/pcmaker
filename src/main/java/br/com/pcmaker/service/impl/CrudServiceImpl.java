package br.com.pcmaker.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.pcmaker.dao.CrudDAO;
import br.com.pcmaker.entity.BaseEntity;
import br.com.pcmaker.service.CrudService;

@Service
public abstract class CrudServiceImpl<T extends BaseEntity> implements CrudService<T>{

	public abstract CrudDAO<T> getDAO();
	
	@Transactional
	public T salvar(T entity) {
		return getDAO().salvar(entity);
	}
	
	@Transactional
	public void excluir(Integer id) {
		T entity = getDAO().get(id);
		getDAO().excluir(entity);
	}
	
	@Transactional
	public void inativar(Integer id) {
		T entity = getDAO().get(id);
		getDAO().inativar(entity);
	}
	
	@Transactional
	public void ativar(Integer id) {
		T entity = getDAO().get(id);
		getDAO().ativar(entity);
	}
	
	@Override
	public T get(Integer id) {
		return getDAO().get(id);
	}
	
	@Override
	public List<T> query() {
		return getDAO().query();
	}
}
