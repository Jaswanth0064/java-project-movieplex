package com.movieplex.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movieplex.dto.DashboardResponse;
import com.movieplex.entity.Payment;
import com.movieplex.repository.BookingRepository;
import com.movieplex.repository.MovieRepository;
import com.movieplex.repository.PaymentRepository;
import com.movieplex.repository.TheatreRepository;
import com.movieplex.repository.UserRepository;
import com.movieplex.service.DashboardService;

@Service
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private TheatreRepository theatreRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public DashboardResponse getDashboard() {

        DashboardResponse response = new DashboardResponse();

        response.setTotalUsers(userRepository.count());

        response.setTotalMovies(movieRepository.count());

        response.setTotalTheatres(theatreRepository.count());

        response.setTotalBookings(bookingRepository.count());

        response.setTotalPayments(paymentRepository.count());

        List<Payment> payments = paymentRepository.findAll();

        double revenue = 0;

        for (Payment payment : payments) {

            if (payment.getAmount() != null) {

                revenue += payment.getAmount();

            }

        }

        response.setTotalRevenue(revenue);

        return response;

    }

}