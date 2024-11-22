package com.zigor.tiendaZigorJPA.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

@Entity
public class Juego {

	@Lob
	@Column(name = "imagen_portada")
	private byte[] imagenPortada;
	
	@Transient
	private int idCategoria;
	
	//Asociaciones
	@ManyToOne(optional = true)
	private Categoria categoria;
	
	@Id
	@GeneratedValue
	private int id;
	
	@Pattern(regexp = "^[A-Za-z0-9 áéíóúñÁÉÍÓÚÑ]{3,40}$", message = "Título solo puede tener números, letras y espacios en blanco y mínimo 3 caracteres")
	private String titulo;
	
	@Size(max = 650, message  = "Descripción no debe superar los 650 caracteres")
	private String descripcion;
	
	@NotNull(message = "Debes insertar un número de jugadores máximo")
	@Min(value = 1, message = "El número mínimo de número de jugadores máximos es 1")
	private int numMaxJugadores;
	
	@NotNull(message = "Debes insertar una edad mínima")
	@Min(value = 3, message = "La edad mínima son 3 años")
	@Max(value = 21, message = "La edad mínima máxima son 21 años")
	private int edadMinima;
	
	@NotNull(message = "Debes insertar un precio")
	@Min(value = 1, message = "El precio mínimo es 1 euro")
	@Max(value = 999, message = "El precio máximo es 999 euros")
	private double precio;
	@Transient
	private MultipartFile archivoSubido;
	private Date fechaUltimaModificacion;
	
	public Juego() {
		// TODO Auto-generated constructor stub
	}

	public Juego(String titulo, String descripcion,  int numMaxJugadores, int edadMinima,
			double precio) {
		super();
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.numMaxJugadores = numMaxJugadores;
		this.edadMinima = edadMinima;
		this.precio = precio;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public int getNumMaxJugadores() {
		return numMaxJugadores;
	}

	public void setNumMaxJugadores(int numMaxJugadores) {
		this.numMaxJugadores = numMaxJugadores;
	}

	public int getEdadMinima() {
		return edadMinima;
	}

	public void setEdadMinima(int edadMinima) {
		this.edadMinima = edadMinima;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public byte[] getImagenPortada() {
		return imagenPortada;
	}

	public void setImagenPortada(byte[] imagenPortada) {
		this.imagenPortada = imagenPortada;
	}

	public MultipartFile getArchivoSubido() {
		return archivoSubido;
	}

	public void setArchivoSubido(MultipartFile archivoSubido) {
		this.archivoSubido = archivoSubido;
	}

	public Date getFechaUltimaModificacion() {
		return fechaUltimaModificacion;
	}

	public void setFechaUltimaModificacion(Date fechaUltimaModificacion) {
		this.fechaUltimaModificacion = fechaUltimaModificacion;
	}
	
	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	

	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	@Override
	public String toString() {
		return "Juego [categoria=" + categoria + ", id=" + id + ", titulo=" + titulo + ", descripcion=" + descripcion
				+ ", numMaxJugadores=" + numMaxJugadores + ", edadMinima=" + edadMinima + ", precio=" + precio
				+ ", fechaUltimaModificacion=" + fechaUltimaModificacion + "]";
	}

	
	
}
