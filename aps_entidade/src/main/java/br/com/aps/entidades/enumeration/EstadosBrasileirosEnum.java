package br.com.aps.entidades.enumeration;

public enum EstadosBrasileirosEnum {
	AC("Acre"), AL("Alagoas"), AM("Amazonas"), AP("Amap�"), BA("Bahia"), CE(
			"Cear�"), DF("Distrito Federal"), ES("Esp�rito Santo"), GO("Goi�s"), MA(
			"Maranh�o"), MG("Minas Gerais"), MS("Mato Grosso do Sul"), MT(
			"Mato Grosso"), PA("Par�"), PB("Para�ba"), PE("Pernambuco"), PI(
			"Piau�"), PR("Paran�"), RJ("Rio de Janeiro"), RN(
			"Rio Grande do Norte"), RO("Rond�nia"), RR("Roraima"), RS(
			"Rio Grande do Sul"), SC("Santa Catarina"), SE("Sergipe"), SP(
			"S�o Paulo"), TO("Tocantins");

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
