package com.example.springbootapi.repository;

import com.example.springbootapi.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    @Query(value = "select distinct producer from Movie where winner is true", nativeQuery = true)
    List<Object> findWinningProducers();
    @Query(value =
            "WITH ordered_movies AS (" +
                "SELECT *, row_number() over (ORDER BY producer, _year) as rn " +
                "FROM Movie ORDER BY producer, _year" +
            ") "+
            "SELECT m1.producer, m1._year, m2._year " +
            "FROM ordered_movies AS m1 " +
            "INNER JOIN ordered_movies AS m2 " +
            "ON m1.winner = true " +
                "AND m2.winner = true " +
                "AND m1.producer = m2.producer " +
                "AND m1.title <> m2.title " +
                "AND m1.rn < m2.rn",
            nativeQuery = true)
    List<List<Object>> findConsecutiveWinnerProducers();
}
