package com.zigor.tiendaZigorJPA.servicios;

import java.util.List;
import java.util.Map;

import com.zigor.tiendaZigorJPA.model.Juego;

public interface ServicioJuegos {
	
	
	void registrarJuego(Juego l);
	
	List<Juego> obtenerJuegos();
	
	List<Juego> obtenerJuegos(String titulo);
	
	List<Juego> obtenerJuegos(String titulo, int comienzo, int resultadosPorPagina);
	
	void borrarJuego (int id);
	
	Juego obtenerJuegoPorId(int id);
	
	void actualizarJuego(Juego l);
	
	Map<String, Object> obtenerJuegoVerDetallesPorId(int id);

	List<Map<String, Object>> obtenerJuegosParaListar();

	List<Map<String, Object>> obtenerJuegosParaListar(String titulo, int comienzo);
	
	int obtenerTotalJuegos();
	
	int obtenerTotalJuegos(String titulo);
}
