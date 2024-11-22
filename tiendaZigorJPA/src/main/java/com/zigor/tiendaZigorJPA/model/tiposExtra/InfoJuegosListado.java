package com.zigor.tiendaZigorJPA.model.tiposExtra;

import java.util.List;
import java.util.Map;

public class InfoJuegosListado {

	private List<Map<String, Object>> juegos;
	private int totalJuegos;
	
	
	public List<Map<String, Object>> getJuegos() {
		return juegos;
	}
	public void setJuegos(List<Map<String, Object>> juegos) {
		this.juegos = juegos;
	}
	public int getTotalJuegos() {
		return totalJuegos;
	}
	public void setTotalJuegos(int totalJuegos) {
		this.totalJuegos = totalJuegos;
	}
	
	
}
