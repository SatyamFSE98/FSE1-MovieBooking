package com.cognizant.moviebookingapp.model;

import java.util.Set;

import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	//@Schema(hidden = true)
	private Long transactionId;// fix--this is mandatory-reference to movie:

	@NotNull
	@Min(value = 1, message = "numberOfTickets must be at least 1")
	@Max(value = 100, message = "numberOfTickets must be at most 100")
	private int numberOfTickets;// can be improved--seatNums will be same as numOfTicks--fix-later/priority-low


	// no need to give moviename and theater name it will be retrieve from movie db
	// when user selects the movie
	@NotBlank
	//@Schema(hidden = true)
	private String movieName;

	@NotBlank
	//@Schema(hidden = true)
	private String theaterName;

	@NotBlank
	//@Schema(hidden = true)
	private String userName;// --fixed--given username when user is logged in--getting from auth

	public Ticket() {
	}

	public Ticket(Long transactionId, @NotNull @Min(1) int numberOfTickets, 
			@NotBlank String movieName, @NotBlank String theaterName, @NotBlank @NotBlank @NotBlank String userName) {
		this.transactionId = transactionId;
		this.numberOfTickets = numberOfTickets;
		this.movieName = movieName;
		this.theaterName = theaterName; 
		this.userName = userName;
	}

	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

	public int getNumberOfTickets() {
		return numberOfTickets;
	}

	public void setNumberOfTickets(int numberOfTickets) {
		this.numberOfTickets = numberOfTickets;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
