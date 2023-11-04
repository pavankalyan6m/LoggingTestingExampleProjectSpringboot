package com.example.ProjectOne.Repository;

import com.example.ProjectOne.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// The @Repository annotation marks this interface as a Spring bean for database access
@Repository
public interface UserJpaRepository extends JpaRepository<User, Integer> {
}
