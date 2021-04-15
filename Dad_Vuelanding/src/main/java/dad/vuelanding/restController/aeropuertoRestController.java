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

import dad.vuelanding.model.Aeropuerto;
import dad.vuelanding.model.Reserva;
import dad.vuelanding.model.Usuario;
import dad.vuelanding.services.aeropuertoServices;
import dad.vuelanding.services.hotelServices;
import dad.vuelanding.services.reservaServices;
import dad.vuelanding.services.usuarioServices;
import dad.vuelanding.services.vueloServices;

@RestController
@RequestMapping("/api/aeropuertos")
public class aeropuertoRestController {
	
	@Autowired
	private aeropuertoServices service;
	
	@Autowired
	private reservaServices serviceR;
	
	@Autowired
	private hotelServices serviceh;
	
	@Autowired
	private vueloServices servicesv;
	
	@GetMapping("/")
	public List<Aeropuerto> getAeropuertos(){
		return service.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Aeropuerto> getAeropuerto(@PathVariable long id){
		Optional<Aeropuerto> ar = service.findById(id);
		if(ar.isPresent()) {
			Aeropuerto aeropuerto = ar.get();
			return new ResponseEntity<>(aeropuerto,HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public Aeropuerto createAeropuerto(@RequestBody Aeropuerto aeropuerto) {

		service.save(aeropuerto);

		return aeropuerto;
	}

	@PutMapping("/{id}")
	public ResponseEntity<Aeropuerto> updateAeropuerto(@PathVariable long id, @RequestBody Aeropuerto updateAeropuerto) {

		if (service.exist(id)) {

			updateAeropuerto.setId(id);
			service.save(updateAeropuerto);

			return new ResponseEntity<>(updateAeropuerto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Aeropuerto> deleteUser(@PathVariable long id) {
		
		if(service.exist(id)) {
			Usuario auxUsuario = service.findById(id).get();
			ArrayList<Reserva> reservaToDelete = serviceR.relatedWithUsuario(auxUsuario);
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
