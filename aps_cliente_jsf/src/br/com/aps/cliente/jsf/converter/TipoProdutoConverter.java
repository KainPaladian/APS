package br.com.aps.cliente.jsf.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import br.com.aps.entidades.TipoProduto;
import br.com.aps.servico.bo.ProdutoBO;

@FacesConverter("tipoProdutoConverter")
public class TipoProdutoConverter implements Converter {

	private static final String VALOR_SELECIONE = "";

	@Inject
	ProdutoBO produtoBO;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if (value != null && !value.equals(VALOR_SELECIONE)) {
			return produtoBO.obterTipoProdutoPorId(Long.valueOf(value));
		} else {
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value != null && value instanceof TipoProduto) {
			return ((TipoProduto) value).getId().toString();
		} else {
			return VALOR_SELECIONE;
		}
	}
}
