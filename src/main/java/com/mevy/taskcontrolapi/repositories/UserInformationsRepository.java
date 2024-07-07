package com.mevy.taskcontrolapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mevy.taskcontrolapi.entities.UserInformations;

public interface UserInformationsRepository extends JpaRepository<UserInformations, Long>{
    
}
