package helper;

public class ListaZauzetihVoznji {
	
	int idVoznja;
	String ime;
	String prezime;
	
	public ListaZauzetihVoznji(int idVoznja, String ime, String prezime) {
		super();
		this.idVoznja = idVoznja;
		this.ime = ime;
		this.prezime = prezime;
	}

	public int getIdVoznja() {
		return idVoznja;
	}

	public void setIdVoznja(int idVoznja) {
		this.idVoznja = idVoznja;
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
		return "ListaZauzetihVoznji [idVoznja=" + idVoznja + ", ime=" + ime + ", prezime=" + prezime + "]";
	}
	
	
	

}
