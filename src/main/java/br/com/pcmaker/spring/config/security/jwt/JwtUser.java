package br.com.pcmaker.spring.config.security.jwt;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * @author y2jm
 *
 */
public class JwtUser implements UserDetails {

	private String chave;
	private String lotacao;
	private String nome;
	private String identificador;
	private String vinculo;
	private final Collection<? extends GrantedAuthority> authorities;
	
	public JwtUser(String chave, String lotacao, String nome, String identificador, String vinculo, Collection<? extends GrantedAuthority> authorities) {
		this.chave = chave;
		this.lotacao = lotacao;
		this.nome = nome;
		this.identificador = identificador;
		this.vinculo = vinculo;
		this.authorities = authorities;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	
	@Override
	@JsonIgnore
	public String getPassword() {
		return null;
	}
	
	@Override
	public String getUsername() {
		return chave;
	}
	
	@Override
	@JsonIgnore
	public boolean isAccountNonExpired() {
		return true;
	}
	
	@Override
	@JsonIgnore
	public boolean isAccountNonLocked() {
		return true;
	}
	
	@Override
	@JsonIgnore
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	@Override
	@JsonIgnore
	public boolean isEnabled() {
		return true;
	}
	
	public String getChave() {
		return chave;
	}
	
	public String getLotacao() {
		return lotacao;
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getIdentificador() {
		return identificador;
	}
	
	public String getVinculo() {
		return vinculo;
	}
	
}
