package com.main.MoviesOnDemand.Entity;

public enum UserType {
    NORMAL("normal"),
    ADMIN("Admin");

    private final String displayType;

    UserType(String displayType) {
        this.displayType = displayType;
    }

    public String getDisplayName() {
        return displayType;
    }
}
