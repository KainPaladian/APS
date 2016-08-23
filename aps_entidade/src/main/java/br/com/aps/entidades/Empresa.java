package br.com.aps.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/***
 * 
 * Empresa cliente do sistema.
 * 
 * @author Gustavo
 *
 */
@Entity
@Table
public class Empresa implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 268395854563522426L;

	@Column(length = 14, nullable = false, name = "cnpj")
	private String cnpj;

	@Id
	@SequenceGenerator(name = "sq_empresa", sequenceName = "sq_empresa")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_empresa")
	private Long id;

	@Column(length = 100, nullable = false, name = "nome_fantasia")
	private String nomeFantasia;

	@Column(length = 100, nullable = false, name = "razao_social")
	private String razaoSocial;

	@OneToMany(mappedBy = "empresa", fetch = FetchType.EAGER)
	private List<Usuario> usuarios;

	public String getCnpj() {
		return cnpj;
	}

	public Long getId() {
		return id;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

}
