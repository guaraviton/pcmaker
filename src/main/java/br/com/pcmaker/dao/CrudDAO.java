package br.com.pcmaker.dao;

import java.util.List;

import br.com.pcmaker.entity.BaseEntity;

public interface CrudDAO<T extends BaseEntity> {

	T salvar(T entidade);
	
	T get(Integer id);
	
	void excluir(T entidade);
	
	void inativar(T entidade);
	
	void ativar(T entidade);
	
	List<T> query();

}
