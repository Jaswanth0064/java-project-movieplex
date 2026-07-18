package com.movieplex.service;

import java.util.List;

import com.movieplex.entity.Show;

public interface ShowService {

    Show addShow(Show show);

    List<Show> getAllShows();

    Show getShowById(Long id);

    Show updateShow(Long id, Show show);

    void deleteShow(Long id);
    
    List<Show> getShowsByMovie(Long movieId);
    
    List<Show> getShowsByMovieAndTheatre(Long movieId, Long theatreId);

}
