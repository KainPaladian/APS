package br.com.aps.servico.bo;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import org.apache.deltaspike.jpa.api.transaction.Transactional;

import br.com.aps.commons.exception.APSServicoException;
import br.com.aps.commons.msg.MensagemResource;
import br.com.aps.entidades.Cliente;
import br.com.aps.servico.dao.ClienteDAO;

public class ClienteBO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5349122258448506492L;

	@Inject
	private ClienteDAO clienteDAO;

	@Transactional
	public void excluir(Cliente cliente) {
		clienteDAO.excluir(cliente);
	}

	public Cliente getPorId(Long id) {
		return clienteDAO.getPorId(id);
	}

	public List<Cliente> listarTodos() {
		return clienteDAO.listarTodos();
	}

	public List<Cliente> pesquisarClientesPorFiltroModelo(
			Cliente clienteModeloFiltro) {
		return clienteDAO.pesquisarClientesPorFiltroModelo(clienteModeloFiltro);
	}

	@Transactional
	public void salvar(Cliente cliente) throws APSServicoException {
		if (!verificarClienteComMesmoDocumento(cliente)) {
			cliente.getEnderecoPrincipal().setCliente(cliente);
			clienteDAO.salvar(cliente);
		} else {
			throw new APSServicoException(
					MensagemResource.ERRO_SERVICO_CLIENTE_DOCUMENTO_JA_CADASTRADO);
		}
	}

	public boolean verificarClienteComMesmoDocumento(
			Cliente clienteParaVerificacao) {
		return clienteDAO
				.verificarClienteComMesmoDocumento(clienteParaVerificacao);
	}
}
