package com.movieplex.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movieplex.entity.Movie;
import com.movieplex.repository.MovieRepository;
import com.movieplex.service.MovieService;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public Movie addMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public Movie getMovieById(Long id) {
        return movieRepository.findById(id).orElse(null);
    }

    @Override
    public Movie updateMovie(Long id, Movie movie) {

        Movie existingMovie = movieRepository.findById(id).orElse(null);

        if (existingMovie != null) {
            existingMovie.setTitle(movie.getTitle());
            existingMovie.setGenre(movie.getGenre());
            existingMovie.setLanguage(movie.getLanguage());
            existingMovie.setDuration(movie.getDuration());
            existingMovie.setRating(movie.getRating());
            existingMovie.setDescription(movie.getDescription());

            return movieRepository.save(existingMovie);
        }

        return null;
    }

    @Override
    public String deleteMovie(Long id) {

        if (movieRepository.existsById(id)) {
            movieRepository.deleteById(id);
            return "Movie Deleted Successfully";
        }

        return "Movie Not Found";
    }
    @Override
    public List<Movie> searchMovies(String title) {
        return movieRepository.findByTitleContainingIgnoreCase(title);
    }

}