package com.spandiar.security.springsecurityexercises;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class SpringSecurityExercisesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityExercisesApplication.class, args);
	}

}
