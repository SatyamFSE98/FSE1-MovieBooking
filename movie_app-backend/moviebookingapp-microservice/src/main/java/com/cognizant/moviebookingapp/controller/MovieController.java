package com.cognizant.moviebookingapp.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.moviebookingapp.exception.AuthorizationException;
//import com.cognizant.moviebookingapp.kafka.KafkaProducer;
//import com.cognizant.moviebookingapp.kafka.KafkaProducer;
import com.cognizant.moviebookingapp.model.Movie;
import com.cognizant.moviebookingapp.model.Ticket;
import com.cognizant.moviebookingapp.service.MovieService;
import com.cognizant.moviebookingapp.service.TicketService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1.0/moviebooking")
public class MovieController {

	@Autowired
	MovieService movieService;

	@Autowired
	TicketService ticketService;

	@Autowired
	AuthService authService;

//	@Autowired
//	private KafkaProducer stringProducer;
	
	@Autowired
	private HttpServletRequest httpServletRequest;

	

	/// role:admin
	@PostMapping("/addmovie")
	@Operation(summary = "add movie into db(admin)")
	public ResponseEntity<?> addMovies(@RequestBody Movie movie) {
		
		return movieService.addMovie(movie);
	}

	@GetMapping("/getAllMovies")
	@Operation(summary = "get all available movies(admin+customer)")
	public ResponseEntity<List<Movie>> getAllMovies(){
//			stringProducer.sendMessage("Getting Movies");
     		return movieService.getAllMovies();
	

	}

	@GetMapping("/movies/search/{movieId}")
	@Operation(summary = "searching movie by it(admin+customer)")
	public ResponseEntity<?> searchMovieById(@PathVariable(value = "movieId") Long movieId){
			
			return movieService.searchMovieById(movieId);
    	}

	

	@DeleteMapping("/delete/{movieId}")
	@Operation(summary = "for deletion of movie(admin)")
	public ResponseEntity<?> deleteMovieById(@PathVariable Long movieId)
			
		 {

			return movieService.deleteMovie(movieId);
           }

	@GetMapping("/getAllTickets")
	@Operation(summary = "for listing all the tickets booked by users(admin)")
	public ResponseEntity<?> getAllTickets() {
			return ticketService.getAllTickets();
            }

	@PostMapping("/book/{movieName}")
	@Operation(summary = "for book a ticket for a movie(admin+customer)")
	public ResponseEntity<?> bookMovie(@PathVariable(value = "movieName") String movieName, @RequestBody Ticket ticket){
         return ticketService.bookMovie(movieName, ticket);


	}

	@GetMapping("/getUserTickets/{userName}") // no need of userId here--fix--if user is already logged in
	@Operation(summary = "for customer to see the booked tickets for a movie(admin+customer)")
	public ResponseEntity<?> getTicketsByUserId(@PathVariable(value = "userName") String userName){
			
			return ticketService.getTicketsUser(userName);
              }


	
	@GetMapping("/get/bookedSeats/{movieName}")
	@Operation(summary = "for getting the booked seats for a movie(admin+customer)")
	public ResponseEntity<?> getBookedTicketList(@PathVariable(value = "movieName") String movieName) {

		return movieService.getBookedTicketList(movieName);


	}

}
