package com.example.springbootapi.initializer;

import com.example.springbootapi.config.CsvConfiguration;
import com.example.springbootapi.model.Movie;
import com.example.springbootapi.repository.MovieRepository;
import com.example.springbootapi.service.CsvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CsvDataInitializer {
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    private CsvConfiguration csvConfiguration;
    @Autowired
    private CsvService csvService;

    public void initializeDataFromCsv() {
        String csvFilePath = csvConfiguration.getCsvFilePath();
        List<Movie> movieList = csvService.parse(csvFilePath);
        movieList.forEach(movie -> movieRepository.save(movie));
    }
}
