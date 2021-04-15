package br.com.ame.service;

import br.com.ame.domain.Planet;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PlanetService {
	
	Mono<Planet> findByName(final String name);
	
	Mono<Planet> findById(final String id);
	
	Flux<Planet> findAll();
	
	Mono<Planet> save(Planet planet);
	
	Mono<Void> delete(String id);

}
