package com.movieplex.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movieplex.entity.Theatre;

public interface TheatreRepository extends JpaRepository<Theatre, Long> {
	List<Theatre> findByCityIgnoreCase(String city);

}

