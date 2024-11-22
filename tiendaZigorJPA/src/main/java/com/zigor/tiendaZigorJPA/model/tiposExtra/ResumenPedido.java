package com.zigor.tiendaZigorJPA.model.tiposExtra;

import java.util.List;
import java.util.Map;

public class ResumenPedido {
	//Productos que hay en el carrito
	private List<Map<String, Object>> juegos;
	
	//datos del paso 1
	private String nombreCompleto;
	private String direccion;
	private String provincia;
	private String localidad;
	private String codigoPostal;
	private String telefono;
	
	//datos del paso 2
	private String titularTarjeta;
	private String numeroTarjeta;
	private String tipoTarjeta;
	private String numeroSeguridadTarjeta;
	private String fechaCaducidadTarjeta;	
	
	//datos del paso 3
	private String detallesEntrega;
	private String valoracionWeb;
	
	
	public List<Map<String, Object>> getJuegos() {
		return juegos;
	}
	public void setJuegos(List<Map<String, Object>> juegos) {
		this.juegos = juegos;
	}
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public String getTitularTarjeta() {
		return titularTarjeta;
	}
	public void setTitularTarjeta(String titularTarjeta) {
		this.titularTarjeta = titularTarjeta;
	}
	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}
	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}
	public String getTipoTarjeta() {
		return tipoTarjeta;
	}
	public void setTipoTarjeta(String tipoTarjeta) {
		this.tipoTarjeta = tipoTarjeta;
	}
	public String getDetallesEntrega() {
		return detallesEntrega;
	}
	public void setDetallesEntrega(String detallesEntrega) {
		this.detallesEntrega = detallesEntrega;
	}
	public String getValoracionWeb() {
		return valoracionWeb;
	}
	public void setValoracionWeb(String valoracionWeb) {
		this.valoracionWeb = valoracionWeb;
	}
	public String getLocalidad() {
		return localidad;
	}
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	public String getCodigoPostal() {
		return codigoPostal;
	}
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getNumeroSeguridadTarjeta() {
		return numeroSeguridadTarjeta;
	}
	public void setNumeroSeguridadTarjeta(String numeroSeguridadTarjeta) {
		this.numeroSeguridadTarjeta = numeroSeguridadTarjeta;
	}
	public String getFechaCaducidadTarjeta() {
		return fechaCaducidadTarjeta;
	}
	public void setFechaCaducidadTarjeta(String fechaCaducidadTarjeta) {
		this.fechaCaducidadTarjeta = fechaCaducidadTarjeta;
	}
	
}
