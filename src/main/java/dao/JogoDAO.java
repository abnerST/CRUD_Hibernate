package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import entidades.Jogo;
import util.JPAUtil;

public class JogoDAO {
	public static void salvar(Jogo j) {
		j.setVRandom();
		EntityManager em = JPAUtil.createEntityManager();
		em.getTransaction().begin();
		em.persist(j);
		em.getTransaction().commit();
		em.close();
	}

	public static void editar(Jogo j) {
		EntityManager em = JPAUtil.createEntityManager();
		em.getTransaction().begin();
		em.merge(j);
		em.getTransaction().commit();
		em.close();
	}

	public static void excluir(Integer id) {
		EntityManager em = JPAUtil.createEntityManager();
		em.getTransaction().begin();
		Jogo j = em.find(Jogo.class, id);
		em.remove(j);
		em.getTransaction().commit();
		em.close();

	}
	
	public static String numPares(Integer id) {
		List<Integer> pares = new ArrayList();
		String resultado = "";
		EntityManager em = JPAUtil.createEntityManager();
		em.getTransaction().begin();
		Jogo j = em.find(Jogo.class, id);
		
		if (j.getV1() % 2 == 0) {
			pares.add(j.getV1());
		}
		if (j.getV2() % 2 == 0) {
			pares.add(j.getV2());

		}
		if (j.getV3() % 2 == 0) {
			pares.add(j.getV3());

		}
		if (j.getV4() % 2 == 0) {
			pares.add(j.getV4());

		}
		if (j.getV5() % 2 == 0) {
			pares.add(j.getV5());

		}
		if (j.getV6() % 2 == 0) {
			pares.add(j.getV6());

		}
		if (j.getV7() % 2 == 0) {
			pares.add(j.getV7());

		}
		if (j.getV8() % 2 == 0) {
			pares.add(j.getV8());

		}
		if (j.getV9() % 2 == 0) {
			pares.add(j.getV9());

		}
		if (j.getV10() % 2 == 0) {
			pares.add(j.getV10());

		}
		
		for(Integer par : pares){
			resultado += par+",";
		}
			
		return resultado;
	} 
	
	
	public static List<Jogo> listar() {
		EntityManager em = JPAUtil.createEntityManager();
		Query query = em.createQuery("select j from Jogo j");
		List<Jogo> retorno = query.getResultList();
		em.close();
		return retorno;
	}

}
