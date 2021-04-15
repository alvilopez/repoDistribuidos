package dad.vuelanding.restController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import dad.vuelanding.model.Hotel;
import dad.vuelanding.model.Reserva;
import dad.vuelanding.model.Usuario;
import dad.vuelanding.services.hotelServices;
import dad.vuelanding.services.reservaServices;
import dad.vuelanding.services.usuarioServices;

@RestController
@RequestMapping("/api/hoteles")
public class hotelRestController {
	
	@Autowired
	private hotelServices service;
	
	@Autowired
	private reservaServices serviceR;
	
	@GetMapping("/")
	public List<Hotel> getUsuarios(){
		return service.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Hotel> getHotel(@PathVariable long id){
		Optional<Hotel> ho = service.findById(id);
		if(ho.isPresent()) {
			Hotel hotel = ho.get();
			return new ResponseEntity<>(hotel,HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public Hotel createhHotel(@RequestBody Hotel hotel) {

		service.save(hotel);

		return hotel;
	}

	@PutMapping("/{id}")
	public ResponseEntity<Hotel> updateHotel(@PathVariable long id, @RequestBody  Hotel updateHotel) {

		if (service.exist(id)) {

			updateHotel.setId(id);
			service.save(updateHotel);

			return new ResponseEntity<>(updateHotel, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Usuario> deleteHotel(@PathVariable long id) {
		
		if(service.exist(id)) {
			Hotel hotel = service.findById(id).get();
			ArrayList<Reserva> reservaToDelete = serviceR.relatedWithHotel(hotel);
			for(Reserva r : reservaToDelete){
				serviceR.delete(r.getId());
			}
			service.delete(id);
			return new ResponseEntity<>(null, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
}
