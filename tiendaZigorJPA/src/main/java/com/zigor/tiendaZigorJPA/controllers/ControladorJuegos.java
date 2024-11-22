package com.zigor.tiendaZigorJPA.controllers;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zigor.tiendaZigorJPA.model.Juego;
import com.zigor.tiendaZigorJPA.servicios.ServicioCategorias;
import com.zigor.tiendaZigorJPA.servicios.ServicioJuegos;


@Controller
@RequestMapping("admin/")
public class ControladorJuegos {

	//Una forma muy comoda que nos da Spring para pedir
	//una bean es esta:
	@Autowired
	private ServicioJuegos servicioJuegos;
	
	@Autowired
	private ServicioCategorias servicioCategorias;
	
	//Lo que pongamos en @RequestMapping es la ruta que atiende el siguiente metodo
	@RequestMapping("juegos")
	public String obtenerJuegos(@RequestParam(name = "titulo", defaultValue = "") String titulo, @RequestParam(name = "comienzo", defaultValue = "0") Integer comienzo, Model model) {
		List<Juego> juegos = servicioJuegos.obtenerJuegos(titulo,comienzo,10);
		int totalJuegos = servicioJuegos.obtenerTotalJuegos(titulo);
		model.addAttribute("juegos", juegos);
		model.addAttribute("titulo", titulo);
		model.addAttribute("siguiente", comienzo + 10);
		model.addAttribute("anterior", comienzo - 10);
		model.addAttribute("total", totalJuegos);
		return "admin/juegos";//esto es la jsp que se muestra
	}
	
	@RequestMapping("juegos-borrar")
	public String borrarJuego(String id, Model model) {
		//Lo suyo sería validar la id antes de nada
		servicioJuegos.borrarJuego(Integer.parseInt(id));
		return obtenerJuegos("",0,model);
	}
	
	@RequestMapping("juegos-nuevo")
	public String nuevoJuego(Model model) {
		//Ahora vamos a mostrarle al usuario un form para
		//registrar un libro
		//Spring MVC nos pide que le mandemos al form
		//un objeto indicando el valor por defecto de cada campo
		Juego j = new Juego();
		j.setPrecio(1);
		model.addAttribute("nuevoJuego", j);
		model.addAttribute("categorias", servicioCategorias.obtenerCategorias());
		return "admin/juegos-nuevo";
	}
	
	@RequestMapping("juegos-guardar-nuevo")
	public String guardarNuevoJuego(@ModelAttribute("nuevoJuego") @Valid Juego nuevoJuego, BindingResult br, Model model, HttpServletRequest request) {
		//Lo suyo seria validar el libro antes de nada
		
		if (br.hasErrors()) {
			model.addAttribute("categorias", servicioCategorias.obtenerCategorias());
			return "admin/juegos-nuevo";
		}
		
		try {
			nuevoJuego.setImagenPortada(nuevoJuego.getArchivoSubido().getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		servicioJuegos.registrarJuego(nuevoJuego);
		
		return obtenerJuegos("",0,model);
	}
	
	@RequestMapping("juegos-editar")
	public String editarJuego(String id, Model model) {
		Juego l = servicioJuegos.obtenerJuegoPorId(Integer.parseInt(id));
		model.addAttribute("juegoEditar", l);
		return "admin/juegos-editar";		
	}
	
	@RequestMapping("juegos-guardar-cambio")
	public String guardarCambiosJuego(Juego juegoEditar, Model model, HttpServletRequest request) {
		//Antes de nada lo suyo seria validar los datos introducidos
		servicioJuegos.actualizarJuego(juegoEditar);
		
		
		return obtenerJuegos("",0,model);
	}
	
}
