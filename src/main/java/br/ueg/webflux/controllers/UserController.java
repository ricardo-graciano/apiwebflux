package br.ueg.webflux.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ueg.webflux.document.User;
import br.ueg.webflux.services.UserServiceImpl;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserServiceImpl userServiceImpl;
	
	@GetMapping()
	public Flux<User> getUsers(){
		return userServiceImpl.findAll();
	}
	
	@GetMapping("/{id}")
	public Mono<User> getUser(@PathVariable String id){
		return userServiceImpl.findById(id);
	}
	
	@PostMapping()
	public Mono<User> saveUser(@RequestBody User user){
		PasswordEncoder passEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();
	    List<String> rolesArray = user.getRolesArray();
		user.setPassword(passEncoder.encode(user.getPassword()));
		for (int i = 0; i < rolesArray.size(); i++) {
			roles.add(new SimpleGrantedAuthority(rolesArray.get(i)));
		}
		user.setRoles(roles);
		user.deleteJson();
		return userServiceImpl.save(user);
		
	}
	
}
