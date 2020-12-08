package br.ueg.webflux.config;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

import br.ueg.webflux.services.UserServiceImpl;

@EnableWebFluxSecurity
public class SecurityConfig {

	@Bean
	public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
		return http
				.csrf().disable()
				.authorizeExchange()
				.pathMatchers(HttpMethod.POST, "/**").hasRole("ADMIN")
				.pathMatchers(HttpMethod.GET, "/**").hasRole("USER")
				.anyExchange().authenticated()
				.and()
					.formLogin()
				.and()
					.httpBasic()
				.and()
					.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	@Bean
	ReactiveAuthenticationManager authenticationManager(UserServiceImpl userService, PasswordEncoder passwordEncoder) {
		PasswordEncoder passEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		UserDetailsRepositoryReactiveAuthenticationManager authenticationManager = new UserDetailsRepositoryReactiveAuthenticationManager(userService);
		authenticationManager.setPasswordEncoder(passEncoder);
	    return authenticationManager;
	}
}
