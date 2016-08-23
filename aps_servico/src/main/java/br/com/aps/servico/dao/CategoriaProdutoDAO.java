package br.com.aps.servico.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.aps.entidades.CategoriaProduto;

public class CategoriaProdutoDAO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2894935484778455077L;

	@Inject
	private EntityManager em;

	public CategoriaProduto getPorId(Long id) {
		return em.find(CategoriaProduto.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<CategoriaProduto> listarTodos() {
		StringBuilder sb = new StringBuilder("SELECT c FROM CategoriaProduto c");
		Query query = em.createQuery(sb.toString());
		return query.getResultList();
	}
}
