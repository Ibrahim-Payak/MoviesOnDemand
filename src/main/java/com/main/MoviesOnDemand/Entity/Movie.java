package com.main.MoviesOnDemand.Entity;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    @Required
    public String name;
    @Required
    public String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date releaseDate;

    @Required
    public double price;
    @Enumerated(EnumType.STRING)
    @Required
    public MovieCategory movieCategory;
    @Required
    public String image;

    //Relationships
    @ManyToMany
    @JoinTable(
            name = "actor_movie",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id")
    )
    public List<Actor> actors;

    //Cinema Relationships
    @ManyToMany
    @JoinTable(
            name = "cinema_movie",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "cinema_id")
    )
    public List<Cinema> cinemas;

    //Producer Relationships
    @ManyToOne
    @JoinColumn(name = "producerId")
    public Producer producer;

    public Movie() {
    }

    public Movie(String name, String description, Date releaseDate, double price, com.main.MoviesOnDemand.Entity.MovieCategory movieCategory, String image, List<Actor> actors, List<Cinema> cinemas, Producer producer) {
        this.name = name;
        this.description = description;
        this.releaseDate = releaseDate;
        this.price = price;
        this.movieCategory = movieCategory;
        this.image = image;
        this.actors = actors;
        this.cinemas = cinemas;
        this.producer = producer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public com.main.MoviesOnDemand.Entity.MovieCategory getMovieCategory() {
        return movieCategory;
    }

    public void setMovieCategory(com.main.MoviesOnDemand.Entity.MovieCategory movieCategory) {
        this.movieCategory = movieCategory;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    public List<Cinema> getCinemas() {
        return cinemas;
    }

    public void setCinemas(List<Cinema> cinemas) {
        this.cinemas = cinemas;
    }

    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }
}
