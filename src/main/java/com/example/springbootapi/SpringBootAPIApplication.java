package com.example.springbootapi;


import com.example.springbootapi.initializer.CsvDataInitializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootAPIApplication implements ApplicationRunner {
	@Autowired
	private CsvDataInitializer csvDataInitializer;
	public static void main(String[] args) {
		SpringApplication.run(SpringBootAPIApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		csvDataInitializer.initializeDataFromCsv();
	}
}
