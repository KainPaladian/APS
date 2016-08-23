package br.com.aps.servico.bo;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.aps.entidades.Empresa;
import br.com.aps.servico.dao.EmpresaDAO;

public class EmpresaBO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -40856460217893141L;

	@Inject
	private EmpresaDAO empresaDAO;

	public Empresa getPorId(Long id) {
		return empresaDAO.getPorId(id);
	}
}
