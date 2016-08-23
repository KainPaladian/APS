package br.com.aps.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.aps.entidades.enumeration.EstadosBrasileirosEnum;

@Entity
@Table
public class Endereco implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8504349413306648310L;

	@Column(name = "bairro", nullable = true, length = 100)
	private String bairro;

	@Column(name = "cep", nullable = true, length = 15)
	private String cep;

	@Column(name = "cidade", nullable = true, length = 100)
	private String cidade;

	@OneToOne(mappedBy = "enderecoPrincipal")
	private Cliente cliente;

	@Column(name = "complemento", nullable = true, length = 50)
	private String complemento;

	@Id
	@SequenceGenerator(name = "sq_endereco", sequenceName = "sq_endereco")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_endereco")
	@Column(unique = true, nullable = false)
	private Long id;

	@Column(name = "logradouro", nullable = true, length = 100)
	private String logradouro;

	@Column(name = "numero", nullable = true, length = 10)
	private Integer numero;

	@Enumerated(EnumType.STRING)
	@Column(name = "uf", nullable = true, length = 2)
	private EstadosBrasileirosEnum uf;

	public String getBairro() {
		return bairro;
	}

	public String getCep() {
		return cep;
	}

	public String getCidade() {
		return cidade;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public String getComplemento() {
		return complemento;
	}

	public Long getId() {
		return id;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public Integer getNumero() {
		return numero;
	}

	public EstadosBrasileirosEnum getUf() {
		return uf;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public void setUf(EstadosBrasileirosEnum uf) {
		this.uf = uf;
	}
}
