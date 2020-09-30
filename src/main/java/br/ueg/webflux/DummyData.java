package br.ueg.webflux;

import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.ueg.webflux.document.Playlist;
import br.ueg.webflux.repository.PlaylistRepository;
import reactor.core.publisher.Flux;

//@Component
//public class DummyData implements CommandLineRunner{
//
//	private final PlaylistRepository playlistRepository;
//	
//	DummyData(PlaylistRepository playlistRepository){
//		this.playlistRepository = playlistRepository;
//	}
//	
//	@Override
//	public void run(String... args) throws Exception {
//		playlistRepository.deleteAll()
//		.thenMany(
//				Flux.just("API REST Spring Boot", "Deploy de uma aplicação java no IBM Cloud", "Java 8",
//                      "Github", "Spring Security", "Web Service RESTFULL", "Bean no Spring Framework")
//					.map(nome -> new Playlist(UUID.randomUUID().toString(), nome))
//					.flatMap(playlistRepository::save))
//		.subscribe(System.out::println);
//	}
//}
