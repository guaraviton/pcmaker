package br.com.pcmaker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.pcmaker.entity.Acessorio;
import br.com.pcmaker.service.AcessorioService;
import br.com.pcmaker.service.CrudService;

@RestController
@RequestMapping("/acessorio")
public class AcessorioController extends CrudController<Acessorio>{
	
	@Autowired
	AcessorioService service;

	@Override
	public CrudService<Acessorio> getService() {
		return service;
	}
	
	@RequestMapping(method = RequestMethod.GET)
    public List<Acessorio> query(@RequestParam(required=false, defaultValue="") String nome) {
        return service.query(nome, null);
    }
}