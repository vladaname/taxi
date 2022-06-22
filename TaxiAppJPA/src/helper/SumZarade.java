package helper;

public class SumZarade {

	int id_korisnik;
	double ukupan_racun;
	String ime;
	String prezime;
	
	public SumZarade(int id_korisnik, double ukupan_racun, String ime, String prezime) {
		super();
		this.id_korisnik = id_korisnik;
		this.ukupan_racun = ukupan_racun;
		this.ime = ime;
		this.prezime = prezime;
	}
	public int getId_korisnik() {
		return id_korisnik;
	}
	public void setId_korisnik(int id_korisnik) {
		this.id_korisnik = id_korisnik;
	}
	public double getUkupan_racun() {
		return ukupan_racun;
	}
	public void setUkupan_racun(double ukupan_racun) {
		this.ukupan_racun = ukupan_racun;
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
	@Override
	public String toString() {
		return "SumZarade [id_korisnik=" + id_korisnik + ", ukupan_racun=" + ukupan_racun + ", ime=" + ime
				+ ", prezime=" + prezime + "]";
	}
	
	
}
