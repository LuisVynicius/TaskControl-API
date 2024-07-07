package com.mevy.taskcontrolapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mevy.taskcontrolapi.entities.Task;

public interface TaskRepository extends JpaRepository<Task, Long>{
    
}
