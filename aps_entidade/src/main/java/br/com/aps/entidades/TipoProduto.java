package br.com.aps.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
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
 *  
 * Agrupamento de {@link CategoriaProduto}
 * 
 * @author Gustavo
 *
 */
@Entity
@Table(name = "tipo_produto")
public class TipoProduto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -716520941497431384L;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tipoProduto")
	private List<CategoriaProduto> categoriasProduto;

	@Column(nullable = false, length = 100)
	private String descricao;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "id_empresa", nullable = false)
	private Empresa empresa;

	@Id
	@SequenceGenerator(name = "sq_tipo_produto", sequenceName = "sq_tipo_produto")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_tipo_produto")
	@Column(unique = true, nullable = false)
	private Long id;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TipoProduto other = (TipoProduto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public List<CategoriaProduto> getCategoriasProduto() {
		return categoriasProduto;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public void setCategoriasProduto(List<CategoriaProduto> categoriasProduto) {
		this.categoriasProduto = categoriasProduto;
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
}
