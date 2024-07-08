package com.mevy.taskcontrolapi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mevy.taskcontrolapi.entities.Task;


public interface TaskRepository extends JpaRepository<Task, Long>{
    
    Optional<Task> findByName(String name);

    void deleteByName(String name);

}
