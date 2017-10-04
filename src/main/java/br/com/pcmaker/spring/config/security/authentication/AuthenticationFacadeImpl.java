package br.com.pcmaker.spring.config.security.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import br.com.pcmaker.entity.Usuario;
import br.com.pcmaker.service.UsuarioService;

@Component
public class AuthenticationFacadeImpl implements AuthenticationFacade{
	
	@Autowired
	UsuarioService usuarioService;
	
	@Override
	public Usuario getUsuario() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return usuarioService.get(authentication.getName());
	}
	
}
