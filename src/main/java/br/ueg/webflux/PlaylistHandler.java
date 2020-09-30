package br.ueg.webflux;

import static org.springframework.web.reactive.function.BodyInserters.fromPublisher;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import br.ueg.webflux.document.Playlist;
import br.ueg.webflux.services.PlaylistService;
import reactor.core.publisher.Mono;

//@Component
public class PlaylistHandler {
	
	@Autowired
	PlaylistService playlistService;
	
	public Mono<ServerResponse> findAll(ServerRequest request){
		return ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(playlistService.findAll(), Playlist.class);
				
	}
	
	public Mono<ServerResponse> findById(ServerRequest request){
		String id = request.pathVariable("id");
		return ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(playlistService.findById(id), Playlist.class);
				
	}
	
	public Mono<ServerResponse> save(ServerRequest request){
		final Mono<Playlist> playlist = request.bodyToMono(Playlist.class);
		return ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(fromPublisher(playlist.flatMap(playlistService::save), Playlist.class));
				
	}
}
