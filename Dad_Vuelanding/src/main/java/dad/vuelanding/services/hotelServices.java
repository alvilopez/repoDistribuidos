package dad.vuelanding.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dad.vuelanding.model.Aeropuerto;
import dad.vuelanding.model.Hotel;
import dad.vuelanding.reposotories.hotelRepository;

@Service
public class hotelServices {
	
	@Autowired
	private hotelRepository hotelRespository;
	
	  
    public Optional<Hotel> findById(long id) {
		return hotelRespository.findById(id);
	}

	public boolean exist(long id) {
		return hotelRespository.existsById(id);
	}

	public List<Hotel> findAll() {
		return hotelRespository.findAll();
	}

	public void save(Hotel hotel) {
		hotelRespository.save(hotel);
	}

	public void delete(long id) {
		hotelRespository.deleteById(id);
	}	
	
	public ArrayList<Hotel> relatedWithAirport(Aeropuerto aer){
		return hotelRespository.findByAeropuerto(aer);
	}
	
}
