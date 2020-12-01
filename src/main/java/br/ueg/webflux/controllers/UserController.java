package br.ueg.webflux.controllers;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@PostMapping
	public Mono<User> saveUser(@RequestBody User user){
		return userServiceImpl.save(user);
	}
	
}
