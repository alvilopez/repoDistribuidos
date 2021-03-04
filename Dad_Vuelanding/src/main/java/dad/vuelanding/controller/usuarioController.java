package dad.vuelanding.controller;

import java.util.ArrayList;
import java.util.HashSet;

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
	private HashSet<String> ciudades = new HashSet<String>();
	
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
		ciudades.add("Madrid");
		ciudades.add("Barcelona");
		ciudades.add("Londres");
		ciudades.add("Paris");
		
		Usuario us1 = new Usuario("Alvi", "Lopez Marcos", 21, "00000000L", "a@gmail.com", "1234");
		usuarioRepository.save(us1);
		
		Vuelo vl1 = new Vuelo("ML1", ap1, ap3);
		Vuelo vl2 = new Vuelo("ML2",ap1,ap3);
		Vuelo vl3 = new Vuelo("LM3", ap3, ap1);
		Vuelo vl4 = new Vuelo("LM4",ap3,ap1);
		vueloRepository.save(vl1);
		vueloRepository.save(vl2);
		vueloRepository.save(vl3);
		vueloRepository.save(vl4);
		
	}
	
	//Inicion Funciones Controlador Iniciar Sesion
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
			usuarioActual = test;
			return "vuelanding/pagina";
		}else{
			return "usuario/errorUsuario";
		}
	}
	//Fin Funciones Controlador Inicar Sesion
	
	//Inicio Funciones Controlador Aplicacion
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
	public String mostrarVuelosCiudades(String origen,String destino,Model model) {
		System.out.println(origen+destino);
		
		if(ciudades.contains(origen)&&ciudades.contains(destino)) {
			Aeropuerto aeropuertoOrigen = aeropuertoRepository.findByCiudad(origen);
			ArrayList<Vuelo> auxArray = vueloRepository.findByAeropouertoSalida(aeropuertoOrigen);
			ArrayList<Vuelo> vuelos = new ArrayList<Vuelo>();
			for (Vuelo v : auxArray) {
				if(v.getAeropuertoLlegada().getCiudad().equalsIgnoreCase(destino)) vuelos.add(v);
			}
			model.addAttribute("vuelos",vuelos);
			return "/vuelanding/buscarVuelo_MostrarVuelos";
 		}
		
		return "vuelanding/buscarVuelo";
	}
	
	@PostMapping("/buscarVueloVuelta")
	public String reservarVuelo(String codigo,Model model) {
		if(vueloRepository.findByCodigo(codigo)!= null) {
			Aeropuerto aeropuertoOrigen = vueloRepository.findByCodigo(codigo).getAeropuertoLlegada();
			
			ArrayList<Vuelo> auxArray = vueloRepository.findByAeropouertoSalida(aeropuertoOrigen);
			System.out.println(auxArray.size());
			ArrayList<Vuelo> vuelos = new ArrayList<Vuelo>();
			for (Vuelo v : auxArray) {
				
				if(v.getAeropuertoLlegada().getCiudad().equalsIgnoreCase(vueloRepository.findByCodigo(codigo).getAeropouertoSalida().getCiudad())) vuelos.add(v);
			}
			model.addAttribute("vuelos",vuelos);
			return "/vuelanding/reservar";
		}else {
			return "/vuelanding/errorReservar";
		}
	}
	
	@GetMapping("/reservar")
	public String reservar(String codigo,Model model) {
		
		return"/vuelading/reservaCorrecta";
	}

	@GetMapping("/informacionPersonal")
	public String informacionPersonal(Model model){
		model.addAttribute("usuario", usuarioActual);
		return "vuelanding/informacionPersonal";
	}

	//Fin Funciones Controlador Aplicacion
}