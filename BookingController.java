package com.movieplex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.movieplex.dto.BookingRequest;
import com.movieplex.dto.BookingResponse;
import com.movieplex.service.BookingService;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping
    public BookingResponse bookTickets(@RequestBody BookingRequest request) {
        return bookingService.bookTickets(request);
    }

    @GetMapping
    public List<BookingResponse> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @GetMapping("/{id}")
    public BookingResponse getBookingById(@PathVariable Long id) {
        return bookingService.getBookingById(id);
    }

    @GetMapping("/user/{userId}")
    public List<BookingResponse> getBookingsByUser(@PathVariable Long userId) {
        return bookingService.getBookingsByUser(userId);
    }
    @DeleteMapping("/{bookingId}")
    public String cancelBooking(@PathVariable Long bookingId) {

        bookingService.cancelBooking(bookingId);

        return "Booking cancelled successfully.";
    }
}