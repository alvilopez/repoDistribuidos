package dad.servicio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;




public interface usuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByNif(String nif);
    List<Usuario> findAllByOrderByNifAsc();
    Usuario findByName(String name);
    
}
