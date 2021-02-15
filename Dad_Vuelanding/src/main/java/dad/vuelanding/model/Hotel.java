package dad.vuelanding.model;

public class Hotel {
	
	private String name;
	private int estrellas;
	private Aeropuerto aeropuerto;
	
	public Hotel(String name, int estrellas, Aeropuerto aeropuerto) {
		this.name = name;
		this.estrellas = estrellas;
		this.aeropuerto = aeropuerto;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getEstrellas() {
		return estrellas;
	}

	public void setEstrellas(int estrellas) {
		this.estrellas = estrellas;
	}

	public Aeropuerto getAeropuerto() {
		return aeropuerto;
	}

	public void setAeropuerto(Aeropuerto aeropuerto) {
		this.aeropuerto = aeropuerto;
	}
	
	
	
	
}
