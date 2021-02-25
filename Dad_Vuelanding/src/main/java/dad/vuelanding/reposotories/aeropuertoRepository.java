package dad.vuelanding.reposotories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dad.vuelanding.model.Aeropuerto;
import dad.vuelanding.model.Usuario;

public interface aeropuertoRepository extends JpaRepository<Aeropuerto,Long> {
	
	Aeropuerto findByNombre(String nombre);
	ArrayList<Aeropuerto> findAllByOrderByNombre();
	
}
