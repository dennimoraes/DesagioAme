package br.com.ame.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ame.domain.Planet;
import br.com.ame.service.PlanetService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/planet")
public class PlanetController {

	@Autowired
	PlanetService planetService;

	@GetMapping("/")
	public Flux<Planet> getPlanets() {

		return planetService.findAll();
	}

	@GetMapping("/name/{name}")
	public Mono<Planet> getPlanetByName(@PathVariable String name) {
		
		return planetService.findByName(name);
	}

	@GetMapping("/{id}")
	public Mono<Planet> getPlanetById(@PathVariable String id) {

		return planetService.findById(id);
	}

	@PostMapping("/")
	public Mono<Planet> create(@RequestBody Planet planet) {

		return planetService.save(planet);
	}

	@DeleteMapping("/{id}")
	public Mono<Void> delete(@PathVariable String id) {

		return planetService.delete(id);
	}

}
