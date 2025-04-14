package com.sistema.bella_face;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class BellaFaceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BellaFaceApplication.class, args);
	}

}
