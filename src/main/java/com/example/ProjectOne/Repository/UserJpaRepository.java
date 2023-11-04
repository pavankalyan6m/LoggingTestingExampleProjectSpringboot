package com.example.ProjectOne.Repository;

import com.example.ProjectOne.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaRepository extends JpaRepository<User,Integer> {

}
