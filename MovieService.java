package com.movieplex.service;

import java.util.List;

import com.movieplex.entity.Movie;

public interface MovieService {

    Movie addMovie(Movie movie);

    List<Movie> getAllMovies();

    Movie getMovieById(Long id);

    Movie updateMovie(Long id, Movie movie);

    String deleteMovie(Long id);
    
    List<Movie> searchMovies(String title);

}