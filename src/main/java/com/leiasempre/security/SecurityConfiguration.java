package com.leiasempre.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.leiasempre.service.AutenticacaoService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private AutenticacaoService autenticacaoService;

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();

	}

	@Override
	public void configure(AuthenticationManagerBuilder builder) throws Exception {
		
		//Código para criar usuários autenticados manualmente
		/*builder.inMemoryAuthentication().withUser("user01").password(passwordEncoder().encode("12345")).roles("ADMIN")
				.and().withUser("user02").password(passwordEncoder().encode("12345")).roles("USER").and()
				.withUser("user03").password(passwordEncoder().encode("12345")).roles("ADMIN", "USER");*/
	builder.inMemoryAuthentication().withUser("adm").password(passwordEncoder().encode("123")).roles("ADMIN");
	builder.authenticationProvider(AuthenticationProvider());
	
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// http.authorizeRequests().antMatchers("/").permitAll();

		// A configuração "permitAll" deve vim antes de utilizar os parâmetros de
		// Autenticação
		http.authorizeRequests().antMatchers("/h2/**").permitAll();
		http.authorizeRequests().antMatchers("/novoUsuario").permitAll();
		http.authorizeRequests().antMatchers("/salvarUsuario").permitAll();
		http.authorizeRequests().antMatchers("/esqueciSenha").permitAll();

		http.authorizeRequests().anyRequest().authenticated().and().formLogin().loginPage("/login")
				.defaultSuccessUrl("/").permitAll().and().logout().logoutUrl("/logout").logoutSuccessUrl("/login");

		http.csrf().disable();

		http.headers().frameOptions().disable(); // Permitir ou não o uso de frames dentro da aplicação.
	}

	// Configurações de recursos estáticos(js, css, imagens, etc.)
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/static/**"); // Igonorar tudo dentro da pasta "static".
	}

	@Bean
	public DaoAuthenticationProvider AuthenticationProvider() {
		DaoAuthenticationProvider autenticador = new DaoAuthenticationProvider();
		autenticador.setUserDetailsService(autenticacaoService);
		autenticador.setPasswordEncoder(passwordEncoder());
		return autenticador;
	}

}
