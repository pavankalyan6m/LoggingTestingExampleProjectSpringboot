package com.example.ProjectOne.Controller;

import com.example.ProjectOne.Entity.User;
import com.example.ProjectOne.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fb-api")
public class UserController {

    // Inject the UserService using the @Autowired annotation
    @Autowired
    private UserService service;

    // Define an HTTP GET endpoint for retrieving all users
    @GetMapping("/getAllUsers")
    public List<User> getAllUsers() {
        // Call the getAllFBUsers() method from the UserService to retrieve a list of users
        return service.getAllFBUsers();
    }

    // Define an HTTP POST endpoint for creating a new user
    @PostMapping("/createUser")
    public void createUser(@RequestBody User user) {
        // Call the insertNewUser() method from the UserService to create a new user
        service.insertNewUser(user);
    }
}
