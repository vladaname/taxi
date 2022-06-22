package helper;

public class listaSlobodnihVozaca {

	String id_korisnik;
	String status_voznje;
	String prezime;
	String ime;

	public listaSlobodnihVozaca(String id_korisnik, String status_voznje, String prezime, String ime) {
		super();
		this.id_korisnik = id_korisnik;
		this.status_voznje = status_voznje;
		this.prezime = prezime;
		this.ime = ime;
	}

	public String getId_korisnik() {
		return id_korisnik;
	}

	public void setId_korisnik(String id_korisnik) {
		this.id_korisnik = id_korisnik;
	}

	public String getStatus_voznje() {
		return status_voznje;
	}

	public void setStatus_voznje(String status_voznje) {
		this.status_voznje = status_voznje;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	@Override
	public String toString() {
		return "listaSlobodnihVozaca [id_korisnik=" + id_korisnik + ", status_voznje=" + status_voznje + ", prezime="
				+ prezime + ", ime=" + ime + "]";
	}

}
