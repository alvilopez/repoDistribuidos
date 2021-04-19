package dad.servicio;

public class Billete {
	
	private String contenido;

	
	public Billete(String contenido) {
		this.contenido = contenido;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	@Override
	public String toString() {
		return "Billete [contenido=" + contenido + "]";
	}
	
	
	
	
}
