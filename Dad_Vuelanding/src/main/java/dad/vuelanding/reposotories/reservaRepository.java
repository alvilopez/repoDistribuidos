package dad.vuelanding.reposotories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import dad.vuelanding.model.Reserva;
import dad.vuelanding.model.Usuario;

public interface reservaRepository extends JpaRepository<Reserva, Long> {
	ArrayList<Reserva> findByUsuario(Usuario aux);
}
