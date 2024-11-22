package com.zigor.tiendaZigorJPA.serviciosJPAImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zigor.tiendaZigorJPA.constantesSQL.ConstantesSQL;
import com.zigor.tiendaZigorJPA.model.Carrito;
import com.zigor.tiendaZigorJPA.model.Pedido;
import com.zigor.tiendaZigorJPA.model.ProductoCarrito;
import com.zigor.tiendaZigorJPA.model.ProductoPedido;
import com.zigor.tiendaZigorJPA.model.Usuario;
import com.zigor.tiendaZigorJPA.model.estadosPedido.EstadosPedido;
import com.zigor.tiendaZigorJPA.model.tiposExtra.ResumenPedido;
import com.zigor.tiendaZigorJPA.servicios.ServicioCarrito;
import com.zigor.tiendaZigorJPA.servicios.ServicioPedidos;

@Service
@Transactional
public class ServicioPedidosJPAImpl implements ServicioPedidos{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private ServicioCarrito servicioCarrito;
	
	//En cuanto el usuario completa el paso 1
	//generamos si no existe ya, un unico pedido incompleto para el usuario
	//sobre el que vamos a preparar los datos de la compra
	private Pedido obtenerPedidoActual(long idUsuario) throws Exception {
		Usuario usuario = entityManager.find(Usuario.class, idUsuario);
		Object pedidoEnProceso = null;
		
		List<Pedido> resultadoConsulta = entityManager.createQuery("SELECT p FROM Pedido p WHERE p.estado = :estado and p.usuario.id = :usuario_id"
				).setParameter("estado", EstadosPedido.INCOMPLETO.name()).setParameter("usuario_id", idUsuario).getResultList();
		
		if (resultadoConsulta.size() == 1) {
			pedidoEnProceso = resultadoConsulta.get(0);
		}else if (resultadoConsulta.size() > 1) {
			throw new Exception("Se ha colado más de un pedido incompleto para el mismo usuario");
		}
		
		Pedido pedido = null;
		if (pedidoEnProceso != null) {
			pedido = (Pedido) pedidoEnProceso;
		}else {
			pedido = new Pedido();
			pedido.setEstado(EstadosPedido.INCOMPLETO.name());
			pedido.setUsuario(usuario);
		}
		
		return pedido;
	}
	
	
	@Override
	public void procesarPaso1(String nombreCompleto, String direccion, String provincia, String localidad, String codigoPostal, String telefono, long idUsuario) {
		try {
			Pedido p = obtenerPedidoActual(idUsuario);
			p.setNombreCompleto(nombreCompleto);
			p.setDireccion(direccion);
			p.setProvincia(provincia);
			p.setLocalidad(localidad);
			p.setCodigoPostal(codigoPostal);
			p.setTelefono(telefono);
			
			entityManager.merge(p);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Hubo un problema obteniendo el pedido actual");
		}
	}

	@Override
	public void procesarPaso2(String titular, String numero, String tipoTarjeta, String numeroSeguridadTarjeta, String fechaCaducidadTarjeta, long idUsuario) {
		try {
			Pedido p = obtenerPedidoActual(idUsuario);
			p.setTitularTarjeta(titular);
			p.setNumeroTarjeta(numero);
			p.setTipoTarjeta(tipoTarjeta);
			p.setNumeroSeguridadTarjeta(numeroSeguridadTarjeta);
			p.setFechaCaducidadTarjeta(fechaCaducidadTarjeta);
			
			entityManager.merge(p);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void procesarPaso3(String detallesEntrega, String valoracionWeb, long idUsuario) {
		try {
			Pedido p = obtenerPedidoActual(idUsuario);
			p.setDetallesEntrega(detallesEntrega);
			p.setValoracionWeb(valoracionWeb);
			
			entityManager.merge(p);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public ResumenPedido obtenerResumenDelPedido(long idUsuario) {
		ResumenPedido resumen = new ResumenPedido();
		
		try {
			Pedido p = obtenerPedidoActual(idUsuario);
			//paso 1
			resumen.setNombreCompleto(p.getNombreCompleto());
			resumen.setDireccion(p.getDireccion());
			resumen.setProvincia(p.getProvincia());
			resumen.setLocalidad(p.getLocalidad());
			resumen.setCodigoPostal(p.getCodigoPostal());
			resumen.setTelefono(p.getTelefono());
			//paso 2
			resumen.setTipoTarjeta(p.getTipoTarjeta());
			resumen.setTitularTarjeta(p.getTitularTarjeta());
			resumen.setNumeroTarjeta(p.getNumeroTarjeta());
			resumen.setNumeroSeguridadTarjeta(p.getNumeroSeguridadTarjeta());
			resumen.setFechaCaducidadTarjeta(p.getFechaCaducidadTarjeta());
			//paso 3
			resumen.setDetallesEntrega(p.getDetallesEntrega());
			resumen.setValoracionWeb(p.getValoracionWeb());
			
			resumen.setJuegos(servicioCarrito.obtenerProductosCarritoUsuario(idUsuario));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resumen;
	}

	@Override
	public void confirmarPedido(long idUsuario) {
		try {
			Pedido pedido = obtenerPedidoActual(idUsuario);
			Usuario usuario = entityManager.find(Usuario.class, idUsuario);
			Carrito carrito = usuario.getCarrito();
			//pasar todos los productos del carrito al pedido
			if (carrito!=null && carrito.getProductosCarrito().size()>0) {
				for (ProductoCarrito pc : carrito.getProductosCarrito()) {
					ProductoPedido pp = new ProductoPedido();
					pp.setCantidad(pc.getCantidad());
					pp.setJuego(pc.getJuego());
					pedido.getProductosPedido().add(pp);
					pp.setPedido(pedido);
					entityManager.persist(pp);
				}
			}
			//borrar los productos del carrito del usuario
			Query query = entityManager.createNativeQuery(ConstantesSQL.SQL_BORRAR_PRODUCTOS_CARRITO);
			query.setParameter("carrito_id", carrito.getId());
			query.executeUpdate();
			
			//finalizar pedido
			pedido.setEstado(EstadosPedido.COMPLETO.name());
			entityManager.merge(pedido);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}//end confirmarPedido


	@Override
	public List<Pedido> obtenerPedidos() {
		return entityManager.createQuery("SELECT p FROM Pedido p ORDER BY p.id DESC").getResultList();
	}


	@Override
	public Pedido obtenerPedidoPorId(int idPedido) {
		return entityManager.find(Pedido.class, idPedido);
	}


	@Override
	public void actualizarEstadoPedido(int id, String estado) {
		Pedido p = entityManager.find(Pedido.class, id);
		p.setEstado(estado);
		entityManager.merge(p);
	}

}
