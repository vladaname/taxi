package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the cenovnik database table.
 * 
 */
@Entity
@NamedQuery(name="Cenovnik.findAll", query="SELECT c FROM Cenovnik c")
public class Cenovnik implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_cenovnik")
	private int idCenovnik;

	private double cena;

	@Column(name="cena_start")
	private double cenaStart;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="datum_promene")
	private Date datumPromene;

	//bi-directional many-to-one association to Racun
	@OneToMany(mappedBy="cenovnik")
	private List<Racun> racuns;

	public Cenovnik() {
	}

	public int getIdCenovnik() {
		return this.idCenovnik;
	}

	public void setIdCenovnik(int idCenovnik) {
		this.idCenovnik = idCenovnik;
	}

	public double getCena() {
		return this.cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	public double getCenaStart() {
		return this.cenaStart;
	}

	public void setCenaStart(double cenaStart) {
		this.cenaStart = cenaStart;
	}

	public Date getDatumPromene() {
		return this.datumPromene;
	}

	public void setDatumPromene(Date datumPromene) {
		this.datumPromene = datumPromene;
	}

	public List<Racun> getRacuns() {
		return this.racuns;
	}

	public void setRacuns(List<Racun> racuns) {
		this.racuns = racuns;
	}

	public Racun addRacun(Racun racun) {
		getRacuns().add(racun);
		racun.setCenovnik(this);

		return racun;
	}

	public Racun removeRacun(Racun racun) {
		getRacuns().remove(racun);
		racun.setCenovnik(null);

		return racun;
	}

}