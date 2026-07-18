package com.movieplex.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movieplex.entity.Screen;
import com.movieplex.entity.Seat;
import com.movieplex.entity.Show;
import com.movieplex.repository.ScreenRepository;
import com.movieplex.repository.SeatRepository;
import com.movieplex.repository.ShowRepository;
import com.movieplex.service.SeatService;

@Service
public class SeatServiceImpl implements SeatService {

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private ScreenRepository screenRepository;

    @Autowired
    private ShowRepository showRepository;

    @Override
    public void generateSeats(Long screenId) {

        Screen screen = screenRepository.findById(screenId).orElse(null);

        if (screen == null) {
            throw new RuntimeException("Screen not found");
        }

        for (char row = 'A'; row <= 'J'; row++) {

            for (int seat = 1; seat <= 20; seat++) {

                Seat s = new Seat();

                s.setScreen(screen);

                s.setSeatNumber(String.valueOf(seat));

                s.setRowName(String.valueOf(row));

                if (row >= 'A' && row <= 'C') {
                    s.setSeatType("SILVER");
                } else if (row >= 'D' && row <= 'F') {
                    s.setSeatType("GOLD");
                } else if (row == 'G') {
                    s.setSeatType("PLATINUM");
                } else {
                    s.setSeatType("VIP");
                }

                s.setBooked(false);

                seatRepository.save(s);
            }
        }
    }

    @Override
    public List<Seat> getAllSeats() {
        return seatRepository.findAll();
    }

    @Override
    public List<Seat> getSeatsByShow(Long showId) {

        Show show = showRepository.findById(showId)
                .orElseThrow(() -> new RuntimeException("Show not found"));

        Long screenId = show.getScreen().getId();

        return seatRepository.findByScreenId(screenId);
    }
}