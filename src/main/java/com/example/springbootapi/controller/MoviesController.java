package com.example.springbootapi.controller;

import com.example.springbootapi.model.Movie;
import com.example.springbootapi.repository.MovieRepository;
import com.example.springbootapi.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class MoviesController {
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    MovieService movieService;
    @GetMapping("/movies")
    @ResponseStatus(HttpStatus.OK)
    public List<Movie> list() {
        return movieRepository.findAll();
    }
    @GetMapping("/winner-producers")
    @ResponseStatus(HttpStatus.OK)
    public Object winnerProducers() {
        return movieRepository.findWinnerProducers();
    }

    @GetMapping("/consecutive-winners")
    @ResponseStatus(HttpStatus.OK)
    public Object consecutiveWinners() {
        List<List<Object>> consecutiveWinners = movieRepository.findConsecutiveWinnerProducers();

        return movieService.buildConsecutivePrizeIntervalObject(consecutiveWinners);
    }

}
