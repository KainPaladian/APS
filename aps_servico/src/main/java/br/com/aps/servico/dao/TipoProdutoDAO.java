package br.com.aps.servico.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import br.com.aps.entidades.TipoProduto;

public class TipoProdutoDAO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3699251937365221752L;

	@Inject
	private EntityManager em;

	public TipoProduto getComCategorias(TipoProduto tipoProduto) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<TipoProduto> q = cb.createQuery(TipoProduto.class);
		Root<TipoProduto> tp = q.from(TipoProduto.class);
		tp.fetch("categoriasProduto", JoinType.INNER);
		q.select(tp);
		q.where(cb.equal(tp.get("id"), tipoProduto.getId()));
		return (TipoProduto) this.em.createQuery(q).getSingleResult();
	}

	public TipoProduto getPorId(Long id) {
		return em.find(TipoProduto.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<TipoProduto> listarTodos() {
		StringBuilder sb = new StringBuilder("SELECT t FROM TipoProduto t");
		Query query = em.createQuery(sb.toString());
		return query.getResultList();
	}
}
