package dad.servicio;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;

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
	
	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> roles;
	
	/*@OneToMany
	private List<Reserva> reserva;*/
	
	public Usuario() {/*reserva = new ArrayList<Reserva>();*/}

	public Usuario(String name, String password){
		this.name = name;
		this.password = password;
	}
	
	public Usuario(String name, String password, String... roles) {
		this.name = name;
		this.password = password;
		this.roles = List.of(roles);
		//reserva = new ArrayList<>();
	}
	
	
	public Usuario(String name, String surname, int edad, String nif, String correo, String password) {
		this.name = name;
		this.surname = surname;
		this.edad = edad;
		this.nif = nif;
		this.correo = correo;
		this.password = password;
		//reserva = new ArrayList<>();
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
	
	public void encodePassword() {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		this.password = encoder.encode(this.password);
		System.out.println(this.password);
	}
	
	
	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	
	
	/*public List<Reserva> getReserva() {
		return reserva;
	}

	public void setReserva(List<Reserva> reserva) {
		this.reserva = reserva;
	}

	public void anadirReserva(Reserva aux) {
		reserva.add(aux);
	}*/
}
