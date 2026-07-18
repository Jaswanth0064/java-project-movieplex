package com.movieplex.serviceimpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movieplex.dto.PaymentRequest;
import com.movieplex.dto.PaymentResponse;
import com.movieplex.entity.Booking;
import com.movieplex.entity.Payment;
import com.movieplex.repository.BookingRepository;
import com.movieplex.repository.PaymentRepository;
import com.movieplex.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public PaymentResponse makePayment(PaymentRequest request) {

        Booking booking = bookingRepository.findById(request.getBookingId())
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        Payment payment = new Payment();

        payment.setBooking(booking);
        payment.setAmount(booking.getTotalAmount());
        payment.setPaymentMethod(request.getPaymentMethod());

        payment.setTransactionId(
                "MPX-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());

        payment.setPaymentStatus("SUCCESS");

        payment.setPaymentTime(LocalDateTime.now());

        Payment savedPayment = paymentRepository.save(payment);

        return convertToResponse(savedPayment);
    }

    @Override
    public List<PaymentResponse> getAllPayments() {

        List<Payment> payments = paymentRepository.findAll();

        List<PaymentResponse> responses = new ArrayList<>();

        for (Payment payment : payments) {
            responses.add(convertToResponse(payment));
        }

        return responses;
    }

    @Override
    public PaymentResponse getPaymentById(Long id) {

        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        return convertToResponse(payment);
    }

    private PaymentResponse convertToResponse(Payment payment) {

        PaymentResponse response = new PaymentResponse();

        response.setPaymentId(payment.getId());
        response.setBookingId(payment.getBooking().getId());
        response.setAmount(payment.getAmount());
        response.setPaymentMethod(payment.getPaymentMethod());
        response.setTransactionId(payment.getTransactionId());
        response.setPaymentStatus(payment.getPaymentStatus());
        response.setPaymentTime(payment.getPaymentTime());

        return response;
    }
}