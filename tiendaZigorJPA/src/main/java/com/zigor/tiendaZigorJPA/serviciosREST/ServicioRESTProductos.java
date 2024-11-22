package com.zigor.tiendaZigorJPA.serviciosREST;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.zigor.tiendaZigorJPA.model.tiposExtra.InfoJuegosListado;
import com.zigor.tiendaZigorJPA.servicios.ServicioJuegos;

//Los servicios ahora en Spring MVC se pueden hacer usando controladores
//que no devuelven una vista, sino JSON

@RestController
public class ServicioRESTProductos {

	@Autowired
	private ServicioJuegos servicioJuegos;
	
	@RequestMapping("obtener-productos-json")
	public InfoJuegosListado obtenerProductosEnJSON(@RequestParam(name="titulo", defaultValue = "") String titulo, @RequestParam(name="comienzo", defaultValue = "0") Integer comienzo){
		InfoJuegosListado info = new InfoJuegosListado();
			
		info.setJuegos(servicioJuegos.obtenerJuegosParaListar(titulo,comienzo));
		info.setTotalJuegos(servicioJuegos.obtenerTotalJuegos(titulo));	
		
		return info;
	}
	
	@RequestMapping("obtener-detalles-juego")
	//@RequestParam("id") Integer id -> es para recibir directamente como entero el id
	public String obtenerDetallesLibro(@RequestParam("id") Integer id) {
		return new Gson().toJson(servicioJuegos.obtenerJuegoPorId(id));
	}
	
}
