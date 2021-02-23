package dad.vuelanding.controller;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;



import dad.vuelanding.model.Usuario;
import dad.vuelanding.reposotories.aeropuertoRepository;
import dad.vuelanding.reposotories.hotelRepository;
import dad.vuelanding.reposotories.reservaRepository;
import dad.vuelanding.reposotories.usuarioRepository;
import dad.vuelanding.reposotories.vueloRepository;


@Controller
public class usuarioController {
	
	@Autowired
	private usuarioRepository usuarioRepository;
	@Autowired
	private hotelRepository usuarioRepository2;
	@Autowired
	private reservaRepository usuarioRepository3;
	@Autowired
	private vueloRepository usuarioRepository4;
	@Autowired
	private aeropuertoRepository usuarioRepository5;
	
	@PostConstruct
	public void Init() {
		
	}
	
	
	@GetMapping("/usuario")
	public String usuario(){
		return "usuario/usuario";
	}

	@GetMapping("/errorUsuario")
	public String errorUsuario(){
		return "usuario/errorUsuario";
	}

	@GetMapping("/confirmacionRegistro")
	public String confirmacionRegistro(){
		return "usuario/confirmacionRegistro";
	}
	
	@PostMapping("/usuario/nuevo")
	public String nuevoUsuario (Model model, Usuario aux) {
		System.out.println(aux.getName());
		return "usuario/usuario";
	}
	
	@GetMapping("/saluda")
	public String saluda(Model model) {
		model.addAttribute("nombre","Spring application");
		System.out.println("Hola");
		return "helloWorld";
	}
	
	@GetMapping("/nuevousuario")
	public String nuevoUsuario(){
		return "usuario/nuevoUsuario";
	}
	
	@PostMapping("/usuario/crearUsuario")
	public String crearUsuario(Model model, Usuario aux) {
		System.out.println(aux.getName());
		return "usuario/ConfirmacionUsuario";
	}
	
}
