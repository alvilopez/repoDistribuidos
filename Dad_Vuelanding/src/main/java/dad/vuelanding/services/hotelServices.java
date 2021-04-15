package dad.vuelanding.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import dad.vuelanding.model.Aeropuerto;
import dad.vuelanding.model.Hotel;
import dad.vuelanding.reposotories.hotelRepository;

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
	
	public List<Hotel> relatedWithAirport(Aeropuerto aer){
		return hotelRespository.findByAeropuerto(aer);
	}
	
}
