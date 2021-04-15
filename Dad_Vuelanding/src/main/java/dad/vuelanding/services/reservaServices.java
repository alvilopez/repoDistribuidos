package dad.vuelanding.services;


import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;


import dad.vuelanding.model.Hotel;
import dad.vuelanding.model.Reserva;
import dad.vuelanding.model.Usuario;

import dad.vuelanding.reposotories.reservaRepository;
import dad.vuelanding.model.Reserva;

public class reservaServices {
	
	
	@Autowired
	private reservaRepository repository;

	public Optional<Reserva> findById(long id) {
		return repository.findById(id);
	}

	public boolean exist(long id) {
		return repository.existsById(id);
	}

	public List<Reserva> findAll() {
		return repository.findAll();
	}

	public void save(Reserva reserva) {
		repository.save(reserva);
	}

	public void delete(long id) {
		repository.deleteById(id);
	}
	
	/*public ArrayList<Reserva> relatedWithUsuario(Usuario user){
		ArrayList<Reserva> aux = reservaRepository.findAllByUsuario(user);
		return aux;
	}

	public ArrayList<Reserva> relatedWithHotel(Hotel h){
		return reservaRepository.findAllByHotel(h);
	}*/

}
