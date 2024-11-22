package com.zigor.tiendaZigorJPA.serviciosJPAImpl;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zigor.tiendaZigorJPA.constantesSQL.ConstantesSQL;
import com.zigor.tiendaZigorJPA.model.Categoria;
import com.zigor.tiendaZigorJPA.model.Juego;
import com.zigor.tiendaZigorJPA.servicios.ServicioJuegos;

@Service
@Transactional
public class ServiciosJuegosJPAImpl implements ServicioJuegos{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void registrarJuego(Juego j) {
		Categoria c = entityManager.find(Categoria.class, j.getIdCategoria());
		j.setCategoria(c);
		entityManager.persist(j);
	}

	@Override
	public List<Juego> obtenerJuegos() {
		return entityManager.createQuery("SELECT j from Juego j").getResultList();
	}

	@Override
	public void borrarJuego(int id) {
		Juego j = entityManager.find(Juego.class, id);
		entityManager.remove(j);
	}

	@Override
	public void actualizarJuego(Juego j) {
		entityManager.merge(j);
	}

	@Override
	public Juego obtenerJuegoPorId(int id) {
		return entityManager.find(Juego.class, id);
	}

	@Override
	public Map<String, Object> obtenerJuegoVerDetallesPorId(int id) {
		Query query = entityManager.createNativeQuery(ConstantesSQL.SQL_OBTENER_DETALLES_JUEGO);
		query.setParameter("id", id);
		
		//Una vez preparada la query para obtener el resultado como tupo Map, hay que hacer esto:
		NativeQueryImpl nativeQuery = (NativeQueryImpl) query;
		nativeQuery.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		
		return (Map<String, Object>) nativeQuery.getSingleResult();
	}

	@Override
	public List<Map<String, Object>> obtenerJuegosParaListar() {
		Query query = entityManager.createNativeQuery(ConstantesSQL.SQL_OBTENER_LISTADO_JUEGOS);
		
		NativeQueryImpl nativeQuery = (NativeQueryImpl) query;
		nativeQuery.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		
		return nativeQuery.getResultList();
	}
	
	@Override
	public int obtenerTotalJuegos() {
		Query q = entityManager.createNativeQuery(ConstantesSQL.SQL_OBTENER_TOTAL_JUEGOS);
		return Integer.parseInt(q.getSingleResult().toString());
	}

	@Override
	public int obtenerTotalJuegos(String titulo) {
		Query q = entityManager.createNativeQuery(ConstantesSQL.SQL_OBTENER_TOTAL_JUEGOS_POR_TITULO);
		q.setParameter("titulo", "%" + titulo + "%");
		return Integer.parseInt(q.getSingleResult().toString());
	}

	@Override
	public List<Juego> obtenerJuegos(String titulo) {
		return entityManager.createQuery("SELECT j from Juego j WHERE j.titulo LIKE :titulo").setParameter("titulo","%" + titulo + "%").getResultList();
	}

	@Override
	public List<Juego> obtenerJuegos(String titulo, int comienzo, int resultadosPorPagina) {
		return entityManager.createQuery("SELECT j from Juego j WHERE j.titulo LIKE :titulo").setParameter("titulo","%" + titulo + "%").setFirstResult(comienzo).setMaxResults(resultadosPorPagina).getResultList();
	}

	@Override
	public List<Map<String, Object>> obtenerJuegosParaListar(String titulo, int comienzo) {
		Query query = entityManager.createNativeQuery(ConstantesSQL.SQL_OBTENER_LISTADO_JUEGOS_POR_TITULO_COMIENZO);
		query.setParameter("titulo", "%" + titulo + "%");
		query.setParameter("comienzo", comienzo);
		NativeQueryImpl nativeQuery = (NativeQueryImpl) query;
		nativeQuery.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		
		return nativeQuery.getResultList();
	}
	
}
