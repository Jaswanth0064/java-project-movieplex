package com.movieplex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.movieplex.entity.Seat;
import com.movieplex.service.SeatService;

@RestController
@RequestMapping("/api/seats")
public class SeatController {

    @Autowired
    private SeatService seatService;

    @PostMapping("/generate/{screenId}")
    public String generateSeats(@PathVariable Long screenId) {

        seatService.generateSeats(screenId);

        return "200 Seats Generated Successfully!";
    }

    @GetMapping
    public List<Seat> getAllSeats() {

        return seatService.getAllSeats();
    }
    
    @GetMapping("/show/{showId}")
    public List<Seat> getSeatsByShow(@PathVariable Long showId) {

        return seatService.getSeatsByShow(showId);

    }

}