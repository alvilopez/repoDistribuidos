package dad.vuelanding.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.Principal;
import java.security.SecureRandom;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dad.vuelanding.model.Aeropuerto;
import dad.vuelanding.model.Billete;
import dad.vuelanding.model.Hotel;
import dad.vuelanding.model.Reserva;
import dad.vuelanding.model.Usuario;
import dad.vuelanding.model.Vuelo;
import dad.vuelanding.reposotories.aeropuertoRepository;
import dad.vuelanding.reposotories.hotelRepository;
import dad.vuelanding.reposotories.reservaRepository;
import dad.vuelanding.reposotories.usuarioRepository;
import dad.vuelanding.reposotories.vueloRepository;


@CrossOrigin
@Controller
public class usuarioController {
	
	private Usuario usuarioActual = new Usuario();
	private Reserva reservaActual = new Reserva();
	private Hotel vacio = new Hotel("","0");
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
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	/*@PostConstruct
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
		
		vacio.setAeropuerto(null);
		Hotel h1 = new Hotel("La bahia", "5");
		Hotel h4 = new Hotel("London Hotel", "5");
		Hotel h3 = new Hotel("Madrid Palace", "5");
		Hotel h2 = new Hotel("El venao", "5");
		h1.setAeropuerto(ap1);
		h2.setAeropuerto(ap2);
		h3.setAeropuerto(ap3);
		h4.setAeropuerto(ap4);
		hotelRepository.save(h1);
		hotelRepository.save(h2);
		hotelRepository.save(h3);
		hotelRepository.save(h4);
		hotelRepository.save(vacio);
		
		Vuelo vl1 = new Vuelo("ML1", ap1, ap3);
		vl1.setFechaSalida(Date.valueOf("2021-05-08"));
		vl1.setFechaLlegada(Date.valueOf("2021-05-08"));
		Vuelo vl2 = new Vuelo("ML2",ap1,ap3);
		vl2.setFechaSalida(Date.valueOf("2021-05-09"));
		vl2.setFechaLlegada(Date.valueOf("2021-05-08"));
		Vuelo vl3 = new Vuelo("LM3", ap3, ap1);
		vl3.setFechaSalida(Date.valueOf("2021-05-10"));
		vl3.setFechaLlegada(Date.valueOf("2021-05-08"));
		Vuelo vl4 = new Vuelo("LM4",ap3,ap1);
		vl4.setFechaSalida(Date.valueOf("2021-05-11"));
		vl4.setFechaLlegada(Date.valueOf("2021-05-08"));
		vueloRepository.save(vl1);
		vueloRepository.save(vl2);
		vueloRepository.save(vl3);
		vueloRepository.save(vl4);
		
	}*/
	
	@ModelAttribute
	public void addAttributes(Model model, HttpServletRequest request) {

		Principal principal = request.getUserPrincipal();

		if(principal != null) {
		
			model.addAttribute("logged", true);		
			model.addAttribute("userName", principal.getName());
			model.addAttribute("admin", request.isUserInRole("ADMIN"));
			
		} else {
			model.addAttribute("logged", false);
		}
	}
	
	
	/*@RequestMapping("/usuario")
	public String home(Model model, HttpServletRequest request) {

	 model.addAttribute("admin", request.isUserInRole("ADMIN"));
	 return "usuario/usuario";
	}*/

	 
	
	//Inicion Funciones Controlador Iniciar Sesion
	@GetMapping("/usuario")
	public String usuario(){
		return "Usuario/usuario";
	}

	@GetMapping("/errorUsuario")
	public String errorUsuario(){
		return "Usuario/errorUsuario";
	}

	@GetMapping("/confirmacionRegistro")
	public String confirmacionRegistro(){
		return "Usuario/confirmacionRegistro";
	}
	
	@PostMapping("/usuario/nuevo")
	public String nuevoUsuario (Model model, Usuario aux, HttpServletRequest request) {
		System.out.println(aux.getName());
		return "Usuario/usuario";
	}
	
	@GetMapping("/saluda")
	public String saluda(Model model) {
		model.addAttribute("nombre","Spring application");
		System.out.println("Hola");
		return "helloWorld";
	}
	
	@GetMapping("/nuevousuario")
	public String nuevoUsuario(){
		return "Usuario/nuevousuario";
	}
	
	@PostMapping("/usuario/crearUsuario")
	public String crearUsuario(Model model, Usuario aux) {
		System.out.println(aux.getName());
		ArrayList<String> rolUsuario = new ArrayList<>();
		rolUsuario.add("USER");
		aux.setRoles(rolUsuario);
		aux.encodePassword();
		usuarioRepository.save(aux);
		return "Usuario/confirmacionRegistro";
	}
	
	@PostMapping("usuario/login")
	public String loginUsuario(Model model, Usuario aux){
		
		if (aux==null){
			return "Usuario/errorUsuario";
		}
	
		Usuario test = usuarioRepository.findByName(aux.getName());
		if (test != null && passwordEncoder.matches(aux.getPassword(),test.getPassword())){
			usuarioActual = test;
			return "Vuelanding/pagina";
		}else{
			return "Usuario/errorUsuario";
		}
	}
	//Fin Funciones Controlador Inicar Sesion
	
	//Inicio Funciones Controlador Aplicacion
	@GetMapping("/pagina")
	public String iniciarAplicacion(){
		return "Vuelanding/pagina";
	}
	
	@GetMapping("/buscarVuelo")
	public String buscarVuelo(Model model){
		ArrayList<Aeropuerto> aeropuertos = aeropuertoRepository.findAllByOrderByNombre();
		System.out.println(aeropuertos.size());
		model.addAttribute("aeropuertos",aeropuertos);
		
		return "Vuelanding/buscarVuelo";
	}
	
	//-----------------------------------------------------------------------------------
	@PostMapping("/buscarVuelo/ViajesEntreCiudades")
	public String mostrarVuelosCiudades(String origen,String destino,Model viajesIdaModel, Model viajesVueltaModel ) {
		System.out.println(origen+destino);
		System.out.println(aeropuertoRepository.existsByCiudad(origen));
		
		if(aeropuertoRepository.existsByCiudad(origen)&&aeropuertoRepository.existsByCiudad(destino)) {
			Aeropuerto aeropuertoOrigen = aeropuertoRepository.findByCiudad(origen);
			ArrayList<Vuelo> auxArray = vueloRepository.findByAeropouertoSalida(aeropuertoOrigen);
			ArrayList<Vuelo> vuelos = new ArrayList<Vuelo>();
			for (Vuelo v : auxArray) {
				if(v.getAeropuertoLlegada().getCiudad().equalsIgnoreCase(destino)) vuelos.add(v);
			}
			System.out.println("Algo");
			viajesIdaModel.addAttribute("vuelos",vuelos);
			
			
			
			Aeropuerto aeropuertoVuelta = aeropuertoRepository.findByCiudad(destino);
			ArrayList<Vuelo> auxArray2 = vueloRepository.findByAeropouertoSalida(aeropuertoVuelta);
			ArrayList<Vuelo> vuelos2 = new ArrayList<Vuelo>();
			for (Vuelo v : auxArray2) {
				if(v.getAeropuertoLlegada().getCiudad().equalsIgnoreCase(origen)) vuelos2.add(v);
			}
			System.out.println(vuelos2.size());
			viajesVueltaModel.addAttribute("vuelos2",vuelos2);
			
			return "Vuelanding/buscarVueloAux";
 		}
		
		return "Vuelanding/errorReservar";
	}
	
	@PostMapping("/reservar")
	public String reservar(String codigoIda,String codigoVuelta, Model model,HttpServletRequest request) {
		if (vueloRepository.findByCodigo(codigoIda) != null && vueloRepository.findByCodigo(codigoVuelta)!= null) {
			Reserva reservaAux = new Reserva();
			reservaAux.setIda(vueloRepository.findByCodigo(codigoIda));
			reservaAux.setVuelta(vueloRepository.findByCodigo(codigoVuelta));
			Hotel vacio = hotelRepository.findByName("");
			reservaAux.setHotel(vacio);
			Usuario usuario = usuarioRepository.findByName(request.getRemoteUser());
			reservaAux.setUsuario(usuario);

			reservaRepository.save(reservaAux);
			model.addAttribute("reservaActual",reservaAux);
			return "Vuelanding/reservar";
		} else {
			return "Vuelanding/errorReservar";
		}
	}
	
	//-------------------------------------------------------------------------------------------------------------------

	//Funciones Viaje Mas Hotel
	
	@GetMapping("/buscarVueloHotel")
	public String buscarVueloHotel(Model model){
		ArrayList<Aeropuerto> aeropuertos = aeropuertoRepository.findAllByOrderByNombre();
		model.addAttribute("aeropuertos",aeropuertos);
		return "Vuelanding/buscarVueloHotel";
	}
	
	@PostMapping("/buscarVuelo/ViajesEntreCiudades/ViajeHotel")
	public String mostrarVuelosCiudadesHotel(String origen,String destino,Model viajesIdaModel, Model viajesVueltaModel, Model hoteles ) {
		System.out.println(origen+destino);
		
		if(aeropuertoRepository.existsByCiudad(origen)&&aeropuertoRepository.existsByCiudad(destino)) {
			Aeropuerto aeropuertoOrigen = aeropuertoRepository.findByCiudad(origen);
			ArrayList<Vuelo> auxArray = vueloRepository.findByAeropouertoSalida(aeropuertoOrigen);
			ArrayList<Vuelo> vuelos = new ArrayList<Vuelo>();
			for (Vuelo v : auxArray) {
				if(v.getAeropuertoLlegada().getCiudad().equalsIgnoreCase(destino)) vuelos.add(v);
			}
			System.out.println("Algo");
			viajesIdaModel.addAttribute("vuelos",vuelos);
			
			Aeropuerto aeropuertoVuelta = aeropuertoRepository.findByCiudad(destino);
			ArrayList<Vuelo> auxArray2 = vueloRepository.findByAeropouertoSalida(aeropuertoVuelta);
			ArrayList<Vuelo> vuelos2 = new ArrayList<Vuelo>();
			for (Vuelo v : auxArray2) {
				if(v.getAeropuertoLlegada().getCiudad().equalsIgnoreCase(origen)) vuelos2.add(v);
			}
			System.out.println(vuelos2.size());
			viajesVueltaModel.addAttribute("vuelos2",vuelos2);
	 		
			ArrayList<Hotel> hotelesL = new ArrayList<>();
			hotelesL = hotelRepository.findByAeropuerto(aeropuertoVuelta);
			hoteles.addAttribute("hoteles",hotelesL);
			
			return "Vuelanding/buscarVueloHotelAux";
 		}
		
		return "Vuelanding/errorReservar";
	}
	
	@PostMapping("/reservar/ViajeHotel")
	public String reservarViajeMasHotel( Model model,String codigoIda,String codigoVuelta,String hotel,HttpServletRequest request) {
		if (vueloRepository.findByCodigo(codigoIda) != null && vueloRepository.findByCodigo(codigoVuelta)!= null) {
			reservaActual = new Reserva();
			reservaActual.setIda(vueloRepository.findByCodigo(codigoIda));
			reservaActual.setVuelta(vueloRepository.findByCodigo(codigoVuelta));
			reservaActual.setHotel(hotelRepository.findByName(hotel));
			Usuario aux = usuarioRepository.findByName(request.getRemoteUser());
			reservaActual.setUsuario(aux);
			
			reservaRepository.save(reservaActual);
			model.addAttribute("reservaActual",reservaActual);
			return "Vuelanding/reservar";
		} else {
			return "Vuelanding/errorReservar";
		}
	}
	
	
	
	
	//Fin Funciones Viaje Mas Hotel
	
	@GetMapping("/informacionPersonal")
	public String informacionPersonal(Model model,HttpServletRequest request){
		Usuario usuario = usuarioRepository.findByName(request.getRemoteUser());
		model.addAttribute("usuario", usuario);
		return "Vuelanding/informacionPersonal";
	}

	@GetMapping("/informacionReservas")
	public String informacionReservas(Model model, HttpServletRequest request){
		ArrayList<Reserva> reservas = new ArrayList<>();
		Usuario usuario = usuarioRepository.findByName(request.getRemoteUser());
		reservas = (reservaRepository.findByUsuario(usuario));
		model.addAttribute("reservas", reservas);
		return "Vuelanding/informacionReserva";
	}

	//Fin Funciones Controlador Aplicacion
	//Funcoines añadir informacion a la base de datos
	
	//Aeropuertos
	
	@GetMapping("/aeropuerto")
	public String aeropuerto(HttpServletRequest request) {
		System.out.println("HOLAOLAOALAOALAOAL");
		/*String name = request.getUserPrincipal().getName();
		
		try {
			Usuario user = usuarioRepository.findByName(name);
		} catch (Exception e) {
			// TODO: handle exception
		}*/
		
		return "Aeropuertos/aeropuertos";
	}
	
	@GetMapping("/aeropuerto/anadir")
	public String aeropuertoDatos() {
		return "Aeropuertos/datosAeropuertos";
	}
	
	@PostMapping("/aeropuerto/crear")
	public String aeropuertoCrear(Aeropuerto aeropuertos) {
		System.out.println(aeropuertos.getCiudad());
		if(aeropuertos.getCiudad()=="" || aeropuertos.getCodigo()=="" || aeropuertos.getNombre() == "") {
			return "errorDatos";
		}
		aeropuertoRepository.save(aeropuertos);
		ciudades.add(aeropuertos.getCiudad());
		return "Aeropuertos/creado";
	}
	
	@GetMapping("aeropuerto/eliminar")
	public String aeropuertoEliminar(Model model) {
		
		model.addAttribute("aeropuertos", aeropuertoRepository.findAllByOrderByNombre());
		return "Aeropuertos/datosEliminarAeropuerto";
	}
	
	@PostMapping("/aeropuerto/eliminar/datos")
	public String aeropuertoEliminado(String ciudad) {
		if(aeropuertoRepository.findByCiudad(ciudad)==null) {
			return "errorDatos";
		}
		
		Aeropuerto  aux = aeropuertoRepository.findByCiudad(ciudad);
		
		//Eliminar Vuelo Hoteles y reservas relacionadas con el aeropuerto
		
		ArrayList<Hotel> hotelesEliminar = hotelRepository.findByAeropuerto(aux);
		ArrayList<Vuelo> vuelosEliminarIda = vueloRepository.findByAeropouertoSalida(aux); 
		ArrayList<Vuelo> vuelosEliminarVuelta = vueloRepository.findByAeropuertoLlegada(aux);;
		HashSet<Reserva> reservasEliminar = new HashSet<Reserva>();
		for(Hotel h:hotelesEliminar) reservasEliminar.add(reservaRepository.findByHotel(h));
		for(Vuelo v:vuelosEliminarIda) reservasEliminar.add(reservaRepository.findByIda(v));
		for(Vuelo v:vuelosEliminarVuelta) reservasEliminar.add(reservaRepository.findByVuelta(v));
		reservasEliminar.remove(null);
		
		if(!reservasEliminar.isEmpty())for(Reserva r:reservasEliminar)reservaRepository.delete(r);
		if (!hotelesEliminar.isEmpty()) for(Hotel h:hotelesEliminar) hotelRepository.delete(h);
		if (!vuelosEliminarIda.isEmpty()) for(Vuelo v:vuelosEliminarIda) vueloRepository.delete(v);
		if (!vuelosEliminarVuelta.isEmpty()) for(Vuelo v:vuelosEliminarVuelta) vueloRepository.delete(v);
		
		aeropuertoRepository.delete(aux);
		return "Aeropuertos/eliminado";
	}
	
	//Hoteles
	
	@GetMapping("/hotel")
	public String hotel() {
		return "Hoteles/hotel";
	}
	
	@GetMapping("/hotel/anadir")
	public String hotelDatos(Model model) {
		ArrayList<Aeropuerto> auxL = aeropuertoRepository.findAllByOrderByNombre();
		model.addAttribute("aeropuertos", auxL);
		return "Hoteles/datosHotel";
	}
	
	@PostMapping("/hotel/crear")
	public String hotelCrear(Hotel hotel) {
		System.out.println(aeropuertoRepository.findByCiudad(hotel.getAeropuertoAux()).getCiudad());
		if(hotel.getName()=="" || hotel.getEstrellas()=="" || aeropuertoRepository.findByCiudad(hotel.getAeropuertoAux())==null) {
			return "errorDatos";
		}
		Aeropuerto auxAeropuerto2 = aeropuertoRepository.findByCiudad(hotel.getAeropuertoAux());
		hotel.setAeropuerto(auxAeropuerto2);
		hotelRepository.save(hotel);
		return "Hoteles/creadoH";
	}
	
	@GetMapping("hotel/eliminar")
	public String hotelEliminar(Model model) {
		
		model.addAttribute("hoteles", hotelRepository.findAllByOrderByName());
		return "Hoteles/datosHotelEliminar";
	}
	
	@PostMapping("/hotel/eliminar/datos")
	public String hotelEliminado(String name) {
		System.out.println(name);
		if(hotelRepository.findByName(name)==null) {
			return "errorDatos";
		}
		Hotel aux = hotelRepository.findByName(name);
		
		ArrayList<Reserva> reservasEliminar =reservaRepository.findAllByHotel(aux);
		reservasEliminar.remove(null);
		if(!reservasEliminar.isEmpty())for(Reserva r:reservasEliminar)reservaRepository.delete(r);
		
		hotelRepository.delete(aux);
		return "Hoteles/eliminadoH";
	}
	//Fin Funcoines añadir informacion a la base de datos
	
	
	
	

	// Funciones control de vuelos
	@GetMapping("/vuelo")
	public String vuelo() {
		return "Vuelos/vuelos";
	}

	@GetMapping("/vuelo/anadir")
	public String vueloDatos(Model model) {
		ArrayList<Aeropuerto> auxL = aeropuertoRepository.findAllByOrderByNombre();
		model.addAttribute("aeropuertos", auxL);
		return "Vuelos/datosVuelos";
	}
	
	@PostMapping("/vuelo/crear")
	public String vueloCrear(String codigo, java.sql.Date fechaSalida, java.sql.Date fechaLlegada, String aeropuertoSalida, String aeropuertoLlegada) {

		if(codigo=="" || fechaLlegada== null || fechaSalida == null || aeropuertoLlegada == "" || aeropuertoLlegada == "" ) {
			if(aeropuertoRepository.findByCiudad(aeropuertoSalida)==null || aeropuertoRepository.findByCiudad(aeropuertoLlegada)==null) {
				return "errorDatos";
			}
			return "errorDatos";
		}
		Aeropuerto llegada, salida;
		
		llegada = (aeropuertoRepository.findByCiudad(aeropuertoSalida));
		salida = (aeropuertoRepository.findByCiudad(aeropuertoLlegada));
		
		System.out.println(aeropuertoLlegada);
		System.out.println(aeropuertoSalida);
		System.out.println(llegada.getCiudad());
		System.out.println(salida.getCiudad());
		Vuelo vuelo = new Vuelo(salida,llegada,fechaSalida,fechaLlegada,codigo);

		vueloRepository.save(vuelo);
		
		return "Vuelos/creado";
	}
	
	
	
	
	
	@RequestMapping("/imprimirBillete2/{id}")
	public String imprimirBillete2(Model model,Authentication auth,@PathVariable long id) {
		try {
			Socket socket = new Socket("server",4444);
			
			PrintWriter escribirServer = new PrintWriter(socket.getOutputStream(),true);
			Reserva billete = reservaRepository.findById(id).get();
			String informacion = billete.toString();

			escribirServer.print(informacion);
			escribirServer.close();
			socket.close();
			
			socket = new Socket("server",4444);
			BufferedReader leerServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String linea = leerServer.readLine();
			String disco = "D:\\HashiCorp\\Vagrant\\bin\\" + linea; 
			model.addAttribute("url",disco);;
			leerServer.close();
			socket.close();
		
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "servicio/imprimir";
	}
	
	
	
	

}