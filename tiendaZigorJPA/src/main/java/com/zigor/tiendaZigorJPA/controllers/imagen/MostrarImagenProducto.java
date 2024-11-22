package com.zigor.tiendaZigorJPA.controllers.imagen;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zigor.tiendaZigorJPA.servicios.ServicioJuegos;

@Controller
public class MostrarImagenProducto {
	
	@Autowired
	private ServicioJuegos servicioJuegos;
	
	@RequestMapping("mostrar-imagen")
	public void mostrarImagen(String id, HttpServletResponse response) throws IOException {
		byte[] info = servicioJuegos.obtenerJuegoPorId(Integer.parseInt(id)).getImagenPortada();
		if (info == null) {
			return ;
		}
		response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
		response.getOutputStream().write(info);
		
		response.getOutputStream().close();
	}
	
	
}
