package com.service.BVHSHOP;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
//******** enabled audit **********
@EnableJpaAuditing
public class BvhshopApplication {

	public static void main(String[] args) {
		SpringApplication.run(BvhshopApplication.class, args);
	}

}
