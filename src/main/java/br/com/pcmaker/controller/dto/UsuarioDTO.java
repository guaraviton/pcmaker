package br.com.pcmaker.controller.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.pcmaker.entity.Usuario;

public class UsuarioDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Usuario usuario;
	private List<PerfilDTO> perfis;

	public UsuarioDTO() {
	}
	
	public UsuarioDTO(Usuario usuario, List<PerfilDTO> perfis) {
		this.usuario = usuario;
		this.perfis = perfis;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<PerfilDTO> getPerfis() {
		return perfis;
	}
	
	public List<String> getPerfisString() {
		List<String> perfisString = new ArrayList<String>();
		for(PerfilDTO perfil: perfis){
			perfisString.add(perfil.getNome());
		}
		return perfisString;
	}

	public void setPerfis(List<PerfilDTO> perfis) {
		this.perfis = perfis;
	}
}
