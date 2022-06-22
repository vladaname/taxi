package manager;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import model.Cenovnik;

public class CenovnikManager {
	
	public static List<Cenovnik> findAllCenovnik(){
		EntityManager em = JPAUtil.getEntityManager();
		try {
			return em.createQuery("FROM Cenovnik c").getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
	public static Cenovnik aktuelniCenovnik() {
		EntityManager em = JPAUtil.getEntityManager();
		int maxCenovnik = (int) em.createQuery("select max(idCenovnik) from Cenovnik").getSingleResult();
		if (maxCenovnik > 0) {
			Cenovnik c = em.find(Cenovnik.class, maxCenovnik);
			return c;
		} else {
			return null;
		}
		
	}
	
	public static boolean createCenovnikCena(double cena, Date datumPromene) {
		EntityManager em = JPAUtil.getEntityManager();
		try {
			em.getTransaction().begin();
			Cenovnik c = new Cenovnik();
			c.setCena(cena);
			c.setDatumPromene(datumPromene);
			em.persist(c);
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
	public static boolean createCenovnikCenaStart(double cenaStart, Date datumPromene) {
		EntityManager em = JPAUtil.getEntityManager();
		try {
			em.getTransaction().begin();
			Cenovnik c = new Cenovnik();
			c.setCenaStart(cenaStart);
			c.setDatumPromene(datumPromene);
			em.persist(c);
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
	
	public static void main(String[] args) {
		Cenovnik cene = aktuelniCenovnik();
			System.out.println(cene.getCena() + " " + cene.getCenaStart());
	}

}
