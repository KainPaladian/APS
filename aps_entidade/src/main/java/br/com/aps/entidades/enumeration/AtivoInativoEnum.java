package br.com.aps.entidades.enumeration;

public enum AtivoInativoEnum {

	A("label.ativo"), I("label.inativo");

	private String label;

	AtivoInativoEnum(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public static AtivoInativoEnum getAtivoInativoEnumPorLabel(String label) {
		AtivoInativoEnum result = null;
		for (AtivoInativoEnum status : AtivoInativoEnum.values()) {
			if (status.getLabel().equals(label)) {
				result = status;
				break;
			}
		}
		return result;
	}
}
