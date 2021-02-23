package dad.vuelanding.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Reserva {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String cliente;
	
	@OneToOne
	private Vuelo ida;
	@OneToOne
	private Vuelo vuelta;
	@OneToOne
	private Hotel hotel;
	
	
	public Reserva(Usuario cliente, Vuelo ida, Vuelo vuelta, Hotel hotel) {
		//this.cliente = cliente;
		this.ida = ida;
		this.vuelta = vuelta;
		this.hotel = hotel;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	/*public Usuario getCliente() {
		return cliente;
	}
	public void setCliente(Usuario cliente) {
		this.cliente = cliente;
	}*/
	public Vuelo getIda() {
		return ida;
	}
	public void setIda(Vuelo ida) {
		this.ida = ida;
	}
	public Vuelo getVuelta() {
		return vuelta;
	}
	public void setVuelta(Vuelo vuelta) {
		this.vuelta = vuelta;
	}
	public Hotel getHotel() {
		return hotel;
	}
	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
	
	
}
