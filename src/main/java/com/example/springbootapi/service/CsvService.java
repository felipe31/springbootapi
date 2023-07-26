package com.example.springbootapi.service;

import com.example.springbootapi.model.Movie;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class CsvService {

    public List<Movie> parse(String path){
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(path));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        List<Movie> movieList = new ArrayList<Movie>();
        bufferedReader.lines().forEach(line -> {
            System.out.println(line);
            String[] lineData = line.split(";");

            try {
                if(lineData.length < 4) {
                    throw new Exception("Data missing to the line: " + line);
                }

                Movie movie = new Movie(Integer.valueOf(lineData[0]), lineData[1], lineData[2], lineData[3]);

                if(lineData.length > 4) {
                    movie.setWinner(lineData[4].trim().equals("yes"));
                }
                movieList.add(movie);
            } catch (Exception e) {
                System.out.println(e);
            }
        });
        return movieList;
    }
}
