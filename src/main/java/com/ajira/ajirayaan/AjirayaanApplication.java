package com.ajira.ajirayaan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class AjirayaanApplication {

	public static void main(String[] args) {
		SpringApplication.run(AjirayaanApplication.class, args);
	}

}
