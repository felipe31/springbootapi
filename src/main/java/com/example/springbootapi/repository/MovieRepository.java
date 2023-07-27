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

    /* Select each movie ordered by producer and _year.
     * For each row, select the data from the current row and the next.
     * Keep only those which have the same producer and both won.
     */
    @Query(value =
            "WITH ordered_movies AS (" +
                "SELECT *, " +
                    "row_number() over (ORDER BY producer, _year) as rn, " +
                    "LEAD(winner) OVER (ORDER BY producer, _year) as next_winner, " +
                    "LEAD(producer) OVER (ORDER BY producer, _year) as next_producer, " +
                    "LEAD(_year) OVER (ORDER BY producer, _year) as next_year " +
                "FROM Movie ORDER BY producer, _year" +
            ") "+
            "SELECT producer, _year, next_year " +
            "FROM ordered_movies " +
            "WHERE winner = true " +
                "AND next_winner = true " +
                "AND producer = next_producer",
            nativeQuery = true)
    List<List<Object>> findConsecutiveWinnerProducers();
}
