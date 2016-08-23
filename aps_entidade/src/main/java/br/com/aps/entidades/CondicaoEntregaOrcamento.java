package br.com.aps.entidades;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.com.aps.entidades.enumeration.TipoCondicaoEntregaEnum;

/**
 * Informações sobre as condições de entrega de um determinado orçamento.
 * 
 * @author Gustavo
 *
 */
@Embeddable
public class CondicaoEntregaOrcamento implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1460229412928479890L;

	@Column(name = "data_entrega", nullable = false)
	private Date dataEntrega;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name = "id_endereco_entrega", nullable = false)
	private Endereco enderecoEntrega = new Endereco();

	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_condicao_entrega", nullable = false, length = 3)
	private TipoCondicaoEntregaEnum tipoCondicaoEntregaEnum;

	public Date getDataEntrega() {
		return dataEntrega;
	}

	public Endereco getEnderecoEntrega() {
		return enderecoEntrega;
	}

	public TipoCondicaoEntregaEnum getTipoCondicaoEntregaEnum() {
		return tipoCondicaoEntregaEnum;
	}

	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public void setEnderecoEntrega(Endereco enderecoEntrega) {
		this.enderecoEntrega = enderecoEntrega;
	}

	public void setTipoCondicaoEntregaEnum(
			TipoCondicaoEntregaEnum tipoCondicaoEntregaEnum) {
		this.tipoCondicaoEntregaEnum = tipoCondicaoEntregaEnum;
	}

}
