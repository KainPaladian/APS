package br.com.aps.servico.bo;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.aps.entidades.Usuario;
import br.com.aps.servico.dao.UsuarioDAO;

public class UsuarioBO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -40856460217893141L;

	@Inject
	private UsuarioDAO usuarioDAO;

	public Usuario getPorId(Long id) {
		return usuarioDAO.getPorId(id);
	}
}
