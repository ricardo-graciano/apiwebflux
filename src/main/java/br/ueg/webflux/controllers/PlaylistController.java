package br.ueg.webflux.controllers;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.ueg.webflux.document.Playlist;
import br.ueg.webflux.services.PlaylistService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@RestController
public class PlaylistController {

	@Autowired
	PlaylistService playlistService;
	
	@GetMapping("/playlists")
	public Flux<Playlist> getPlaylists(){
		return playlistService.findAll()
				.switchIfEmpty(monoResponseStatusNotFound());
	}
	
	@GetMapping("/playlists/{id}")
	public Mono<Playlist> getPlaylist(@PathVariable String id){
		return playlistService.findById(id)
				.switchIfEmpty(monoResponseStatusNotFound());
	}
	
	@PostMapping("/playlists")
	public Mono<Playlist> savePlaylist(@RequestBody Playlist playlist){
		return playlistService.save(playlist);
	}
	
	@GetMapping(value="/playlists/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Tuple2<Long, Playlist>> getPlaylistByEvents(){

		Flux<Long> interval = Flux.interval(Duration.ofSeconds(5));
        Flux<Playlist> events = playlistService.findAll();
        System.out.println("Passou aqui events");
        return Flux.zip(interval, events);
        
	}
	
	public <T> Mono<T> monoResponseStatusNotFound(){
		return Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Playlist not found"));
	}
}
