package br.com.aps.cliente.jsf.filter;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.aps.cliente.jsf.util.ViewConstantes;
import br.com.aps.entidades.Empresa;
import br.com.aps.entidades.Usuario;
import br.com.aps.servico.bo.EmpresaBO;
import br.com.aps.servico.bo.UsuarioBO;

@WebFilter("/faces/*")
public class AutorizacaoFilter implements Filter {

	@Inject
	private EmpresaBO empresaBO;

	@Inject
	private UsuarioBO usuarioBO;

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		HttpSession session = request.getSession();
		// TODO: válido até criar o mecanismo de autenticação.
		Object objEmpresaSelecionada = session
				.getAttribute(ViewConstantes.NOME_PARAMETRO_SESSAO_EMPRESA_SELECIONADA);
		Object objUsuarioAutenticado = session
				.getAttribute(ViewConstantes.NOME_PARAMETRO_SESSAO_USUARIO_AUTENTICADO);
		if (objEmpresaSelecionada == null) {
			Empresa empresaSelecionada = empresaBO.getPorId(1L);
			session.setAttribute(
					ViewConstantes.NOME_PARAMETRO_SESSAO_EMPRESA_SELECIONADA,
					empresaSelecionada);
		}
		if (objUsuarioAutenticado == null) {
			Usuario usuarioAutenticado = usuarioBO.getPorId(1L);
			session.setAttribute(
					ViewConstantes.NOME_PARAMETRO_SESSAO_USUARIO_AUTENTICADO,
					usuarioAutenticado);
		}
		filterChain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}
}
