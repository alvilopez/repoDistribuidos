/*package dad.services;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.squareup.okhttp.*;
import com.squareup.okhttp.OkHttpClient;
import dad.vuelanding.model.Billete;
import dad.vuelanding.reposotories.usuarioRepository;

@Controller
public class ApiBillete {
	
	@Autowired
	private usuarioRepository usuarioRepository;
	
	
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
	
	@RequestMapping("/imprimirBillete")
	public String imprimirBillete(Model model, long id_usuario, String mensaje,Authentication auth) {
		
		Billete billete = new Billete(mensaje);
		
		
			OkHttpClient client = new OkHttpClient();
			MediaType mediaType = MediaType.parse("application/json");
			RequestBody body = RequestBody.create(mediaType,"{/n/t Contenido:"+billete.getContenido());
			RequestBody body2 = RequestBody.create(mediaType, "{\n\t\"correo\":");
			Request request = new Request.Builder()
					.url("http://127.0.0.1:4444/ServicioBillete/enviarInformacionPDF")
					.post(body)
					.addHeader("Content-Type", "application/json")
					.addHeader("Cache-Control", "no-cache")
					.addHeader("Postman-Token", "1fae90e0-8c7e-48dd-9abe-1e1fdfdfd2a8")
					.build();
			System.out.println("Hace algo");
		try {
			Response response = client.newCall(request).execute();
		}catch (IOException e) {
			// TODO: handle exception
		}
		
		
		return "/usuario/usuario";
	}
}*/
