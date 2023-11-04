package com.example.ProjectOne.Service;

import com.example.ProjectOne.Entity.User;
import com.example.ProjectOne.Repository.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

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

    // Method to retrieve a user by their ID
    public Optional<User> getUserById(int id) {
        return userJpaRepository.findById(id);
    }

    // Method to delete a user by their ID
    public void deleteUserDetailsById(int id) {
        userJpaRepository.deleteById(id);
    }

    // Method to update user details by ID
    public void updateUserDetailsById(Optional<User> old_user, User updated_user) {
        if (old_user.isPresent()) {
            // If the old user is found, update its properties with the new values
            User user = old_user.get();
            user.setUserId(updated_user.getUserId());
            user.setUserEmail(updated_user.getUserEmail());
            user.setUsername(updated_user.getUsername());

            // Save the updated user to the database
            userJpaRepository.save(user);
        }
    }
}
