package com.movieplex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.movieplex.entity.Screen;

public interface ScreenRepository extends JpaRepository<Screen, Long> {

}