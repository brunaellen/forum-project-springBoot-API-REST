package br.com.alura;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ForumAluraApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForumAluraApplication.class, args);
	}
}
