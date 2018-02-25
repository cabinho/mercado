package com.mercado.creditos.creditos.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("joao").password("joao").roles("CADASTRAR_CREDITO").and()
			.withUser("maria").password("maria").roles("CADASTRAR_CREDITO", "LISTAR_CREDITO",
					 "ALTERAR_CREDITO", "EXCLUIR_CREDITO");
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
			.antMatchers("/layout/**");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/creditos/novo").hasRole("CADASTRAR_CREDITO")
				.antMatchers("/creditos**").hasRole("LISTAR_CREDITO")
				.antMatchers("/creditos**").hasRole("ALTERAR_CREDITO")
				.antMatchers("/creditos**").hasRole("EXCLUIR_CREDITO")
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login")
				.permitAll()
				.and()
			.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
	}
	
}
