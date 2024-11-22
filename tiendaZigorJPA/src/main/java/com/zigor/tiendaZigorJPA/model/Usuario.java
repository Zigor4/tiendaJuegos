package com.zigor.tiendaZigorJPA.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;


@Entity
public class Usuario {

	private String nombre;

	@Column(unique = true)
	private String email;
	private String pass;
	private String telefono;
	private String direccion;
	private String dni;
	
	@OneToOne
	private Carrito carrito;
	
	@Id
	@GeneratedValue
	private long id;
	@Transient
	private MultipartFile imagen;
	
	
	public Usuario() {
		// TODO Auto-generated constructor stub
	}

	
	public Usuario(String nombre, String email, String pass, String telefono, String direccion, String dni) {
		super();
		this.nombre = nombre;
		this.email = email;
		this.pass = pass;
		this.telefono = telefono;
		this.direccion = direccion;
		this.dni = dni;
	}


	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
	

	public String getTelefono() {
		return telefono;
	}


	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}


	public String getDireccion() {
		return direccion;
	}


	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	public String getDni() {
		return dni;
	}


	public void setDni(String dni) {
		this.dni = dni;
	}


	public Carrito getCarrito() {
		return carrito;
	}


	public void setCarrito(Carrito carrito) {
		this.carrito = carrito;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public MultipartFile getImagen() {
		return imagen;
	}


	public void setImagen(MultipartFile imagen) {
		this.imagen = imagen;
	}


	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", email=" + email + ", pass=" + pass + ", telefono=" + telefono
				+ ", direccion=" + direccion + ", dni=" + dni + ", id=" + id + "]";
	}

	
	
}
