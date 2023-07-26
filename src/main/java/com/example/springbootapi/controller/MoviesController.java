package com.example.springbootapi.controller;

import com.example.springbootapi.model.Movie;
import com.example.springbootapi.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.rmi.server.ExportException;
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

    public void importCSV() {
        importCSV("./movielist.csv");
    }

    public void importCSV(String path){
        BufferedReader bufferedReader= null;
        try {
            bufferedReader = new BufferedReader(new FileReader(path));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        bufferedReader.lines().forEach(line -> {
            System.out.println(line);
            String[] lineData = line.split(";");

            try {
                Movie movie = new Movie(Integer.valueOf(lineData[0]), lineData[1], lineData[2], lineData[3]);

                if(lineData.length > 4) {
                    System.out.println("+++++++" + lineData[4] + lineData[4].trim()+ lineData[4].trim().equals("yes"));
                    System.out.println("+++++++" + lineData[4].isEmpty());
                    movie.setWinner(lineData[4].trim().equals("yes"));
                }
                movieRepository.save(movie);
            } catch (Exception e) {
                System.out.println(e);
            }
        });



    }
}
