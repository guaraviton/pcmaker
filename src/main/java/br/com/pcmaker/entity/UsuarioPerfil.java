package br.com.pcmaker.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "usuario_perfil")
public class UsuarioPerfil extends AutoIncrementIdEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@JoinColumn(name = "usuario_id", nullable = false)
	private Usuario usuario;
	
	@JoinColumn(name = "perfil_id", nullable = false)
	private Perfil perfil;
	
	@ManyToOne(fetch = FetchType.LAZY)
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	public Perfil getPerfil() {
		return perfil;
	}
	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
}