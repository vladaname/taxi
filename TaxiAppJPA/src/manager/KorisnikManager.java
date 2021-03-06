package manager;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.EntityManager;

import org.jasypt.util.password.StrongPasswordEncryptor;

import com.mysql.fabric.xmlrpc.base.Array;
import com.sun.org.apache.regexp.internal.recompile;

import helper.SumZarade;
import helper.listaSlobodnihVozaca;
import model.Korisnik;
import model.Racun;
import model.Uloga;

public class KorisnikManager {
	
	public static final String STATUS_VOZNJE_ZAUZETO = "zauzeto";
	public static final String STATUS_VOZNJE_SLOBODAN = "slobodno";
	public static final String STATUS_VOZNJE_ZAVRSENO = "zavrseno";
	


	public static List<Korisnik> findAllKorisnik() {
		EntityManager em = JPAUtil.getEntityManager();
		return em.createQuery("FROM Korisnik k").getResultList();
	}

	public static Korisnik findByTelefon(String telefon) {
		EntityManager em = JPAUtil.getEntityManager();
		return (Korisnik) em.createQuery("FROM Korisnik k WHERE k.telefon =:telefon").setParameter("telefon", telefon)
				.getSingleResult();
	}

	public static Korisnik findByEmail(String email) {
		EntityManager em = JPAUtil.getEntityManager();
		return (Korisnik) em.createQuery("FROM Korisnik k WHERE k.email =:email").setParameter("email", email)
				.getSingleResult();
	}

	public static Korisnik logovanje(String username, String pass) {
		System.out.println(username + pass);
		EntityManager em = JPAUtil.getEntityManager();
		Korisnik k = (Korisnik) em.createQuery("FROM Korisnik k WHERE k.username =:username")
				.setParameter("username", username).getSingleResult();
		StrongPasswordEncryptor passEncryptor = new StrongPasswordEncryptor();
		String encryptedPass = k.getPass();

		if (pass.equals(k.getPass()) || passEncryptor.checkPassword(pass, encryptedPass)) {
			return k;
		} else {
			return null;
		}
	}
//	public static Korisnik countSumByIdKorisnik(int idKorisnik) {
//		EntityManager em = JPAUtil.getEntityManager();
//		return (Korisnik) em.createNativeQuery("select sum(ukupan_racun) as ukupan_racun, k.ime as ime, k.prezime as prezime "
//				+ "from Racun as r " + 
//				"inner join voznja as v on r.voznja_id_voznja = v.id_voznja " + 
//				"inner join korisnik as k on v.korisnik_id_korisnik = k.id_korisnik " + 
//				"inner join uloga as u on k.uloga_id_uloga = u.id_uloga " + 
//				"where uloga_id_uloga = 2 and k.id_korisnik =:id_korisnik ")
//				.setParameter("idKorisnik", idKorisnik).getSingleResult();
//	}
	
	public static SumZarade sumZaradeKorisnika(int idKorisnik){
		EntityManager em = JPAUtil.getEntityManager();
		Query q = em.createNativeQuery("select id_korisnik, sum(ukupan_racun) as ukupan_racun, k.ime as ime, k.prezime as prezime " + 
				"from Racun as r " + 
				"inner join voznja as v on r.voznja_id_voznja = v.id_voznja " + 
				"inner join korisnik as k on v.korisnik_id_korisnik = k.id_korisnik " + 
				"inner join uloga as u on k.uloga_id_uloga = u.id_uloga " + 
				"where uloga_id_uloga = 2 and k.id_korisnik =:id_korisnik ").setParameter("id_korisnik", idKorisnik);
		Object[] o = (Object[]) q.getSingleResult();
//		for (Object[] o : listaSum) {
		SumZarade s = new SumZarade(idKorisnik, Double.parseDouble(o[1].toString()), o[2].toString(), o[3].toString());
//			sum.add(s);
//		}
		return s;
	}
//	public static String sumtByIdKorisnik(int idKorisnik) {
//		EntityManager em = JPAUtil.getEntityManager();
//		Query q = em.createNativeQuery("select id_korisnik, sum(ukupan_racun) as ukupan_racun, k.ime as ime, k.prezime as prezime " + 
//				"from Racun as r " + 
//				"inner join voznja as v on r.voznja_id_voznja = v.id_voznja " + 
//				"inner join korisnik as k on v.korisnik_id_korisnik = k.id_korisnik " + 
//				"inner join uloga as u on k.uloga_id_uloga = u.id_uloga " + 
//				"where uloga_id_uloga = 2 and k.id_korisnik =:id_korisnik ").setParameter("id_korisnik", idKorisnik);
//		Object[] sum = (Object[]) q.getSingleResult();
//		return sum[0].toString() + " " + sum[1].toString() + " " +sum[2].toString() + " " + sum[3].toString();
//		
//	}
	
	public static List<listaSlobodnihVozaca> findAllSlobodanVozac(String statusVoznje){
		EntityManager em = JPAUtil.getEntityManager();
			List<listaSlobodnihVozaca> listaSlobodnihVozaca = new ArrayList();
//			Query q = (Query) em.createNativeQuery("select k.prezime, k.ime, k.obrisan from "
//					+ "korisnik k where k.uloga_id_uloga = 2 and k.obrisan = 0 and k.id_korisnik not in " 
//					+ "(select distinct v.korisnik_id_korisnik from voznja v where v.status_voznje = 'zauzeto'); ")
//					.setParameter("status_voznje", status_voznje);

//			return em.createQuery.getResultList();
			
			Query query = em.createQuery("SELECT distinct id_korisnik, korisnik.ime, korisnik.prezime FROM Voznja as v "
					+ "WHERE v.korisnik.uloga.idUloga = 2 and v.statusVoznje =:statusVoznje ")
					.setParameter("statusVoznje", statusVoznje);
			List<Object[]> rows = query.getResultList();
			for (Object[] objects : rows) {
				listaSlobodnihVozaca a = new listaSlobodnihVozaca(objects[0].toString(),statusVoznje, objects[1].toString(), objects[2].toString());
				listaSlobodnihVozaca.add(a);
			}
			return listaSlobodnihVozaca;
			
//			List<Object[]> slobodanAuto = qh.getResultList();
//			for (Object[] o : slobodanAuto) {
//				listaSlobodnihVozaca a = new listaSlobodnihVozaca(o[0].toString(), o[1].toString());
//				listaSlobodnihVozaca.add(a);
//			}
//			return listaSlobodnihVozaca;
		
	}
	
//	public static List<listaSlobodnihVozaca> slobodniVozaciAll(String status_voznje){
//		EntityManager em = JPAUtil.getEntityManager();
//		List<listaSlobodnihVozaca> listaSV = new ArrayList(); 
//		Query q = (Query) em.createNativeQuery("select distinct k.ime, k.prezime from voznja v " + 
//				"inner join korisnik as k on v.korisnik_id_korisnik = k.id_korisnik " + 
//				"inner join uloga as u on k.uloga_id_uloga = u.id_uloga " + 
//				"where uloga_id_uloga = 2 and obrisan = 0 and status_voznje = 'slobodno';")
//		Query qh = em.createQuery("SELECT distinct k.ime, k.prezime FROM Voznja as v WHERE v.korisnik.uloga = 2 and v.status_voznje = 'slobodno'")
//				.setParameter("status_voznje", status_voznje);
//		List<Object[]> slobodniAuto = q.getResultList();
//				
//	}

	public static boolean createKorisnik(String pass, String email, String ime, String prezime, String telefon,
			String username) {
		EntityManager em = JPAUtil.getEntityManager();
		try {
			em.getTransaction().begin();
			StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
			Korisnik k = new Korisnik();
			k.setEmail(email);
			k.setIme(ime);
			k.setObrisan(0);
			passwordEncryptor.encryptPassword(pass);
			k.setPass(pass);
			k.setPrezime(prezime);
			k.setTelefon(telefon);
			k.setUsername(username);
			em.persist(k);
			em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		} finally {
			em.close();
		}
	}

	public static boolean setUloga(Object id_korisnik, int id_uloga) {
		EntityManager em = JPAUtil.getEntityManager();
		try {
			em.getTransaction().begin();
			Korisnik k = em.find(Korisnik.class, id_korisnik);
			if (k != null) {
				Uloga u = em.find(Uloga.class, id_uloga);
				if (u == null) {
					return false;
				}
				k.setUloga(u);
				em.persist(k);
				em.getTransaction().commit();
				return true;
			}
			return false;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		} finally {
			em.close();
		}
	}

	public static boolean updateKorisnik(int id_korisnik, String pass, String ime, String email, String prezime,
			String telefon, String username) {
		EntityManager em = JPAUtil.getEntityManager();
		try {
			em.getTransaction().begin();
			StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
			Korisnik k = em.find(Korisnik.class, id_korisnik);
			if (k != null) {
				k.setEmail(email);
				k.setIme(ime);
				k.setObrisan(0);
				passwordEncryptor.encryptPassword(pass);
				k.setPass(pass);
				k.setPrezime(prezime);
				k.setTelefon(telefon);
				k.setUsername(username);
				em.persist(k);
				em.getTransaction().commit();
				return true;
			}
			return false;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		} finally {
			em.close();
		}
	}

	public static boolean deleteKorisnik(int id_korisnik) {
		EntityManager em = JPAUtil.getEntityManager();
		try {
			em.getTransaction().begin();
			Korisnik k = em.find(Korisnik.class, id_korisnik);
			k.setObrisan(1);
			em.persist(k);
			em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;

		} finally {
			em.close();
		}
	}
	
	
	public static void main(String[] args) {
//		Korisnik k = logovanje("test", "test123");
//		System.out.println(k);
		List<listaSlobodnihVozaca> lista = findAllSlobodanVozac(STATUS_VOZNJE_SLOBODAN);
		System.out.println(lista);
		
//		String countById = sumtByIdKorisnik(1);
//		System.out.println(countById);
		
//		SumZarade suma = sumZaradeKorisnika(1);
//		for (SumZarade sumZarade : suma) {
//			System.out.println(suma);
//		}
	}

}
