package com.movieplex.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movieplex.entity.Theatre;
import com.movieplex.repository.TheatreRepository;
import com.movieplex.service.TheatreService;

@Service
public class TheatreServiceImpl implements TheatreService {

    @Autowired
    private TheatreRepository theatreRepository;

    @Override
    public Theatre addTheatre(Theatre theatre) {
        return theatreRepository.save(theatre);
    }

    @Override
    public List<Theatre> getAllTheatres() {
        return theatreRepository.findAll();
    }

    @Override
    public Theatre getTheatreById(Long id) {
        return theatreRepository.findById(id).orElse(null);
    }

    @Override
    public Theatre updateTheatre(Long id, Theatre theatre) {

        Theatre existing = theatreRepository.findById(id).orElse(null);

        if (existing != null) {

            existing.setName(theatre.getName());
            existing.setCity(theatre.getCity());
            existing.setAddress(theatre.getAddress());

            return theatreRepository.save(existing);
        }

        return null;
    }

    @Override
    public String deleteTheatre(Long id) {

        if (theatreRepository.existsById(id)) {

            theatreRepository.deleteById(id);
            return "Theatre Deleted Successfully";
        }

        return "Theatre Not Found";
    }
    
    @Override
    public List<Theatre> getTheatresByCity(String city) {

        return theatreRepository.findByCityIgnoreCase(city);

    }

}