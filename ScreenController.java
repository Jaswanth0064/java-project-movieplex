package com.movieplex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.movieplex.entity.Screen;
import com.movieplex.service.ScreenService;

@RestController
@RequestMapping("/api/screens")
public class ScreenController {

    @Autowired
    private ScreenService screenService;

    @PostMapping
    public Screen addScreen(@RequestBody Screen screen) {
        return screenService.addScreen(screen);
    }

    @GetMapping
    public List<Screen> getAllScreens() {
        return screenService.getAllScreens();
    }

    @GetMapping("/{id}")
    public Screen getScreenById(@PathVariable Long id) {
        return screenService.getScreenById(id);
    }

    @PutMapping("/{id}")
    public Screen updateScreen(@PathVariable Long id,
                               @RequestBody Screen screen) {
        return screenService.updateScreen(id, screen);
    }

    @DeleteMapping("/{id}")
    public String deleteScreen(@PathVariable Long id) {
        return screenService.deleteScreen(id);
    }
}