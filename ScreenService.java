package com.movieplex.service;

import java.util.List;
import com.movieplex.entity.Screen;

public interface ScreenService {

    Screen addScreen(Screen screen);

    List<Screen> getAllScreens();

    Screen getScreenById(Long id);

    Screen updateScreen(Long id, Screen screen);

    String deleteScreen(Long id);
}