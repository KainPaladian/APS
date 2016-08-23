package br.com.aps.entidades;

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
import javax.persistence.Transient;

import br.com.aps.entidades.enumeration.AtivoInativoEnum;
import br.com.aps.entidades.enumeration.SimNaoEnum;

/**
 * Peça de metal bruta, utilizada como matéria prima, que cortada, produz as
 * peças solicitadas nos projetos.
 * 
 * @author Gustavo
 *
 */
@Entity
@Table
public class Produto {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_categoria_produto", nullable = false)
	private CategoriaProduto categoriaProduto;

	@Column(nullable = true, precision = 2, length = 6)
	private Double comprimento;

	@Column(length = 50, nullable = false)
	private String descricao;

	@Column(nullable = true, precision = 2, length = 6)
	private Double diametro;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "id_empresa", nullable = false)
	private Empresa empresa;

	@Column(length = 50, nullable = false)
	private String especificacao;

	@Column(nullable = true, precision = 2, length = 6)
	private Double espessura;

	@Id
	@SequenceGenerator(name = "sq_produto", sequenceName = "sq_produto")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_produto")
	@Column(unique = true, nullable = false)
	private Long id;

	@Column(nullable = true, precision = 2)
	private Double largura;

	@Column(nullable = true, precision = 2, length = 6)
	private Double massa;

	@Column(name = "perct_max_desc_pag_a_vistsa", nullable = true, precision = 2)
	private Double percentualMaximoDescontoPagamentoAvistsa;

	@Enumerated(EnumType.STRING)
	@Column(name = "permite_desconto", nullable = false, length = 1)
	private SimNaoEnum permiteDesconto = SimNaoEnum.S;

	@Column(precision = 2, nullable = false)
	private Double preco;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 1)
	private AtivoInativoEnum status = AtivoInativoEnum.A;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 1)
	private SimNaoEnum sucata = SimNaoEnum.N;

	@Transient
	private TipoProduto tipoProduto;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public CategoriaProduto getCategoriaProduto() {
		return categoriaProduto;
	}

	public Double getComprimento() {
		return comprimento;
	}

	public String getDescricao() {
		return descricao;
	}

	public Double getDiametro() {
		return diametro;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public String getEspecificacao() {
		return especificacao;
	}

	public Double getEspessura() {
		return espessura;
	}

	public Long getId() {
		return id;
	}

	public Double getLargura() {
		return largura;
	}

	public Double getMassa() {
		return massa;
	}

	public Double getPercentualMaximoDescontoPagamentoAvistsa() {
		return percentualMaximoDescontoPagamentoAvistsa;
	}

	public boolean getPermiteDesconto() {
		return permiteDesconto.equals(SimNaoEnum.S);
	}

	public Double getPreco() {
		return preco;
	}

	public AtivoInativoEnum getStatus() {
		return status;
	}

	public SimNaoEnum getSucata() {
		return sucata;
	}

	public Boolean getSucataBoolean() {
		return sucata != null ? this.sucata.equals(SimNaoEnum.S) : null;
	}

	public TipoProduto getTipoProduto() {
		return tipoProduto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public boolean isAtivo() {
		return getStatus() != null && getStatus().equals(AtivoInativoEnum.A);
	}

	public void setAtivo(boolean ativo) {
		if (ativo)
			setStatus(AtivoInativoEnum.A);
		else
			setStatus(AtivoInativoEnum.I);
	}

	public void setCategoriaProduto(CategoriaProduto categoriaProduto) {
		this.categoriaProduto = categoriaProduto;
	}

	public void setComprimento(Double comprimento) {
		this.comprimento = comprimento;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setDiametro(Double diametro) {
		this.diametro = diametro;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public void setEspecificacao(String especificacao) {
		this.especificacao = especificacao;
	}

	public void setEspessura(Double espessura) {
		this.espessura = espessura;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setLargura(Double largura) {
		this.largura = largura;
	}

	public void setMassa(Double massa) {
		this.massa = massa;
	}

	public void setPercentualMaximoDescontoPagamentoAvistsa(
			Double percentualMaximoDescontoPagamentoAvistsa) {
		this.percentualMaximoDescontoPagamentoAvistsa = percentualMaximoDescontoPagamentoAvistsa;
	}

	public void setPermiteDesconto(boolean permiteDesconto) {
		this.permiteDesconto = permiteDesconto ? SimNaoEnum.S : SimNaoEnum.N;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public void setStatus(AtivoInativoEnum status) {
		this.status = status;
	}

	public void setSucata(SimNaoEnum sucata) {
		this.sucata = sucata;
	}

	public void setSucataBoolean(Boolean sucata) {
		if (sucata != null) {
			this.sucata = sucata ? SimNaoEnum.S : SimNaoEnum.N;
		} else {
			this.sucata = null;
		}
	}

	public void setTipoProduto(TipoProduto tipoProduto) {
		this.tipoProduto = tipoProduto;
	}

}
