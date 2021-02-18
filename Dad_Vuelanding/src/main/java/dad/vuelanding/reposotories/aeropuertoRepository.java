package dad.vuelanding.reposotories;

import org.springframework.data.jpa.repository.JpaRepository;

import dad.vuelanding.model.Aeropuerto;

public interface aeropuertoRepository extends JpaRepository<Aeropuerto,Long> {
	
}
