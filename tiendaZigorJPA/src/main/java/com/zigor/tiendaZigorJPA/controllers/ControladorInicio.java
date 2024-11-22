package com.zigor.tiendaZigorJPA.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zigor.tiendaZigorJPA.servicioSetUp.SetUp;

@Controller
public class ControladorInicio {

	
	@Autowired
	private SetUp setUp;
	
	@Autowired
	private MessageSource messageSource;
	
	//Este es el método que se ejecuta por defecto
	//al entrar en la aplicación
	@RequestMapping()
	public String inicio() {
		setUp.setUp();
		//vamos a ver el idioma del usuario para devolverle index.html o index_en.html
		String idiomaActual = messageSource.getMessage("idioma", null, LocaleContextHolder.getLocale());
		
		if (idiomaActual.equals("en")) {
			return "index_en";
		}
		
		if (idiomaActual.equals("de")) {
			return "index_de";
		}
		
		return "index";
	}
	
}
