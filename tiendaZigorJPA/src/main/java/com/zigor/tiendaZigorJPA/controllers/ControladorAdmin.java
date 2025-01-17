package com.zigor.tiendaZigorJPA.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ControladorAdmin {

	@Autowired
	private ControladorInicio controladorInicio;
	
	@RequestMapping("admin/")
	public String admin() {
		return "admin/inicio"; //Devolvemos la vista en jsps/admin/inicio.jsp
	}
	
	@RequestMapping("loginAdmin")
	public String loginAdmin() {
		return "admin/login-admin";
	}

	@RequestMapping("logout-admin")
	public String logoutAdmin(HttpServletRequest request) {
		request.getSession().invalidate();
//		request.getSession().removeAttribute("usuario");
		return controladorInicio.inicio();
	}
}
