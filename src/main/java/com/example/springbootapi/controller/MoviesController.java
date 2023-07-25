package com.example.springbootapi.controller;

import com.example.springbootapi.model.Movie;
import com.example.springbootapi.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.rmi.server.ExportException;
import java.util.List;
import java.util.Scanner;

@RestController
@RequestMapping("/movies")
public class MoviesController {

    @Autowired
    MovieRepository movieRepository;

    @GetMapping
    public List<Movie> list() {
        importCSV();
        return movieRepository.findAll();
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

                if(lineData.length >4) movie.setWinner(lineData[4] == "yes");
                movieRepository.save(movie);
            } catch (Exception e) {
                System.out.println(e);
            }
        });



    }
}
