package com.example.springbootapi.model;

import jakarta.persistence.*;

@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer _year;

    @Column(nullable = false)
    private String title;
    private String studios, producers;
    private boolean winner = false;

    public Movie(Integer year, String title, String studios, String producers) {
        this._year = year;
        this.title = title;
        this.studios = studios;
        this.producers = producers;
    }

    public Movie(Integer year, String title, String studios, String producers, boolean winner) {
        this._year = year;
        this.title = title;
        this.studios = studios;
        this.producers = producers;
        this.winner = winner;
    }

    public Long getId() {
        return id;
    }

    public Integer get_year() {
        return _year;
    }

    public void set_year(Integer _year) {
        this._year = _year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStudios() {
        return studios;
    }

    public void setStudios(String studios) {
        this.studios = studios;
    }

    public String getProducers() {
        return producers;
    }

    public void setProducers(String producers) {
        this.producers = producers;
    }

    public boolean isWinner() {
        return winner;
    }

    public void setWinner(boolean winner) {
        this.winner = winner;
    }

}

