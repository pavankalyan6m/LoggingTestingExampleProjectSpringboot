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

    @Autowired
    private UserService service;

      @GetMapping("/getAllUsers")
      public List<User> getAllUsers()
       {
           return service.getAllFBUsers();
       }

       @PostMapping("/createUser")
       public void createUser(@RequestBody User user)
       {
           service.insertNewUser(user);
       }
}
