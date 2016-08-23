package br.com.aps.entidades.enumeration;

public enum TipoDescontoEnum {

	P("tipodescontoenum.percentual"), V("tipodescontoenum.valor.unitario");

	private String label;

	TipoDescontoEnum(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public static TipoDescontoEnum getTipoDescontoEnum(String label) {
		TipoDescontoEnum result = null;
		for (TipoDescontoEnum status : TipoDescontoEnum.values()) {
			if (status.getLabel().equals(label)) {
				result = status;
				break;
			}
		}
		return result;
	}
}
