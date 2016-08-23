package br.com.aps.cliente.jsf.util;

/**
 * Tipos de fluxos das telas de CRUD;
 * 
 * @author Gustavo
 *
 */
public enum TipoFluxoCRUDEnum {

	CREATE, UPDATE, READ, SEARCH;

	TipoFluxoCRUDEnum() {

	}

	public static TipoFluxoCRUDEnum getTipoFluxoCRUDEnumPorLabel(String label) {
		TipoFluxoCRUDEnum result = null;
		for (TipoFluxoCRUDEnum tipoFluxoCRUDEnum : TipoFluxoCRUDEnum.values()) {
			if (tipoFluxoCRUDEnum.toString().equals(label)) {
				result = tipoFluxoCRUDEnum;
				break;
			}
		}
		return result;
	}

}
