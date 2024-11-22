package com.zigor.tiendaZigorJPA.serviciosJPAImpl;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zigor.tiendaZigorJPA.constantesSQL.ConstantesSQL;
import com.zigor.tiendaZigorJPA.model.Usuario;
import com.zigor.tiendaZigorJPA.servicios.ServicioUsuarios;
import com.zigor.tiendaZigorJPA.utilidades.Utilidades;

@Service
@Transactional
public class ServicioUsuariosJPAImpl implements ServicioUsuarios{

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void registrarUsuario(Usuario u) {
		entityManager.persist(u);
	}

	@Override
	public Usuario obtenerUsuarioPorEmailYPass(String email, String pass) {
		//Las consultas con Query son con JPQL
		Query query = entityManager.createQuery("Select u FROM Usuario u WHERE u.email = :email AND u.pass = :pass");
		query.setParameter("email", email);
		query.setParameter("pass", pass);
		List<Usuario> resultado = query.getResultList();		
		if (resultado.size() == 0) {
			return null;
		} else {
			return resultado.get(0);
		}
	}

	@Override
	public boolean comprobarEmailExiste(String email) {
		Query query = entityManager.createNativeQuery(ConstantesSQL.SQL_OBTENER_ID_USUARIO_POR_EMAIL);
		query.setParameter("email", email);
		List<Map<String, Object>> res = Utilidades.procesaNativeQuery(query);
		
		if (res.size()>0) {
			return true;
		}else {
			return false;
		}
	}
	
	
}
