package dad.vuelanding.reposotories;

import java.util.ArrayList;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import dad.vuelanding.model.Aeropuerto;

@CacheConfig(cacheNames = "aeropuertos")
public interface aeropuertoRepository extends JpaRepository<Aeropuerto,Long> {
	
	@CacheEvict(allEntries = true)
	Aeropuerto save(Aeropuerto aeropuerto);
	
	@Cacheable
	Aeropuerto findByCiudad(String nombre);
	@Cacheable
	Aeropuerto findByNombre(String nombre);
	@Cacheable
	ArrayList<Aeropuerto> findAllByOrderByNombre();
	//@CacheEvict(allEntries = true)
	Boolean existsByCiudad(String nombre);
	
}
