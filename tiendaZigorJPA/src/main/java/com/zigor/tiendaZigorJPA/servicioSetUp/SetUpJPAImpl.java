package com.zigor.tiendaZigorJPA.servicioSetUp;

import java.io.IOException;
import java.net.URL;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zigor.tiendaZigorJPA.model.Categoria;
import com.zigor.tiendaZigorJPA.model.Juego;
import com.zigor.tiendaZigorJPA.model.Pedido;
import com.zigor.tiendaZigorJPA.model.ProductoPedido;
import com.zigor.tiendaZigorJPA.model.Usuario;
import com.zigor.tiendaZigorJPA.model.estadosPedido.EstadosPedido;
import com.zigor.tiendaZigorJPA.servicios.ServicioCarrito;

//Este servicio de setup es para simplemente meter unos
//cuantos registros cuando se cree la BD

@Service
@Transactional
public class SetUpJPAImpl implements SetUp{

	@PersistenceContext
	private EntityManager entityManager;
	

	@Autowired
	private ServicioCarrito servicioCarrito;
	
	@Override
	public void setUp() {
		//Vamos a usar una entidad para ver si ya hemos inicializado la BD
		//Dicha entidad la llamamos SetUp
		
		com.zigor.tiendaZigorJPA.model.SetUp registroSetUp = null;
		
		try {
			//Casi identifo en SQL a 'SELECT s.* FROM tabla_setup as s'
			registroSetUp = (com.zigor.tiendaZigorJPA.model.SetUp) entityManager.createQuery("SELECT s FROM SetUp as s").getSingleResult();
		} catch (Exception e) {
			System.out.println("No se encontro ningún registro de setup, comenazamos setup...");
		}
		
		if (registroSetUp == null || !registroSetUp.isCompleto()) {
			//Si no hay ningun registro en la tabla de SetUp
			//pues preparemos los registros para todo el sistema
			Categoria dados = new Categoria("Dados", "Categoría de juegos que utilizan dados para avanzar en el tablero");
			entityManager.persist(dados);

			Categoria cartas = new Categoria("Cartas", "Categoría de juegos que utilizan cartas como principal elemento de juego");
			entityManager.persist(cartas);

			Categoria palabras = new Categoria("Palabras", "Categoría de juegos que se basan en la creación de palabras");
			entityManager.persist(palabras);

			Categoria estrategia = new Categoria("Estrategia", "Categoría de juegos que requieren planificación y táctica");
			entityManager.persist(estrategia);

			Categoria misterio = new Categoria("Misterio", "Categoría de juegos que involucran deducción e investigación");
			entityManager.persist(misterio);

			Categoria fichas = new Categoria("Fichas", "Categoría de juegos donde se utilizan fichas como piezas de juego");
			entityManager.persist(fichas);

			Categoria cooperativo = new Categoria("Cooperativo", "Categoría de juegos donde los jugadores colaboran para ganar");
			entityManager.persist(cooperativo);

			Categoria accion = new Categoria("Acción", "Categoría de juegos que requieren movimiento físico o agilidad");
			entityManager.persist(accion);

			Categoria habilidad = new Categoria("Habilidad", "Categoría de juegos que requieren destreza o rapidez");
			entityManager.persist(habilidad);

			Categoria trivia = new Categoria("Trivia", "Categoría de juegos basados en conocimientos generales y preguntas");
			entityManager.persist(trivia);

			Categoria suerte = new Categoria("Suerte", "Categoría de juegos donde el azar tiene un papel importante");
			entityManager.persist(suerte);

			
			Juego j1 = new Juego("Scrabble", "Juego de palabras donde se forman palabras con fichas", 4, 8, 12.5);
			j1.setCategoria(palabras);
			j1.setImagenPortada(leerBytesDeRutaOrigen("http://localhost:8080/imagenes_base/1.png"));
			entityManager.persist(j1);

			Juego j2 = new Juego("Parchís", "Juego de dados clásico para avanzar en el tablero", 4, 8, 20);
			j2.setCategoria(dados);
			j2.setImagenPortada(leerBytesDeRutaOrigen("http://localhost:8080/imagenes_base/2.png"));
			entityManager.persist(j2);

			Juego j3 = new Juego("Oca", "Juego de recorrido de casillas con dados", 4, 8, 23.5);
			j3.setCategoria(dados);
			j3.setImagenPortada(leerBytesDeRutaOrigen("http://localhost:8080/imagenes_base/3.png"));
			entityManager.persist(j3);

			Juego j4 = new Juego("Ajedrez", "Juego de estrategia y táctica en un tablero de 8x8", 2, 12, 15);
			j4.setCategoria(estrategia);
			j4.setImagenPortada(leerBytesDeRutaOrigen("http://localhost:8080/imagenes_base/4.png"));
			entityManager.persist(j4);

			Juego j5 = new Juego("Uno", "Juego de cartas de colores para toda la familia", 10, 7, 7.5);
			j5.setCategoria(cartas);
			j5.setImagenPortada(leerBytesDeRutaOrigen("http://localhost:8080/imagenes_base/5.png"));
			entityManager.persist(j5);

			Juego j6 = new Juego("Monopoly", "Juego de comercio y adquisición de propiedades", 6, 10, 25);
			j6.setCategoria(estrategia);
			j6.setImagenPortada(leerBytesDeRutaOrigen("http://localhost:8080/imagenes_base/6.png"));
			entityManager.persist(j6);

			Juego j7 = new Juego("Clue", "Juego de deducción y misterio", 6, 8, 18);
			j7.setCategoria(misterio);
			j7.setImagenPortada(leerBytesDeRutaOrigen("http://localhost:8080/imagenes_base/7.png"));
			entityManager.persist(j7);

			Juego j8 = new Juego("Catan", "Juego de estrategia y comercio para construir colonias", 4, 10, 35);
			j8.setCategoria(estrategia);
			j8.setImagenPortada(leerBytesDeRutaOrigen("http://localhost:8080/imagenes_base/8.png"));
			entityManager.persist(j8);

			Juego j9 = new Juego("Risk", "Juego de conquista y estrategia global", 6, 10, 30);
			j9.setCategoria(estrategia);
			j9.setImagenPortada(leerBytesDeRutaOrigen("http://localhost:8080/imagenes_base/9.png"));
			entityManager.persist(j9);

			Juego j10 = new Juego("Dominó", "Juego de fichas para hacer coincidir números", 4, 8, 12);
			j10.setCategoria(fichas);
			j10.setImagenPortada(leerBytesDeRutaOrigen("http://localhost:8080/imagenes_base/10.png"));
			entityManager.persist(j10);

			Juego j11 = new Juego("Pandemic", "Juego cooperativo para salvar el mundo de pandemias", 4, 12, 27);
			j11.setCategoria(cooperativo);
			j11.setImagenPortada(leerBytesDeRutaOrigen("http://localhost:8080/imagenes_base/11.png"));
			entityManager.persist(j11);

			Juego j12 = new Juego("Twister", "Juego de acción física y equilibrio", 4, 6, 15);
			j12.setCategoria(accion);
			j12.setImagenPortada(leerBytesDeRutaOrigen("http://localhost:8080/imagenes_base/12.png"));
			entityManager.persist(j12);

			Juego j13 = new Juego("Jenga", "Juego de habilidad para construir y retirar bloques", 4, 6, 14);
			j13.setCategoria(habilidad);
			j13.setImagenPortada(leerBytesDeRutaOrigen("http://localhost:8080/imagenes_base/13.png"));
			entityManager.persist(j13);

			Juego j14 = new Juego("Exploding Kittens", "Juego de cartas explosivas con gatos", 5, 10, 20);
			j14.setCategoria(cartas);
			j14.setImagenPortada(leerBytesDeRutaOrigen("http://localhost:8080/imagenes_base/14.png"));
			entityManager.persist(j14);

			Juego j15 = new Juego("Dixit", "Juego de imaginación y narración visual", 6, 8, 30);
			j15.setCategoria(cartas);
			j15.setImagenPortada(leerBytesDeRutaOrigen("http://localhost:8080/imagenes_base/15.png"));
			entityManager.persist(j15);

			Juego j16 = new Juego("Carcassonne", "Juego de construcción de ciudades medievales", 5, 8, 20);
			j16.setCategoria(estrategia);
			j16.setImagenPortada(leerBytesDeRutaOrigen("http://localhost:8080/imagenes_base/16.png"));
			entityManager.persist(j16);

			Juego j17 = new Juego("Azul", "Juego de colocación de azulejos decorativos", 4, 8, 32);
			j17.setCategoria(estrategia);
			j17.setImagenPortada(leerBytesDeRutaOrigen("http://localhost:8080/imagenes_base/17.png"));
			entityManager.persist(j17);

			Juego j18 = new Juego("Dobble", "Juego de observación y rapidez visual", 8, 6, 10);
			j18.setCategoria(habilidad);
			j18.setImagenPortada(leerBytesDeRutaOrigen("http://localhost:8080/imagenes_base/18.png"));
			entityManager.persist(j18);
			
			Juego j19 = new Juego("Trivial Pursuit", "Juego de trivia y conocimientos generales", 6, 12, 25);
			j19.setCategoria(trivia);
			j19.setImagenPortada(leerBytesDeRutaOrigen("http://localhost:8080/imagenes_base/19.png"));
			entityManager.persist(j19);

			Juego j20 = new Juego("Bingo", "Juego de azar donde se deben completar cartones con números", 20, 8, 15);
			j20.setCategoria(suerte);
			j20.setImagenPortada(leerBytesDeRutaOrigen("http://localhost:8080/imagenes_base/20.png"));
			entityManager.persist(j20);

			Juego j21 = new Juego("Scattergories", "Juego de palabras y creatividad bajo presión de tiempo", 6, 12, 18);
			j21.setCategoria(palabras);
			j21.setImagenPortada(leerBytesDeRutaOrigen("http://localhost:8080/imagenes_base/21.png"));
			entityManager.persist(j21);


			
			
			Usuario u1 = new Usuario("Ares", "ares@gmail.com", "123", "666666666", "Calle del Programador, 33", "00010202P");
			Usuario u2 = new Usuario("Kilian", "kilian@gmail.com", "123", "663566856", "Calle del Programador, 23", "02010552F");
			Usuario u3 = new Usuario("Zigor", "zigor@gmail.com", "123", "666666856", "Calle del Programador, 11", "08070202P");
			
			entityManager.persist(u1);
			entityManager.persist(u2);
			entityManager.persist(u3);
			
			//Meter unos pedidos
			Pedido pedido1 = new Pedido();
			pedido1.setNombreCompleto("Juan martinez");
			pedido1.setDireccion("Argüelles 12");
			pedido1.setLocalidad("Leganés");
			pedido1.setCodigoPostal("28080");
			pedido1.setTelefono("686479565");
			pedido1.setProvincia("Madrid");
			pedido1.setTipoTarjeta("VISA");
			pedido1.setTitularTarjeta("Juan Martinez");
			pedido1.setNumeroTarjeta("3310 2210 5445 5555");
			pedido1.setNumeroSeguridadTarjeta("123");
			pedido1.setFechaCaducidadTarjeta("12/26");
			pedido1.setDetallesEntrega("No funciona el telefonillo.");
			pedido1.setValoracionWeb("4");
			
			pedido1.setUsuario(u1);
			pedido1.setEstado(EstadosPedido.COMPLETO.name());
			entityManager.persist(pedido1);
			
			Pedido pedido2 = new Pedido();
			pedido2.setNombreCompleto("Ana López");
			pedido2.setDireccion("Calle Mayor 5");
			pedido2.setLocalidad("Getafe");
			pedido2.setCodigoPostal("28901");
			pedido2.setTelefono("634275690");
			pedido2.setProvincia("Madrid");
			pedido2.setTipoTarjeta("MASTER CARD");
			pedido2.setTitularTarjeta("Ana López");
			pedido2.setNumeroTarjeta("5492 1030 2265 8810");
			pedido2.setNumeroSeguridadTarjeta("456");
			pedido2.setFechaCaducidadTarjeta("11/25");
			pedido2.setDetallesEntrega("Dejar en portería si no contesto.");
			pedido2.setValoracionWeb("5");
			pedido2.setUsuario(u2);
			pedido2.setEstado(EstadosPedido.COMPLETO.name());
			entityManager.persist(pedido2);

			Pedido pedido3 = new Pedido();
			pedido3.setNombreCompleto("Carlos Sánchez");
			pedido3.setDireccion("Avenida de las Américas 32");
			pedido3.setLocalidad("Alcorcón");
			pedido3.setCodigoPostal("28920");
			pedido3.setTelefono("611209876");
			pedido3.setProvincia("Madrid");
			pedido3.setTipoTarjeta("VISA");
			pedido3.setTitularTarjeta("Carlos Sánchez");
			pedido3.setNumeroTarjeta("4234 2201 7777 1234");
			pedido3.setNumeroSeguridadTarjeta("789");
			pedido3.setFechaCaducidadTarjeta("06/24");
			pedido3.setDetallesEntrega("Llamar antes de entregar.");
			pedido3.setValoracionWeb("2");
			pedido3.setUsuario(u3);
			pedido3.setEstado(EstadosPedido.INCOMPLETO.name());
			entityManager.persist(pedido3);
			
			Pedido pedido4 = new Pedido();
			pedido4.setNombreCompleto("Elena Martínez");
			pedido4.setDireccion("Calle O'Donnell 7");
			pedido4.setLocalidad("Madrid");
			pedido4.setCodigoPostal("28009");
			pedido4.setTelefono("678345678");
			pedido4.setProvincia("Madrid");
			pedido4.setTipoTarjeta("MASTER CARD");
			pedido4.setTitularTarjeta("Elena Martínez");
			pedido4.setNumeroTarjeta("5201 3345 6678 9023");
			pedido4.setNumeroSeguridadTarjeta("321");
			pedido4.setFechaCaducidadTarjeta("09/27");
			pedido4.setDetallesEntrega("Entregar solo en horario laboral.");
			pedido4.setValoracionWeb("3");
			pedido4.setUsuario(u1);
			pedido4.setEstado(EstadosPedido.COMPLETO.name());
			entityManager.persist(pedido4);
			
			Pedido pedido5 = new Pedido();
			pedido5.setNombreCompleto("María Fernanda");
			pedido5.setDireccion("Calle de la Luna 15");
			pedido5.setLocalidad("Móstoles");
			pedido5.setCodigoPostal("28930");
			pedido5.setTelefono("699123456");
			pedido5.setProvincia("Madrid");
			pedido5.setTipoTarjeta("VISA");
			pedido5.setTitularTarjeta("María Fernanda");
			pedido5.setNumeroTarjeta("4921 5567 8930 1235");
			pedido5.setNumeroSeguridadTarjeta("654");
			pedido5.setFechaCaducidadTarjeta("03/28");
			pedido5.setDetallesEntrega("Preferiblemente en la tarde.");
			pedido5.setValoracionWeb("1");
			pedido5.setUsuario(u2);
			pedido5.setEstado(EstadosPedido.INCOMPLETO.name());
			entityManager.persist(pedido5);
			
			Pedido pedido6 = new Pedido();
			pedido6.setNombreCompleto("Javier González");
			pedido6.setDireccion("Plaza Castilla 18");
			pedido6.setLocalidad("Madrid");
			pedido6.setCodigoPostal("28046");
			pedido6.setTelefono("675987432");
			pedido6.setProvincia("Madrid");
			pedido6.setTipoTarjeta("VISA");
			pedido6.setTitularTarjeta("Javier González");
			pedido6.setNumeroTarjeta("4012 8888 3333 4444");
			pedido6.setNumeroSeguridadTarjeta("987");
			pedido6.setFechaCaducidadTarjeta("08/24");
			pedido6.setDetallesEntrega("Entregar en mano.");
			pedido6.setValoracionWeb("5");
			pedido6.setUsuario(u3);
			pedido6.setEstado(EstadosPedido.COMPLETO.name());
			entityManager.persist(pedido6);
			
			
			ProductoPedido pp1 = new ProductoPedido();
			pp1.setPedido(pedido1);
			pp1.setJuego(j1);
			pp1.setCantidad(2);
			entityManager.persist(pp1);
			
			ProductoPedido pp2 = new ProductoPedido();
			pp2.setPedido(pedido1);
			pp2.setJuego(j2);
			pp2.setCantidad(4);
			entityManager.persist(pp2);
			
			ProductoPedido pp3 = new ProductoPedido();
			pp3.setPedido(pedido2);
			pp3.setJuego(j3);
			pp3.setCantidad(1);
			entityManager.persist(pp3);
			
			ProductoPedido pp4 = new ProductoPedido();
			pp4.setPedido(pedido2);
			pp4.setJuego(j1);
			pp4.setCantidad(3);
			entityManager.persist(pp4);
			
			ProductoPedido pp5 = new ProductoPedido();
			pp5.setPedido(pedido2);
			pp5.setJuego(j2);
			pp5.setCantidad(4);
			entityManager.persist(pp5);
			
			ProductoPedido pp6 = new ProductoPedido();
			pp6.setPedido(pedido3);
			pp6.setJuego(j2);
			pp6.setCantidad(5);
			entityManager.persist(pp6);
			
			ProductoPedido pp7 = new ProductoPedido();
			pp7.setPedido(pedido4);
			pp7.setJuego(j1);
			pp7.setCantidad(1);
			entityManager.persist(pp7);
			
			ProductoPedido pp8 = new ProductoPedido();
			pp8.setPedido(pedido5);
			pp8.setJuego(j3);
			pp8.setCantidad(4);
			entityManager.persist(pp8);
			
			ProductoPedido pp9 = new ProductoPedido();
			pp9.setPedido(pedido6);
			pp9.setJuego(j1);
			pp9.setCantidad(2);
			entityManager.persist(pp9);
			
			
			com.zigor.tiendaZigorJPA.model.SetUp setup = new com.zigor.tiendaZigorJPA.model.SetUp();
			setup.setCompleto(true);
			entityManager.persist(setup);
			

			servicioCarrito.agregarProducto(j1.getId(), u1.getId(), 5);
			servicioCarrito.agregarProducto(j2.getId(), u1.getId(), 2);
			servicioCarrito.agregarProducto(j3.getId(), u2.getId(), 1);
			servicioCarrito.agregarProducto(j1.getId(), u2.getId(), 3);
			
		}
	}
	
	private byte[] leerBytesDeRutaOrigen(String rutaOrigen) {
		byte[] info = null;
		
		try {
			URL url = new URL(rutaOrigen);
			info = IOUtils.toByteArray(url);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("No pude leer de la ruta indicada.");
		}
		return info;
		
	}

	
	
}
