package com.movieplex.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movieplex.entity.BookingSeat;

public interface BookingSeatRepository extends JpaRepository<BookingSeat, Long> {

}