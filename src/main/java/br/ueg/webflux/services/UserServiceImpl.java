package br.ueg.webflux.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import br.ueg.webflux.document.User;
import br.ueg.webflux.repository.UserRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserServiceImpl implements ReactiveUserDetailsService {

	@Autowired
	UserRepository userRepository;
	
	public Flux<User> findAll() {
		return userRepository.findAll();
	}

	public Mono<User> findById(String id) {
		return userRepository.findById(id);
	}

	public Mono<User> save(User user) {
		return userRepository.save(user);
	}

	@Override
	public Mono<UserDetails> findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

}
