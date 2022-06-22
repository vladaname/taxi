package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the vozilo database table.
 * 
 */
@Entity
@NamedQuery(name="Vozilo.findAll", query="SELECT v FROM Vozilo v")
public class Vozilo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_vozilo")
	private int idVozilo;

	private String gorivo;

	private String marka;

	private String model;

	private int obrisan;

	private String tip;

	//bi-directional many-to-one association to Korisnik
	@OneToMany(mappedBy="vozilo")
	private List<Korisnik> korisniks;

	public Vozilo() {
	}

	public int getIdVozilo() {
		return this.idVozilo;
	}

	public void setIdVozilo(int idVozilo) {
		this.idVozilo = idVozilo;
	}

	public String getGorivo() {
		return this.gorivo;
	}

	public void setGorivo(String gorivo) {
		this.gorivo = gorivo;
	}

	public String getMarka() {
		return this.marka;
	}

	public void setMarka(String marka) {
		this.marka = marka;
	}

	public String getModel() {
		return this.model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getObrisan() {
		return this.obrisan;
	}

	public void setObrisan(int obrisan) {
		this.obrisan = obrisan;
	}

	public String getTip() {
		return this.tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public List<Korisnik> getKorisniks() {
		return this.korisniks;
	}

	public void setKorisniks(List<Korisnik> korisniks) {
		this.korisniks = korisniks;
	}

	public Korisnik addKorisnik(Korisnik korisnik) {
		getKorisniks().add(korisnik);
		korisnik.setVozilo(this);

		return korisnik;
	}

	public Korisnik removeKorisnik(Korisnik korisnik) {
		getKorisniks().remove(korisnik);
		korisnik.setVozilo(null);

		return korisnik;
	}

}