package com.example.springbootapi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CsvConfiguration {
    @Value("${csv.file.path}")
    private String csvFilePath;

    public String getCsvFilePath() {
        return csvFilePath;
    }
}
