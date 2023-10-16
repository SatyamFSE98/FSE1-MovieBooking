package com.moviebooking.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moviebooking.auth.exception.InvalidInputException;
import com.moviebooking.auth.security.JwtUtils;

@RestController
@RequestMapping("/api/test")
public class TestController {

	@Autowired
	JwtUtils jwtUtils;

	@GetMapping("/all")
	public String allAccess() {
		return "Public Content.";
	}

	@GetMapping("/user")
	public String userAccess(@RequestHeader("Authorization") String token) {
		validateToken(token);

		// Check if the user has the required role
		if (hasRequiredRole(token, "ROLE_CUSTOMER") || hasRequiredRole(token, "ROLE_ADMIN")) {
			return "User Content.";
		} else {
			throw new InvalidInputException("User does not have the required role.");
		}
	}

	@GetMapping("/admin")
	public String adminAccess(@RequestHeader("Authorization") String token) {
		validateToken(token);

		// Check if the user has the required role
		if (hasRequiredRole(token, "ROLE_ADMIN")) {
			return "admin Content.";
		} else {
			throw new InvalidInputException("User does not have the required role.");
		}
	}

	private void validateToken(String token) {
		if (!jwtUtils.validateJwtToken(token)) {
			throw new InvalidInputException("Invalid token.");
		}
	}

	private boolean hasRequiredRole(String token, String requiredRole) {
		String authToken = token.substring(7);
		String roleFromToken = jwtUtils.getRoleFromToken(authToken);
		return roleFromToken.contains(requiredRole);
	}

}