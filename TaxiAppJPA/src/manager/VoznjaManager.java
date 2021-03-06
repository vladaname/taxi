package manager;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.jpa.QueryHints;

import com.sun.xml.internal.messaging.saaj.packaging.mime.util.QEncoderStream;

import helper.ListaSlobodnihVoznji;
import helper.ListaZauzetihVoznji;
import helper.SlobodanVoziloModelLista;
import helper.listaSlobodnihVozaca;
import jdk.nashorn.internal.ir.RuntimeNode.Request;
import model.Cenovnik;
import model.Korisnik;
import model.Racun;
import model.Voznja;

public class VoznjaManager {
	public static final String STATUS_VOZNJE_SLOBODAN = "slobodno";
	public static final String STATUS_VOZNJE_ZAUZETO = "zauzeto";
	public static final String STATUS_VOZNJE_ZAVRSENO = "zavrseno";
	public static final int ULOGA_VOZAC = 2;

	public static List<Voznja> findAllVoznja() {
		EntityManager em = JPAUtil.getEntityManager();
		try {
			return em.createQuery("FROM Voznja v").getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	public static List<Voznja> findAllSlobodnaVoznja() {
		EntityManager em = JPAUtil.getEntityManager();
		try {
			return em.createQuery("FROM Voznja z WHERE z.statusVoznje =:slobodno")
					.setParameter("slobodno", STATUS_VOZNJE_SLOBODAN).getResultList();

		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	public static List<Voznja> findAllZauzetoVoznja1() {
		EntityManager em = JPAUtil.getEntityManager();
		return em.createQuery("SELECT v.idVoznja FROM Voznja v WHERE v.statusVoznje =:zauzeto")
				.setParameter("zauzeto", STATUS_VOZNJE_ZAUZETO).getResultList();
	}

	public static List<ListaZauzetihVoznji> findAllZauzetoVoznja() {
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		List<ListaZauzetihVoznji> zauzeto = new ArrayList<>();
		Query q = (Query) em.createNativeQuery("select v.id_voznja, k.ime, k.prezime from voznja v "
				+ "inner join korisnik as k on v.korisnik_id_korisnik = k.id_korisnik "
				+ "where status_voznje = 'zauzeto' ");
		List<Object[]> zauzetoAllId = q.getResultList();
		for (Object[] o : zauzetoAllId) {
			ListaZauzetihVoznji z = new ListaZauzetihVoznji(Integer.parseInt(o[0].toString()), o[1].toString(),
					o[2].toString());
			zauzeto.add(z);

		}
		em.close();
		return zauzeto;

	}
	public static List<ListaSlobodnihVoznji> findAllSlobodnoVoznjaAssign() {
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		List<ListaSlobodnihVoznji> slobodno = new ArrayList<>();
		Query q = (Query) em.createNativeQuery("select distinct v.id_voznja, k.ime, k.prezime from voznja v "
				+ "inner join korisnik as k on v.korisnik_id_korisnik = k.id_korisnik "
				+ "where status_voznje = 'slobodno' ");
	
		List<Object[]> slobodnoAllId = q.getResultList();
		for (Object[] o : slobodnoAllId) {
			ListaSlobodnihVoznji s = new ListaSlobodnihVoznji(Integer.parseInt(o[0].toString()), o[1].toString(),
					o[2].toString());
			slobodno.add(s);
		}
		
		
		em.close();
		return slobodno;

	}

	public static List<ListaSlobodnihVoznji> findAllSlobodnoVoznja() {
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		List<ListaSlobodnihVoznji> slobodno = new ArrayList<>();
		Query q = (Query) em.createNativeQuery("select distinct v.id_voznja, k.ime, k.prezime from voznja v "
				+ "inner join korisnik as k on v.korisnik_id_korisnik = k.id_korisnik "
				+ "where status_voznje = 'slobodno' ");
		Query q1 = (Query) em.createNativeQuery("select distinct v.id_voznja from voznja v "
				+ "where status_voznje = 'slobodno' and v.korisnik_id_korisnik is null ");
		List<Object[]> slobodnoAllId = q.getResultList();
		for (Object[] o : slobodnoAllId) {
			ListaSlobodnihVoznji s = new ListaSlobodnihVoznji(Integer.parseInt(o[0].toString()), o[1].toString(),
					o[2].toString());
			slobodno.add(s);
		}
		List<Integer> slobodnoAllId1 = q1.getResultList();
		for (Integer o : slobodnoAllId1) {
			ListaSlobodnihVoznji s = new ListaSlobodnihVoznji(o, "Bez vozaca",
					"Bez vozaca");
			slobodno.add(s);
		}
		
		em.close();
		return slobodno;

	}

	public static boolean assignZavrsenaVoznja(int IdVoznja) {
		EntityManager em = JPAUtil.getEntityManager();
		try {
			em.getTransaction().begin();
			Voznja zavrsenaVoznja = em.find(Voznja.class, IdVoznja);
			if (zavrsenaVoznja != null) {
				zavrsenaVoznja.setStatusVoznje(STATUS_VOZNJE_ZAVRSENO);
				zavrsenaVoznja.getKorisnik().setObrisan(1);
				em.persist(zavrsenaVoznja);
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

	// ???????????????????????????????????????????????????????????????String
	// zauzet????
	public static boolean assignSlobodanaVoznja() {
		EntityManager em = JPAUtil.getEntityManager();
		try {
			em.getTransaction().begin();
			Voznja slobodnaVoznja = (Voznja) em
					.createQuery("SELECT v.status_voznje FROM Voznja v WHERE v.status_voznje =:zavrseno")
					.getSingleResult();
			if (slobodnaVoznja != null) {
				slobodnaVoznja.setStatusVoznje("slobodno");
				em.persist(slobodnaVoznja);
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

	public static boolean assignVoznja(int id_voznja, int id_korisnik) {
		EntityManager em = JPAUtil.getEntityManager();
		try {
			em.getTransaction().begin();
			Voznja v = em.find(Voznja.class, id_voznja);
			if (v != null) {
				Korisnik k = em.find(Korisnik.class, id_korisnik);
				if (k != null && k.getUloga().getIdUloga() == ULOGA_VOZAC) {
					v.setStatusVoznje(STATUS_VOZNJE_ZAUZETO);
					v.setKorisnik(k);
					em.persist(v);
					em.getTransaction().commit();

					return true;
				}

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

	public static boolean createVoznja(String adresaPolazak, Date vremePolazak, String adresaCilj, String telefonMob) {
		EntityManager em = JPAUtil.getEntityManager();
		try {
			em.getTransaction().begin();
			Voznja v = new Voznja();
			v.setAdresaPolazak(adresaPolazak);
			Timestamp vremePolaskats = new Timestamp(vremePolazak.getTime());
			v.setVremePolazak(vremePolaskats);
			v.setAdresaCilj(adresaCilj);
			// Korisnik k = em.find(Korisnik.class, id_korisnik);
			// v.setKorisnik(k);
			v.setTelefonMob(telefonMob);
			v.setStatusVoznje(STATUS_VOZNJE_SLOBODAN);
			v.setUkupnoKm(0);
			Racun r = new Racun();
			r.setUkupanRacun(0);
			int maxCenovnik = (int) em.createQuery("select max(idCenovnik) from Cenovnik").getSingleResult();
			if (maxCenovnik > 0) {
				Cenovnik c = em.find(Cenovnik.class, maxCenovnik);
				r.setCenovnik(c);
			} else {
				return false;
			}
			em.persist(v);
			em.flush();
			// Voznja persistV = em.find(Voznja.class, v.getIdVoznja());
			r.setVoznja(v);
			em.persist(r);
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

	public static boolean updateVoznja(int id_voznja, int ukupnoKm) {
		EntityManager em = JPAUtil.getEntityManager();
		try {
			em.getTransaction().begin();
			Voznja v = em.find(Voznja.class, id_voznja);
			if (v != null) {
				v.setUkupnoKm(ukupnoKm);
				v.setStatusVoznje(STATUS_VOZNJE_ZAVRSENO);
				em.persist(v);
				em.flush();
				Racun r = (Racun) em.createQuery("SELECT r FROM Racun r WHERE r.voznja.idVoznja =:idVoznja ")
						.setParameter("idVoznja", id_voznja).getSingleResult();
				if (r != null) {
					double ukupanRacun = r.getCenovnik().getCenaStart() + (r.getCenovnik().getCena() * v.getUkupnoKm());
					r.setUkupanRacun(ukupanRacun);
				} else {
					return false;
				}
				em.flush();
				em.persist(r);
				em.getTransaction().commit();
				return true;
			}

			return false;

		} catch (

		Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		} finally {
			em.close();
		}
	}

	public static List<listaSlobodnihVozaca> findAllSlobodanVozac() {
		EntityManager em = JPAUtil.getEntityManager();
		List<listaSlobodnihVozaca> listaSlobodnihVozaca = new ArrayList();
		Query q = (Query) em
				.createNativeQuery("select k.id_korisnik, k.prezime, k.ime, k.obrisan from "
						+ "korisnik k where k.uloga_id_uloga = 2 and k.obrisan = 0 and k.id_korisnik not in "
						+ "(select distinct v.korisnik_id_korisnik from voznja v where v.status_voznje =:zauzeto); ")
				.setParameter("zauzeto", STATUS_VOZNJE_ZAUZETO);
		List<Object[]> slobodanAuto = q.getResultList();
		for (Object[] o : slobodanAuto) {
			listaSlobodnihVozaca a = new listaSlobodnihVozaca(o[0].toString(), null, o[1].toString(), o[2].toString());
			listaSlobodnihVozaca.add(a);
		}
		return listaSlobodnihVozaca;

	}

	// SimpleDateFormat date = new .....
	// date

	// suma racun za svakog vozaca posebno DATUM? nisam uradio
	// Racun sumSracun = ("SELECT sum(r) FROM Racun r WHERE
	// r.voznja.korisnik.id_korisnik =:id_korisnik").getSingleResult();
	public static List<SlobodanVoziloModelLista> findAllSlobodnoVozilo() {
		EntityManager em = JPAUtil.getEntityManager();
		List<SlobodanVoziloModelLista> lista = new ArrayList<>();
		Query q = em.createNativeQuery(
				"select distinct v.adresa_cilj, k.id_korisnik, k.ime, k.prezime, z.model from voznja v "
						+ "inner join korisnik as k on v.korisnik_id_korisnik = k.id_korisnik "
						+ "inner join vozilo as z on k.vozilo_id_vozilo = z.id_vozilo "
						+ "inner join uloga as g on k.uloga_id_uloga = g.id_uloga "
						+ "where g.id_uloga = 2 and v.status_voznje = 'slobodno' ");
		List<Object[]> o = q.getResultList();
		for (Object[] obj : o) {
			SlobodanVoziloModelLista s = new SlobodanVoziloModelLista(obj[0].toString(),
					Integer.parseInt(obj[1].toString()), obj[2].toString(), obj[3].toString(), obj[4].toString());
			lista.add(s);
		}
		return lista;
	}

	public static void main(String[] args) {
		// List<SlobodanVoziloModelLista> slo = findAllSlobodnoVozilo();
		// System.out.println(slo.toString());
		
//		 List<ListaZauzetihVoznji> lista = findAllZauzetoVoznja();
//		 System.out.println(lista.toString());

		// boolean zavrsiVoznju = assignZavrsenaVoznja(1);
		// if(zavrsiVoznju) {
		// System.out.println("Voznja je zavrsena");
		// }
		// else {
		// System.out.println("Greska!");
		// }

		 List<listaSlobodnihVozaca> lista = findAllSlobodanVozac();
		 System.out.println(lista);

//		 List<Voznja> lista1 = findAllSlobodnaVoznja();
//		 System.out.println(lista1.toString());
		
//		List<ListaSlobodnihVoznji> a = findAllSlobodnoVoznja();
//		System.out.println(a);

		// List<Voznja> slobodneVoznje = findAllSlobodnaVoznja();
		// System.out.println("Slobodna voznja je: " + slobodneVoznje.size());
		// for (Voznja voznja : slobodneVoznje) {
		// System.out.println(voznja);

//		boolean voznja = createVoznja("Bul Cara Lazara 2", new Date(), "Jovana Subotica 5", "7890654");
//		if (voznja) {
//			System.out.println("Voznja je kreirana");
//		} else {
//			System.out.println("Voznja nije kreirana");
//		}
	}

}
