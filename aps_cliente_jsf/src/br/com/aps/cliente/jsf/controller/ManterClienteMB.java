package br.com.aps.cliente.jsf.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedProperty;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.deltaspike.core.api.scope.ViewAccessScoped;

import br.com.aps.cliente.jsf.util.MensagemResource;
import br.com.aps.cliente.jsf.util.NavegacaoImplicita;
import br.com.aps.cliente.jsf.util.TipoFluxoCRUDEnum;
import br.com.aps.cliente.jsf.util.ViewConstantes;
import br.com.aps.commons.exception.APSServicoException;
import br.com.aps.entidades.Cliente;
import br.com.aps.entidades.enumeration.AtivoInativoEnum;
import br.com.aps.entidades.enumeration.EstadosBrasileirosEnum;
import br.com.aps.entidades.enumeration.TipoPessoaEnum;
import br.com.aps.servico.bo.ClienteBO;

@Named
@ViewAccessScoped
public class ManterClienteMB extends APSManageBean implements Serializable {

	private static final String MSG_CLIENTE_EXCLUIDO_COM_SUCESSO = "gerenciar.cliente.msg.cliente.excluido.sucesso";

	private static final String MSG_CLIENTE_FOI_SALVO_COM_SUCESSO = "gerenciar.cliente.msg.cliente.salvo.sucesso";

	private static final String MSG_TIPO_FLUXO_CADASTRO_CLIENTE = "gerenciar.cliente.tipo.fluxo.cadastro.cliente";

	private static final String MSG_TIPO_FLUXO_EDICAO_CLIENTE = "gerenciar.cliente.tipo.fluxo.edicao.cliente";

	private static final String MSG_TIPO_FLUXO_PESQUISAR_CLIENTE = "gerenciar.cliente.tipo.fluxo.pesquisar.cliente";

	private static final long serialVersionUID = 6825684696298692594L;

	@Inject
	private ClienteBO clienteBO;

	private Cliente clienteEmEdicao;

	private Cliente clienteModeloFiltro;

	private List<Cliente> listaClientes = new ArrayList<Cliente>();

	private String outcome;

	@ManagedProperty("#{param.tipoFluxoCRUD}")
	private TipoFluxoCRUDEnum tipoFluxoTela;

	public String clickBotaoEditar() {
		if (hasClienteEmEdicao()) {
			iniciarFluxoEdicao(this.clienteEmEdicao);
			return NavegacaoImplicita.EDITAR_CLIENTE;
		} else {
			addMensagemAlerta(MensagemResource.MSG_GERAL_SELECIONE_UM_REGISTRO_PARA_EDICAO);
			return null;
		}
	}

	public void clickBotaoExcluir() {
		if (hasClienteEmEdicao()) {
			clienteBO.excluir(this.clienteEmEdicao);
			listaClientes.remove(this.clienteEmEdicao);
			addMensagemSucesso(MSG_CLIENTE_EXCLUIDO_COM_SUCESSO);
		} else {
			addMensagemAlerta(MensagemResource.MSG_GERAL_SELECIONE_UM_REGISTRO_PARA_EXCLUSAO);
		}
	}

	public void clickBotaoLimpar() {
		listaClientes.clear();
		limparFiltro();
	}

	public String clickBotaoNovo() {
		iniciarFluxoNovo();
		return NavegacaoImplicita.NOVO_CLIENTE;
	}

	public void clickBotaoPesquisar() {
		clienteModeloFiltro.setEmpresa(getEmpresaSelecionada());
		this.listaClientes = clienteBO
				.pesquisarClientesPorFiltroModelo(clienteModeloFiltro);
		addMensagemSucesso(MensagemResource.MSG_GERAL_PESQUISA_REALIZADA_COM_SUCESSO);
	}

	public String clickBotaoSelecionar() {
		if (clienteEmEdicao == null && listaClientes != null
				&& listaClientes.size() == 1) {
			setClienteEmEdicao(listaClientes.get(0));
		}
		return gerarURLOutComeExplicita(
				outcome,
				ViewConstantes.NOME_PARAMETRO_ID_CLIENTE_SELECIONADO
						+ "="
						+ (clienteEmEdicao != null ? clienteEmEdicao.getId()
								.toString()
								: ViewConstantes.VALOR_PARAMETRO_CLIENTE_NAO_SELECIONADO));
	}

	public String clickBotaoVoltarSelecaoCliente() {
		setClienteEmEdicao(null);
		return clickBotaoSelecionar();
	}

	public Cliente getClienteEmEdicao() {
		return clienteEmEdicao;
	}

	public Cliente getClienteModeloFiltro() {
		return clienteModeloFiltro;
	}

	public Cliente getClienteSelecionado() {
		return getClienteEmEdicao();
	}

	public List<EstadosBrasileirosEnum> getEstadosBrasileiros() {
		return Arrays.asList(EstadosBrasileirosEnum.values());
	}

	public List<Cliente> getListaClientes() {
		return listaClientes;
	}

	public String getOutcome() {
		return outcome;
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
		StringBuilder result = new StringBuilder();
		if (this.tipoFluxoTela == TipoFluxoCRUDEnum.CREATE) {
			result.append(getStringResource(MSG_TIPO_FLUXO_CADASTRO_CLIENTE));
		} else if (this.tipoFluxoTela == TipoFluxoCRUDEnum.UPDATE) {
			result.append(getStringResource(MSG_TIPO_FLUXO_EDICAO_CLIENTE));
			result.append(" ");
			result.append(this.clienteEmEdicao.getId());
		} else if (this.tipoFluxoTela == TipoFluxoCRUDEnum.SEARCH) {
			result.append(getStringResource(MSG_TIPO_FLUXO_PESQUISAR_CLIENTE));
		}
		return result != null ? result.toString() : null;
	}

	public String iniciarFluxoNovo() {
		this.tipoFluxoTela = TipoFluxoCRUDEnum.CREATE;
		this.clienteEmEdicao = new Cliente();
		return NavegacaoImplicita.NOVO_CLIENTE;
	}

	public String iniciarFluxoPesquisa() {
		this.tipoFluxoTela = TipoFluxoCRUDEnum.SEARCH;
		limparFiltro();
		limparListaClientes();
		return NavegacaoImplicita.LISTAR_CLIENTES;
	}

	@PostConstruct
	public void init() {
		String sTipoFluxoCRUD = getTipoFluxoCRUDFromQueryString();
		if (sTipoFluxoCRUD != null) {
			setTipoFluxoTela(TipoFluxoCRUDEnum
					.getTipoFluxoCRUDEnumPorLabel(sTipoFluxoCRUD));
			if (getTipoFluxoTela() != null) {
				switch (getTipoFluxoTela()) {
				case SEARCH:
					iniciarFluxoPesquisa();
					break;
				default:
					iniciarFluxoPesquisa();
					break;
				}
			}
		}
	}

	private String getTipoFluxoCRUDFromQueryString() {
		String sTipoFluxoCRUD = getContext().getExternalContext()
				.getRequestParameterMap()
				.get(ViewConstantes.NOME_PARAMETRO_TIPO_FLUXO_CRUD);
		return sTipoFluxoCRUD;
	}

	public String salvar() {
		try {
			clienteEmEdicao.setEmpresa(getEmpresaSelecionada());
			clienteBO.salvar(clienteEmEdicao);
			addMensagemSucesso(MSG_CLIENTE_FOI_SALVO_COM_SUCESSO);
			return voltarTelaListar();
		} catch (APSServicoException e) {
			addMensagemErro(e.getMessage());
			return null;
		}
	}

	public void setClienteEmEdicao(Cliente clienteEmEdicao) {
		this.clienteEmEdicao = clienteEmEdicao;
	}

	public void setClienteModeloFiltro(Cliente clienteModeloFiltro) {
		this.clienteModeloFiltro = clienteModeloFiltro;
	}

	public void setListaClientes(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}

	public void setTipoFluxoTela(TipoFluxoCRUDEnum tipoFluxoTela) {
		this.tipoFluxoTela = tipoFluxoTela;
	}

	public String voltarTelaListar() {
		this.tipoFluxoTela = TipoFluxoCRUDEnum.SEARCH;
		clickBotaoLimpar();
		return NavegacaoImplicita.LISTAR_CLIENTES;
	}

	private boolean hasClienteEmEdicao() {
		return this.clienteEmEdicao != null
				&& this.clienteEmEdicao.getId() != null
				&& this.clienteEmEdicao.getId().intValue() != 0;
	}

	private void iniciarFluxoEdicao(Cliente clienteParaEditar) {
		this.tipoFluxoTela = TipoFluxoCRUDEnum.UPDATE;
		this.clienteEmEdicao = clienteParaEditar;
	}

	private void limparFiltro() {
		clienteModeloFiltro = new Cliente();
		clienteModeloFiltro.setStatus(null);
	}

	private void limparListaClientes() {
		this.listaClientes = new ArrayList<Cliente>();
	}
}
