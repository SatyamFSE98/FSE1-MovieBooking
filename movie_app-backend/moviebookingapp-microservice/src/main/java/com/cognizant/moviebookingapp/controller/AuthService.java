package com.cognizant.moviebookingapp.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.cognizant.moviebookingapp.exception.InvalidInputException;

@Component
public class AuthService {

	@Value("${auth.service.url}")
	private String baseUrl;

	private RestTemplate restTemplate = new RestTemplate();

	public Map<String, String> validateToken(String token) {
	    HttpEntity<String> entity = getHttpEntityWithHeaders(token);
	    try {
	        ResponseEntity<Map<String, String>> response = restTemplate.exchange(baseUrl + "/validate", HttpMethod.POST, entity,
	            new ParameterizedTypeReference<Map<String, String>>() {});
	        System.err.println(response.getBody());
	        if (response.getStatusCode() == HttpStatus.OK) {
	            return response.getBody();
	        } else {
	            throw new InvalidInputException("Failed to validate token: " + response.getStatusCode());
	        }
	    } catch (Exception e) {
	        throw new InvalidInputException("connection refused: " + e.getMessage());
	    }
	}


	private HttpEntity<String> getHttpEntityWithHeaders(String token) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", token);
		return new HttpEntity<>("", headers);
	}
}
