package br.com.aps.entidades;

import java.io.Serializable;

import javax.persistence.CascadeType;
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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.aps.entidades.enumeration.AtivoInativoEnum;
import br.com.aps.entidades.enumeration.TipoPessoaEnum;

/**
 * Cliente de uma {@link Empresa}.
 * 
 * @author Gustavo
 *
 */
@Entity
@Table
public class Cliente implements Serializable {

	private static final long serialVersionUID = 2381507774246931196L;

	@Column(name = "celular_1", nullable = true, length = 20)
	private String celular1;

	@Column(name = "celular_2", nullable = true, length = 20)
	private String celular2;

	@Column(name = "cpf_cnpj", nullable = false, length = 14)
	private String cpfCnpj;

	@Column(name = "email", nullable = false, length = 60)
	private String email;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "id_empresa", nullable = false)
	private Empresa empresa;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_endereco_principal")
	private Endereco enderecoPrincipal = new Endereco();

	@Column(name = "fax", nullable = true, length = 20)
	private String fax;

	@Id
	@SequenceGenerator(name = "sq_cliente", sequenceName = "sq_cliente")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_cliente")
	@Column(unique = true, nullable = false)
	private Long id;

	@Column(name = "nome_fantasia", nullable = false, length = 100)
	private String nomeFantasia;

	@Column(name = "nome_razao_social", nullable = false, length = 100)
	private String nomeRazaoSocial;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false, length = 1)
	private AtivoInativoEnum status = AtivoInativoEnum.A;

	@Column(name = "telefone_fixo_1", nullable = true, length = 20)
	private String telefoneFixo1;

	@Column(name = "telefone_fixo_2", nullable = true, length = 20)
	private String telefoneFixo2;

	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_pessoa", nullable = false, length = 2)
	private TipoPessoaEnum tipoPessoa;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public String getCelular1() {
		return celular1;
	}

	public String getCelular2() {
		return celular2;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public String getEmail() {
		return email;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public Endereco getEnderecoPrincipal() {
		return enderecoPrincipal;
	}

	public String getFax() {
		return fax;
	}

	public Long getId() {
		return id;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public String getNomeRazaoSocial() {
		return nomeRazaoSocial;
	}

	public AtivoInativoEnum getStatus() {
		return status;
	}

	public String getTelefoneFixo1() {
		return telefoneFixo1;
	}

	public String getTelefoneFixo2() {
		return telefoneFixo2;
	}

	public TipoPessoaEnum getTipoPessoa() {
		return tipoPessoa;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public boolean isStatusBoolean() {
		return getStatus() != null && getStatus().equals(AtivoInativoEnum.A);
	}

	public void setCelular1(String celular1) {
		this.celular1 = celular1;
	}

	public void setCelular2(String celular2) {
		this.celular2 = celular2;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public void setEnderecoPrincipal(Endereco enderecoPrincipal) {
		this.enderecoPrincipal = enderecoPrincipal;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public void setNomeRazaoSocial(String nomeRazaoSocial) {
		this.nomeRazaoSocial = nomeRazaoSocial;
	}

	public void setStatus(AtivoInativoEnum status) {
		this.status = status;
	}

	public void setStatusBoolean(boolean statusBoolean) {
		setStatus(statusBoolean == true ? AtivoInativoEnum.A
				: AtivoInativoEnum.I);
	}

	public void setTelefoneFixo1(String telefoneFixo1) {
		this.telefoneFixo1 = telefoneFixo1;
	}

	public void setTelefoneFixo2(String telefoneFixo2) {
		this.telefoneFixo2 = telefoneFixo2;
	}

	public void setTipoPessoa(TipoPessoaEnum tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

}