package com.mevy.taskcontrolapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mevy.taskcontrolapi.entities.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long>{
    
}
