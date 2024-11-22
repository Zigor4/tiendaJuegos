package com.zigor.tiendaZigorJPA.serviciosJPAImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.zigor.tiendaZigorJPA.constantesSQL.ConstantesSQL;
import com.zigor.tiendaZigorJPA.model.Carrito;
import com.zigor.tiendaZigorJPA.model.Juego;
import com.zigor.tiendaZigorJPA.model.ProductoCarrito;
import com.zigor.tiendaZigorJPA.model.Usuario;
import com.zigor.tiendaZigorJPA.servicios.ServicioCarrito;
import com.zigor.tiendaZigorJPA.utilidades.Utilidades;


@Service
@Transactional
public class ServicioCarritoJPAImpl implements ServicioCarrito{

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void agregarProducto(int idProducto, long idUsuario, int cantidad) {
		Usuario usuario = entityManager.find(Usuario.class, idUsuario);
		Carrito carrito = usuario.getCarrito();
		//Si el usuario no tiene asociado un carrito, pues creamos un
		//carrito para el usuario
		if (carrito == null) {
			carrito = new Carrito();
			carrito.setUsuario(usuario);
			usuario.setCarrito(carrito);
			entityManager.persist(carrito);
		}
		//Si el carrito no tiene un productoCarrito del idProducto que
		//se quiere agregar, pues lo creamos
		boolean productoEnCarrito = false;
		for(ProductoCarrito pc : carrito.getProductosCarrito()) {
			if(pc.getJuego().getId() == idProducto) {
				productoEnCarrito = true;
				//aprovechamos e incrementamos su cantidad
				pc.setCantidad(pc.getCantidad() + cantidad);
				entityManager.merge(pc);
			}
		}//end for
		
		if(!productoEnCarrito) {
			ProductoCarrito pc = new ProductoCarrito();
			pc.setCarrito(carrito);
			pc.setJuego(entityManager.find(Juego.class, idProducto));
			pc.setCantidad(cantidad);
			entityManager.persist(pc);
		}
	}//end agregarProducto

	@Override
	public List<Map<String, Object>> obtenerProductosCarritoUsuario(long idUsuario) {
		Usuario usuario = entityManager.find(Usuario.class, idUsuario);
		Carrito carrito = usuario.getCarrito();
		List<Map<String, Object>> res = new ArrayList<Map<String,Object>>();
		if (carrito != null) {
			//createNativeQuery es para poder usar SQL
			Query query = entityManager.createNativeQuery(ConstantesSQL.SQL_OBTENER_JUEGOS_CARRITO);
			query.setParameter("carrito_id", carrito.getId());
			res = Utilidades.procesaNativeQuery(query);
		}
		return res;
	}
	
	
}
