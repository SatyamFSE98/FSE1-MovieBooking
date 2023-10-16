//package com.cognizant.moviebookingapp;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.when;
//
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.Set;
//
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import com.cognizant.moviebookingapp.controller.MovieController;
//import com.cognizant.moviebookingapp.exception.AuthorizationException;
//import com.cognizant.moviebookingapp.model.Movie;
//import com.cognizant.moviebookingapp.model.Ticket;
//import com.cognizant.moviebookingapp.repository.MovieRepository;
//import com.cognizant.moviebookingapp.service.MovieService;
//import com.cognizant.moviebookingapp.service.TicketService;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class MoviebookingappMicroserviceApplicationTests {
//	
//	@MockBean
//	private MovieRepository movieRepository;  
//	
//	@MockBean
//	private MovieService movieService;
//	
//	@MockBean
//	private TicketService ticketService;
//	
//	@Autowired
//	private MovieController movieController;
//
//	@Test
//	public void contextLoads() {
//		assertEquals(1,1);
//	}
//	
//	@Test
//	public void testAddMovie() throws AuthorizationException {
//		Movie movie = new Movie();
//		movie.setMovieName("Inception");
//		movie.setMovieId(405L);
//		movie.setTheaterName("dev");
//		movie.setTicketStatus("book ASAP");
//		movie.setTotalTickets(100);
//	    Mockito.when(movieService.addMovie(movie)).thenReturn(new ResponseEntity<>(HttpStatus.CREATED));
//		assertEquals(new ResponseEntity<>(HttpStatus.CREATED), movieController.addMovies(movie));
//		}
//	
//	
//	 @Test
//	 public void testDeleteMovie() {
//	 Mockito.when(movieService.deleteMovie(405L)).thenReturn(new ResponseEntity<String>("movie deleted successfully!", HttpStatus.OK));
//		 assertEquals(new ResponseEntity<String>("movie deleted successfully!", HttpStatus.OK), movieController.deleteMovieById(405L));
//	 }
//	 
//	 @Test
//	 public void testGetAllMovies() {
//     Mockito.when(movieService.getAllMovies()).thenReturn(new ResponseEntity<>(HttpStatus.OK));
//     assertEquals(new ResponseEntity<>(HttpStatus.OK),movieController.getAllMovies());
//	 }
//	 
//	 @Test
//	 public void testgetMovieById() {
//	 Mockito.when(movieService.searchMovieById(102L)).thenReturn(new ResponseEntity<>(HttpStatus.OK));
//	 assertEquals(new ResponseEntity<>(HttpStatus.OK),movieController.searchMovieById(102L));
//	 }
//	 
//	 @Test
//	 public void testBookTicket() {
//		  Ticket ticket=new Ticket();
//		  ticket.setTransactionId(1L);
//		  ticket.setMovieName("Inception");
//		  ticket.setNumberOfTickets(1);
//		  Set<String> tickets=new HashSet<String>();
//		  ticket.setTheaterName("shubha");
//		  ticket.setUserName("joshi");
//		  Mockito.when(ticketService.bookMovie("Inception", ticket)).thenReturn(new ResponseEntity<>(HttpStatus.CREATED));
//		  assertEquals(new ResponseEntity<>(HttpStatus.CREATED),movieController.bookMovie("Inception", ticket));
//	 }
//	 
//	 @Test
//	 public void testgetAllTickets() {
//		 Mockito.when(ticketService.getAllTickets()).thenReturn(new ResponseEntity<>(HttpStatus.OK));
//		 assertEquals(new ResponseEntity<>(HttpStatus.OK),movieController.getAllTickets());
//	 }
//
//}
