package com.movieplex.service;

import com.movieplex.dto.TicketResponse;

public interface TicketService {

    byte[] generateQRCode(Long bookingId);

    TicketResponse getTicket(Long bookingId);

}