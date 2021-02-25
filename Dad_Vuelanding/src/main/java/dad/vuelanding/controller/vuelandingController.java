package dad.vuelanding.controller;

import java.util.ArrayList;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dad.vuelanding.model.Aeropuerto;
import dad.vuelanding.reposotories.aeropuertoRepository;
import dad.vuelanding.reposotories.hotelRepository;
import dad.vuelanding.reposotories.reservaRepository;
import dad.vuelanding.reposotories.usuarioRepository;
import dad.vuelanding.reposotories.vueloRepository;

@Controller
public class vuelandingController {
	
	@Autowired
	private usuarioRepository usuarioRepository;
	@Autowired
	private hotelRepository usuarioRepository2;
	@Autowired
	private reservaRepository usuarioRepository3;
	@Autowired
	private vueloRepository usuarioRepository4;
	@Autowired
	private aeropuertoRepository aeropuertoRepository;
	
	@GetMapping("/pagina")
	public String usuario(){
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
		
		return "/vuelanding/buscarVuelo_MostrarVuelos";
	}
}
