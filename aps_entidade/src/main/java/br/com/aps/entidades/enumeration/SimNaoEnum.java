package br.com.aps.entidades.enumeration;

public enum SimNaoEnum {

	S("label.sim"), N("label.nao");

	private String label;

	SimNaoEnum(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public static SimNaoEnum getAtivoInativoEnumPorLabel(String label) {
		SimNaoEnum result = null;
		for (SimNaoEnum status : SimNaoEnum.values()) {
			if (status.getLabel().equals(label)) {
				result = status;
				break;
			}
		}
		return result;
	}
}
