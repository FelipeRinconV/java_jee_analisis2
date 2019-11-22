package co.uniquindio.filters;

import java.io.IOException;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
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

import co.uniquindio.beans.SeguridadBean;


//@WebFilter("/secure/*")
public class SeguridadFilter implements Filter {

	@Inject
	private BeanManager beanManager;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		request.getSession(false);
		String loginURL = request.getContextPath() + "/pag1.xhtml";
		
		
		
		
		
		System.out.println(loginURL);
		Bean<?> bean = beanManager.getBeans("seguridadBean").iterator().next();
		CreationalContext<?> ctx = beanManager.createCreationalContext(bean);
		SeguridadBean seguridadBean = (SeguridadBean) beanManager.getReference(bean, bean.getBeanClass(), ctx);
		boolean autenticado = seguridadBean != null && seguridadBean.isAutenticado();
		if (autenticado) {
			chain.doFilter(request, response);
		} else {
			response.sendRedirect(loginURL);
		}
	}

	

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
