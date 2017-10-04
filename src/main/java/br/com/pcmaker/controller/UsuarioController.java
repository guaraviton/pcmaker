package br.com.pcmaker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.pcmaker.entity.Perfil;
import br.com.pcmaker.entity.Usuario;
import br.com.pcmaker.service.CrudService;
import br.com.pcmaker.service.PerfilService;
import br.com.pcmaker.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController extends CrudController<Usuario>{
	
	@Autowired
	UsuarioService service;
	
	@Autowired
	PerfilService perfilService ;

	@Override
	public CrudService<Usuario> getService() {
		return service;
	}
	
	@RequestMapping(method = RequestMethod.GET)
    public List<Usuario> query(@RequestParam(required=false, defaultValue="") String nome, @RequestParam(required=false, defaultValue="") String login) {
        return service.query(nome, login);
    }
	
	@RequestMapping(value="/{id}/perfis", method = RequestMethod.GET)
    public List<Perfil> getRoles(@PathVariable Integer id) {
        return perfilService.query(id);
    }
}