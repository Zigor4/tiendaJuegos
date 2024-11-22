package com.zigor.tiendaZigorJPA.constantesSQL;

//Cuando JPA se quede corto y queramos ser más especificos con las 
//consultas sobre una bd concreta, podemos usar PJA como SQL
public class ConstantesSQL {
	
	//Con SQL los nombres de las tablas y las columnas son las de la BD	

	public final static String SQL_OBTENER_DETALLES_JUEGO = "SELECT j.titulo, j.descripcion, j.precio, j.id, j.numMaxJugadores, j.edadMinima from juego as j WHERE id = :id";
	
	public final static String SQL_OBTENER_LISTADO_JUEGOS = "SELECT j.titulo, j.precio, j.id from juego as l";
	
	public final static String SQL_OBTENER_LISTADO_JUEGOS_POR_TITULO_COMIENZO = "SELECT j.titulo, j.precio, j.id from juego as j where j.titulo like :titulo ORDER BY j.id limit :comienzo, 10";
	
	public static final String SQL_OBTENER_JUEGOS_CARRITO = "SELECT j.id, j.titulo, j.precio, pc.cantidad "
			+ "FROM juego AS j, producto_carrito AS pc "
			+ "WHERE pc.juego_id = j.id AND pc.carrito_id = :carrito_id "
			+ "ORDER BY j.precio DESC";
	
	public static final String SQL_BORRAR_PRODUCTOS_CARRITO = "DELETE FROM producto_carrito WHERE carrito_id = :carrito_id";

	public static final String SQL_OBTENER_ID_USUARIO_POR_EMAIL = "SELECT id FROM usuario WHERE email = :email";
	
	public static final String SQL_OBTENER_TOTAL_JUEGOS = "SELECT Count(id) FROM juego";
	
	public static final String SQL_OBTENER_TOTAL_JUEGOS_POR_TITULO = "SELECT Count(id) FROM juego WHERE titulo LIKE :titulo";
	
}
