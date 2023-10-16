package com.cognizant.moviebookingapp.service;

import org.springframework.http.ResponseEntity;

import com.cognizant.moviebookingapp.model.Ticket;

public interface TicketService {
	
	public ResponseEntity<?> bookMovie(String movieName,Ticket ticket);
	
	public ResponseEntity<?> getAllTickets();
	
	public ResponseEntity<?> getTicketsUser(String userId);//todo--after auth

}
