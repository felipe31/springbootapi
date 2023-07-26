package com.example.springbootapi.controller;

import com.example.springbootapi.model.Movie;
import com.example.springbootapi.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class MoviesController {

    @Autowired
    MovieRepository movieRepository;

    @GetMapping("/movies")
    public List<Movie> list() {
        return movieRepository.findAll();
    }
    @GetMapping("/time-between-prizes")
    public Object timeBetweenPrizes() {
        return movieRepository.findWinnerProducers();
    }

}
