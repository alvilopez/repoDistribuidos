package dad.vuelanding.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dad.vuelanding.model.Aeropuerto;
import dad.vuelanding.model.Usuario;
import dad.vuelanding.reposotories.aeropuertoRepository;
import dad.vuelanding.reposotories.usuarioRepository;

@Component
public class aeropuertoServices{
	
	@Autowired
	private aeropuertoRepository repositorioAeropuerto;
	
    
    public Optional<Aeropuerto> findById(long id) {
		return repositorioAeropuerto.findById(id);
	}

	public boolean exist(long id) {
		return repositorioAeropuerto.existsById(id);
	}

	public List<Aeropuerto> findAll() {
		return repositorioAeropuerto.findAll();
	}

	public void save(Aeropuerto aeropuerto) {
		repositorioAeropuerto.save(aeropuerto);
	}

	public void delete(long id) {
		repositorioAeropuerto.deleteById(id);
	}	
}
