package dad.vuelanding.reposotories;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dad.vuelanding.model.Vuelo;

public interface vueloRepository extends JpaRepository<Vuelo, Long> {

    List<Vuelo> findByFechaSalida(Date fechaSalida);
}
