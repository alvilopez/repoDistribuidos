package dad.vuelanding.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import dad.vuelanding.model.Usuario;
import dad.vuelanding.reposotories.usuarioRepository;


public class usuarioServices {
    @Autowired
    private usuarioRepository usuarioRepository;
    
    public Optional<Usuario> findById(long id) {
		return usuarioRepository.findById(id);
	}

	public boolean exist(long id) {
		return usuarioRepository.existsById(id);
	}

	public List<Usuario> findAll() {
		return usuarioRepository.findAll();
	}

	public void save(Usuario usuario) {
		usuarioRepository.save(usuario);
	}

	public void delete(long id) {
		usuarioRepository.deleteById(id);
	}
}
