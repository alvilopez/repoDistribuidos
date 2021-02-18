package dad.vuelanding.reposotories;

import org.springframework.data.jpa.repository.JpaRepository;

import dad.vuelanding.model.Reserva;

public interface reservaRepository extends JpaRepository<Reserva, Long> {
	
}
