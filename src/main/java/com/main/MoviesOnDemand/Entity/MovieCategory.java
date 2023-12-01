package com.main.MoviesOnDemand.Entity;

public enum MovieCategory {
    ACTION("Action"),
    COMEDY("Comedy"),
    DRAMA("Drama"),
    ROMANCE("Romance"),
    DOCUMENTARY("Documentary"),
    HORROR("Horror");

    private final String displayName;

    MovieCategory(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
