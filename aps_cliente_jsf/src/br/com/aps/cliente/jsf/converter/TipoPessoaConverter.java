package br.com.aps.cliente.jsf.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.aps.entidades.enumeration.TipoPessoaEnum;

@FacesConverter("tipoPessoaConverter")
public class TipoPessoaConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if (value != null && !value.trim().isEmpty()) {
			return TipoPessoaEnum.getTipoPessoaEnumPorLabel(value);
		} else {
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value != null && !value.toString().isEmpty()) {
			return ((TipoPessoaEnum) value).getLabel();
		} else {
			return null;
		}
	}
}
