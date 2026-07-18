package com.movieplex.entity;

import jakarta.persistence.*;
import java.util.List;
import jakarta.persistence.OneToMany;

@Entity
@Table(name = "seats")
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String seatNumber;

    private String rowName;

    private String seatType;

    private boolean booked;

    @ManyToOne
    @JoinColumn(name = "screen_id")
    private Screen screen;

    public Seat() {
    }

    public Long getId() {
        return id;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getRowName() {
        return rowName;
    }

    public void setRowName(String rowName) {
        this.rowName = rowName;
    }

    public String getSeatType() {
        return seatType;
    }

    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }

    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }

    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }
    @OneToMany(mappedBy = "seat")
	private List<BookingSeat> bookingSeats;
	
	public List<BookingSeat> getBookingSeats() {
	    return bookingSeats;
	}

	public void setBookingSeats(List<BookingSeat> bookingSeats) {
	    this.bookingSeats = bookingSeats;
	}
    
}
	