package br.com.aps.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Orçamento solicitado por um cliente para a produção de determinadas peças.
 * 
 * @author Gustavo
 *
 */
@Entity
@Table
public class Orcamento implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1784459122094479166L;

	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "id_cliente", nullable = true)
	private Cliente cliente;

	@Embedded
	private ClienteBalcao clienteBalcao;

	@Embedded
	private CondicaoEntregaOrcamento condicaoEntregaOrcamento;

	@Column(name = "data_hora_emissao", nullable = false)
	private Date dataHoraEmissao;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "id_empresa", nullable = false)
	private Empresa empresa;

	@Id
	@SequenceGenerator(name = "sq_orcamento", sequenceName = "sq_orcamento")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_orcamento")
	@Column(unique = true, nullable = false)
	private Long id;

	@OneToMany(mappedBy = "orcamento", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<ItemOrcamento> itens;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "id_usuario_vendedor", nullable = false)
	private Usuario vendedor;

	public Cliente getCliente() {
		return cliente;
	}

	public ClienteBalcao getClienteBalcao() {
		return clienteBalcao;
	}

	public CondicaoEntregaOrcamento getCondicaoEntregaOrcamento() {
		return condicaoEntregaOrcamento;
	}

	public Date getDataHoraEmissao() {
		return dataHoraEmissao;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public Long getId() {
		return id;
	}

	public List<ItemOrcamento> getItens() {
		return itens;
	}

	public Usuario getVendedor() {
		return vendedor;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void setClienteBalcao(ClienteBalcao clienteBalcao) {
		this.clienteBalcao = clienteBalcao;
	}

	public void setCondicaoEntregaOrcamento(
			CondicaoEntregaOrcamento condicaoEntregaOrcamento) {
		this.condicaoEntregaOrcamento = condicaoEntregaOrcamento;
	}

	public void setDataHoraEmissao(Date dataHoraEmissao) {
		this.dataHoraEmissao = dataHoraEmissao;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setItens(List<ItemOrcamento> itens) {
		this.itens = itens;
	}

	public void setVendedor(Usuario vendedor) {
		this.vendedor = vendedor;
	}

}
