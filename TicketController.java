package com.movieplex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.movieplex.dto.TicketResponse;
import com.movieplex.service.TicketService;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @GetMapping(value="/qr/{bookingId}",
            produces=MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getQRCode(
            @PathVariable Long bookingId){

        return ResponseEntity.ok(
                ticketService.generateQRCode(bookingId));

    }

    @GetMapping("/{bookingId}")
    public TicketResponse getTicket(
            @PathVariable Long bookingId){

        return ticketService.getTicket(bookingId);

    }

}