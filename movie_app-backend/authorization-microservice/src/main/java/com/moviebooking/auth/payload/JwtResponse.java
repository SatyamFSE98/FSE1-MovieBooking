package com.moviebooking.auth.payload;

import java.util.Set;

import com.moviebooking.auth.model.Role;

public class JwtResponse {
	private String token;
	private Long id;
	private String username;
	private String email;
	private Set<Role> roles;

	public JwtResponse(String accessToken, Long long1, String username, String email, Set<Role> set) {
		this.token = accessToken;
		this.id = long1;
		this.username = username;
		this.email = email;
		this.roles = set;
	}

	public String getAccessToken() {
		return token;
	}

	public void setAccessToken(String accessToken) {
		this.token = accessToken;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Set<Role> getRoles() {
		return roles;
	}
}
