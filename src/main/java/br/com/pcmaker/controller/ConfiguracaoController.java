package br.com.pcmaker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.pcmaker.entity.Configuracao;
import br.com.pcmaker.service.ConfiguracaoService;
import br.com.pcmaker.service.CrudService;

@RestController
@RequestMapping("/configuracao")
public class ConfiguracaoController extends CrudController<Configuracao>{
	
	@Autowired
	ConfiguracaoService service;

	@Override
	public CrudService<Configuracao> getService() {
		return service;
	}
	
	@RequestMapping(method = RequestMethod.GET)
    public List<Configuracao> query(@RequestParam(required=false, defaultValue="") String chave) {
        return service.query(chave);
    }
}