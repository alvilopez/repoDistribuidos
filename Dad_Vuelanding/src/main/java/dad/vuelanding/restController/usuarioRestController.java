package dad.vuelanding.restController;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/usuarios")
public class usuarioRestController {
	
	@Autowired
	private usuarioRepository usuarioRepository;
	
	
}
