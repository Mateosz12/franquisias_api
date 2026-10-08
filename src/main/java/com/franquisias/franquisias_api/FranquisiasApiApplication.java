package com.franquisias.franquisias_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.franquisias")
@EnableMongoRepositories(basePackages = "com.franquisias.repository")
public class FranquisiasApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FranquisiasApiApplication.class, args);
	}

}
