package com.example.ProjectOne.Entity;

import jakarta.persistence.*;


@Entity
@Table(name ="fb_users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)//"To Auto Increment primary key Value"
    @Column(name = "user_id")
    private int userId;

    @Column(name = "user_name")
    private String username;

    @Column(name = "user_email")
    private String userEmail;


    //generate getters & setters
    //generate both the constructors
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }


    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", userEmail='" + userEmail + '\'' +
                '}';
    }

    //non-parameterized constructor
    public User() {
    }

    //parameterized constructor
    public User(int userId, String username, String userEmail) {
        this.userId = userId;
        this.username = username;
        this.userEmail = userEmail;
    }


}
