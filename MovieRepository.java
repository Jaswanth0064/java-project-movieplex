package com.movieplex.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movieplex.entity.Movie;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
	
	

	List<Movie> findByTitleContainingIgnoreCase(String title);

}

