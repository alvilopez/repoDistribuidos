package dad.vuelanding.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Hotel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name;
	private String estrellas;
	
	private String aeropuertoAux;
	
	@OneToOne
	private Aeropuerto aeropuerto;

	public Hotel () {}
	
	public Hotel(String name, String estrellas) {
		this.name = name;
		this.estrellas = estrellas;
	}
	
	public Hotel(String name, String estrellas, String aeropuertoAux) {
		this.name = name;
		this.estrellas = estrellas;
		this.aeropuertoAux = aeropuertoAux;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEstrellas() {
		return estrellas;
	}

	public void setEstrellas(String estrellas) {
		this.estrellas = estrellas;
	}

	public Aeropuerto getAeropuerto() {
		return aeropuerto;
	}

	public void setAeropuerto(Aeropuerto aeropuerto) {
		this.aeropuerto = aeropuerto;
	}

	public String getAeropuertoAux() {
		return aeropuertoAux;
	}

	public void setAeropuertoAux(String aeropuertoAux) {
		this.aeropuertoAux = aeropuertoAux;
	}
	
	
	
	
}
