package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import entidades.Usuario;
import util.JPAUtil;

public class UsuarioDAO {

	public static Usuario loginUsuario(String acesso, String senha) {
		EntityManager em = JPAUtil.createEntityManager();
		try {
			Query query = em.createQuery("select u from Usuario u where u.usuario = :acesso and u.senha = :senha");
			query.setParameter("acesso", acesso);
			query.setParameter("senha", senha);
			Usuario usuario = (Usuario) query.getSingleResult();
			return usuario;
		} catch (Exception e) {

		}
		return null;
	}

	public static void salvar(Usuario u) {
		EntityManager em = JPAUtil.createEntityManager();
		em.getTransaction().begin();
		em.persist(u);
		em.getTransaction().commit();
		em.close();
	}

	public static void editar(Usuario u) {
		EntityManager em = JPAUtil.createEntityManager();
		em.getTransaction().begin();
		em.merge(u);
		em.getTransaction().commit();
		em.close();
	}

	public static void excluir(Integer id) {
		EntityManager em = JPAUtil.createEntityManager();
		em.getTransaction().begin();
		Usuario u = em.find(Usuario.class, id);
		em.remove(u);
		em.getTransaction().commit();
		em.close();

	}

	public static List<Usuario> listar() {
		EntityManager em = JPAUtil.createEntityManager();
		Query query = em.createQuery("select u from Usuario u");
		List<Usuario> retorno = query.getResultList();
		em.close();
		return retorno;
	}

}
