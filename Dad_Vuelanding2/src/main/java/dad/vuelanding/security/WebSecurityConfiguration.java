package dad.vuelanding.security;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;



@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	RepositoryUserDetailsService userDetailsService;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(10, new SecureRandom());
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		// Public pages
		
		http.authorizeRequests().antMatchers("/usuario").permitAll();
		http.authorizeRequests().antMatchers("/usuario/nuevo").permitAll();
		http.authorizeRequests().antMatchers("/nuevousuario").permitAll();
		http.authorizeRequests().antMatchers("/errorUsuario").permitAll();
		http.authorizeRequests().antMatchers("/confirmacionRegistro").permitAll();
		http.authorizeRequests().antMatchers("/usuario/crearUsuario").permitAll();
		http.authorizeRequests().antMatchers("/usuario/login").permitAll();
		http.authorizeRequests().antMatchers("/aeropuerto/crear").permitAll();
		
		// Private pages
		http.authorizeRequests().antMatchers("/pagina").hasAnyRole("USER","ADMIN");
		http.authorizeRequests().antMatchers("/buscarVuelo").hasAnyRole("USER","ADMIN");
		http.authorizeRequests().antMatchers("/buscarVuelo/ViajesEntreCiudades").hasAnyRole("USER","ADMIN");
		http.authorizeRequests().antMatchers("/buscarVueloHotel").hasAnyRole("USER");
		http.authorizeRequests().antMatchers("/buscarVuelo/ViajesEntreCiudades/ViajeHotel").hasAnyRole("USER","ADMIN");
		http.authorizeRequests().antMatchers("/reservar/ViajeHotel").hasAnyRole("USER","ADMIN");
		http.authorizeRequests().antMatchers("/informacionPersonal").hasAnyRole("USER","ADMIN");
		http.authorizeRequests().antMatchers("/informacionReservas").hasAnyRole("USER","ADMIN");
		http.authorizeRequests().antMatchers("/imprimirBillete").hasAnyRole("USER","ADMIN");
		http.authorizeRequests().antMatchers("/ServicioBillete/enviarInformacionPDF").hasAnyRole("USER","ADMIN");
		
		
		http.authorizeRequests().antMatchers("/aeropuerto/anadir").hasAnyRole("ADMIN");
		//http.authorizeRequests().antMatchers("/aeropuerto/crear").hasAnyRole("ADMIN","USER");
		http.authorizeRequests().antMatchers("aeropuerto/eliminar").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers("/aeropuerto/eliminar/datos").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers("/hotel").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers("/hotel/anadir").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers("/hotel/crear").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers("/hotel/eliminar").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers("/hotel/eliminar/datos").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers("/vuelo").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers("/vuelo/anadir").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers("/vuelo/crear").hasAnyRole("ADMIN");
		
		
		// Login form
		http.formLogin().loginPage("/usuario");
		http.formLogin().usernameParameter("name");
		http.formLogin().passwordParameter("password");
		http.formLogin().defaultSuccessUrl("/pagina");
		http.formLogin().failureUrl("/errorUsuario");
		
		//http.cors().disable();
		// Logout
		/*http.logout().logoutUrl("/logout");
		http.logout().logoutSuccessUrl("/usuario");*/
		
		
	}

}
