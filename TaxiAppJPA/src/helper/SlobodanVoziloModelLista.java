package helper;

public class SlobodanVoziloModelLista {
	String adresaCilj;
	int idKorisnik;
	String ime;
	String prezime;
	String model;
	
	public SlobodanVoziloModelLista(String adresaCilj, int idKorisnik, String ime, String prezime, String model) {
		super();
		this.adresaCilj = adresaCilj;
		this.idKorisnik = idKorisnik;
		this.ime = ime;
		this.prezime = prezime;
		this.model = model;
	}

	public String getAdresaCilj() {
		return adresaCilj;
	}

	public void setAdresaCilj(String adresaCilj) {
		this.adresaCilj = adresaCilj;
	}

	public int getIdKorisnik() {
		return idKorisnik;
	}

	public void setIdKorisnik(int idKorisnik) {
		this.idKorisnik = idKorisnik;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	@Override
	public String toString() {
		return "SlobodanVoziloModelLista [adresaCilj=" + adresaCilj + ", idKorisnik=" + idKorisnik + ", ime=" + ime
				+ ", prezime=" + prezime + ", model=" + model + "]";
	}
	
		
		

}
