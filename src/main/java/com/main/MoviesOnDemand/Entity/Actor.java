package com.main.MoviesOnDemand.Entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "actors")
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int actorId;

    @Required
    public String profilePicture;

    @Required
    public String fullName;

    @Required
    public String bio;

    //ManyToMany Relationships
    @ManyToMany(mappedBy = "actors")
    public List<Movie> movies = new ArrayList<>();

    public Actor() {
    }

    public Actor(String profilePicture, String fullName, String bio, List<Movie> movies) {
        this.profilePicture = profilePicture;
        this.fullName = fullName;
        this.bio = bio;
        this.movies = movies;
    }


    public int getActorId() {
        return actorId;
    }

    public void setActorId(int actorId) {
        this.actorId = actorId;
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
