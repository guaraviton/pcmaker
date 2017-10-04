package br.com.pcmaker.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity 
@Table(name = "usuario")
public class Usuario extends StatusRegistroEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "nome", nullable = false)
	private String nome;
	
	@Column(name = "login", nullable = true)
	private String login;
	
	@Column(name = "email", nullable = false)
	private String email;
	
	@Column(name = "telefone", nullable = true)
	private String telefone;
	
	@Column(name = "senha", nullable = false)
	private String senha;
	
	private List<UsuarioPerfil> usuarioPerfil = new ArrayList<UsuarioPerfil>(0);
	
	public Usuario() {
	}
	 
	public Usuario(Integer id, String login, String nome) {
		this.id = id;
		this.nome = nome;
		this.login = login;
	}
	
	public Usuario(String nome, String login) {
		this.nome = nome;
		this.login = login;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	
	public String getLogin() {
		return login;
	}

	public void setLogin(String usuario) {
		this.login = usuario;
	}

	@JsonIgnore
	public String getSenha() {
		return senha;
	}

	@JsonProperty
	public void setSenha(String senha) {
		this.senha = senha;
	}

	@JsonIgnore
	@OneToMany(fetch=FetchType.LAZY, mappedBy="usuario", orphanRemoval = true, cascade = {CascadeType.ALL})
	public List<UsuarioPerfil> getUsuarioPerfil() {
		return usuarioPerfil;
	}

	@JsonProperty
	public void setUsuarioPerfil(List<UsuarioPerfil> usuarioPerfil) {
		this.usuarioPerfil = usuarioPerfil;
	}
	
}
