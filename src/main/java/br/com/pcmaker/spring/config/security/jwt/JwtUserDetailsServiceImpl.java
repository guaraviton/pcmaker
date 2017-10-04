package br.com.pcmaker.spring.config.security.jwt;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.pcmaker.common.exception.GlobalException;

/**
 * 
 * @author y2jm
 *
 */
@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
    JwtTokenUtil jwtTokenUtil;
	
    public UserDetails loadUserByToken(String token) throws UsernameNotFoundException {
    	String chaveUsuario = jwtTokenUtil.getLoginFromToken(token);
        if (chaveUsuario == null) {
            throw new UsernameNotFoundException(String.format("Usuario nao encontrado com a chave '%s'.", chaveUsuario));
        } else {
        	List<GrantedAuthority> perfilUsuario = jwtTokenUtil.getAuthoritiesFromToken(token);
            return new User(chaveUsuario, "####", perfilUsuario);
        }
    }
	
    @Override
    public UserDetails loadUserByUsername(String token) throws UsernameNotFoundException {
    	throw new GlobalException("Acesso via token");
    }
}
