package br.com.aps.cliente.jsf.controller;

import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import br.com.aps.cliente.jsf.util.MensagemResource;
import br.com.aps.cliente.jsf.util.ViewConstantes;
import br.com.aps.entidades.Empresa;
import br.com.aps.entidades.Usuario;

public class APSManageBean {

	public Date getDataAtual() {
		return new Date();
	}

	public Empresa getEmpresaSelecionada() {
		Empresa result = null;
		Object objEmpresa = getContext().getExternalContext().getSessionMap()
				.get(ViewConstantes.NOME_PARAMETRO_SESSAO_EMPRESA_SELECIONADA);
		if (objEmpresa != null) {
			result = (Empresa) objEmpresa;
		}
		return result;
	}

	public Locale getLocale() {
		return getRequest().getLocale();
	}

	public Usuario getUsuarioAutenticado() {
		Usuario usuario = null;
		Object objUsuario = getContext().getExternalContext().getSessionMap()
				.get(ViewConstantes.NOME_PARAMETRO_SESSAO_USUARIO_AUTENTICADO);
		if (objUsuario != null) {
			usuario = (Usuario) objUsuario;
		}
		return usuario;
	}

	public boolean isPostback() {
		return FacesContext.getCurrentInstance().isPostback();
	}

	private void addMessage(String chaveResource, String chaveResourceTitulo,
			Severity severity, String idMsg) {
		getContext().addMessage(
				idMsg,
				new FacesMessage(severity,
						getStringResource(chaveResourceTitulo),
						getStringResource(chaveResource)));
	}

	private HttpServletRequest getRequest() {
		HttpServletRequest request = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		return request;
	}

	/**
	 * Adiciona uma mensagem de alerta
	 * 
	 * @param chaveResource
	 *            Chave com o resource da mensagem.
	 */
	protected void addMensagemAlerta(String chaveResource) {
		String chaveResourceTitulo = MensagemResource.ALERTA_WARN_TITULO;
		Severity severity = FacesMessage.SEVERITY_WARN;
		addMessage(chaveResource, chaveResourceTitulo, severity, null);
	}

	/**
	 * Adiciona uma mensagem de erro.
	 * 
	 * @param chaveResource
	 *            Chave com o resource da mensagem.
	 */
	protected void addMensagemErro(String chaveResource) {
		String chaveResourceTitulo = MensagemResource.ALERTA_ERROR_TITULO;
		Severity severity = FacesMessage.SEVERITY_ERROR;
		addMessage(chaveResource, chaveResourceTitulo, severity, null);
	}

	/**
	 * Adiciona uma mensagem de sucesso.
	 * 
	 * @param chaveResource
	 *            Chave com o resource da mensagem.
	 */
	protected void addMensagemSucesso(String chaveResource) {
		String chaveResourceTitulo = MensagemResource.ALERTA_INFO_TITULO;
		Severity severity = FacesMessage.SEVERITY_INFO;
		addMessage(chaveResource, chaveResourceTitulo, severity, null);
	}

	/**
	 * Gera uma URL para redirecionamento, permitindo a passagem de par�metros
	 * por querystring.
	 * 
	 * @param url
	 * @param parametros
	 * @return
	 */
	protected String gerarURLOutComeExplicita(String url, String... parametros) {
		StringBuilder result = new StringBuilder();
		result.append(url).append("?").append("faces-redirect=true");
		if (parametros != null && parametros.length > 0) {
			for (int i = 0; i < parametros.length; i++) {
				result.append("&").append(parametros[i]);
			}
		}
		return result.toString();
	}

	/**
	 * Retorna o contexto JSF
	 * 
	 * @return
	 */
	protected FacesContext getContext() {
		FacesContext context = FacesContext.getCurrentInstance();
		return context;
	}

	/**
	 * Recupera uma string dos arquivo de resource conforme o locale atual.
	 * 
	 * @param chaveBundle
	 * @return String
	 */
	protected String getStringResource(String chaveBundle) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		String messageBundleName = facesContext.getApplication()
				.getMessageBundle();
		Locale locale = facesContext.getViewRoot().getLocale();
		ResourceBundle bundle = ResourceBundle.getBundle(messageBundleName,
				locale);
		return bundle.getString(chaveBundle);
	}

}