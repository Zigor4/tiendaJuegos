package com.zigor.tiendaZigorJPA;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.zigor.tiendaZigorJPA.interceptores.InterceptorAdmin;

@Component
public class Config implements WebMvcConfigurer{

	@Autowired
	private InterceptorAdmin interceptorAdmin;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(interceptorAdmin);
		registry.addInterceptor(localeChangeInterceptor());
	}
		
	@Bean
	public SessionLocaleResolver localeResolver() {
		//En spring cuando ponemos @Bean tanto sobre una clase como 
		//sobre un metodo, estamos diciendo:
		// Si es una clase: que un objeto de la misma pase a formar
		// parte del contenedor de Spring, y podemos pedirlo por @Autowired
		//
		// Si es sobre un método: el objeto que de en el return el método
		// es el que pasa a formar parte del contedeor de spring, y también
		// se puede pedir por @Autowired
		
		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		
		localeResolver.setDefaultLocale(Locale.getDefault());
		
		return localeResolver;
	}
	
	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		//La anterior @Bean es solo para indicar que el idioma del usuario
		//se mantenga en sesion, y por defecto sea el que tiene en el navegador
		
		//Esta @Bean es para indicar como realizar un cambio de idioma
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		
		localeChangeInterceptor.setIgnoreInvalidLocale(true);

		localeChangeInterceptor.setParamName("idioma");
		
		return localeChangeInterceptor;
	}
}
