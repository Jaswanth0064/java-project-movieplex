package com.movieplex.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movieplex.entity.Show;
import com.movieplex.repository.ShowRepository;
import com.movieplex.service.ShowService;

@Service
public class ShowServiceImpl implements ShowService {

    @Autowired
    private ShowRepository showRepository;

    @Override
    public Show addShow(Show show) {
        return showRepository.save(show);
    }

    @Override
    public List<Show> getAllShows() {
        return showRepository.findAll();
    }

    @Override
    public Show getShowById(Long id) {
        return showRepository.findById(id).orElse(null);
    }

    @Override
    public Show updateShow(Long id, Show show) {

        Show existingShow = showRepository.findById(id).orElse(null);

        if (existingShow != null) {

            existingShow.setMovie(show.getMovie());
            existingShow.setScreen(show.getScreen());
            existingShow.setShowDate(show.getShowDate());
            existingShow.setShowTime(show.getShowTime());
            existingShow.setSilverPrice(show.getSilverPrice());
            existingShow.setGoldPrice(show.getGoldPrice());
            existingShow.setPlatinumPrice(show.getPlatinumPrice());
            existingShow.setVipPrice(show.getVipPrice());
            return showRepository.save(existingShow);
        }

        return null;
    }

    @Override
    public void deleteShow(Long id) {
        showRepository.deleteById(id);
    }
    
    @Override
    public List<Show> getShowsByMovie(Long movieId) {

        return showRepository.findByMovieId(movieId);

    }
    
    @Override
    public List<Show> getShowsByMovieAndTheatre(Long movieId, Long theatreId) {

        return showRepository.findByMovieIdAndScreenTheatreId(movieId, theatreId);

    }

}