package com.mevy.taskcontrolapi.services;

import java.util.List;
import java.util.Objects;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.mevy.taskcontrolapi.entities.Department;
import com.mevy.taskcontrolapi.entities.dtos.DepartmentDTO;
import com.mevy.taskcontrolapi.repositories.DepartmentRepository;
import com.mevy.taskcontrolapi.services.exceptions.DatabaseIntegrityException;
import com.mevy.taskcontrolapi.services.exceptions.ResourceNotFoundException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DepartmentService {
    
    private final DepartmentRepository departmentRepository;

    public List<Department> findAll() {
        List<Department> departments = departmentRepository.findAll();
        return departments;
    }

    public List<Department> findEnabledDepartments() {
        List<Department> departments = departmentRepository.findByDisabledAtIsNull();
        return departments;
    }

    public Department findByName(String name) {
        Department department = departmentRepository.findByName(name).orElseThrow(
            () -> new ResourceNotFoundException(Department.class, name)
        );
        return department;
    }

    public Department create(Department department) {
        if (departmentRepository.existsByName(department.getName())) {
            throw new DatabaseIntegrityException("Name already in use. ");
        }
        department = departmentRepository.save(department);
        return department;
    }

    public void deleteByName(String name) {
        try {
            departmentRepository.deleteByName(name);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseIntegrityException("This department cannot be deleted. ");
        }
    }

    public void updateByName(String name, Department newDepartment) {
        if (departmentRepository.existsByName(newDepartment.getName())) {
            throw new DatabaseIntegrityException("Name already in use. ");
        }
        Department department = findByName(name);
        try {
            updateData(department, newDepartment);
            departmentRepository.save(department);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseIntegrityException("Something bad occured. ");
        }
    }

    public Department fromDTO(DepartmentDTO departmentDTO) {
        Department department = Department.builder()
                                            .name(departmentDTO.name())
                                            .description(departmentDTO.description())
                                            .build();
        return department;
    }

    private void updateData(Department department, Department newDepartment) {
        department.setName(
            Objects.nonNull(newDepartment.getName()) ? newDepartment.getName() : department.getName()
        );
        department.setDescription(
            Objects.nonNull(newDepartment.getDescription()) ? newDepartment.getDescription() : department.getDescription()
        );
    }

}
