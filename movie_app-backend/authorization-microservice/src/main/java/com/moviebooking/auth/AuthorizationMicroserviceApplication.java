package com.moviebooking.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.moviebooking.auth.model.ERole;

@SpringBootApplication
public class AuthorizationMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthorizationMicroserviceApplication.class, args);
		System.out.println(ERole.ROLE_ADMIN);
		System.out.println(ERole.ROLE_ADMIN.toString());
	}

}
