package com.zigor.tiendaZigorJPA.interceptores;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class InterceptorAdmin implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		//La idea es que este codigo se va  ejecitar siempre antes de cualquier acceso a /admin
		
		//si este codigo recibe un parametro con la contraseña de admin pues guardo
		//un token en sesion, para identificar al usuario actual como admin
		
		String passAdmin = "123";
		if (request.getParameter("pass-login-admin") != null) {
			if (request.getParameter("pass-login-admin").equals(passAdmin)) {
				request.getSession().setAttribute("token-admin", "ok");
			}
		}
		
		//a parte de lo anterior, compruebo el token para permitir
		//accesde haciea admin o redirigir a un form de identificacion de admin
		if(request.getRequestURI().contains("/admin/")) {
			if (!(request.getSession().getAttribute("token-admin") != null && request.getSession().getAttribute("token-admin").equals("ok"))) {
				response.sendRedirect("../loginAdmin");
				return false;
			}
		}
		
		
		
		// TODO Auto-generated method stub
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

	
	
}
