package com.movieplex.service;

import java.util.List;

import com.movieplex.dto.BookingRequest;
import com.movieplex.dto.BookingResponse;

public interface BookingService {

    BookingResponse bookTickets(BookingRequest request);

    List<BookingResponse> getAllBookings();

    BookingResponse getBookingById(Long id);

    List<BookingResponse> getBookingsByUser(Long userId);
    
    void cancelBooking(Long bookingId);

}