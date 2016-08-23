package br.com.aps.cliente.jsf.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.deltaspike.core.api.scope.ViewAccessScoped;
import org.primefaces.event.FlowEvent;

import br.com.aps.cliente.jsf.util.MensagemResource;
import br.com.aps.cliente.jsf.util.NavegacaoExplicita;
import br.com.aps.cliente.jsf.util.NavegacaoImplicita;
import br.com.aps.cliente.jsf.util.TipoFluxoCRUDEnum;
import br.com.aps.cliente.jsf.util.ViewConstantes;
import br.com.aps.commons.exception.APSServicoException;
import br.com.aps.entidades.Cliente;
import br.com.aps.entidades.ClienteBalcao;
import br.com.aps.entidades.Orcamento;
import br.com.aps.entidades.enumeration.AtivoInativoEnum;
import br.com.aps.entidades.enumeration.EstadosBrasileirosEnum;
import br.com.aps.entidades.enumeration.TipoPessoaEnum;
import br.com.aps.servico.bo.ClienteBO;
import br.com.aps.servico.bo.OrcamentoBO;

@Named
@ViewAccessScoped
public class ManterOrcamentoMB extends APSManageBean implements Serializable {

	public static final String MSG_CLIENTE_NAO_SELECIONADO = "manter.orcamento.msg.cliente.nao.selecionado";

	public static final String MSG_CLIENTE_SELECIONADO_SUCESSO = "manter.orcamento.msg.cliente.selecionado.sucesso";

	private static final String MSG_DADOS_CLIENTE_NAO_INFORMADOS = "manter.orcamento.msg.dados.cliente.nao.informados";

	private static final String MSG_ORCAMENTO_EXCLUIDO_COM_SUCESSO = "manter.orcamento.msg.excluido.sucesso.orcamento";

	private static final String MSG_ORCAMENTO_FOI_SALVO_COM_SUCESSO = "manter.orcamento.msg.salvo.sucesso.orcamento";

	private static final String MSG_TIPO_FLUXO_CADASTRO_ORCAMENTO = "manter.orcamento.tipo.fluxo.cadastro.orcamento";

	private static final String MSG_TIPO_FLUXO_EDICAO_ORCAMENTO = "manter.orcamento.tipo.fluxo.edicao.orcamento";

	private static final String MSG_TIPO_FLUXO_PESQUISAR_ORCAMENTO = "manter.orcamento.tipo.fluxo.pesquisar.orcamento";

	private static final long serialVersionUID = -8081874099698550375L;

	private boolean clienteBalcao = true;

	@Inject
	private ClienteBO clienteBO;

	private List<Orcamento> listaOrcamentos;

	@Inject
	private OrcamentoBO orcamentoBO;

	private Orcamento orcamentoEmEdicao;

	private Orcamento orcamentoModeloFiltro;

	private TipoFluxoCRUDEnum tipoFluxoTela;

	public String clickBotaoEditar() {
		if (hasOrcamentoEmEdicao()) {
			iniciarFluxoEdicao(this.orcamentoEmEdicao);
			return NavegacaoImplicita.EDITAR_ORCAMENTO;
		} else {
			addMensagemAlerta(MensagemResource.MSG_GERAL_SELECIONE_UM_REGISTRO_PARA_EDICAO);
			return null;
		}
	}

	public void clickBotaoExcluir() {
		if (hasOrcamentoEmEdicao()) {
			orcamentoBO.excluir(this.orcamentoEmEdicao);
			listaOrcamentos.remove(this.orcamentoEmEdicao);
			addMensagemSucesso(MSG_ORCAMENTO_EXCLUIDO_COM_SUCESSO);
		} else {
			addMensagemAlerta(MensagemResource.MSG_GERAL_SELECIONE_UM_REGISTRO_PARA_EXCLUSAO);
		}
	}

	public void clickBotaoLimpar() {
		iniciarFluxoPesquisa();
	}

	public String clickBotaoNovo() {
		iniciarFluxoNovo();
		return NavegacaoImplicita.NOVO_ORCAMENTO;
	}

	public void clickBotaoPesquisar() {
		this.listaOrcamentos = orcamentoBO
				.pesquisarOrcamentosPorFiltroModelo(orcamentoModeloFiltro);
		addMensagemSucesso(MensagemResource.MSG_GERAL_PESQUISA_REALIZADA_COM_SUCESSO);
	}

	public String clickBotaoSelecionarCliente() {
		return gerarURLOutComeExplicita(NavegacaoExplicita.SELECAO_CLIENTE,
				ViewConstantes.NOME_PARAMETRO_OUTCOME + "="
						+ NavegacaoExplicita.MANTER_ORCAMENTO,
				ViewConstantes.NOME_PARAMETRO_TIPO_FLUXO_CRUD + "="
						+ TipoFluxoCRUDEnum.SEARCH);
	}

	public ClienteBO getClienteBO() {
		return clienteBO;
	}

	public Cliente getClienteSelecionado() {
		return orcamentoEmEdicao.getCliente();
	}

	public List<EstadosBrasileirosEnum> getEstadosBrasileiros() {
		return Arrays.asList(EstadosBrasileirosEnum.values());
	}

	public List<Orcamento> getListaOrcamentos() {
		return listaOrcamentos;
	}

	public OrcamentoBO getOrcamentoBO() {
		return orcamentoBO;
	}

	public Orcamento getOrcamentoEmEdicao() {
		return orcamentoEmEdicao;
	}

	public Orcamento getOrcamentoModeloFiltro() {
		return orcamentoModeloFiltro;
	}

	public List<AtivoInativoEnum> getStatus() {
		return Arrays.asList(AtivoInativoEnum.values());
	}

	public TipoFluxoCRUDEnum getTipoFluxoTela() {
		return tipoFluxoTela;
	}

	public List<TipoPessoaEnum> getTiposPessoas() {
		return Arrays.asList(TipoPessoaEnum.values());
	}

	public String getTituloPagina() {
		if (this.tipoFluxoTela == TipoFluxoCRUDEnum.CREATE) {
			return getStringResource(MSG_TIPO_FLUXO_CADASTRO_ORCAMENTO);
		} else if (this.tipoFluxoTela == TipoFluxoCRUDEnum.UPDATE) {
			return getStringResource(MSG_TIPO_FLUXO_EDICAO_ORCAMENTO);
		} else if (this.tipoFluxoTela == TipoFluxoCRUDEnum.SEARCH) {
			return getStringResource(MSG_TIPO_FLUXO_PESQUISAR_ORCAMENTO);
		}
		return null;
	}

	public String handleFlow(FlowEvent event) {
		String currentStepId = event.getOldStep();
		String stepToGo = event.getNewStep();
		if (orcamentoEmEdicao.getCliente() != null
				|| orcamentoEmEdicao.getClienteBalcao() != null) {
			if (!stepToGo.isEmpty())
				return stepToGo;
			else
				return currentStepId;
		} else {
			addMensagemErro(MSG_DADOS_CLIENTE_NAO_INFORMADOS);
			return currentStepId;
		}
	}

	public String iniciarFluxoNovo() {
		this.tipoFluxoTela = TipoFluxoCRUDEnum.CREATE;
		this.orcamentoEmEdicao = new Orcamento();
		this.orcamentoEmEdicao.setClienteBalcao(new ClienteBalcao());
		this.clienteBalcao = true;
		return NavegacaoImplicita.NOVO_ORCAMENTO;
	}

	public String iniciarFluxoPesquisa() {
		this.tipoFluxoTela = TipoFluxoCRUDEnum.SEARCH;
		orcamentoModeloFiltro = new Orcamento();
		atualizarListaOrcamentos();
		return NavegacaoImplicita.LISTAR_ORCAMENTOS;
	}

	@PostConstruct
	public void init() {
		iniciarFluxoNovo();
	}

	public boolean isClienteBalcao() {
		return clienteBalcao;
	}

	public void limpar() {
		Orcamento novoOrcamento = new Orcamento();
		novoOrcamento.setId(this.orcamentoEmEdicao.getId());
		this.orcamentoEmEdicao = novoOrcamento;
	}

	public void onPreRenderView(ComponentSystemEvent event) {
		String nomeParametro = ViewConstantes.NOME_PARAMETRO_ID_CLIENTE_SELECIONADO;
		String returnIdClienteSelecionado = getContext().getExternalContext()
				.getRequestParameterMap().get(nomeParametro);
		boolean clienteInformadoNoQueryString = returnIdClienteSelecionado != null
				&& !returnIdClienteSelecionado.isEmpty();
		if (clienteInformadoNoQueryString
				&& !returnIdClienteSelecionado
						.equals(ViewConstantes.VALOR_PARAMETRO_CLIENTE_NAO_SELECIONADO)) {
			setClienteSelecionado(clienteBO.getPorId(Long
					.parseLong(returnIdClienteSelecionado)));
			addMensagemSucesso(MSG_CLIENTE_SELECIONADO_SUCESSO);
		} else if (clienteInformadoNoQueryString
				&& returnIdClienteSelecionado
						.equals(ViewConstantes.VALOR_PARAMETRO_CLIENTE_NAO_SELECIONADO)) {
			addMensagemAlerta(MSG_CLIENTE_NAO_SELECIONADO);
			removerClienteSelecionado();
		}
	}

	public void onValueChangeListenerClienteBalcao(ValueChangeEvent event) {
		clienteBalcao = Boolean.parseBoolean(event.getNewValue().toString());
		if (clienteBalcao) {
			orcamentoEmEdicao.setClienteBalcao(new ClienteBalcao());
		} else {
			removerClienteBalcao();
		}
		removerClienteSelecionado();
	}

	public void removerClienteSelecionado() {
		setClienteSelecionado(null);
	}

	public String salvar() {
		try {
			orcamentoBO.salvar(orcamentoEmEdicao);
			atualizarListaOrcamentos();
			addMensagemSucesso(MSG_ORCAMENTO_FOI_SALVO_COM_SUCESSO);
			return voltarTelaListar();
		} catch (APSServicoException e) {
			addMensagemErro(e.getMessage());
			return null;
		}
	}

	public void setClienteBalcao(boolean clienteBalcao) {
		this.clienteBalcao = clienteBalcao;
	}

	public void setClienteBO(ClienteBO clienteBO) {
		this.clienteBO = clienteBO;
	}

	public void setClienteSelecionado(Cliente clienteSelecionado) {
		this.orcamentoEmEdicao.setCliente(clienteSelecionado);
		this.clienteBalcao = false;
	}

	public void setListaOrcamentos(List<Orcamento> listaOrcamentos) {
		this.listaOrcamentos = listaOrcamentos;
	}

	public void setOrcamentoBO(OrcamentoBO orcamentoBO) {
		this.orcamentoBO = orcamentoBO;
	}

	public void setOrcamentoEmEdicao(Orcamento OrcamentoEmEdicao) {
		this.orcamentoEmEdicao = OrcamentoEmEdicao;
	}

	public void setOrcamentoModeloFiltro(Orcamento OrcamentoModeloFiltro) {
		this.orcamentoModeloFiltro = OrcamentoModeloFiltro;
	}

	public void setTipoFluxoTela(TipoFluxoCRUDEnum tipoFluxoTela) {
		this.tipoFluxoTela = tipoFluxoTela;
	}

	public String voltarTelaListar() {
		this.tipoFluxoTela = TipoFluxoCRUDEnum.SEARCH;
		return NavegacaoImplicita.LISTAR_ORCAMENTOS;
	}

	private void atualizarListaOrcamentos() {
		listaOrcamentos = obterTodosOrcamentos();
	}

	private boolean hasOrcamentoEmEdicao() {
		return this.orcamentoEmEdicao != null
				&& this.orcamentoEmEdicao.getId() != null
				&& this.orcamentoEmEdicao.getId().intValue() != 0;
	}

	private void iniciarFluxoEdicao(Orcamento OrcamentoParaEditar) {
		this.tipoFluxoTela = TipoFluxoCRUDEnum.UPDATE;
		this.orcamentoEmEdicao = OrcamentoParaEditar;
	}

	private List<Orcamento> obterTodosOrcamentos() {
		return orcamentoBO.listarTodos();
	}

	private void removerClienteBalcao() {
		orcamentoEmEdicao.setClienteBalcao(null);
	}

}
