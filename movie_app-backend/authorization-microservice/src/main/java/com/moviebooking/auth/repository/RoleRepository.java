package com.moviebooking.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.moviebooking.auth.model.ERole;
import com.moviebooking.auth.model.Role;

public interface RoleRepository extends JpaRepository<Role, String> {

	Optional<Role> findByName(String type);
}