package com.cognizant.moviebookingapp.service.Impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cognizant.moviebookingapp.model.Movie;
import com.cognizant.moviebookingapp.model.Ticket;
import com.cognizant.moviebookingapp.repository.MovieRepository;
import com.cognizant.moviebookingapp.repository.TicketRepository;
import com.cognizant.moviebookingapp.service.TicketService;

/**
 * @author arvind 
 * todo--fix: add exception handling here: scope:--> invalid inputs
 */
@Service
public class TicketServiceImpl implements TicketService {

	@Autowired
	TicketRepository ticketRepo;

	@Autowired
	MovieRepository movieRepo;

	@Override
	public ResponseEntity<?> bookMovie(String movieName, Ticket ticket) {
		Optional<Movie> movie = movieRepo.findByMovieName(movieName);
		if (movie.isEmpty()) {
			return new ResponseEntity<>("Movie not found", HttpStatus.NOT_FOUND);
		}

		int availableTickets = movie.get().getTotalTickets();// from movie document
		int totalTicketsBuy = ticket.getNumberOfTickets();// from ticket object
		if (availableTickets <= 0) {
			return new ResponseEntity<>("All tickets sold out", HttpStatus.BAD_REQUEST);
		}
		if (availableTickets < totalTicketsBuy) {
			return new ResponseEntity<>("Insufficient tickets available", HttpStatus.BAD_REQUEST);
		}

		// Checking into movie DB if seat is booked or not
		  int bookedSeatsMovie = movie.get().getBookedSeats();// this is type of set<String> in movie model
		  

		// Update final ticket count and movie data
		int finalTicketCount = availableTickets - totalTicketsBuy;
		bookedSeatsMovie = bookedSeatsMovie+totalTicketsBuy;
		Ticket newTicket = new Ticket();
		newTicket.setNumberOfTickets(ticket.getNumberOfTickets());
		//newTicket.setSeatNumbers(ticket.getSeatNumbers());  
		//newTicket.setTransactionId(ticket.getTransactionId());
		newTicket.setMovieName(movieName);
		newTicket.setTheaterName(movie.get().getTheaterName());
		newTicket.setUserName(ticket.getUserName());// --fix-me planned

		//bookedSeatsMovie.addAll(seatNumbersTicket);
		movie.get().getTickets().add(newTicket);
		movie.get().setTotalTickets(finalTicketCount);// updating final count of ticket in movie db
		movie.get().setBookedSeats(bookedSeatsMovie);
		ticketRepo.save(newTicket);
		movieRepo.save(movie.get());

		return new ResponseEntity<>("Successfully booked movie " + movieName, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<?> getAllTickets() {
		try {
			List<Ticket> tickets = ticketRepo.findAll();
			return new ResponseEntity<>(tickets, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Error occurred while fetching tickets: " + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<?> getTicketsUser(String userId) {
		// todo: get ticket list for a specific user
		List<Ticket> userTickets = ticketRepo.findByUserName(userId);
		return new ResponseEntity<>(userTickets, HttpStatus.OK);
	}

	// for generating unique txn id
	public static String generateTransactionId() {
		final String PREFIX = "TXN-";
		final int RANDOM_BOUND = 100;
		// Generate a random number between 0 and 99
		Random random = new Random();
		int randomNumber = random.nextInt(RANDOM_BOUND);
		// Get the current timestamp
		LocalDateTime now = LocalDateTime.now();
		// Format the timestamp as a string
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
		String timestamp = now.format(formatter);
		String transactionId = PREFIX + timestamp + randomNumber;
		return transactionId;
	}

	// finding if seat is already there in movie document--intersection retailAll
	// only works with integer types
	private Set<String> findDuplicates(Set<String> set1, Set<String> set2) {
		Set<String> duplicates = new HashSet<>();
		for (String s : set1) {
			if (set2.contains(s)) {
				duplicates.add(s);
			}
		}
		return duplicates;
	}

	private Set<String> validateSeatNumbers(Set<String> seatNumbersTicket) {
		Set<String> invalidSeatNumbers = new HashSet<>();
		for (String seatNumber : seatNumbersTicket) {
			if (!seatNumber.matches("^[A-J]\\d{1,2}$")) { // Check format of seat number (e.g. A1, B2, etc.)
				invalidSeatNumbers.add(seatNumber);
			} else {
				int row = seatNumber.charAt(0) - 'A' + 1; // Convert row letter to number (A=1, B=2, etc.)
				int col = Integer.parseInt(seatNumber.substring(1)); // Get column number
				if (row < 1 || row > 10 || col < 1 || col > 10) {
					invalidSeatNumbers.add(seatNumber);// if user enters eg. A12
				}
			}
		}
		return invalidSeatNumbers;
	}

}
