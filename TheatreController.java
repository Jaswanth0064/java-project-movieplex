package com.movieplex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.movieplex.entity.Theatre;
import com.movieplex.service.TheatreService;

@RestController
@RequestMapping("/api/theatres")
public class TheatreController {

    @Autowired
    private TheatreService theatreService;

    // Add Theatre
    @PostMapping
    public Theatre addTheatre(@RequestBody Theatre theatre) {
        return theatreService.addTheatre(theatre);
    }

    // Get All Theatres
    @GetMapping
    public List<Theatre> getAllTheatres() {
        return theatreService.getAllTheatres();
    }

    // Get Theatre By ID
    @GetMapping("/{id}")
    public Theatre getTheatreById(@PathVariable Long id) {
        return theatreService.getTheatreById(id);
    }

    // Update Theatre
    @PutMapping("/{id}")
    public Theatre updateTheatre(@PathVariable Long id,
                                 @RequestBody Theatre theatre) {
        return theatreService.updateTheatre(id, theatre);
    }

    // Delete Theatre
    @DeleteMapping("/{id}")
    public String deleteTheatre(@PathVariable Long id) {
        return theatreService.deleteTheatre(id);
    }
    
    @GetMapping("/city/{city}")
    public List<Theatre> getTheatresByCity(@PathVariable String city) {

        return theatreService.getTheatresByCity(city);

    }
}