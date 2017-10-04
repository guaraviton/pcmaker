package br.com.pcmaker.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pcmaker.common.util.DigestUtils;
import br.com.pcmaker.common.util.StringUtils;
import br.com.pcmaker.dao.CrudDAO;
import br.com.pcmaker.dao.UsuarioDAO;
import br.com.pcmaker.entity.Usuario;
import br.com.pcmaker.entity.UsuarioPerfil;
import br.com.pcmaker.service.UsuarioService;

@Service
public class UsuarioServiceImpl extends CrudServiceImpl<Usuario> implements UsuarioService {
	
	@Autowired
	UsuarioDAO usuarioDao;

	@Override
	public CrudDAO<Usuario> getDAO() {
		return usuarioDao;
	}

	@Override
	public Usuario get(String login) {
		if("admin".equals(login)){
			return new Usuario("admin", "Admin");
		}
		return usuarioDao.get(login);
	}

	@Override
	public List<Usuario> query(String nome, String login) {
		return usuarioDao.query(nome, login);
	}
	
	@Override
	public Usuario salvar(Usuario entity) {
		tratarSenha(entity);
		tratarRelacionamento(entity);
		return super.salvar(entity);	
	}
	
	private void tratarRelacionamento(Usuario entity) {
		for(UsuarioPerfil usuarioPerfil : entity.getUsuarioPerfil()){
			usuarioPerfil.setUsuario(entity);
		}
	}
	
	private void tratarSenha(Usuario entity) {
		if(StringUtils.isNotBlank(entity.getSenha())){
			entity.setSenha(DigestUtils.sha1Hex(entity.getSenha()));
		}else{
			Usuario usuarioTmp = get(entity.getId());
			entity.setSenha(usuarioTmp.getSenha());
		}
	}
}
