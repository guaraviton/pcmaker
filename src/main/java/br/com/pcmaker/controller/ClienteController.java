package br.com.pcmaker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.pcmaker.common.util.StringUtils;
import br.com.pcmaker.entity.Cliente;
import br.com.pcmaker.service.ClienteService;
import br.com.pcmaker.service.CrudService;

@RestController
@RequestMapping("/cliente")
public class ClienteController extends CrudController<Cliente>{
	
	@Autowired
	ClienteService service;

	@Override
	public CrudService<Cliente> getService() {
		return service;
	}
	
	@RequestMapping(method = RequestMethod.GET)
    public List<Cliente> query(@RequestParam(required=false, defaultValue="") String nome, @RequestParam(required=false, defaultValue="") String cpf, @RequestParam(required=false, defaultValue="") String nomeEmailCpfLike) {
        return service.query(nome, cpf, StringUtils.decode(nomeEmailCpfLike));
    }
}