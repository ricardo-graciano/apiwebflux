package br.ueg.webflux.services;

import br.ueg.webflux.document.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {

	Flux<User> findAll();
	Mono<User> findById(String id);
	Mono<User> save (User user);
	Mono<User> findByUsername(String userName);
}
