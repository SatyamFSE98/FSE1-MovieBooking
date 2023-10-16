package com.cognizant.moviebookingapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.cognizant.moviebookingapp.model.Ticket;

@EnableMongoRepositories
public interface TicketRepository extends JpaRepository<Ticket, String>{
	
	List<Ticket> findByUserName(String userId);

}
