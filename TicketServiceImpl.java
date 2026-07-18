package com.movieplex.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movieplex.dto.TicketResponse;
import com.movieplex.entity.Booking;
import com.movieplex.entity.BookingSeat;
import com.movieplex.entity.Payment;
import com.movieplex.repository.BookingRepository;
import com.movieplex.service.TicketService;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private QrCodeService qrCodeService;

    @Override
    public byte[] generateQRCode(Long bookingId) {

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        String qrText =
                "Booking ID : " + booking.getId()
                + "\nMovie : " + booking.getShow().getMovie().getTitle()
                + "\nUser : " + booking.getUser().getFullName()
                + "\nStatus : " + booking.getStatus();

        return qrCodeService.generateQRCode(qrText, 250, 250);
    }

    @Override
    public TicketResponse getTicket(Long bookingId) {

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        TicketResponse response = new TicketResponse();

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
                booking.getShow()
                        .getShowDate());

        response.setShowTime(
                booking.getShow()
                        .getShowTime());

        response.setAmount(
                booking.getTotalAmount());

        response.setBookingStatus(
                booking.getStatus());

        List<String> seats = new ArrayList<>();

        if (booking.getBookingSeats() != null) {

            for (BookingSeat bookingSeat : booking.getBookingSeats()) {

                seats.add(
                        bookingSeat.getSeat().getRowName()
                        + bookingSeat.getSeat().getSeatNumber());

            }

        }

        response.setSeats(seats);

        Payment payment = booking.getPayment();

        if (payment != null) {

            response.setTransactionId(
                    payment.getTransactionId());

            response.setPaymentStatus(
                    payment.getPaymentStatus());

        } else {

            response.setTransactionId("N/A");
            response.setPaymentStatus("PENDING");

        }

        String qrData =
                "Booking ID : " + booking.getId()
                + "\nMovie : " + booking.getShow().getMovie().getTitle()
                + "\nTheatre : " + booking.getShow().getScreen().getTheatre().getName()
                + "\nScreen : " + booking.getShow().getScreen().getScreenName()
                + "\nSeats : " + seats
                + "\nAmount : ₹" + booking.getTotalAmount()
                + "\nStatus : " + booking.getStatus();

        response.setQrData(qrData);

        return response;
    }

}