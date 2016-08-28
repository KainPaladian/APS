package br.com.aps.cliente.jsf.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.deltaspike.core.api.scope.ViewAccessScoped;

import br.com.aps.cliente.jsf.util.MensagemResource;
import br.com.aps.cliente.jsf.util.NavegacaoImplicita;
import br.com.aps.cliente.jsf.util.TipoFluxoCRUDEnum;
import br.com.aps.cliente.jsf.util.ViewConstantes;
import br.com.aps.commons.exception.APSServicoException;
import br.com.aps.entidades.CategoriaProduto;
import br.com.aps.entidades.Produto;
import br.com.aps.entidades.TipoProduto;
import br.com.aps.entidades.enumeration.AtivoInativoEnum;
import br.com.aps.entidades.enumeration.TipoPessoaEnum;
import br.com.aps.servico.bo.ProdutoBO;

@Named
@ViewAccessScoped
public class ManterProdutoMB extends APSManageBean implements Serializable {

	private static final String MSG_PRODUTO_EXCLUIDO_COM_SUCESSO = "gerenciar.produto.msg.produto.excluido.sucesso";

	private static final String MSG_PRODUTO_FOI_SALVO_COM_SUCESSO = "gerenciar.produto.msg.produto.salvo.sucesso";

	private static final String MSG_TIPO_FLUXO_CADASTRO_PRODUTO = "gerenciar.produto.tipo.fluxo.cadastro.produto";

	private static final String MSG_TIPO_FLUXO_EDICAO_PRODUTO = "gerenciar.produto.tipo.fluxo.edicao.produto";

	private static final String MSG_TIPO_FLUXO_PESQUISAR_PRODUTO = "gerenciar.produto.tipo.fluxo.pesquisar.produto";

	/**
	 * 
	 */
	private static final long serialVersionUID = 2962152447124391603L;

	private List<CategoriaProduto> listaCategoriasProduto;

	private List<Produto> listaProdutos = new ArrayList<Produto>();

	private List<TipoProduto> listaTiposProduto;

	private String outcome;

	@Inject
	private ProdutoBO produtoBO;

	private Produto produtoEmEdicao = new Produto();

	private Produto produtoModeloFiltro = new Produto();

	private TipoFluxoCRUDEnum tipoFluxoTela;

	private TipoProduto tipoProdutoSelecionado;

	private void carregarListaCategoriasProduto(TipoProduto tipoProduto) {
		this.listaCategoriasProduto = produtoBO.obterTipoProdutoComCategorias(
				tipoProduto).getCategoriasProduto();
	}

	private void carregarListaTiposProduto() {
		this.listaTiposProduto = produtoBO.listarTiposProduto();
	}

	public String clickBotaoEditar() {
		if (hasProdutoEmEdicao()) {
			iniciarFluxoEdicao(this.produtoEmEdicao);
			return NavegacaoImplicita.EDITAR_PRODUTO;
		} else {
			addMensagemAlerta(MensagemResource.MSG_GERAL_SELECIONE_UM_REGISTRO_PARA_EDICAO);
			return null;
		}
	}

	public void clickBotaoExcluir() {
		if (hasProdutoEmEdicao()) {
			produtoBO.excluir(this.produtoEmEdicao);
			listaProdutos.remove(this.produtoEmEdicao);
			addMensagemSucesso(MSG_PRODUTO_EXCLUIDO_COM_SUCESSO);
		} else {
			addMensagemAlerta(MensagemResource.MSG_GERAL_SELECIONE_UM_REGISTRO_PARA_EXCLUSAO);
		}
	}

	public void clickBotaoLimpar() {
		listaProdutos.clear();
		limparFiltro();
	}

	public String clickBotaoNovo() {
		iniciarFluxoNovo();
		return NavegacaoImplicita.NOVO_PRODUTO;
	}

	public void clickBotaoPesquisar() {
		produtoModeloFiltro.setEmpresa(getEmpresaSelecionada());
		produtoModeloFiltro.setTipoProduto(tipoProdutoSelecionado);
		this.listaProdutos = produtoBO
				.pesquisarProdutosPorFiltroModelo(produtoModeloFiltro);
		addMensagemSucesso(MensagemResource.MSG_GERAL_PESQUISA_REALIZADA_COM_SUCESSO);
	}

	public String clickBotaoSelecionar() {
		if (produtoEmEdicao == null && listaProdutos != null
				&& listaProdutos.size() == 1) {
			setProdutoEmEdicao(listaProdutos.get(0));
		}
		return gerarURLOutComeExplicita(
				outcome,
				ViewConstantes.NOME_PARAMETRO_ID_PRODUTO_SELECIONADO
						+ "="
						+ (produtoEmEdicao != null ? produtoEmEdicao.getId()
								.toString()
								: ViewConstantes.VALOR_PARAMETRO_CLIENTE_NAO_SELECIONADO));
	}

	public String clickBotaoVoltarSelecaoProduto() {
		setProdutoEmEdicao(null);
		return clickBotaoSelecionar();
	}

	public void cmbTipoProdutoValuChangeListener(ValueChangeEvent event) {

	}

	public List<CategoriaProduto> getListaCategoriasProduto() {
		return listaCategoriasProduto;
	}

	public List<Produto> getListaProdutos() {
		return listaProdutos;
	}

	public List<TipoProduto> getListaTiposProduto() {
		return listaTiposProduto;
	}

	public String getOutcome() {
		return outcome;
	}

	public Produto getProdutoEmEdicao() {
		return produtoEmEdicao;
	}

	public Produto getProdutoModeloFiltro() {
		return produtoModeloFiltro;
	}

	public Produto getProdutoSelecionado() {
		return getProdutoEmEdicao();
	}

	public List<AtivoInativoEnum> getStatus() {
		return Arrays.asList(AtivoInativoEnum.values());
	}

	public TipoFluxoCRUDEnum getTipoFluxoTela() {
		return tipoFluxoTela;
	}

	public TipoProduto getTipoProdutoSelecionado() {
		return tipoProdutoSelecionado;
	}

	public List<TipoPessoaEnum> getTiposPessoas() {
		return Arrays.asList(TipoPessoaEnum.values());
	}

	public String getTituloPagina() {
		StringBuilder result = new StringBuilder();
		if (this.tipoFluxoTela == TipoFluxoCRUDEnum.CREATE) {
			result.append(getStringResource(MSG_TIPO_FLUXO_CADASTRO_PRODUTO));
		} else if (this.tipoFluxoTela == TipoFluxoCRUDEnum.UPDATE) {
			result.append(getStringResource(MSG_TIPO_FLUXO_EDICAO_PRODUTO));
			result.append(" ");
			result.append(this.produtoEmEdicao.getId());
		} else if (this.tipoFluxoTela == TipoFluxoCRUDEnum.SEARCH) {
			result.append(getStringResource(MSG_TIPO_FLUXO_PESQUISAR_PRODUTO));
		}
		return result != null ? result.toString() : null;
	}

	private boolean hasProdutoEmEdicao() {
		return this.produtoEmEdicao != null
				&& this.produtoEmEdicao.getId() != null
				&& this.produtoEmEdicao.getId().intValue() != 0;
	}

	private void iniciarFluxoEdicao(Produto produtoParaEditar) {
		this.tipoFluxoTela = TipoFluxoCRUDEnum.UPDATE;
		this.produtoEmEdicao = produtoBO
				.obterProdutoParaEdicao(produtoParaEditar);
		carregarListaTiposProduto();
		this.tipoProdutoSelecionado = this.produtoEmEdicao
				.getCategoriaProduto().getTipoProduto();
		carregarListaCategoriasProduto(this.tipoProdutoSelecionado);
	}

	public String iniciarFluxoNovo() {
		this.tipoFluxoTela = TipoFluxoCRUDEnum.CREATE;
		this.produtoEmEdicao = new Produto();
		limparListaProdutos();
		carregarListaTiposProduto();
		limparListaCategoriaProduto();
		return NavegacaoImplicita.EDITAR_PRODUTO;
	}

	public String iniciarFluxoPesquisa() {
		this.tipoFluxoTela = TipoFluxoCRUDEnum.SEARCH;
		limparFiltro();
		limparListaProdutos();
		carregarListaTiposProduto();
		this.produtoModeloFiltro.setStatus(null);
		this.produtoModeloFiltro.setSucata(null);
		return NavegacaoImplicita.LISTAR_PRODUTOS;
	}

	@PostConstruct
	public void init() {

	}

	private void limparFiltro() {
		produtoModeloFiltro = new Produto();
		produtoModeloFiltro.setStatus(null);
		produtoModeloFiltro.setSucata(null);
		this.tipoProdutoSelecionado = null;
	}

	private void limparListaCategoriaProduto() {
		if (this.listaCategoriasProduto != null) {
			this.listaCategoriasProduto.clear();
		}
		if (this.produtoEmEdicao != null)
			this.produtoEmEdicao.setCategoriaProduto(null);
		if (this.produtoModeloFiltro != null)
			this.produtoModeloFiltro.setCategoriaProduto(null);
	}

	private void limparListaProdutos() {
		this.listaProdutos = new ArrayList<Produto>();
		this.tipoProdutoSelecionado = null;
	}

	public void onChangeTipoProduto() {
		if (tipoProdutoSelecionado != null) {
			tipoProdutoSelecionado = produtoBO
					.obterTipoProdutoComCategorias(tipoProdutoSelecionado);
			carregarListaCategoriasProduto(tipoProdutoSelecionado);
		} else {
			limparListaCategoriaProduto();
		}
	}

	private void prepararEmpresa(Produto produtoEmEdicao) {
		produtoEmEdicao.setEmpresa(getEmpresaSelecionada());
	}

	private void prepararMedidas(Produto produtoEmEdicao) {
		if (!produtoEmEdicao.getCategoriaProduto().isUsaComprimento())
			produtoEmEdicao.setComprimento(null);
		if (!produtoEmEdicao.getCategoriaProduto().isUsaDiametro())
			produtoEmEdicao.setDiametro(null);
		if (!produtoEmEdicao.getCategoriaProduto().isUsaEspessura())
			produtoEmEdicao.setEspessura(null);
		if (!produtoEmEdicao.getCategoriaProduto().isUsaLargura())
			produtoEmEdicao.setLargura(null);
		if (!produtoEmEdicao.getCategoriaProduto().isUsaMassa())
			produtoEmEdicao.setMassa(null);
	}

	private void prepararPercentualMaximoDesconto(Produto produtoEmEdicao) {
		if (!produtoEmEdicao.getPermiteDesconto()) {
			produtoEmEdicao.setPercentualMaximoDescontoPagamentoAvistsa(null);
		}
	}

	private void prepararProdutoParaSalvar(Produto produtoEmEdicao) {
		prepararEmpresa(produtoEmEdicao);
		prepararPercentualMaximoDesconto(produtoEmEdicao);
		prepararMedidas(produtoEmEdicao);

	}

	public String salvar() {
		try {
			prepararProdutoParaSalvar(this.produtoEmEdicao);
			produtoBO.salvar(produtoEmEdicao);
			addMensagemSucesso(MSG_PRODUTO_FOI_SALVO_COM_SUCESSO);
			return voltarTelaListar();
		} catch (APSServicoException e) {
			addMensagemErro(e.getMessage());
			return null;
		}
	}

	public void setListaCategoriasProduto(
			List<CategoriaProduto> listaCategoriasProduto) {
		this.listaCategoriasProduto = listaCategoriasProduto;
	}

	public void setListaProdutos(List<Produto> listaProdutos) {
		this.listaProdutos = listaProdutos;
	}

	public void setListaTiposProduto(List<TipoProduto> listaTiposProduto) {
		this.listaTiposProduto = listaTiposProduto;
	}

	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}

	public void setProdutoEmEdicao(Produto produtoEmEdicao) {
		this.produtoEmEdicao = produtoEmEdicao;
	}

	public void setProdutoModeloFiltro(Produto produtoModeloFiltro) {
		this.produtoModeloFiltro = produtoModeloFiltro;
	}

	public void setTipoFluxoTela(TipoFluxoCRUDEnum tipoFluxoTela) {
		this.tipoFluxoTela = tipoFluxoTela;
	}

	public void setTipoProdutoSelecionado(TipoProduto tipoProdutoSelecionado) {
		this.tipoProdutoSelecionado = tipoProdutoSelecionado;
	}

	public String voltarTelaListar() {
		this.tipoFluxoTela = TipoFluxoCRUDEnum.SEARCH;
		clickBotaoLimpar();
		return NavegacaoImplicita.LISTAR_PRODUTOS;
	}
}
