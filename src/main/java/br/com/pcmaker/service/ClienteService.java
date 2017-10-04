package br.com.pcmaker.service;

import java.util.List;

import br.com.pcmaker.entity.Cliente;

public interface ClienteService extends CrudService<Cliente>{

	List<Cliente> query(String nome, String cpf, String nomeEmailCpfLike);

}
