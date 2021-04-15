package dad.vuelanding.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import dad.vuelanding.model.Hotel;
import dad.vuelanding.model.Reserva;
import dad.vuelanding.model.Usuario;
import dad.vuelanding.reposotories.reservaRepository;

public class reservaServices {
	
	@Autowired
	private reservaRepository reservaRepository;
	
	
	
	public ArrayList<Reserva> relatedWithUsuario(Usuario user){
		return reservaRepository.findByUsuario(user);
	}

	public ArrayList<Reserva> relatedWithHotel(Hotel h){
		return reservaRepository.findAllByHotel(h);
	}

	public void delete(long id) {
		reservaRepository.deleteById(id);
	}
}
