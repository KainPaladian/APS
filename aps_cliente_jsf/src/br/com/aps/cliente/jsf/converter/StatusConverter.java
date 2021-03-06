package br.com.aps.cliente.jsf.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.aps.entidades.enumeration.AtivoInativoEnum;

@FacesConverter("statusConverter")
public class StatusConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if (value != null && !value.trim().isEmpty()) {
			return AtivoInativoEnum.getAtivoInativoEnumPorLabel(value);
		} else {
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value != null && !value.toString().isEmpty()) {
			return ((AtivoInativoEnum) value).getLabel();
		} else {
			return null;
		}
	}
}
