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

import br.com.aps.entidades.enumeration.SimNaoEnum;

/**
 * Agrupamento de {@link Produto}
 * 
 * @author Gustavo
 *
 */
@Entity
@Table(name = "categoria_produto")
public class CategoriaProduto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5165625107939968751L;

	@Column(length = 50, nullable = false)
	private String descricao;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "id_empresa", nullable = false)
	private Empresa empresa;

	@Id
	@SequenceGenerator(name = "sq_categoria_produto", sequenceName = "sq_categoria_produto")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_categoria_produto")
	@Column(unique = true, nullable = false)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_tipo_produto", nullable = false)
	private TipoProduto tipoProduto;

	@Enumerated(EnumType.STRING)
	@Column(name = "usa_comprimento", nullable = false, length = 1)
	private SimNaoEnum usaComprimento;

	@Enumerated(EnumType.STRING)
	@Column(name = "usa_diametro", nullable = false, length = 1)
	private SimNaoEnum usaDiametro;

	@Enumerated(EnumType.STRING)
	@Column(name = "usa_espessura", nullable = false, length = 1)
	private SimNaoEnum usaEspessura;

	@Enumerated(EnumType.STRING)
	@Column(name = "usa_largura", nullable = false, length = 1)
	private SimNaoEnum usaLargura;

	@Enumerated(EnumType.STRING)
	@Column(name = "usa_massa", nullable = false, length = 1)
	private SimNaoEnum usaMassa;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CategoriaProduto other = (CategoriaProduto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public String getDescricao() {
		return descricao;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public Long getId() {
		return id;
	}

	public TipoProduto getTipoProduto() {
		return tipoProduto;
	}

	public SimNaoEnum getUsaComprimento() {
		return usaComprimento;
	}

	public SimNaoEnum getUsaDiametro() {
		return usaDiametro;
	}

	public SimNaoEnum getUsaEspessura() {
		return usaEspessura;
	}

	public SimNaoEnum getUsaLargura() {
		return usaLargura;
	}

	public SimNaoEnum getUsaMassa() {
		return usaMassa;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public boolean isUsaComprimento() {
		return getUsaComprimento().equals(SimNaoEnum.S);
	}

	public boolean isUsaDiametro() {
		return getUsaDiametro().equals(SimNaoEnum.S);
	}

	public boolean isUsaEspessura() {
		return getUsaEspessura().equals(SimNaoEnum.S);
	}

	public boolean isUsaLargura() {
		return getUsaLargura().equals(SimNaoEnum.S);
	}

	public boolean isUsaMassa() {
		return getUsaMassa().equals(SimNaoEnum.S);
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setTipoProduto(TipoProduto tipoProduto) {
		this.tipoProduto = tipoProduto;
	}

	public void setUsaComprimento(SimNaoEnum usaComprimento) {
		this.usaComprimento = usaComprimento;
	}

	public void setUsaDiametro(SimNaoEnum usaDiametro) {
		this.usaDiametro = usaDiametro;
	}

	public void setUsaEspessura(SimNaoEnum usaEspessura) {
		this.usaEspessura = usaEspessura;
	}

	public void setUsaLargura(SimNaoEnum usaLargura) {
		this.usaLargura = usaLargura;
	}

	public void setUsaMassa(SimNaoEnum usaMassa) {
		this.usaMassa = usaMassa;
	}

}
