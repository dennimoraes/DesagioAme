package br.com.ame.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import br.com.ame.domain.Pagination;
import br.com.ame.domain.Planet;
import br.com.ame.repository.PlanetRepository;
import br.com.ame.service.PlanetService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
public class PlanetServiceImpl implements PlanetService {
	
	@Autowired
	private PlanetRepository planetRepository;
	
	private WebClient webClient;
	
	@Autowired
	public PlanetServiceImpl(WebClient webClient) {
		this.webClient = webClient;
	}
	

	@Override
	public Flux<Planet> findAll() {
		return planetRepository.findAll();
	}

	@Override
	public Mono<Planet> save(Planet planet) {
		return planetRepository.findByName(planet.getName()).onErrorResume(fallback -> Mono.empty()).switchIfEmpty(planetRepository.save(planet));
	}

	@Override
	public Mono<Void> delete(String id) {
		return planetRepository.deleteById(id)
				.onErrorResume(fallback -> Mono.error(new RuntimeException()));
		
		//Mono.just(handleError(throwable))
	}

	@Override
	public Mono<Planet> findByName(String name) {
		return planetRepository.findByName(name).onErrorResume(fallback -> Mono.empty()).switchIfEmpty(getPlanetFromApi(name));
	}

	@Override
	public Mono<Planet> findById(String id) {
		return planetRepository.findById(id);
	}
	
	private Mono<Planet> getPlanetFromApi(String name) {
		return getFluxPagination().filter(planet -> name.equals(planet.getName())).next()
				.doOnNext(planet -> Flux.just(planet).parallel(2).runOn(Schedulers.elastic()).flatMap(p -> this.save(p))
				.subscribe(p -> System.out.println("Persisting Planet -> " + p.getName())));
	}
	
	/*private Mono<Planet> persistPlanetParallel(String name) {
		return getPlanetFromApi(name)
				.doOnNext(planet -> Flux.just(planet).parallel(2).runOn(Schedulers.elastic()).flatMap(p -> this.save(p))
				.subscribe(p -> System.out.println("Persisting Planet -> " + p.getName())));
	}*/
	
	private Flux<Planet> getFluxPagination(){
		return webClient.get().uri("https://swapi.dev/api/planets/").exchange().retry(2).flatMap(page -> page.bodyToMono(Pagination.class))
				.map(page -> page.getResults())
				.flatMapMany(page -> Flux.fromIterable(page));
	}

}
