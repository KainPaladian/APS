package br.com.aps.servico.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.aps.entidades.CategoriaProduto;
import br.com.aps.entidades.Empresa;
import br.com.aps.entidades.Produto;
import br.com.aps.entidades.TipoProduto;
import br.com.aps.entidades.enumeration.AtivoInativoEnum;
import br.com.aps.entidades.enumeration.SimNaoEnum;

public class ProdutoDAO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6559346906962913978L;

	@Inject
	private EntityManager em;

	public void excluir(Produto produto) {
		em.remove(getMerge(produto));
	}

	public Produto getParaEdicao(Produto produto) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Produto> q = cb.createQuery(Produto.class);
		Root<Produto> p = q.from(Produto.class);
		p.fetch("categoriaProduto", JoinType.INNER);
		q.select(p);
		q.where(cb.equal(p.get("id"), produto.getId()));
		return (Produto) this.em.createQuery(q).getSingleResult();
	}

	public Produto getPorId(Long id) {
		return em.find(Produto.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Produto> listarTodos() {
		StringBuilder sb = new StringBuilder("SELECT p FROM Produto p");
		Query query = em.createQuery(sb.toString());
		return query.getResultList();
	}

	public List<Produto> pesquisarPorFiltroModelo(Produto produtoModeloFiltro) {
		List<Produto> result = new ArrayList<Produto>();

		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Produto> produtoQuery = criteriaBuilder
				.createQuery(Produto.class);
		Root<Produto> produtoRoot = produtoQuery.from(Produto.class);

		List<Predicate> predicates = new ArrayList<Predicate>();
		if (produtoModeloFiltro.getEmpresa() != null) {
			Join<Produto, Empresa> join = produtoRoot.join("empresa");
			Predicate predicate = criteriaBuilder.equal(join.get("id"),
					produtoModeloFiltro.getEmpresa().getId());
			predicates.add(predicate);
		}
		if (produtoModeloFiltro.getId() != null) {
			Predicate predicate = criteriaBuilder.equal(
					produtoRoot.<Integer> get("id"),
					produtoModeloFiltro.getId());
			predicates.add(predicate);
		}
		if (produtoModeloFiltro.getTipoProduto() != null) {
			Join<Produto, CategoriaProduto> joinCategoriaProduto = produtoRoot
					.join("categoriaProduto");
			Join<CategoriaProduto, TipoProduto> joinTipoProduto = joinCategoriaProduto
					.join("tipoProduto");
			Predicate predicate = criteriaBuilder.equal(joinTipoProduto
					.get("id"), produtoModeloFiltro.getTipoProduto().getId());
			predicates.add(predicate);
		}
		if (produtoModeloFiltro.getCategoriaProduto() != null) {
			Join<Produto, CategoriaProduto> join = produtoRoot
					.join("categoriaProduto");
			Predicate predicate = criteriaBuilder.equal(join.get("id"),
					produtoModeloFiltro.getCategoriaProduto().getId());
			predicates.add(predicate);
		}
		if (produtoModeloFiltro.getDiametro() != null) {
			Predicate precidate = criteriaBuilder.equal(
					produtoRoot.<Double> get("diametro"),
					produtoModeloFiltro.getDiametro());
			predicates.add(precidate);
		}
		if (produtoModeloFiltro.getEspessura() != null) {
			Predicate precidate = criteriaBuilder.equal(
					produtoRoot.<Double> get("espessura"),
					produtoModeloFiltro.getEspessura());
			predicates.add(precidate);
		}
		if (produtoModeloFiltro.getComprimento() != null) {
			Predicate precidate = criteriaBuilder.equal(
					produtoRoot.<Double> get("comprimento"),
					produtoModeloFiltro.getComprimento());
			predicates.add(precidate);
		}
		if (produtoModeloFiltro.getLargura() != null) {
			Predicate precidate = criteriaBuilder.equal(
					produtoRoot.<Double> get("largura"),
					produtoModeloFiltro.getLargura());
			predicates.add(precidate);
		}
		if (produtoModeloFiltro.getMassa() != null) {
			Predicate precidate = criteriaBuilder.equal(
					produtoRoot.<Double> get("massa"),
					produtoModeloFiltro.getMassa());
			predicates.add(precidate);
		}
		if (produtoModeloFiltro.getDescricao() != null
				&& !produtoModeloFiltro.getDescricao().trim().isEmpty()) {
			Predicate predicate = criteriaBuilder.like(criteriaBuilder
					.lower(produtoRoot.<String> get("descricao")), "%"
					+ produtoModeloFiltro.getDescricao().toLowerCase().trim()
					+ "%");
			predicates.add(predicate);
		}
		if (produtoModeloFiltro.getEspecificacao() != null
				&& !produtoModeloFiltro.getEspecificacao().trim().isEmpty()) {
			Predicate predicate = criteriaBuilder.like(criteriaBuilder
					.lower(produtoRoot.<String> get("especificacao")), "%"
					+ produtoModeloFiltro.getEspecificacao().toLowerCase()
							.trim() + "%");
			predicates.add(predicate);
		}
		if (produtoModeloFiltro.getStatus() != null) {
			Predicate predicate = criteriaBuilder.equal(
					produtoRoot.<AtivoInativoEnum> get("status"),
					produtoModeloFiltro.getStatus());
			predicates.add(predicate);
		}
		if (produtoModeloFiltro.getSucata() != null) {
			Predicate predicate = criteriaBuilder.equal(
					produtoRoot.<SimNaoEnum> get("sucata"),
					produtoModeloFiltro.getSucata());
			predicates.add(predicate);
		}

		produtoQuery.where(predicates.toArray(new Predicate[] {}));
		TypedQuery<Produto> query = em.createQuery(produtoQuery);
		result = query.getResultList();

		return result;
	}

	public void salvar(Produto produto) {
		Produto produtoMerge = getMerge(produto);
		em.persist(produtoMerge);
	}

	private Produto getMerge(Produto produto) {
		Produto produtoMerge = em.merge(produto);
		return produtoMerge;
	}
}
