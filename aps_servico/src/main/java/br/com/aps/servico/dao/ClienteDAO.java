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
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.aps.entidades.Cliente;
import br.com.aps.entidades.Empresa;
import br.com.aps.entidades.enumeration.AtivoInativoEnum;
import br.com.aps.entidades.enumeration.TipoPessoaEnum;

public class ClienteDAO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8160030098064190910L;

	@Inject
	private EntityManager em;

	public void excluir(Cliente cliente) {
		em.remove(getMerge(cliente));
	}

	private Cliente getMerge(Cliente cliente) {
		Cliente clienteMerge = em.merge(cliente);
		return clienteMerge;
	}

	public Cliente getPorId(Long id) {
		return em.find(Cliente.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Cliente> listarTodos() {
		StringBuilder sb = new StringBuilder("SELECT c FROM Cliente c");
		Query query = em.createQuery(sb.toString());
		return query.getResultList();
	}

	public List<Cliente> pesquisarClientesPorFiltroModelo(
			Cliente clienteModeloFiltro) {
		List<Cliente> result = new ArrayList<Cliente>();

		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Cliente> clienteQuery = criteriaBuilder
				.createQuery(Cliente.class);
		Root<Cliente> root = clienteQuery.from(Cliente.class);

		List<Predicate> predicates = new ArrayList<Predicate>();
		if (clienteModeloFiltro.getEmpresa() != null) {
			Join<Cliente, Empresa> join = root.join("empresa");
			Predicate codigoPredicate = criteriaBuilder.equal(join.get("id"),
					clienteModeloFiltro.getEmpresa().getId());
			predicates.add(codigoPredicate);
		}
		if (clienteModeloFiltro.getId() != null) {
			Predicate codigoPredicate = criteriaBuilder.equal(
					root.<Integer> get("id"), clienteModeloFiltro.getId());
			predicates.add(codigoPredicate);
		}
		if (clienteModeloFiltro.getTipoPessoa() != null) {
			Predicate tipoPessoaPFPredicate = criteriaBuilder.equal(
					root.<TipoPessoaEnum> get("tipoPessoa"),
					clienteModeloFiltro.getTipoPessoa());
			predicates.add(tipoPessoaPFPredicate);
		}
		if (clienteModeloFiltro.getStatus() != null) {
			Predicate statusPFPredicate = criteriaBuilder.equal(
					root.<AtivoInativoEnum> get("status"),
					clienteModeloFiltro.getStatus());
			predicates.add(statusPFPredicate);
		}
		if (clienteModeloFiltro.getNomeRazaoSocial() != null
				&& !clienteModeloFiltro.getNomeRazaoSocial().trim().isEmpty()) {
			Predicate nomeRazaoSocialPFPredicate = criteriaBuilder
					.like(criteriaBuilder.lower(root
							.<String> get("nomeRazaoSocial")), "%"
							+ clienteModeloFiltro.getNomeRazaoSocial()
									.toLowerCase().trim() + "%");
			predicates.add(nomeRazaoSocialPFPredicate);
		}
		if (clienteModeloFiltro.getCpfCnpj() != null
				&& !clienteModeloFiltro.getCpfCnpj().trim().isEmpty()) {
			Predicate cpfCnpjPFPredicate = criteriaBuilder.like(
					criteriaBuilder.lower(root.<String> get("cpfCnpj")), "%"
							+ clienteModeloFiltro.getCpfCnpj().toLowerCase()
									.trim() + "%");
			predicates.add(cpfCnpjPFPredicate);
		}
		if (clienteModeloFiltro.getTelefoneFixo1() != null
				&& !clienteModeloFiltro.getTelefoneFixo1().isEmpty()) {
			Predicate telefone1Predicate = criteriaBuilder.equal(
					root.<String> get("telefoneFixo1"),
					clienteModeloFiltro.getTelefoneFixo1());
			predicates.add(telefone1Predicate);
		}
		if (clienteModeloFiltro.getCelular1() != null
				&& !clienteModeloFiltro.getCelular1().isEmpty()) {
			Predicate telefone1Predicate = criteriaBuilder.equal(
					root.<String> get("celular1"),
					clienteModeloFiltro.getCelular1());
			predicates.add(telefone1Predicate);
		}

		clienteQuery.where(predicates.toArray(new Predicate[] {}));
		TypedQuery<Cliente> query = em.createQuery(clienteQuery);
		result = query.getResultList();

		return result;
	}

	public void salvar(Cliente cliente) {
		Cliente clienteMerge = getMerge(cliente);
		em.persist(clienteMerge);
	}

	public boolean verificarClienteComMesmoDocumento(
			Cliente clienteParaVerificacao) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder
				.createQuery(Long.class);
		Root<Cliente> root = criteriaQuery.from(Cliente.class);
		criteriaQuery.select(criteriaBuilder.count(root));
		List<Predicate> predicates = new ArrayList<Predicate>();
		if (clienteParaVerificacao.getId() != null) {
			Predicate idClientePredicate = criteriaBuilder.notEqual(
					root.<Integer> get("id"), clienteParaVerificacao.getId());
			predicates.add(idClientePredicate);
		}
		Predicate cpfCnpjPredicate = criteriaBuilder.equal(
				root.<Integer> get("cpfCnpj"),
				clienteParaVerificacao.getCpfCnpj());
		predicates.add(cpfCnpjPredicate);
		criteriaQuery.where(predicates.toArray(new Predicate[] {}));
		Long qntd = em.createQuery(criteriaQuery).getSingleResult();
		return qntd == null || qntd.longValue() > 0;
	}
}
