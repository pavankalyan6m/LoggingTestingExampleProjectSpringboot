package com.example.ProjectOne.Controller;

import com.example.ProjectOne.Entity.User;
import com.example.ProjectOne.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/fb-api")
public class UserController {

    // Create a logger for UserController
    //for logging
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);


    // Inject the UserService using the @Autowired annotation
    @Autowired
    private UserService service;

    // Define an HTTP GET endpoint for retrieving all users
    @GetMapping("/getAllUsers")
    public List<User> getAllUsers() {
        // Call the getAllFBUsers() method from the UserService to retrieve a list of users
        logger.info("Fetching all users");
        //logger.trace("A TRACE Message");
        //logger.debug("A DEBUG Message");
        //logger.info("An INFO Message");
        //logger.warn("A WARN Message");
        //logger.error("An ERROR Message");
        return service.getAllFBUsers();
    }

    // Define an HTTP POST endpoint for creating a new user
    @PostMapping("/createUser")
    public void createUser(@RequestBody User user) {
        // Call the insertNewUser() method from the UserService to create a new user
        logger.info("Creating a new user: {}", (user.toString()));
        //log.info("This is log");
        service.insertNewUser(user);
    }

    // Define an HTTP GET endpoint for retrieving user details by ID
    @GetMapping("/getUserDetailsById/{id}")
    public Optional<User> getDetailsById(@PathVariable int id) {
        // Call the getUserById() method from the UserService to retrieve user details by ID
        logger.info("Fetching user details for ID: {}", id);
        return service.getUserById(id);
    }

    // Define an HTTP DELETE endpoint for deleting a user by ID
    @DeleteMapping("/deleteUserById/{id}")
    public void deleteUserById(@PathVariable int id) {
        // Call the deleteUserDetailsById() method from the UserService to delete a user by ID
        logger.info("Deleting user with ID: {}", id);
        service.deleteUserDetailsById(id);
    }

    // Define an HTTP PUT endpoint for updating existing user details by ID
    @PutMapping("/updateUserById/{id}")
    public void updateUserById(@RequestBody User updatedUser, @PathVariable int id) {

        logger.info("Updating user with ID: {}", id);


        // Call the getDetailsById() method to retrieve the old user by ID
        Optional<User> oldUser = getDetailsById(id);

        logger.info("Old user details: {}", oldUser.get());
        logger.info("Updated user details: {}", updatedUser);

        // Call the updateUserDetailsById() method from the UserService to update user details
        service.updateUserDetailsById(oldUser, updatedUser);

    }
}
