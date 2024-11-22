package com.zigor.tiendaZigorJPA.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Carrito {

	@OneToOne
	private Usuario usuario;
	
	@OneToMany(mappedBy = "carrito")
	private List<ProductoCarrito> productosCarrito = new ArrayList<ProductoCarrito>();
	
	private Date ultimoUso;
	
	@Id
	@GeneratedValue
	private long id;
	
	//En java si no existe ningun constructor en la clase resulta ser
	//que a la clase se le incorpora uno vacío por defecto
	

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public List<ProductoCarrito> getProductosCarrito() {
		return productosCarrito;
	}

	public void setProductosCarrito(List<ProductoCarrito> productosCarrito) {
		this.productosCarrito = productosCarrito;
	}

	public Date getUltimoUso() {
		return ultimoUso;
	}

	public void setUltimoUso(Date ultimoUso) {
		this.ultimoUso = ultimoUso;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	
	
	
}
