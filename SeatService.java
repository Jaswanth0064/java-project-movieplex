package com.movieplex.service;

import java.util.List;

import com.movieplex.entity.Seat;

public interface SeatService {

    void generateSeats(Long screenId);

    List<Seat> getAllSeats();
    
    List<Seat> getSeatsByShow(Long showId);

}