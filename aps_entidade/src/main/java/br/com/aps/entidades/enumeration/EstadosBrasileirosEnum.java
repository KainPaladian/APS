package br.com.aps.entidades.enumeration;

public enum EstadosBrasileirosEnum {
	AC("Acre"), AL("Alagoas"), AM("Amazonas"), AP("Amapá"), BA("Bahia"), CE(
			"Ceará"), DF("Distrito Federal"), ES("Espírito Santo"), GO("Goiás"), MA(
			"Maranhão"), MG("Minas Gerais"), MS("Mato Grosso do Sul"), MT(
			"Mato Grosso"), PA("Pará"), PB("Paraíba"), PE("Pernambuco"), PI(
			"Piauí"), PR("Paraná"), RJ("Rio de Janeiro"), RN(
			"Rio Grande do Norte"), RO("Rondônia"), RR("Roraima"), RS(
			"Rio Grande do Sul"), SC("Santa Catarina"), SE("Sergipe"), SP(
			"São Paulo"), TO("Tocantins");

	private String nome;

	EstadosBrasileirosEnum(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public static EstadosBrasileirosEnum getEstadosBrasileirosEnumPorSigla(
			String nome) {
		EstadosBrasileirosEnum result = null;
		for (EstadosBrasileirosEnum estado : EstadosBrasileirosEnum.values()) {
			if (estado.toString().equals(nome)) {
				result = estado;
				break;
			}
		}
		return result;
	}
}
