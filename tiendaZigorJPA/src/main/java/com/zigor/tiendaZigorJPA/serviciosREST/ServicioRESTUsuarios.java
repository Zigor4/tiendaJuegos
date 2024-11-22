package com.zigor.tiendaZigorJPA.serviciosREST;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zigor.tiendaZigorJPA.constantesValidaciones.ConstantesValidaciones;
import com.zigor.tiendaZigorJPA.model.Usuario;
import com.zigor.tiendaZigorJPA.servicios.ServicioUsuarios;
import com.zigor.tiendaZigorJPA.serviciosREST.respuestas.RespuestaLogin;
 
 
@RestController
public class ServicioRESTUsuarios {

	@Autowired
	private ServicioUsuarios servicioUsuarios;
	
	@RequestMapping("registrar-usuario-cliente")
	public String registrarUsuario(String nombre, String pass, String email, String telefono, String direccion, String dni) {
		
		if ( servicioUsuarios.comprobarEmailExiste(email)) {
			return "Email ya registrado";
		}
		
		//Validar datos:
		Pattern patternNombre = Pattern.compile(ConstantesValidaciones.regExpNombreRegistroUsuario, Pattern.CASE_INSENSITIVE);
		Pattern patternEmail = Pattern.compile(ConstantesValidaciones.regExpEmailRegistroUsuario, Pattern.CASE_INSENSITIVE);
		Pattern patternPass = Pattern.compile(ConstantesValidaciones.regExpPassRegistroUsuario, Pattern.CASE_INSENSITIVE);
		Pattern patternTelefono = Pattern.compile(ConstantesValidaciones.regExpTelefonoRegistroUsuario, Pattern.CASE_INSENSITIVE);
		Pattern patternDireccion = Pattern.compile(ConstantesValidaciones.regExpDireccionRegistroUsuario, Pattern.CASE_INSENSITIVE);
		Pattern patternDNI = Pattern.compile(ConstantesValidaciones.regExpDNIUsuario, Pattern.CASE_INSENSITIVE);
		
		Matcher matcherNombre = patternNombre.matcher(nombre);
		Matcher matcherEmail = patternEmail.matcher(email);
		Matcher matcherPass = patternPass.matcher(pass);
		Matcher matcherTelefono = patternTelefono.matcher(telefono);
		Matcher matcherDireccion = patternDireccion.matcher(direccion);
		Matcher matcherDNI = patternDNI.matcher(dni);
		
		if (!matcherNombre.matches()) {
			return "Nombre incorrecto desde el lado del servidor";
		}
		if (!matcherEmail.matches()) {
			return "Email incorrecto desde el lado del servidor";
		}
		if (!matcherPass.matches()) {
			return "Pass incorrecta desde el lado del servidor";
		}
		if (!matcherTelefono.matches()) {
			return "Teléfono incorrecto desde el lado del servidor";
		}
		if (!matcherDireccion.matches()) {
			return "Dirección incorrecto desde el lado del servidor";
		}
		if (!matcherDNI.matches()) {
			return "DNI incorrecto desde el lado del servidor";
		}
		
		
		
		Usuario u = new Usuario(nombre, pass, email, telefono, direccion, dni);
		servicioUsuarios.registrarUsuario(u);
		
		return "Usuario registrado correctamente";
	}
	
	@RequestMapping("identificar-usuario")
	public RespuestaLogin identificarUsuario(String email, String pass, HttpServletRequest request) {
		Usuario u = servicioUsuarios.obtenerUsuarioPorEmailYPass(email, pass);
		RespuestaLogin rl = null;
		if( u != null) {
			rl = new RespuestaLogin("ok", u.getNombre());
			//Vamos a meter en sesion la informacion del usuario que se ha identificado
			request.getSession().setAttribute("usuario", u);
		}else {
			rl = new RespuestaLogin("no-ok", "");
		}
		
		return rl;
	}
	
	@RequestMapping("cerrar-sesion-usuario")
	public String cerrarSesionUsuario(HttpServletRequest request) {
		request.getSession().removeAttribute("usuario");
		return "ok";		
	}
	
}
