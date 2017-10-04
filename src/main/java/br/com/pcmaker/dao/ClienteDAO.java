package br.com.pcmaker.dao;

import java.util.List;

import br.com.pcmaker.entity.Cliente;

public interface ClienteDAO extends CrudDAO<Cliente>{
	
	List<Cliente> query(String nome, String cpf, String nomeEmailCpfLike);
	
}
