package com.main.MoviesOnDemand.Entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cinemas")
public class Cinema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int cinemaId;

    @Required
    public String cinemaLogo;


    @Column(length = 50)
    @Required
    public String cinemaName;

    @Required
    public String cinemaDescription;

    //many-to-many Relationship
    @ManyToMany(mappedBy = "cinemas")
    public List<Movie> movies = new ArrayList<>();

    public Cinema() {
    }

    public Cinema(String cinemaLogo, String cinemaName, String cinemaDescription, List<Movie> movies) {
        this.cinemaLogo = cinemaLogo;
        this.cinemaName = cinemaName;
        this.cinemaDescription = cinemaDescription;
        this.movies = movies;
    }

    public int getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(int cinemaId) {
        this.cinemaId = cinemaId;
    }

    public String getCinemaLogo() {
        return cinemaLogo;
    }

    public void setCinemaLogo(String cinemaLogo) {
        this.cinemaLogo = cinemaLogo;
    }

    public String getCinemaName() {
        return cinemaName;
    }

    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName;
    }

    public String getCinemaDescription() {
        return cinemaDescription;
    }

    public void setCinemaDescription(String cinemaDescription) {
        this.cinemaDescription = cinemaDescription;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}
