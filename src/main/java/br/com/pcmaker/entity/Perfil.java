package br.com.pcmaker.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity 
@Table(name = "perfil")
public class Perfil extends AutoIncrementIdEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<UsuarioPerfil> usuarioPerfil = new ArrayList<UsuarioPerfil>(0);
	
	@Column(name = "nome", nullable = false)
	private String nome;
	
	@JsonIgnore
	@OneToMany(fetch=FetchType.LAZY, mappedBy="perfil")
	public List<UsuarioPerfil> getUsuarioPerfil() {
		return usuarioPerfil;
	}
	
	public void setUsuarioPerfil(List<UsuarioPerfil> usuarioPerfil) {
		this.usuarioPerfil = usuarioPerfil;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
}