package dad.vuelanding.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import dad.vuelanding.model.Usuario;

@Controller
public class usuarioController {
	
	
	@GetMapping("/usuario")
	public String usuario(){
		return "usuario.mustache";
	}
	
	@PostMapping("/usuario/nuevo")
	public String nuevoUsuario (Model model, Usuario aux) {
		System.out.println(aux.getName());
		return "usuario.mustache";
	}
	
	
	
	
}
