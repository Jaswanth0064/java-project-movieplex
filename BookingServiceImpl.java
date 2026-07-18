package com.movieplex.serviceimpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movieplex.dto.BookingRequest;
import com.movieplex.dto.BookingResponse;
import com.movieplex.entity.Booking;
import com.movieplex.entity.BookingSeat;
import com.movieplex.entity.Seat;
import com.movieplex.entity.Show;
import com.movieplex.entity.User;
import com.movieplex.repository.BookingRepository;
import com.movieplex.repository.BookingSeatRepository;
import com.movieplex.repository.SeatRepository;
import com.movieplex.repository.ShowRepository;
import com.movieplex.repository.UserRepository;
import com.movieplex.service.BookingService;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private BookingSeatRepository bookingSeatRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private SeatRepository seatRepository;

    @Override
    public BookingResponse bookTickets(BookingRequest request) {

        User user = userRepository.findById(request.getUserId()).orElseThrow();

        Show show = showRepository.findById(request.getShowId()).orElseThrow();

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setShow(show);
        booking.setBookingTime(LocalDateTime.now());
        booking.setStatus("BOOKED");

        List<BookingSeat> bookingSeats = new ArrayList<>();

        double totalAmount = 0;

        for (Long seatId : request.getSeatIds()) {

            Seat seat = seatRepository.findById(seatId).orElseThrow();

            if (seat.isBooked()) {
                throw new RuntimeException(
                        "Seat " + seat.getRowName() + seat.getSeatNumber() + " already booked");
            }

            seat.setBooked(true);
            seatRepository.save(seat);

            BookingSeat bookingSeat = new BookingSeat();
            bookingSeat.setBooking(booking);
            bookingSeat.setSeat(seat);

            bookingSeats.add(bookingSeat);

            switch (seat.getSeatType()) {

            case "SILVER":
                totalAmount += show.getSilverPrice();
                break;

            case "GOLD":
                totalAmount += show.getGoldPrice();
                break;

            case "PLATINUM":
                totalAmount += show.getPlatinumPrice();
                break;

            case "VIP":
                totalAmount += show.getVipPrice();
                break;
            }
        }

        booking.setTotalAmount(totalAmount);

        Booking savedBooking = bookingRepository.save(booking);

        for (BookingSeat bs : bookingSeats) {

            bs.setBooking(savedBooking);

            bookingSeatRepository.save(bs);
        }

        savedBooking.setBookingSeats(bookingSeats);

        return convertToResponse(savedBooking);
    }

    @Override
    public List<BookingResponse> getAllBookings() {

        List<Booking> bookings = bookingRepository.findAll();

        List<BookingResponse> responses = new ArrayList<>();

        for (Booking booking : bookings) {
            responses.add(convertToResponse(booking));
        }

        return responses;
    }

    @Override
    public BookingResponse getBookingById(Long id) {

        Booking booking = bookingRepository.findById(id).orElse(null);

        if (booking == null) {
            return null;
        }

        return convertToResponse(booking);
    }

    @Override
    public List<BookingResponse> getBookingsByUser(Long userId) {

        List<Booking> bookings = bookingRepository.findByUserId(userId);

        List<BookingResponse> responses = new ArrayList<>();

        for (Booking booking : bookings) {
            responses.add(convertToResponse(booking));
        }

        return responses;
    }

    private BookingResponse convertToResponse(Booking booking) {

        BookingResponse response = new BookingResponse();

        response.setBookingId(booking.getId());

        response.setMovieName(
                booking.getShow()
                       .getMovie()
                       .getTitle());

        response.setTheatreName(
                booking.getShow()
                       .getScreen()
                       .getTheatre()
                       .getName());

        response.setScreenName(
                booking.getShow()
                       .getScreen()
                       .getScreenName());

        response.setShowDate(
                booking.getShow().getShowDate());

        response.setShowTime(
                booking.getShow().getShowTime());

        response.setTotalAmount(
                booking.getTotalAmount());

        response.setStatus(
                booking.getStatus());

        List<String> seats = new ArrayList<>();

        if (booking.getBookingSeats() != null) {

            for (BookingSeat bs : booking.getBookingSeats()) {

                seats.add(
                        bs.getSeat().getRowName()
                        + bs.getSeat().getSeatNumber());
            }
        }

        response.setSeats(seats);

        return response;
    }
    @Override
    public void cancelBooking(Long bookingId) {

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        // Already cancelled?
        if ("CANCELLED".equalsIgnoreCase(booking.getStatus())) {
            throw new RuntimeException("Booking is already cancelled");
        }

        // Release all booked seats
        for (BookingSeat bookingSeat : booking.getBookingSeats()) {

            Seat seat = bookingSeat.getSeat();

            seat.setBooked(false);

            seatRepository.save(seat);
        }

        // Update booking status
        booking.setStatus("CANCELLED");

        bookingRepository.save(booking);
    }
}