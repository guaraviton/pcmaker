package br.com.pcmaker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.pcmaker.entity.Perfil;
import br.com.pcmaker.service.CrudService;
import br.com.pcmaker.service.PerfilService;

@RestController
@RequestMapping("/perfil")
public class PerfilController extends CrudController<Perfil>{
	
	@Autowired
	PerfilService service;
	
	@Override
	public CrudService<Perfil> getService() {
		return service;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
    public List<Perfil> query(@RequestParam(required=false, defaultValue="") String nome) {
		return service.query(nome);
    }
	
}