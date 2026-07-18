package com.movieplex.service;

import java.util.List;
import com.movieplex.entity.Theatre;

public interface TheatreService {

    Theatre addTheatre(Theatre theatre);

    List<Theatre> getAllTheatres();

    Theatre getTheatreById(Long id);

    Theatre updateTheatre(Long id, Theatre theatre);

    String deleteTheatre(Long id);
    
    List<Theatre> getTheatresByCity(String city);

}