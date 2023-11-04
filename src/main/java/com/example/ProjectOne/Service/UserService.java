package com.example.ProjectOne.Service;

import com.example.ProjectOne.Entity.User;
import com.example.ProjectOne.Repository.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    // Inject the UserJpaRepository using the @Autowired annotation
    @Autowired
    private UserJpaRepository userJpaRepository;

    // Method to retrieve a list of all users
    public List<User> getAllFBUsers() {
        // Call the findAll() method from UserJpaRepository to get all users from the database
        return userJpaRepository.findAll();
    }

    // Method to insert a new user
    public void insertNewUser(User user) {
        // Call the save() method from UserJpaRepository to save the new user to the database
        userJpaRepository.save(user);
    }
}
