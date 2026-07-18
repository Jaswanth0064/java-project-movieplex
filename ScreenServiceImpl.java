package com.movieplex.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movieplex.entity.Screen;
import com.movieplex.repository.ScreenRepository;
import com.movieplex.service.ScreenService;

@Service
public class ScreenServiceImpl implements ScreenService {

    @Autowired
    private ScreenRepository screenRepository;

    @Override
    public Screen addScreen(Screen screen) {
        return screenRepository.save(screen);
    }

    @Override
    public List<Screen> getAllScreens() {
        return screenRepository.findAll();
    }

    @Override
    public Screen getScreenById(Long id) {
        return screenRepository.findById(id).orElse(null);
    }

    @Override
    public Screen updateScreen(Long id, Screen screen) {

        Screen existing = screenRepository.findById(id).orElse(null);

        if (existing != null) {

            existing.setScreenName(screen.getScreenName());
            existing.setTotalSeats(screen.getTotalSeats());

            return screenRepository.save(existing);
        }

        return null;
    }

    @Override
    public String deleteScreen(Long id) {

        if (screenRepository.existsById(id)) {
            screenRepository.deleteById(id);
            return "Screen Deleted Successfully";
        }

        return "Screen Not Found";
    }
}