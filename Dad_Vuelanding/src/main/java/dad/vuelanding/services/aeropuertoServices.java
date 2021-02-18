package dad.vuelanding.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dad.vuelanding.reposotories.aeropuertoRepository;

@Component
public class aeropuertoServices{
	
	@Autowired
	private aeropuertoRepository repositorioAeropuerto;
	
}
