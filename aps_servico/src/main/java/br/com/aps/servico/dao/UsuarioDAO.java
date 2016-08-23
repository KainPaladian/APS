package br.com.aps.servico.dao;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.aps.entidades.Usuario;

public class UsuarioDAO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3476740358648051770L;

	@Inject
	private EntityManager em;

	public Usuario getPorId(Long id) {
		return em.find(Usuario.class, id);
	}
}
