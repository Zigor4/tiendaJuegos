package com.zigor.tiendaZigorJPA.servicios;

import com.zigor.tiendaZigorJPA.model.Usuario;

public interface ServicioUsuarios {

	void registrarUsuario(Usuario u);
	
	Usuario obtenerUsuarioPorEmailYPass(String email, String pass);
	
	boolean comprobarEmailExiste(String email);
	
}
