package com.zigor.tiendaZigorJPA.servicios;

import java.util.List;

import com.zigor.tiendaZigorJPA.model.Pedido;
import com.zigor.tiendaZigorJPA.model.tiposExtra.ResumenPedido;

public interface ServicioPedidos {
	
	List<Pedido> obtenerPedidos();
	Pedido obtenerPedidoPorId(int idPedido);
	
	void procesarPaso1(String nombreCompleto, String direccion, String provincia, String localidad, String codigoPostal, String telefono, long idUsuario);
	void procesarPaso2(String titular, String numero, String tipoTarjeta, String numeroSeguridadTarjeta, String fechaCaducidadTarjeta, long idUsuario);
	void procesarPaso3(String detallesEntrega, String valoracionWeb, long idUsuario);
	ResumenPedido obtenerResumenDelPedido(long idUsuario);
	void confirmarPedido(long idUsuario);
	void actualizarEstadoPedido(int id, String estado);
	
	
}
