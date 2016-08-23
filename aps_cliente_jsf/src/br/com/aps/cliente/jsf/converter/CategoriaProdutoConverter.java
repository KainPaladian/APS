package br.com.aps.cliente.jsf.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import br.com.aps.entidades.CategoriaProduto;
import br.com.aps.servico.bo.ProdutoBO;

@FacesConverter("categoriaProdutoConverter")
public class CategoriaProdutoConverter implements Converter {

	private static final String VALOR_SELECIONE = "0";

	@Inject
	ProdutoBO produtoBO;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if (value != null && !value.equals(VALOR_SELECIONE)) {
			return produtoBO.obterCategoriaProdutoPorId(Long.valueOf(value));
		} else {
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value != null && value instanceof CategoriaProduto) {
			return ((CategoriaProduto) value).getId().toString();
		} else {
			return VALOR_SELECIONE;
		}
	}
}
