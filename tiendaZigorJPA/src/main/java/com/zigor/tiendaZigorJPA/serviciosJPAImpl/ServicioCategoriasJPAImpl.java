package com.zigor.tiendaZigorJPA.serviciosJPAImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.zigor.tiendaZigorJPA.model.Categoria;
import com.zigor.tiendaZigorJPA.servicios.ServicioCategorias;

@Service
@Transactional
public class ServicioCategoriasJPAImpl implements ServicioCategorias{

	@PersistenceContext
	private EntityManager entityManager;
	
	
	@Override
	public List<Categoria> obtenerCategorias() {
		return entityManager.createQuery("SELECT c FROM Categoria c").getResultList();
	}

}
