package com.zigor.tiendaZigorJPA.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Pedido {

	
	@OneToMany(mappedBy = "pedido")
	private List<ProductoPedido> productosPedido = new ArrayList<ProductoPedido>();
	
	
	private String estado;
		
	//se pide en paso 1 
	private String nombreCompleto;
	private String direccion;
	private String provincia;
	private String localidad;
	private String codigoPostal;
	private String telefono;
	//fin paso 1
	
	//se pide en paso 2 
	private String titularTarjeta;
	private String numeroTarjeta;
	private String tipoTarjeta;
	private String numeroSeguridadTarjeta;
	private String fechaCaducidadTarjeta;	
	//fin paso 2
	
	//se pide en paso 3
	private String detallesEntrega;
	private String valoracionWeb;
	//se pide en paso 3
	
	@ManyToOne(targetEntity = Usuario.class, optional = false)
	private Usuario usuario;
	
	@Id
	@GeneratedValue
	private int id;

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
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
	
	public List<ProductoPedido> getProductosPedido() {
		return productosPedido;
	}

	public void setProductosPedido(List<ProductoPedido> productosPedido) {
		this.productosPedido = productosPedido;
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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	

}
