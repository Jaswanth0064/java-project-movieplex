package com.movieplex.service;

import java.util.List;

import com.movieplex.dto.PaymentRequest;
import com.movieplex.dto.PaymentResponse;

public interface PaymentService {

    PaymentResponse makePayment(PaymentRequest request);

    List<PaymentResponse> getAllPayments();

    PaymentResponse getPaymentById(Long id);

}