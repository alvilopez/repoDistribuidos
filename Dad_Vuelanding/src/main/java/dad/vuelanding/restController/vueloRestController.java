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

import dad.vuelanding.services.vueloServices;
import dad.vuelanding.model.Vuelo;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/vuelo")
public class vueloRestController{

        @Autowired
        private vueloServices service;
        
        @GetMapping("/")
        public Collection<Vuelo> getvuelos() {
            return service.findAll();
        }

        @GetMapping("/{id}")
        public ResponseEntity<Vuelo> getvuelo(@PathVariable long id){
            Optional<Vuelo> op = service.findById(id);
            if (op.isPresent()){
                Vuelo vuelo = op.get();
                return new ResponseEntity<>(vuelo, HttpStatus.OK);
		    } else {
			    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		    }
        }

        @PostMapping("/")
	    @ResponseStatus(HttpStatus.CREATED)
	    public Vuelo createBook(@RequestBody Vuelo vuelo) {

		    service.save(vuelo);

		    return vuelo;
	    }

        @PutMapping("/{id}")
	    public ResponseEntity<Vuelo> updateBook(@PathVariable long id, @RequestBody Vuelo updatedvuelo) {

		    if (service.exist(id)) {

			    updatedvuelo.setId(id);
			    service.save(updatedvuelo);

			    return new ResponseEntity<>(updatedvuelo, HttpStatus.OK);
		    } else {
			    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		    }
        }

        @DeleteMapping("/{id}")
	    public ResponseEntity<Vuelo> deletevuelo(@PathVariable long id) {
	        try {
		        service.delete(id);
		        return new ResponseEntity<>(null, HttpStatus.OK);
	        } catch (EmptyResultDataAccessException e) {
		        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	        }
        }
}
