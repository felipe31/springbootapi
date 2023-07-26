package com.example.springbootapi.repository;

import com.example.springbootapi.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    @Query(value = "select producer from Movie where winner is true", nativeQuery = true)
    List<Object> findWinnerProducers();

}
