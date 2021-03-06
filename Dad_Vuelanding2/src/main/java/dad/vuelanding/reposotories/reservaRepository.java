package dad.vuelanding.reposotories;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dad.vuelanding.model.Hotel;
import dad.vuelanding.model.Reserva;
import dad.vuelanding.model.Usuario;
import dad.vuelanding.model.Vuelo;

public interface reservaRepository extends JpaRepository<Reserva, Long> {
	
	ArrayList<Reserva> findAllByUsuario(Usuario aux);
	Reserva findByHotel(Hotel aux);
	Reserva findByIda(Vuelo aux);
	Reserva findByVuelta(Vuelo aux);
	ArrayList<Reserva> findAllByHotel(Hotel aux);
	ArrayList<Reserva> findByUsuario(Usuario aux);
	
	
}
