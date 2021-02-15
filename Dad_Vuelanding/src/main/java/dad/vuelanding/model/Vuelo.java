package dad.vuelanding.model;

public class Vuelo {
    private Aeropuerto aeropouertoSalida;
    private Aeropuerto aeropuertoLlegada;
    private java.sql.Date fechaSalida;
    private java.sql.Date fechaLlegada;


    public Vuelo() {
    }

    public Vuelo(Aeropuerto aeropouertoSalida, Aeropuerto aeropuertoLlegada, java.sql.Date fechaSalida, java.sql.Date fechaLlegada) {
        this.aeropouertoSalida = aeropouertoSalida;
        this.aeropuertoLlegada = aeropuertoLlegada;
        this.fechaSalida = fechaSalida;
        this.fechaLlegada = fechaLlegada;
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


   

}