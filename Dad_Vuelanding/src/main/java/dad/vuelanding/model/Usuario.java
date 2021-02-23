package dad.vuelanding.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String name;
	private String surname;
	private int edad;
	private String nif;
	private String correo;
	private String password;
	
	@OneToMany
	private List<Reserva> reserva;
	
	public Usuario() {}
	
	public Usuario(String name, String surname) {
		this.name = name;
		this.surname = surname;
	}

	public Usuario(String name, String surname, int edad, String nif, String correo, String password) {
		this.name = name;
		this.surname = surname;
		this.edad = edad;
		this.nif = nif;
		this.correo = correo;
		this.password = password;
		reserva = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
