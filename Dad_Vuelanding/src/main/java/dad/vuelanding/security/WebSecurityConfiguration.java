package dad.vuelanding.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		
		
		// Public pages
		http.authorizeRequests().antMatchers("/usuario").permitAll();
		http.authorizeRequests().antMatchers("/usuario/nuevo").permitAll();
		http.authorizeRequests().antMatchers("/nuevousuario").permitAll();
		http.authorizeRequests().antMatchers("/errorUsuario").permitAll();
		http.authorizeRequests().antMatchers("/confirmacionRegistro").permitAll();
		http.authorizeRequests().antMatchers("usuario/crearUsuario").permitAll();
		http.authorizeRequests().antMatchers("usuario/login").permitAll();

		
		/*http.authorizeRequests().antMatchers("/loginerror").permitAll();
		http.authorizeRequests().antMatchers("/logout").permitAll();*/

		// Private pages
		/*http.authorizeRequests().antMatchers("/pagina").authenticated();
		http.authorizeRequests().antMatchers("/buscarVuelo").authenticated();
		http.authorizeRequests().antMatchers("/buscarVuelo/ViajesEntreCiudades").authenticated();
		http.authorizeRequests().antMatchers("/buscarVueloHotel").authenticated();
		http.authorizeRequests().antMatchers("/buscarVuelo/ViajesEntreCiudades/ViajeHotel").authenticated();
		http.authorizeRequests().antMatchers("/reservar/ViajeHotel").authenticated();
		http.authorizeRequests().antMatchers("/informacionPersonal").authenticated();
		http.authorizeRequests().antMatchers("/informacionReservas").authenticated();
		http.authorizeRequests().antMatchers("/aeropuerto").authenticated();
		http.authorizeRequests().antMatchers("/aeropuerto/anadir").authenticated();
		http.authorizeRequests().antMatchers("/aeropuerto/crear").authenticated();
		http.authorizeRequests().antMatchers("aeropuerto/eliminar").authenticated();
		http.authorizeRequests().antMatchers("/aeropuerto/eliminar/datos").authenticated();
		http.authorizeRequests().antMatchers("/hotel").authenticated();
		http.authorizeRequests().antMatchers("/hotel/anadir").authenticated();
		http.authorizeRequests().antMatchers("/hotel/crear").authenticated();
		http.authorizeRequests().antMatchers("/hotel/eliminar").authenticated();
		http.authorizeRequests().antMatchers("/hotel/eliminar/datos").authenticated();
		http.authorizeRequests().antMatchers("/vuelo").authenticated();
		http.authorizeRequests().antMatchers("/vuelo/anadir").authenticated();
		http.authorizeRequests().antMatchers("/vuelo/crear").authenticated();*/
		
		
		//

		// Login form
		http.formLogin().loginPage("/usuario");
		http.formLogin().usernameParameter("name");
		http.formLogin().passwordParameter("password");
		http.formLogin().defaultSuccessUrl("/pagina");
		http.formLogin().failureUrl("/errorUsuario");

		// Logout
		/*http.logout().logoutUrl("/logout");
		http.logout().logoutSuccessUrl("/usuario");*/
		
		http.csrf().disable();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		String encodedPassword = encoder.encode("pass");
		auth.inMemoryAuthentication().withUser("user").password(encodedPassword).roles("USER");
	}

}
