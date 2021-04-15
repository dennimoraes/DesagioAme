package br.com.ame.repository;

import org.springframework.data.repository.reactive.ReactiveSortingRepository;

import br.com.ame.domain.Planet;
import reactor.core.publisher.Mono;

public interface PlanetRepository extends ReactiveSortingRepository<Planet, String> {
	
	Mono<Planet> findByName(final String name);

}
