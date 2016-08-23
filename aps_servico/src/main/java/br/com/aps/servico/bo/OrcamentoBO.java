package br.com.aps.servico.bo;

import java.io.Serializable;
import java.util.List;

import org.apache.deltaspike.jpa.api.transaction.Transactional;

import br.com.aps.commons.exception.APSServicoException;
import br.com.aps.entidades.Orcamento;

public class OrcamentoBO implements Serializable {

	private static final long serialVersionUID = 7791583645045455417L;

	@Transactional
	public void excluir(Orcamento orcamento) {
		// TODO
	}

	public List<Orcamento> listarTodos() {
		// TODO
		return null;
	}

	public List<Orcamento> pesquisarOrcamentosPorFiltroModelo(
			Orcamento orcamentoModeloFiltro) {
		// TODO
		return null;
	}

	@Transactional
	public void salvar(Orcamento orcamento) throws APSServicoException {
		// TODO
	}

	public boolean verificarClienteComMesmoDocumento(
			Orcamento orcamentoParaVerificacao) {
		// TODO
		return false;
	}
}
