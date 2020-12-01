package br.ueg.webflux.config;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
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
	
//	@Bean
//	public MapReactiveUserDetailsService userDetailsService() {
//		
//		PasswordEncoder passEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//		UserDetails admin = User.withUsername("admin")
//										.password(passEncoder.encode("admin"))
//										.roles("ADMIN", "USER")
//										.build();
//		
//		UserDetails user = User.withUsername("ricardo")
//										.password(passEncoder.encode("ricardo"))
//										.roles("USER")
//										.build();
//		
//		return new MapReactiveUserDetailsService(admin, user);
//	}
	
	@Bean
	ReactiveAuthenticationManager authenticationManager(UserServiceImpl userService) {
		return new UserDetailsRepositoryReactiveAuthenticationManager(userService);
	}
}
