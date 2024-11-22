package com.zigor.tiendaZigorJPA.serviciosREST;

import javax.servlet.http.HttpServletRequest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zigor.tiendaZigorJPA.constantesValidaciones.ConstantesValidaciones;
import com.zigor.tiendaZigorJPA.model.Usuario;
import com.zigor.tiendaZigorJPA.model.tiposExtra.ResumenPedido;
import com.zigor.tiendaZigorJPA.servicios.ServicioPedidos;

@RestController
public class ServicioRESTPedidos {
	
	@Autowired
	private ServicioPedidos servicioPedidos;

	@RequestMapping("realizar-pedido-paso1")
	public String realizarPedidoPaso1(String nombre, String direccion, String provincia, String localidad, String codigoPostal, String telefono, HttpServletRequest request) {
		//Al completar el paso 1 vamos a generar una instancia/registro
		//de la entidad Pedido con el campo estado a INCOMPLETO
		//cuando el usuario complette todos los pasos, marcaremos el 
		//estado del pedido a completo
	    // Validaciones de datos
	    Pattern patternNombre = Pattern.compile(ConstantesValidaciones.regExpNombreCompletoCheckout, Pattern.CASE_INSENSITIVE);
	    Pattern patternDireccion = Pattern.compile(ConstantesValidaciones.regExpDireccionCheckout, Pattern.CASE_INSENSITIVE);
	    Pattern patternProvincia = Pattern.compile(ConstantesValidaciones.regExpProvinciaCheckout, Pattern.CASE_INSENSITIVE);
	    Pattern patternLocalidad = Pattern.compile(ConstantesValidaciones.regExpLocalidadCheckout, Pattern.CASE_INSENSITIVE);
	    Pattern patternCodigoPostal = Pattern.compile(ConstantesValidaciones.regExpCodigoPostalCheckout);
	    Pattern patternTelefono = Pattern.compile(ConstantesValidaciones.regExpTelefonoContactoCheckout);

	    Matcher matcherNombre = patternNombre.matcher(nombre);
	    Matcher matcherDireccion = patternDireccion.matcher(direccion);
	    Matcher matcherProvincia = patternProvincia.matcher(provincia);
	    Matcher matcherLocalidad = patternLocalidad.matcher(localidad);
	    Matcher matcherCodigoPostal = patternCodigoPostal.matcher(codigoPostal);
	    Matcher matcherTelefono = patternTelefono.matcher(telefono);

	    if (!matcherNombre.matches()) {
	        return "Nombre incorrecto desde el lado del servidor";
	    }
	    if (!matcherDireccion.matches()) {
	        return "Dirección incorrecta desde el lado del servidor";
	    }
	    if (!matcherProvincia.matches()) {
	        return "Provincia incorrecta desde el lado del servidor";
	    }
	    if (!matcherLocalidad.matches()) {
	        return "Localidad incorrecta desde el lado del servidor";
	    }
	    if (!matcherCodigoPostal.matches()) {
	        return "Código Postal incorrecto desde el lado del servidor";
	    }
	    if (!matcherTelefono.matches()) {
	        return "Teléfono incorrecto desde el lado del servidor";
	    }
		Usuario u = (Usuario) request.getSession().getAttribute("usuario");
		servicioPedidos.procesarPaso1(nombre, direccion, provincia, localidad, codigoPostal, telefono, u.getId());
		
		return "ok";
	}
	
	@RequestMapping("realizar-pedido-paso2")
	public String realizarPedidoPaso2(String tarjeta, String numero, String titular, String numeroSeguridadTarjeta, String fechaCaducidadTarjeta, HttpServletRequest request) {
	    // Validaciones de datos		
	    Pattern patternNumeroTarjeta = Pattern.compile(ConstantesValidaciones.regExpNumeroTarjetaCheckout);
	    Pattern patternTitularTarjeta = Pattern.compile(ConstantesValidaciones.regExpTitularTarjetaCheckout, Pattern.CASE_INSENSITIVE);
	    Pattern patternCVV = Pattern.compile(ConstantesValidaciones.regExpCVVCheckout);
	    Pattern patternFechaCaducidad = Pattern.compile(ConstantesValidaciones.regExpFechaCaducidadCheckout);

	    Matcher matcherNumeroTarjeta = patternNumeroTarjeta.matcher(numero);
	    Matcher matcherTitularTarjeta = patternTitularTarjeta.matcher(titular);
	    Matcher matcherCVV = patternCVV.matcher(numeroSeguridadTarjeta);
	    Matcher matcherFechaCaducidad = patternFechaCaducidad.matcher(fechaCaducidadTarjeta);
	    
	    if ("0".equals(tarjeta)) {
	        return "Tipo de tarjeta no seleccionado desde el lado del servidor";
	    }

	    if (!matcherNumeroTarjeta.matches()) {
	        return "Número de tarjeta incorrecto desde el lado del servidor";
	    }
	    if (!matcherTitularTarjeta.matches()) {
	        return "Titular de tarjeta incorrecto desde el lado del servidor";
	    }
	    if (!matcherCVV.matches()) {
	        return "Código de seguridad (CVV) incorrecto desde el lado del servidor";
	    }
	    if (!matcherFechaCaducidad.matches()) {
	        return "Fecha de caducidad incorrecta desde el lado del servidor";
	    }
		
		Usuario u = (Usuario) request.getSession().getAttribute("usuario");
		servicioPedidos.procesarPaso2(titular, numero, tarjeta, numeroSeguridadTarjeta, fechaCaducidadTarjeta, u.getId());
		
		return "ok";
	}
	
	@RequestMapping("realizar-pedido-paso3")
	public ResumenPedido realizarPedidoPaso3(String detallesEntrega, String valoracionWeb,  HttpServletRequest request) {
	    // Validación del detalle de entrega
	    Pattern patternDetallesEntrega = Pattern.compile(ConstantesValidaciones.regExpDetallesEntregaCheckout, Pattern.CASE_INSENSITIVE);
	    Matcher matcherDetallesEntrega = patternDetallesEntrega.matcher(detallesEntrega);

	    int valoracion;
	    try {
	        valoracion = Integer.parseInt(valoracionWeb);
	        if (valoracion < 1 || valoracion > 5) {
	            return null;
	        }
	    } catch (NumberFormatException e) {
	        return null; 
	    }
	    
	    
	    if (!matcherDetallesEntrega.matches()) {
	        throw new IllegalArgumentException("Detalles de entrega incorrectos desde el lado del servidor");
	    }
		
		
		Usuario u = (Usuario) request.getSession().getAttribute("usuario");
		servicioPedidos.procesarPaso3(detallesEntrega, valoracionWeb, u.getId());
		
		ResumenPedido resumen = servicioPedidos.obtenerResumenDelPedido(u.getId());
		
		return resumen;
	}
	
	@RequestMapping("confirmar_pedido")
	public String confirmarPedido(HttpServletRequest request) {
		Usuario u = (Usuario) request.getSession().getAttribute("usuario");
		servicioPedidos.confirmarPedido(u.getId());
		return "pedido completado";
	}	
	
}
