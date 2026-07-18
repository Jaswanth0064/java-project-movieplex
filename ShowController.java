package com.movieplex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.movieplex.entity.Show;
import com.movieplex.service.ShowService;

@RestController
@RequestMapping("/api/shows")
public class ShowController {

    @Autowired
    private ShowService showService;

    @PostMapping
    public Show addShow(@RequestBody Show show) {
        return showService.addShow(show);
    }

    @GetMapping
    public List<Show> getAllShows() {
        return showService.getAllShows();
    }

    @GetMapping("/{id}")
    public Show getShowById(@PathVariable Long id) {
        return showService.getShowById(id);
    }

    @PutMapping("/{id}")
    public Show updateShow(@PathVariable Long id,
                           @RequestBody Show show) {

        return showService.updateShow(id, show);
    }

    @DeleteMapping("/{id}")
    public void deleteShow(@PathVariable Long id) {
        showService.deleteShow(id);
    }
    
    @GetMapping("/movie/{movieId}")
    public List<Show> getShowsByMovie(@PathVariable Long movieId) {

        return showService.getShowsByMovie(movieId);

    }
    
    @GetMapping("/movie/{movieId}/theatre/{theatreId}")
    public List<Show> getShowsByMovieAndTheatre(
            @PathVariable Long movieId,
            @PathVariable Long theatreId) {

        return showService.getShowsByMovieAndTheatre(movieId, theatreId);

    }
}