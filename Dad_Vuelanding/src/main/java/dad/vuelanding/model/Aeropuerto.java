package dad.vuelanding.model;

public class Aeropuerto {
    private String ciudad;
    private String codigo;
    private String nombre;



    public Aeropuerto() {
    }

    public Aeropuerto(String ciudad, String codigo, String nombre) {
        this.ciudad = ciudad;
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public String getCiudad() {
        return this.ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getCodigo() {
        return this.codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    

    @Override
    public String toString() {
        return "{" +
            " ciudad='" + getCiudad() + "'" +
            ", codigo='" + getCodigo() + "'" +
            ", nombre='" + getNombre() + "'" +
            "}";
    }

}