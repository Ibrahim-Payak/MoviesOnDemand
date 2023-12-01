package com.main.MoviesOnDemand.Entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "producers")
public class Producer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int producerId;

    @Required
    public String profilePicture;

    @Column(length = 50)
    @Required
    public String fullName;

    @Required
    public String bio;

    //One-to-Many Relationships
    @OneToMany(mappedBy = "producer")
    public List<Movie> movies;

    public Producer() {
    }

    public Producer(String profilePicture, String fullName, String bio, List<Movie> movies) {
        this.profilePicture = profilePicture;
        this.fullName = fullName;
        this.bio = bio;
        this.movies = movies;
    }

    public int getProducerId() {
        return producerId;
    }

    public void setProducerId(int producerId) {
        this.producerId = producerId;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}
