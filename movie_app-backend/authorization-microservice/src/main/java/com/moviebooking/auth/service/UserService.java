package com.moviebooking.auth.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.moviebooking.auth.model.User;
import com.moviebooking.auth.payload.LoginRequest;
import com.moviebooking.auth.payload.ResetRequest;
import com.moviebooking.auth.payload.SignupRequest;

public interface UserService {

	public ResponseEntity<?> addUser(SignupRequest signupRequest);

	// this will work like a authentication manager for validating user
	public boolean loginUser(LoginRequest loginRequest);

	public List<User> getAllUsers();
	
	//replicating spring inbuit class--fix here
	public Optional<User> getUserByUsername(String username);
	
	public ResponseEntity<?> updatePassword(ResetRequest resetRequest);

}
