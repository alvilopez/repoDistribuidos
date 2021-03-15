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
import dad.vuelanding.model.Hotel;
import dad.vuelanding.model.Reserva;
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
	
	@PostConstruct
	public void Init() {
		/*Aeropuerto ap1 = new Aeropuerto("Madrid", "BAR", "Barajas");
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
		*/
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
		if (test != null && test.getPassword().equals(aux.getPassword())){
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
	
	//-----------------------------------------------------------------------------------
	@PostMapping("/buscarVuelo/ViajesEntreCiudades")
	public String mostrarVuelosCiudades(String origen,String destino,Model viajesIdaModel, Model viajesVueltaModel ) {
		System.out.println(origen+destino);
		
		if(ciudades.contains(origen)&&ciudades.contains(destino)) {
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
			
			return "/vuelanding/buscarVueloAux";
 		}
		
		return "vuelanding/errorReservar";
	}
	
	@PostMapping("/reservar")
	public String reservar(String codigoIda,String codigoVuelta, Model model) {
		if (vueloRepository.findByCodigo(codigoIda) != null && vueloRepository.findByCodigo(codigoVuelta)!= null) {
			Reserva reservaAux = new Reserva();
			reservaAux.setIda(vueloRepository.findByCodigo(codigoIda));
			reservaAux.setVuelta(vueloRepository.findByCodigo(codigoVuelta));
			Hotel vacio = hotelRepository.findByName("");
			reservaAux.setHotel(vacio);
			Usuario aux = usuarioRepository.findByName(usuarioActual.getName());
			reservaAux.setUsuario(aux);

			reservaRepository.save(reservaAux);
			return "/vuelanding/reservar";
		} else {
			return "/vuelanding/errorReservar";
		}
	}
	
	//-------------------------------------------------------------------------------------------------------------------

	//Funciones Viaje Mas Hotel
	
	@GetMapping("/buscarVueloHotel")
	public String buscarVueloHotel(Model model){
		ArrayList<Aeropuerto> aeropuertos = aeropuertoRepository.findAllByOrderByNombre();
		model.addAttribute("aeropuertos",aeropuertos);
		return "vuelanding/buscarVueloHotel";
	}
	
	@PostMapping("/buscarVuelo/ViajesEntreCiudades/ViajeHotel")
	public String mostrarVuelosCiudadesHotel(String origen,String destino,Model viajesIdaModel, Model viajesVueltaModel, Model hoteles ) {
		System.out.println(origen+destino);
		
		if(ciudades.contains(origen)&&ciudades.contains(destino)) {
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
			
			return "/vuelanding/buscarVueloHotelAux";
 		}
		
		return "vuelanding/errorReservar";
	}
	
	@PostMapping("/reservar/ViajeHotel")
	public String reservarViajeMasHotel(String codigoIda,String codigoVuelta,String hotel, Model model) {
		if (vueloRepository.findByCodigo(codigoIda) != null && vueloRepository.findByCodigo(codigoVuelta)!= null) {
			reservaActual = new Reserva();
			reservaActual.setIda(vueloRepository.findByCodigo(codigoIda));
			reservaActual.setVuelta(vueloRepository.findByCodigo(codigoVuelta));
			reservaActual.setHotel(hotelRepository.findByName(hotel));
			Usuario aux = usuarioRepository.findByName(usuarioActual.getName());
			reservaActual.setUsuario(aux);
			
			reservaRepository.save(reservaActual);
			System.out.println(reservaActual.getUsuario());
			return "/vuelanding/reservar";
		} else {
			return "/vuelanding/errorReservar";
		}
	}
	
	
	
	
	//Fin Funciones Viaje Mas Hotel
	
	@GetMapping("/informacionPersonal")
	public String informacionPersonal(Model model){
		model.addAttribute("usuario", usuarioActual);
		return "vuelanding/informacionPersonal";
	}

	@GetMapping("/informacionReservas")
	public String informacionReservas(Model model){
		ArrayList<Reserva> reservas = new ArrayList<>();
		Usuario aux = usuarioRepository.findByName(usuarioActual.getName());
		reservas = (reservaRepository.findByUsuario(aux));
		model.addAttribute("reservas", reservas);
		return "vuelanding/informacionReserva";
	}

	//Fin Funciones Controlador Aplicacion
	//Funcoines añadir informacion a la base de datos
	
	//Aeropuertos
	
	@GetMapping("/aeropuerto")
	public String aeropuerto() {
		return "aeropuertos/aeropuertos";
	}
	
	@GetMapping("/aeropuerto/anadir")
	public String aeropuertoDatos() {
		return "aeropuertos/datosAeropuertos";
	}
	
	@PostMapping("/aeropuerto/crear")
	public String aeropuertoCrear(Aeropuerto aeropuertos) {
		System.out.println(aeropuertos.getCiudad());
		if(aeropuertos.getCiudad()=="" || aeropuertos.getCodigo()=="" || aeropuertos.getNombre() == "") {
			return "errorDatos";
		}
		aeropuertoRepository.save(aeropuertos);
		ciudades.add(aeropuertos.getCiudad());
		return "/aeropuertos/creado";
	}
	
	@GetMapping("aeropuerto/eliminar")
	public String aeropuertoEliminar(Model model) {
		
		model.addAttribute("aeropuertos", aeropuertoRepository.findAllByOrderByNombre());
		return "aeropuertos/datosEliminarAeropuerto";
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
		return "aeropuertos/eliminado";
	}
	
	//Hoteles
	
	@GetMapping("/hotel")
	public String hotel() {
		return "hoteles/hotel";
	}
	
	@GetMapping("/hotel/anadir")
	public String hotelDatos(Model model) {
		ArrayList<Aeropuerto> auxL = aeropuertoRepository.findAllByOrderByNombre();
		model.addAttribute("aeropuertos", auxL);
		return "hoteles/datosHotel";
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
		return "/hoteles/creadoH";
	}
	
	@GetMapping("hotel/eliminar")
	public String hotelEliminar(Model model) {
		
		model.addAttribute("hoteles", hotelRepository.findAllByOrderByName());
		return "hoteles/datosHotelEliminar";
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
		return "hoteles/eliminadoH";
	}
	//Fin Funcoines añadir informacion a la base de datos
	
	
	
	

	// Funciones control de vuelos
	@GetMapping("/vuelo")
	public String vuelo() {
		return "vuelos/vuelos";
	}

	@GetMapping("/vuelo/anadir")
	public String vueloDatos() {
		return "vuelos/datosVuelos";
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
		
		return "/vuelos/creado";
	}

}