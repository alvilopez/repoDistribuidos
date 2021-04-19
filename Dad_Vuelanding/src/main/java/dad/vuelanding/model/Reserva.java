package dad.vuelanding.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Reserva {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	
	
	@OneToOne
	private Vuelo ida;
	@OneToOne
	private Vuelo vuelta;
	@OneToOne
	private Hotel hotel;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Usuario usuario;
	
	public Reserva() {}
	
	public Reserva(Usuario cliente, Vuelo ida, Vuelo vuelta, Hotel hotel) {
		//this.cliente = cliente;
		this.ida = ida;
		this.vuelta = vuelta;
		this.hotel = hotel;
	}

	public Reserva(Vuelo ida, Vuelo vuelta) {
		super();
		this.ida = ida;
		this.vuelta = vuelta;
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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		StringBuilder reserva = new StringBuilder();
		reserva.append("USUARIO:\n");
		reserva.append(usuario.toString());
		reserva.append("\n");
		if(!hotel.getName().equals("")) reserva.append("HOTEL:\n"); reserva.append(hotel.toString()); reserva.append("\n");
		reserva.append("IDA:\n");
		reserva.append(ida.toString());
		reserva.append("\n");
		reserva.append("VUELTA:\n");
		reserva.append(vuelta.toString());
		reserva.append("\n");
		return reserva.toString();
	}
	
	
}
