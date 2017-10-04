package br.com.pcmaker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.pcmaker.entity.Avaria;
import br.com.pcmaker.service.AvariaService;
import br.com.pcmaker.service.CrudService;

@RestController
@RequestMapping("/avaria")
public class AvariaController extends CrudController<Avaria>{
	
	@Autowired
	AvariaService service;

	@Override
	public CrudService<Avaria> getService() {
		return service;
	}
	
	@RequestMapping(method = RequestMethod.GET)
    public List<Avaria> query(@RequestParam(required=false, defaultValue="") String nome) {
        return service.query(nome, null);
    }
}