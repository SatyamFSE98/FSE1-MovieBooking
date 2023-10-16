//package com.cognizant.moviebookingapp;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.when;
//
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Optional;
//
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import com.cognizant.moviebookingapp.model.Movie;
//import com.cognizant.moviebookingapp.repository.MovieRepository;
//import com.cognizant.moviebookingapp.service.MovieService;
//import com.cognizant.moviebookingapp.service.Impl.MovieServiceImpl;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class MovierepoTest {
//  
//	@MockBean
//	private MovieRepository movieRepository;
//	
//	@Autowired
//	private MovieService movieService;
//	
//	@Test
//	public void addMovie() {
//		assertEquals(1,1);
//		Movie movie = new Movie();
//		movie.setTotalTickets(100);
//		Mockito.when(movieRepository.save(movie)).thenReturn(movie);
//		movieService.addMovie(movie);
//	}
//	
//	@Test
//	public void addMovieSoldOut() {
//		assertEquals(1,1);
//		Movie movie = new Movie();
//		movie.setTotalTickets(0);
//		Mockito.when(movieRepository.save(movie)).thenReturn(movie);
//		movieService.addMovie(movie);
//	}
//	@Test
//	public void addMovieNotFound() {
//		assertEquals(1,1);
//		Movie movie = new Movie();
//		Mockito.when(movieRepository.existsByMovieName(movie.getMovieName())).thenReturn(true);
//		movieService.addMovie(movie);
//	}
//	
//	@Test
//	public void getAllMovie() {
//		List<Movie> movieList=new ArrayList<>();
//		Mockito.when(movieRepository.findAll()).thenReturn(movieList);
//		movieService.getAllMovies();
//	}
//	@Test
//	public void getMovieById() {
//		Movie movie = new Movie();
//		Optional obj=Optional.of(movie);
//		Mockito.when(movieRepository.findByMovieId(101L)).thenReturn(obj);
//		movieService.searchMovieById(101L);
//	}
//	
//	@Test
//	public void getMovieByIdNotPresent() {
//		Optional obj=Optional.empty();
//		Mockito.when(movieRepository.findByMovieId(101L)).thenReturn(obj);
//		movieService.searchMovieById(101L);
//	}
//	
//	@Test
//	public void deleteMovie() {
//		Movie movie = new Movie();
//		Optional obj=Optional.of(movie);
//		Mockito.when(movieRepository.findByMovieId(101L)).thenReturn(obj);
//		movieService.deleteMovie(101L);
//	}
//	
//	@Test
//	public void deleteMovieNotFound() {
//		Optional obj=Optional.empty();
//		Mockito.when(movieRepository.findByMovieId(101L)).thenReturn(obj);
//		movieService.deleteMovie(101L);
//		
//	}
//	@Test
//	public void getTicketList() {
//		Movie movie = new Movie();
//		Optional obj=Optional.of(movie);
//		Mockito.when(movieRepository.findByMovieName("RRR")).thenReturn(obj);
//		movieService.getBookedTicketList("RRR");
//	}
//	@Test
//	public void getTicketListMovieNotFound() {
//		Optional obj=Optional.empty();
//		Mockito.when(movieRepository.findByMovieName("RRR")).thenReturn(obj);
//		movieService.getBookedTicketList("RRR");
//	}
//	
//	@Test
//	public void updateMovieTickets() {
//		movieService.updateMovie("RRR", 0);
//	}
//	
//	@Test
//	public void updateMovieTicketsMovieFound() {
//		Movie movie = new Movie();
//		Optional obj=Optional.of(movie);
//		Mockito.when(movieRepository.findByMovieName("RRR")).thenReturn(obj);
//		movieService.updateMovie("RRR", 50);
//	}
//	@Test
//	public void updateMovieTicketsMovieNotFound() {
//		Optional obj=Optional.empty();
//		Mockito.when(movieRepository.findByMovieName("RRR")).thenReturn(obj);
//		movieService.updateMovie("RRR", 50);
//	}
//
//}
