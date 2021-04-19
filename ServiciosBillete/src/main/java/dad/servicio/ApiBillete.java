package dad.servicio;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mailjet.client.ClientOptions;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.MailjetResponse;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;
import com.mailjet.client.resource.Emailv31;


import org.springframework.web.bind.annotation.ResponseStatus;



@RestController
public class ApiBillete {
	
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
	
	
	@RequestMapping(value="/ServicioBillete/enviarInformacionPDF",method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.CREATED)
	public void getUser(@RequestBody Billete billete) throws JSONException{
		System.out.println(billete.toString());
		System.out.println("HOLIWIs");
		MailjetClient client;
		MailjetRequest request;
		MailjetResponse response;
		client = new MailjetClient("cc6a41628b7f05daa9ca653bde779ea1", "cd294e4d31df1d422df5e0c8cf23120c", new ClientOptions("v3.1"));
		request = new MailjetRequest(Emailv31.resource)
				.property(Emailv31.MESSAGES, new JSONArray()
						.put(new JSONObject()
								.put("Email", "prueba")));
		try {
			response = client.post(request);
			System.out.println(response.getStatus());
			System.out.println(response.getData());
		}catch (MailjetException e) {
			e.printStackTrace();
		}catch (MailjetSocketTimeoutException e) {
			e.printStackTrace();
		}
	}
	
}
