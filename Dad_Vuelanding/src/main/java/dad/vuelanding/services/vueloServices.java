package dad.vuelanding.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import dad.vuelanding.model.Vuelo;
import dad.vuelanding.reposotories.vueloRepository;

public class vueloServices {
    @Autowired
	private vueloRepository repository;

	public Optional<Vuelo> findById(long id) {
		return repository.findById(id);
	}

	public boolean exist(long id) {
		return repository.existsById(id);
	}

	public List<Vuelo> findAll() {
		return repository.findAll();
	}

	public void save(Vuelo vuelo) {
		repository.save(vuelo);
	}

	public void delete(long id) {
		repository.deleteById(id);
	}
}
