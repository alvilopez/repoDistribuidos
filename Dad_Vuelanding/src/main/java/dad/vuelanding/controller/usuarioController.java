package dad.vuelanding.controller;

import java.util.ArrayList;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import dad.vuelanding.model.Aeropuerto;
import dad.vuelanding.model.Usuario;
import dad.vuelanding.model.Vuelo;
import dad.vuelanding.reposotories.aeropuertoRepository;
import dad.vuelanding.reposotories.hotelRepository;
import dad.vuelanding.reposotories.reservaRepository;
import dad.vuelanding.reposotories.usuarioRepository;
import dad.vuelanding.reposotories.vueloRepository;


@Controller
public class usuarioController {
	
	private Usuario usuarioActual = new Usuario();
	
	@Autowired
	private usuarioRepository usuarioRepository;
	@Autowired
	private hotelRepository hotelRepository;
	@Autowired
	private reservaRepository reservaRepository;
	@Autowired
	private vueloRepository vueloRepository;
	@Autowired
	private aeropuertoRepository aeropuertoRepository;
	
	@PostConstruct
	public void Init() {
		Aeropuerto ap1 = new Aeropuerto("Madrid", "BAR", "Barajas");
		Aeropuerto ap2 = new Aeropuerto("Barcelona", "PRT", "El Prat");
		Aeropuerto ap3 = new Aeropuerto("Londres","LON","Londres Airport");
		Aeropuerto ap4 = new Aeropuerto("Paris","FRP","France Port");
		aeropuertoRepository.save(ap1);
		aeropuertoRepository.save(ap2);
		aeropuertoRepository.save(ap3);
		aeropuertoRepository.save(ap4);
		
		Usuario us1 = new Usuario("Pablo", "1234");
		usuarioRepository.save(us1);
		
		Vuelo vl1 = new Vuelo("ML1", ap1, ap3);
		Vuelo vl2 = new Vuelo("ML2",ap1,ap3);
		vueloRepository.save(vl1);
		vueloRepository.save(vl2);
		
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
	
	@PostMapping("usuario/crearUsuario")
	public String crearUsuario(Model model, Usuario aux) {
		System.out.println(aux.getName());
		usuarioRepository.save(aux);
		return "Usuario/confirmacionRegistro";
	}
	
	@PostMapping("usuario/login")
	public String loginUsuario(Model model, Usuario aux){
		if (aux==null){
			return "usuario/errorUsuario";
		}
		
		Usuario test = usuarioRepository.findByName(aux.getName());
		if (test.getPassword().equals(aux.getPassword())){
			usuarioActual = aux;
			return "vuelanding/pagina";
		}else{
			return "usuario/errorUsuario";
		}
	}
	
	@GetMapping("/pagina")
	public String iniciarAplicacion(){
		return "vuelanding/pagina";
	}
	
	@GetMapping("/buscarVuelo")
	public String buscarVuelo(Model model){
		ArrayList<Aeropuerto> aeropuertos = aeropuertoRepository.findAllByOrderByNombre();
		System.out.println(aeropuertos.size());
		model.addAttribute("aeropuertos",aeropuertos);
		
		return "vuelanding/buscarVuelo";
	}
	
	@PostMapping("/buscarVuelo/ViajesEntreCiudades")
	public String mostrarVuelosCiudades(String origen,String destino) {
		System.out.println(origen+destino);
		Aeropuerto aeropuerto = aeropuertoRepository.findByNombre(origen);
		ArrayList<Vuelo> auxArray = vueloRepository.findByAeropouertoSalida(aeropuerto);
		return "/vuelanding/buscarVuelo_MostrarVuelos";
	}
}