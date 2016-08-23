package br.com.aps.servico.dao;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.aps.entidades.Empresa;

public class EmpresaDAO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3881201026147223070L;

	@Inject
	private EntityManager em;

	public Empresa getPorId(Long id) {
		return em.find(Empresa.class, id);
	}
}
