package manager;

import java.util.List;

import javax.persistence.EntityManager;

import model.Vozilo;

public class VoziloManager {
	
	public static List<Vozilo> findAll(){
		EntityManager em = JPAUtil.getEntityManager();
		return em.createQuery("FROM Vozilo v").getResultList();
	}
	public static List<Vozilo> findAllByMarka(Object marka){
		EntityManager em = JPAUtil.getEntityManager();
		try {
			return em.createQuery("FROM Vozilo v WHERE v.marka =:marka")
					.setParameter("marka", marka).getResultList();
			
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
	public static List<Vozilo> findAllByModel(Object model){
		EntityManager em = JPAUtil.getEntityManager();
		try {
			return em.createQuery("FROM Vozilo v WHERE v.model =:model")
					.setParameter("model", model).getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
	public static boolean createVozilo(String gorivo, String marka, String model, String tip) {
		EntityManager em = JPAUtil.getEntityManager();
		try {
			em.getTransaction().begin();
			Vozilo v = new Vozilo();
			v.setGorivo(gorivo);
			v.setMarka(marka);
			v.setModel(model);
			v.setObrisan(0);
			v.setTip(tip);
			em.persist(v);
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
	
	public static boolean updateVozilo(int id_vozilo, String gorivo, String marka, String model, String tip) {
		EntityManager em = JPAUtil.getEntityManager();
		try {
			em.getTransaction().begin();
			Vozilo v = em.find(Vozilo.class, id_vozilo);
			if(v != null) {
				v.setGorivo(gorivo);
				v.setMarka(marka);
				v.setModel(model);
				v.setTip(tip);
				em.persist(v);
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
	
	public static List<Vozilo> findAllSlobodnaVozila(){
		EntityManager em = JPAUtil.getEntityManager();
		try {
			return em.createQuery("FROM Vozilo v WHERE v.obrisan = 1").getResultList();
		} catch (Exception e) {
			// TODO: handle exceptione,
			e.printStackTrace();
			return null;
		}
	}
	
	public static boolean deleteVozilo(int id_vozilo) {
		EntityManager em = JPAUtil.getEntityManager();
		try {
			em.getTransaction().begin();
			Vozilo v = em.find(Vozilo.class, id_vozilo);
			if(v != null) {
				v.setObrisan(1);
				em.persist(v);
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
	
	public static void main(String[] args) {
		List<Vozilo> findAllVozilo = findAll();
		for (Vozilo vozilo : findAllVozilo) {
			System.out.println(vozilo.getMarka() + " " + vozilo.getModel());
		}
	}

}
