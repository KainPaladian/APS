package br.com.aps.servico.bo;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import org.apache.deltaspike.jpa.api.transaction.Transactional;

import br.com.aps.commons.exception.APSServicoException;
import br.com.aps.entidades.CategoriaProduto;
import br.com.aps.entidades.Produto;
import br.com.aps.entidades.TipoProduto;
import br.com.aps.servico.dao.CategoriaProdutoDAO;
import br.com.aps.servico.dao.ProdutoDAO;
import br.com.aps.servico.dao.TipoProdutoDAO;

public class ProdutoBO implements Serializable {

	private static final long serialVersionUID = 214446464357946909L;

	@Inject
	private CategoriaProdutoDAO categoriaProdutoDAO;

	@Inject
	private ProdutoDAO produtoDAO;

	@Inject
	private TipoProdutoDAO tipoProdutoDAO;

	@Transactional
	public void excluir(Produto produtoEmEdicao) {
		produtoDAO.excluir(produtoEmEdicao);
	}

	public List<TipoProduto> listarTiposProduto() {
		return tipoProdutoDAO.listarTodos();
	}

	public CategoriaProduto obterCategoriaProdutoPorId(Long id) {
		return categoriaProdutoDAO.getPorId(id);
	}

	public Produto obterProdutoParaEdicao(Produto produtoParaEdicao) {
		return produtoDAO.getParaEdicao(produtoParaEdicao);
	}

	public TipoProduto obterTipoProdutoComCategorias(TipoProduto tipoProduto) {
		return tipoProdutoDAO.getComCategorias(tipoProduto);
	}

	public TipoProduto obterTipoProdutoPorId(Long id) {
		return tipoProdutoDAO.getPorId(id);
	}

	public List<Produto> pesquisarProdutosPorFiltroModelo(
			Produto produtoModeloFiltro) {
		return produtoDAO.pesquisarPorFiltroModelo(produtoModeloFiltro);
	}

	@Transactional
	public void salvar(Produto produtoEmEdicao) throws APSServicoException {
		produtoDAO.salvar(produtoEmEdicao);
	}
}
