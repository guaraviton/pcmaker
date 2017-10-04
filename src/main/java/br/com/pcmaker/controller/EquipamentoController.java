package br.com.pcmaker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.pcmaker.common.util.StringUtils;
import br.com.pcmaker.entity.Equipamento;
import br.com.pcmaker.service.CrudService;
import br.com.pcmaker.service.EquipamentoService;

@RestController
@RequestMapping("/equipamento")
public class EquipamentoController extends CrudController<Equipamento>{
	
	@Autowired
	EquipamentoService service;

	@Override
	public CrudService<Equipamento> getService() {
		return service;
	}
	
	@RequestMapping(method = RequestMethod.GET)
    public List<Equipamento> query(@RequestParam(required=false, defaultValue="") String nome) {
        return service.query(StringUtils.decode(nome));
    }
}