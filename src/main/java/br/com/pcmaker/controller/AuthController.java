package br.com.pcmaker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.pcmaker.controller.dto.LoginDTO;
import br.com.pcmaker.controller.dto.UsuarioDTO;
import br.com.pcmaker.service.AuthService;
import br.com.pcmaker.spring.config.security.jwt.JwtAuthenticationResponse;

@RestController
@RequestMapping("/auth")
public class AuthController{
	
	@Autowired
    AuthService authService;
	
	@RequestMapping(method = RequestMethod.POST)
    public JwtAuthenticationResponse login(@RequestBody LoginDTO loginDTO) {
		return new JwtAuthenticationResponse(authService.login(loginDTO.getChave(), loginDTO.getSenha()));
    }
	
	@RequestMapping(value="/refresh", method=RequestMethod.GET)
    public JwtAuthenticationResponse refreshAndGetAuthenticationToken() {
    	return new JwtAuthenticationResponse(authService.refresh());
    }
	
	@RequestMapping(method = RequestMethod.GET)
    public UsuarioDTO get() {
		return authService.get();
	}
}