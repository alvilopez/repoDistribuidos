package dad.vuelanding.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Vuelo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    private String codigo;

    
    
    //private Aeropuerto aeropouertoSalida;
    //private Aeropuerto aeropuertoLlegada;
    private java.sql.Date fechaSalida;
    private java.sql.Date fechaLlegada;

    @OneToOne
    private Aeropuerto aeropouertoSalida;

    @OneToOne
    private Aeropuerto aeropuertoLlegada;


    public Vuelo() {
    }
    
    
    
    public Vuelo(String codigo, Aeropuerto aeropouertoSalida, Aeropuerto aeropuertoLlegada) {
		this.codigo = codigo;
		this.aeropouertoSalida = aeropouertoSalida;
		this.aeropuertoLlegada = aeropuertoLlegada;
	}

    

	public Vuelo(Aeropuerto aeropouertoSalida, Aeropuerto aeropuertoLlegada, java.sql.Date fechaSalida, java.sql.Date fechaLlegada,String codigo) {
        this.aeropouertoSalida = aeropouertoSalida;
        this.aeropuertoLlegada = aeropuertoLlegada;
        this.fechaSalida = fechaSalida;
        this.fechaLlegada = fechaLlegada;
        this.codigo = codigo;
    }

    public String getCodigo() {
        return this.codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Aeropuerto getAeropouertoSalida() {
        return this.aeropouertoSalida;
    }

    public void setAeropouertoSalida(Aeropuerto aeropouertoSalida) {
        this.aeropouertoSalida = aeropouertoSalida;
    }

    public Aeropuerto getAeropuertoLlegada() {
        return this.aeropuertoLlegada;
    }

    public void setAeropuertoLlegada(Aeropuerto aeropuertoLlegada) {
        this.aeropuertoLlegada = aeropuertoLlegada;
    }

    public java.sql.Date getFechaSalida() {
        return this.fechaSalida;
    }

    public void setFechaSalida(java.sql.Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public java.sql.Date getFechaLlegada() {
        return this.fechaLlegada;
    }

    public void setFechaLlegada(java.sql.Date fechaLlegada) {
        this.fechaLlegada = fechaLlegada;
    }


    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }



	@Override
	public String toString() {
		StringBuilder vuelo = new StringBuilder();
		vuelo.append("Codigo: "+codigo+"\n");
		vuelo.append("Fecha Ida: " +fechaSalida.toString()+"\n");
		vuelo.append("Fecha Vuelta: " +fechaLlegada.toString()+"\n");
		vuelo.append("Ciudad Salida: "+ aeropouertoSalida.getCiudad()+"\n");
		vuelo.append("Ciudad Llegada: "+ aeropuertoLlegada.getCiudad()+"\n");
		return vuelo.toString();
	}

   

}