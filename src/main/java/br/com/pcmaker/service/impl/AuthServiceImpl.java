package br.com.pcmaker.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import br.com.pcmaker.common.util.ConfiguracaoUtils;
import br.com.pcmaker.controller.dto.PerfilDTO;
import br.com.pcmaker.controller.dto.UsuarioDTO;
import br.com.pcmaker.entity.Usuario;
import br.com.pcmaker.service.AuthService;
import br.com.pcmaker.service.UsuarioService;
import br.com.pcmaker.spring.config.security.jwt.JwtTokenUtil;
import br.com.pcmaker.spring.config.security.jwt.JwtUserDetailsServiceImpl;

@Service
public class AuthServiceImpl implements AuthService{

	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenUtil jwtTokenUtil;
    
    @Autowired
    JwtUserDetailsServiceImpl userDetailsService;
	
    private List<PerfilDTO> toList(Collection<? extends GrantedAuthority> authorities) {
		List<PerfilDTO> perfis = new ArrayList<PerfilDTO>();
		for(GrantedAuthority authority : authorities){
			perfis.add(new PerfilDTO(authority.getAuthority()));
		}
		return perfis;
	}

	@Override
	public String login(String chaveUsuario, String senhaUsuario) {
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(chaveUsuario, senhaUsuario));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtTokenUtil.generateToken(usuarioService.get(authentication.getName()), authentication.getAuthorities());
	}

	@Override
	public UsuarioDTO get() {
		String token = request.getHeader(ConfiguracaoUtils.get("jwt.header"));
		UserDetails user = userDetailsService.loadUserByToken(token);
		return new UsuarioDTO(new Usuario(jwtTokenUtil.getIdFromToken(token), jwtTokenUtil.getLoginFromToken(token), jwtTokenUtil.getNomeFromToken(token)), toList(user.getAuthorities()));
	}

	@Override
	public String refresh() {
		String token = request.getHeader(ConfiguracaoUtils.get("jwt.header"));
		return jwtTokenUtil.refreshToken(token);
	}

	
}