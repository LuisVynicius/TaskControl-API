package com.mevy.taskcontrolapi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mevy.taskcontrolapi.entities.User;


public interface UserRepository extends JpaRepository<User, Long>{
    
    Optional<User> findByFullName(String fullName);

    Optional<User> findByEmail(String email);

    void deleteByFullName(String fullName);

    boolean existsByEmail(String email);

}
