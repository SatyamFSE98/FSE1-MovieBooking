package com.cognizant.moviebookingapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.cognizant.moviebookingapp.model.Movie;

@EnableMongoRepositories
public interface MovieRepository extends JpaRepository<Movie, Long> {

	Optional<Movie> findByMovieId(Long movieId);

	boolean existsByMovieId(Long movieId);

	boolean existsByMovieName(String movieName);

	Optional<Movie> findByMovieName(String movieName);

}
