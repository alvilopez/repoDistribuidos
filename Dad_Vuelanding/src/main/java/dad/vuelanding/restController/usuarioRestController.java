package dad.vuelanding.restController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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

import dad.vuelanding.model.Reserva;
import dad.vuelanding.model.Usuario;
import com.mysql.cj.x.protobuf.MysqlxCrud.Collection;

import dad.vuelanding.services.reservaServices;
import dad.vuelanding.services.usuarioServices;


@RestController
@RequestMapping("/api/usuarios")
public class usuarioRestController {
	
	@Autowired
	private usuarioServices service;
	
	@Autowired
	private reservaServices serviceR;
	
	@GetMapping("/")
	public List<Usuario> getUsuarios(){
		return service.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> getUsuario(@PathVariable long id){
		Optional<Usuario> us = service.findById(id);
		if(us.isPresent()) {
			Usuario user = us.get();
			return new ResponseEntity<>(user,HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario createUser(@RequestBody Usuario user) {

		service.save(user);

		return user;
	}

	@PutMapping("/{id}")
	public ResponseEntity<Usuario> updateUser(@PathVariable long id, @RequestBody Usuario updateUser) {

		if (service.exist(id)) {

			updateUser.setId(id);
			service.save(updateUser);

			return new ResponseEntity<>(updateUser, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Usuario> deleteUser(@PathVariable long id) {
		
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
