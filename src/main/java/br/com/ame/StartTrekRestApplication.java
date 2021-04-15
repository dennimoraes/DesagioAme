package br.com.ame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class StartTrekRestApplication {

	//private static final Logger log = LoggerFactory.getLogger(ConsumingRestApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(StartTrekRestApplication.class, args);
	}
	
	@Bean
	public WebClient createWebClient() {
	    return WebClient.create();
	}

	/*@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			
			Pagination pagination = restTemplate.getForObject(
					"https://swapi.dev/api/planets/", Pagination.class);
			log.info(pagination.toString());
		};
	}*/
}
