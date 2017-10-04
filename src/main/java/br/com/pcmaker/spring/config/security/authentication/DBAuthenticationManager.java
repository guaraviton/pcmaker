package br.com.pcmaker.spring.config.security.authentication;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import br.com.pcmaker.common.exception.LoginException;
import br.com.pcmaker.common.util.ConfiguracaoUtils;
import br.com.pcmaker.common.util.DigestUtils;
import br.com.pcmaker.entity.Perfil;
import br.com.pcmaker.entity.Usuario;
import br.com.pcmaker.service.PerfilService;
import br.com.pcmaker.service.UsuarioService;

/**
 * 
 * @author y2jm
 *
 */
public class DBAuthenticationManager implements AuthenticationManager{
	
	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	PerfilService perfilService;
	
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
	    String loginUsuario = authentication.getPrincipal().toString();
	    String senhaUsuario = authentication.getCredentials().toString();
	    return login(loginUsuario, senhaUsuario);
	}
	
	private Authentication login(String loginUsuario, String senhaUsuario) {
		if(isAdmin(loginUsuario)){
			loginAdmin(senhaUsuario);
		}else{
			loginUsuario(loginUsuario, senhaUsuario);
		}
		return new UsernamePasswordAuthenticationToken(loginUsuario, senhaUsuario, getAuthorities(loginUsuario));
	}

	private void loginUsuario(String loginUsuario, String senhaUsuario) {
		Usuario usuario = usuarioService.get(loginUsuario);
		if(usuario == null){
			throw new LoginException(loginUsuario);
		}
		if(isSenhaCorreta(usuario.getSenha(), senhaUsuario)){
			throw new LoginException(loginUsuario);
		}
	}

	private boolean isSenhaCorreta(String senhaDigestUsuario, String senhaUsuario) {
		return !senhaDigestUsuario.equals(DigestUtils.sha1Hex(senhaUsuario));
	}

	private void loginAdmin(String senhaUsuario) {
		if(isSenhaCorreta(ConfiguracaoUtils.getSenhaAdmin(), senhaUsuario)){
			throw new LoginException("Admin");
		}
	}

	private boolean isAdmin(String loginUsuario) {
		return "admin".equalsIgnoreCase(loginUsuario);
	}

	private List<GrantedAuthority> getAuthorities(String login){
		if(isAdmin(login)){
			List<GrantedAuthority> authoritiesList = new ArrayList<GrantedAuthority>();
			authoritiesList.add(new SimpleGrantedAuthority("admin"));
			return authoritiesList;
		}
		return getPerfisUsuario(login);
	}

	private List<GrantedAuthority> getPerfisUsuario(String login) {
		Usuario usuario = usuarioService.get(login);
		List<Perfil> perfisUsuario = perfilService.query(usuario.getId());
		List<GrantedAuthority> authoritiesList = new ArrayList<GrantedAuthority>();
		for(Perfil perfil : perfisUsuario){
			authoritiesList.add(new SimpleGrantedAuthority(perfil.getNome()));
		}
		return authoritiesList;
	}
}