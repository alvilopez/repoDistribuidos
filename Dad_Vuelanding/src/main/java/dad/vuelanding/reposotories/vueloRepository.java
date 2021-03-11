package dad.vuelanding.reposotories;

import java.sql.Date;
import java.util.ArrayList;


import org.springframework.data.jpa.repository.JpaRepository;

import dad.vuelanding.model.Aeropuerto;
import dad.vuelanding.model.Vuelo;

public interface vueloRepository extends JpaRepository<Vuelo, Long> {

	ArrayList<Vuelo> findByAeropouertoSalida(Aeropuerto origen);
    ArrayList<Vuelo> findByFechaSalida(Date fechaSalida);
    Vuelo findByCodigo(String Codigo);
}
