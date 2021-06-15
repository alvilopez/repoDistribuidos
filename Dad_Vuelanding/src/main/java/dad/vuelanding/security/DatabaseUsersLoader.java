
//Usar cada vez que se dropea el esquema en mySql

package dad.vuelanding.security;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import dad.vuelanding.model.Usuario;
import dad.vuelanding.reposotories.usuarioRepository;

@Component
public class DatabaseUsersLoader {

    @Autowired
    private usuarioRepository userRepository;
    
    @Autowired
	private PasswordEncoder passwordEncoder;

    @PostConstruct
    private void initDatabase() {
    	
    	userRepository.save(new Usuario("user", passwordEncoder.encode("pass"), "USER"));
		userRepository.save(new Usuario("admin", passwordEncoder.encode("adminpass"), "USER", "ADMIN"));
    }
}
