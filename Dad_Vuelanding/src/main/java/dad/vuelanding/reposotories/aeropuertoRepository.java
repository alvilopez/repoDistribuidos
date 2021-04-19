package dad.vuelanding.reposotories;

import java.util.ArrayList;


import org.springframework.data.jpa.repository.JpaRepository;

import dad.vuelanding.model.Aeropuerto;


public interface aeropuertoRepository extends JpaRepository<Aeropuerto,Long> {
	
	Aeropuerto findByCiudad(String nombre);
	Aeropuerto findByNombre(String nombre);
	ArrayList<Aeropuerto> findAllByOrderByNombre();
	Boolean existsByCiudad(String nombre);
	
}
