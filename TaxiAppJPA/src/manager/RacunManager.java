package manager;

import java.util.List;

import javax.persistence.EntityManager;

import model.Cenovnik;
import model.Racun;
import model.Voznja;

public class RacunManager {
	
	public static List<Racun> findAllRacun(){
		EntityManager em = JPAUtil.getEntityManager();
		return em.createQuery("FROM Racun r").getResultList();
	}
	
//	NE RADI!!!!!!
//	public static boolean createRacun(double ukupanRacun, Object id_cenovnik, Object id_voznja) {
//		EntityManager em = JPAUtil.getEntityManager();
//		try {
//			em.getTransaction().begin();
//			Racun r = new Racun();
//			Cenovnik c = em.find(Cenovnik.class, id_cenovnik);
//			Voznja v  = em.find(Voznja.class, id_voznja);
//			ukupanRacun =  c.getCenaStart() + (c.getCena() * v.getUkupnoKm());
//			r.setUkupanRacun(ukupanRacun);
//			em.persist(r);
//			em.getTransaction().commit();
//			return true;
//		
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//			return false;
//		}
//		finally {
//			em.close();
//		}
//	}
	
//	public static boolean createRacun(Object id_cenovnik, Object id_voznja) {
//		EntityManager em = JPAUtil.getEntityManager();
//		try {
//			em.getTransaction().begin();
//			Racun r = new Racun();
//			Cenovnik c = em.find(Cenovnik.class, id_cenovnik);
//			Voznja v  = em.find(Voznja.class, id_voznja);
//			double ukupanRacun =  c.getCenaStart() + (c.getCena() * v.getUkupnoKm());
//			r.setUkupanRacun(ukupanRacun);
//			em.persist(r);
//			em.getTransaction().commit();
//			return true;
//		
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//			return false;
//		}
//		finally {
//			em.close();
//		}
//	}
//	
//	public static boolean updateRacun(Object id_racun, double ukupanRacun) {
//		EntityManager em = JPAUtil.getEntityManager();
//		try {
//			Racun r = em.find(Racun.class, id_racun);
//			if(r != null) {
//				r.setUkupanRacun(ukupanRacun);
//				em.persist(r);
//				em.getTransaction().commit();
//				return true;
//			}
//			return false;
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//			return false;
//		}
//		finally {
//			em.close();
//		}
//	}
}
