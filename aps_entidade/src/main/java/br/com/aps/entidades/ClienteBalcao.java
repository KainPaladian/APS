package br.com.aps.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import br.com.aps.entidades.enumeration.EstadosBrasileirosEnum;
import br.com.aps.entidades.enumeration.TipoPessoaEnum;

/**
 * Informações sobre um cliente atendido no balcão, ou seja, um cliente ainda
 * não cadastrado, que deseja um orçamento sem compromisso.
 * 
 * @author Gustavo
 *
 */
@Embeddable
public class ClienteBalcao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1756142261795102087L;

	@Column(name = "cliente_balcao_bairro", nullable = true, length = 100)
	private String bairro;

	@Column(name = "cliente_balcao_celular", length = 20, nullable = true)
	private String celular;

	@Column(name = "cliente_balcao_cep", nullable = true, length = 15)
	private String cep;

	@Column(name = "cliente_balcao_cidade", nullable = true, length = 100)
	private String cidade;

	@Column(name = "cliente_balcao_complemento", nullable = true, length = 50)
	private String complemento;

	@Column(name = "cliente_balcao_cpf_cnpj", nullable = true, length = 14)
	private String cpfCnpj;

	@Column(name = "cliente_balcao_email", nullable = true, length = 60)
	private String email;

	@Column(name = "cliente_balcao_logradouro", nullable = true, length = 100)
	private String logradouro;

	@Column(name = "cliente_balcao_nome_razao_social", length = 100, nullable = true)
	private String nomeRazaoSocial;

	@Column(name = "cliente_balcao_numero", nullable = true, length = 10)
	private Integer numero;

	@Column(name = "cliente_balcao_telefone", length = 20, nullable = true)
	private String telefone;

	@Enumerated(EnumType.STRING)
	@Column(name = "cliente_balcao_tipo_pessoa", nullable = true, length = 2)
	private TipoPessoaEnum tipoPessoa;

	@Enumerated(EnumType.STRING)
	@Column(name = "cliente_balcao_uf", nullable = true, length = 2)
	private EstadosBrasileirosEnum uf;

	public String getBairro() {
		return bairro;
	}

	public String getCelular() {
		return celular;
	}

	public String getCep() {
		return cep;
	}

	public String getCidade() {
		return cidade;
	}

	public String getComplemento() {
		return complemento;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public String getEmail() {
		return email;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public String getNomeRazaoSocial() {
		return nomeRazaoSocial;
	}

	public Integer getNumero() {
		return numero;
	}

	public String getTelefone() {
		return telefone;
	}

	public TipoPessoaEnum getTipoPessoa() {
		return tipoPessoa;
	}

	public EstadosBrasileirosEnum getUf() {
		return uf;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public void setNomeRazaoSocial(String nomeRazaoSocial) {
		this.nomeRazaoSocial = nomeRazaoSocial;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public void setTipoPessoa(TipoPessoaEnum tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	public void setUf(EstadosBrasileirosEnum uf) {
		this.uf = uf;
	}

}
