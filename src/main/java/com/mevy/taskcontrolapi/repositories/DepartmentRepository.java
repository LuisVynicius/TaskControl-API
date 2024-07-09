package com.mevy.taskcontrolapi.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mevy.taskcontrolapi.entities.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long>{
    
    List<Department> findByDisabledAtIsNull();

    void deleteByName(String name);

    Optional<Department> findByName(String name);

    boolean existsByName(String name);

}
