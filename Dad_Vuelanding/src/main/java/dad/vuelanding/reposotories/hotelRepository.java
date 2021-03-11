package dad.vuelanding.reposotories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import dad.vuelanding.model.Aeropuerto;
import dad.vuelanding.model.Hotel;

public interface hotelRepository extends JpaRepository<Hotel, Long>{
	ArrayList<Hotel> findByAeropuerto(Aeropuerto ciudad);
	Hotel findByName(String name);
	ArrayList<Hotel> findAllByOrderByName();
}
