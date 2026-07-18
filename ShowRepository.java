package com.movieplex.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movieplex.entity.Show;

public interface ShowRepository extends JpaRepository<Show, Long> {

    List<Show> findByScreenId(Long screenId);
    
    

    List<Show> findByMovieId(Long movieId);
    
    List<Show> findByMovieIdAndScreenTheatreId(Long movieId, Long theatreId);

}

