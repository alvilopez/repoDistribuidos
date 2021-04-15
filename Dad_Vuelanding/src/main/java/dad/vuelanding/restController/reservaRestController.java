package dad.vuelanding.restController;



import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import dad.vuelanding.services.reservaServices;
import dad.vuelanding.model.Reserva;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/reserva")
public class reservaRestController{

        @Autowired
        private reservaServices service;
        
        @GetMapping("/")
        public Collection<Reserva> getReservas() {
            return service.findAll();
        }

        @GetMapping("/{id}")
        public ResponseEntity<Reserva> getReserva(@PathVariable long id){
            Optional<Reserva> op = service.findById(id);
            if (op.isPresent()){
                Reserva reserva = op.get();
                return new ResponseEntity<>(reserva, HttpStatus.OK);
		    } else {
			    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		    }
        }

        @PostMapping("/")
	    @ResponseStatus(HttpStatus.CREATED)
	    public Reserva createBook(@RequestBody Reserva reserva) {

		    service.save(reserva);

		    return reserva;
	    }

        @PutMapping("/{id}")
	    public ResponseEntity<Reserva> updateBook(@PathVariable long id, @RequestBody Reserva updatedReserva) {

		    if (service.exist(id)) {

			    updatedReserva.setId(id);
			    service.save(updatedReserva);

			    return new ResponseEntity<>(updatedReserva, HttpStatus.OK);
		    } else {
			    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		    }
        }

        @DeleteMapping("/{id}")
	    public ResponseEntity<Reserva> deleteReserva(@PathVariable long id) {
	        try {
		        service.delete(id);
		        return new ResponseEntity<>(null, HttpStatus.OK);
	        } catch (EmptyResultDataAccessException e) {
		        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	        }
        }
}





