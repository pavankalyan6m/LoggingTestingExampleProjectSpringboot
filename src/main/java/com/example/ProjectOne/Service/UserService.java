package com.example.ProjectOne.Service;


import com.example.ProjectOne.Entity.User;
import com.example.ProjectOne.Repository.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserJpaRepository userJpaRepository;

    public List<User> getAllFBUsers()
    {
        return userJpaRepository.findAll();
    }

    public void insertNewUser(User user) {
          userJpaRepository.save(user);
    }
}
