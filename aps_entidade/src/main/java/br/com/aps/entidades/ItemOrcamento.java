package br.com.aps.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.aps.entidades.enumeration.TipoDescontoEnum;

/**
 * Itens que compoem um {@link Orcamento}.
 * 
 * @author Gustavo
 *
 */
@Entity
@Table(name = "item_orcamento")
public class ItemOrcamento implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1162544429615795953L;

	@Column(precision = 2)
	private Double desconto;

	@Id
	@SequenceGenerator(name = "sq_item_orcamento", sequenceName = "sq_item_orcamento")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_item_orcamento")
	@Column(unique = true, nullable = false)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "id_orcamento", nullable = false)
	private Orcamento orcamento;

	@Column(nullable = false)
	private Integer quantidade;

	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_desconto", nullable = false, length = 1)
	private TipoDescontoEnum tipoDesconto;

	public Double getDesconto() {
		return desconto;
	}

	public Long getId() {
		return id;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public TipoDescontoEnum getTipoDesconto() {
		return tipoDesconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public void setTipoDesconto(TipoDescontoEnum tipoDesconto) {
		this.tipoDesconto = tipoDesconto;
	}

}
