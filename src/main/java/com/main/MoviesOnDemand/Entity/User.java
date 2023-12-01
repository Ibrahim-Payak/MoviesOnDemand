package com.main.MoviesOnDemand.Entity;

import jakarta.persistence.*;

@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int userId;

    @Required
    public String userName;

    @Required
    public String userEmail;

    @Required
    public String userPassword;

    @Required
    public String userConfirmPassword;

    public UserType userType;

    public User() {
    }

    public User(String userName, String userEmail, String userPassword, String userConfirmPassword, UserType userType) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userConfirmPassword = userConfirmPassword;
        this.userType = userType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserConfirmPassword() {
        return userConfirmPassword;
    }

    public void setUserConfirmPassword(String userConfirmPassword) {
        this.userConfirmPassword = userConfirmPassword;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}
