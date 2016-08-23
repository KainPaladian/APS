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
 * Usuário do sistema. Normalmente é um funcionário de uma {@link Empresa}.
 * 
 * @author Gustavo
 *
 */
@Entity
@Table
public class Usuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2182397314691141364L;

	@Column(length = 100)
	private String email;

	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "id_empresa", nullable = true)
	private Empresa empresa;

	@Id
	@SequenceGenerator(name = "sq_usuario", sequenceName = "sq_usuario")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_usuario")
	private Long id;

	@Column(length = 50)
	private String matricula;

	@Column(length = 100, nullable = false)
	private String nome;

	@OneToMany(mappedBy = "vendedor", fetch = FetchType.EAGER)
	private List<Orcamento> orcamentos;

	public String getEmail() {
		return email;
	}

	public Long getId() {
		return id;
	}

	public String getMatricula() {
		return matricula;
	}

	public String getNome() {
		return nome;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
