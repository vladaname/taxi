package manager;

import java.util.List;

import javax.persistence.EntityManager;

import model.Uloga;

public class UlogaMenager {
	
	public static List<Uloga> findAllUloga(){
		EntityManager em = JPAUtil.getEntityManager();
		return em.createQuery("FROM Uloga u").getResultList();
	}
	
	public static boolean createUloga(String nazivUloga) {
		EntityManager em = JPAUtil.getEntityManager();
		try {
			em.getTransaction().begin();
			Uloga u = new Uloga();
			u.setNazivUloga(nazivUloga);
			em.persist(u);
			em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		finally {
			em.close();
		}
	}
	public static boolean updateUloga(Object id_uloga, String nazivUloga) {
		EntityManager em = JPAUtil.getEntityManager();
		try {
			em.getTransaction().begin();
			Uloga u = em.find(Uloga.class, id_uloga);
			if(u != null) {
			u.setNazivUloga(nazivUloga);
			em.persist(u);
			em.getTransaction().commit();
			return true;
			}
			return false;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		finally {
			em.close();
		}
	}

}
