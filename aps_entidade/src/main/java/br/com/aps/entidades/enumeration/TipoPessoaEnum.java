package br.com.aps.entidades.enumeration;

public enum TipoPessoaEnum {

	PF("label.pessoa.fisica"), PJ("label.pessoa.juridica");

	private String label;

	TipoPessoaEnum(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public static TipoPessoaEnum getTipoPessoaEnumPorLabel(String label) {
		TipoPessoaEnum result = null;
		for (TipoPessoaEnum tipoPessoa : TipoPessoaEnum.values()) {
			if (tipoPessoa.getLabel().equals(label)) {
				result = tipoPessoa;
				break;
			}
		}
		return result;
	}
}
