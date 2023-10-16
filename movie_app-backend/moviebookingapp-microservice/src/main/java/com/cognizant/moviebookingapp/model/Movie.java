package com.cognizant.moviebookingapp.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Movie {

	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	//@Schema(hidden = true)
	private Long movieId;

	@NotBlank
	private String movieName;

	@NotBlank
	private String theaterName;

	@NotNull
	@Min(value = 0, message = "numberOfTickets must be at least 1")
	//@Max(value = 100, message = "numberOfTickets must be at most 100")
	private int totalTickets;

	@NotBlank
	private String ticketStatus;

//	@Schema(hidden = true)
//	private Set<Integer> bookedSeats = new HashSet<>();// movie will maintain the seats booked--unique
	@Schema(hidden = true) 
	private int bookedSeats;
	@OneToMany(targetEntity =Ticket.class,fetch = FetchType.EAGER,cascade = CascadeType.ALL )
	@Schema(hidden = true)
	@JsonIgnore
	private List<Ticket> tickets = new ArrayList<Ticket>();

	public Movie() {
	}

	public Movie(Long movieId, @NotBlank String movieName, @NotBlank String theaterName, @NotNull int totalTickets,
			@NotBlank String ticketStatus, int bookedSeats, List<Ticket> tickets) {
		this.movieId = movieId;
		this.movieName = movieName;
		this.theaterName = theaterName;
		this.totalTickets = totalTickets;
		this.ticketStatus = ticketStatus;
		this.bookedSeats = bookedSeats;
		this.tickets = tickets;
	}

	public Long getMovieId() {
		return movieId;
	}

	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getTheaterName() {
		return theaterName;
	}

	public void setTheaterName(String theaterName) {
		this.theaterName = theaterName;
	}

	public int getTotalTickets() {
		return totalTickets;
	}

	public void setTotalTickets(int totalTickets) {
		this.totalTickets = totalTickets;
	}

	public String getTicketStatus() {
		return ticketStatus;
	}

	public void setTicketStatus(String ticketStatus) {
		this.ticketStatus = ticketStatus;
	}

	public int getBookedSeats() {
		return bookedSeats;
	}

	public void setBookedSeats(int bookedSeats) {
		this.bookedSeats = bookedSeats;
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

}
