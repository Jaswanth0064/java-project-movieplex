package com.movieplex.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movieplex.entity.Seat;

public interface SeatRepository extends JpaRepository<Seat, Long> {
		
	List<Seat> findByScreenId(Long screenId);

}