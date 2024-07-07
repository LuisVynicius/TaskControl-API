package com.mevy.taskcontrolapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mevy.taskcontrolapi.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{
    
}
