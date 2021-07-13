package ar.edu.unju.fi.tpfinal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import ar.edu.unju.fi.tpfinal.service.imp.LoginUsserServiceImp;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private AutenticationSuccessHandler autentication;
	
	String[] resources =new String[] {
			"/include/**","/css/**","/icons/**","/img/**","/js/**","/layer/**","/webjars/**"
	};
	

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
			.antMatchers(resources).permitAll()
			.antMatchers("/","/logout","/index").permitAll()
			.antMatchers("/office/**","/employee/**").hasAuthority("Administrator")
			//.antMatchers("/producto").hasAuthority("administrador")
			//.antMatchers("/cargarRegistro/","/buscarPersona","/cargarPersona", "/guardarCondicion", "/").hasAuthority("CLIENTE")
			.anyRequest().authenticated()
			.and()
			.exceptionHandling().accessDeniedPage("/")
			.and()
		.formLogin()				
			.loginPage("/login").permitAll()
			//.defaultSuccessUrl("/index")
			.successHandler(autentication)				
			.failureUrl("/login?error=true")
			.usernameParameter("username")
			.passwordParameter("password")
			.and()
		.logout()
			.permitAll()
			.logoutUrl("/logout")
			.logoutSuccessUrl("/login");	
			//http.csrf().disable();
	}	
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(4);
	}
	
	@Autowired
	LoginUsserServiceImp userDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());

	}

}
