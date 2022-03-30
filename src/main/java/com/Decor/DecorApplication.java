package com.Decor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com.*")
@EntityScan("com.Decor.entities")
@EnableJpaRepositories("com.Decor.repos")
public class DecorApplication {

	public static void main(String[] args) {
		SpringApplication.run(DecorApplication.class, args);
	}

}
