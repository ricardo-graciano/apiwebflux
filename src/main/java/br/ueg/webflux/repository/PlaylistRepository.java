package br.ueg.webflux.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import br.ueg.webflux.document.Playlist;

public interface PlaylistRepository extends ReactiveMongoRepository<Playlist, String> {

}
