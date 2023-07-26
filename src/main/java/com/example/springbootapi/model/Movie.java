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
    private String studio, producer;
    private boolean winner = false;

    public Movie() {
    }

    public Movie(Integer year, String title, String studio, String producer) {
        this._year = year;
        this.title = title;
        this.studio = studio;
        this.producer = producer;
    }

    public Movie(Integer year, String title, String studio, String producer, boolean winner) {
        this._year = year;
        this.title = title;
        this.studio = studio;
        this.producer = producer;
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

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public boolean isWinner() {
        return winner;
    }

    public void setWinner(boolean winner) {
        this.winner = winner;
    }

}

