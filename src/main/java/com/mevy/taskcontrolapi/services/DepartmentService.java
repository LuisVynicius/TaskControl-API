package com.mevy.taskcontrolapi.services;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.mevy.taskcontrolapi.entities.Department;
import com.mevy.taskcontrolapi.entities.dtos.DepartmentDTO;
import com.mevy.taskcontrolapi.repositories.DepartmentRepository;

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
        Department department = departmentRepository.findByName(name).get();
        return department;
    }

    public Department create(Department department) {
        department = departmentRepository.save(department);
        return department;
    }

    public void deleteByName(String name) {
        departmentRepository.deleteByName(name);
    }

    public void updateByName(String name, Department newDepartment) {
        Department department = departmentRepository.findByName(name).get();
        updateData(department, newDepartment);
        departmentRepository.save(department);
    }

    public Department fromDTO(DepartmentDTO departmentDTO) {
        Department department = new Department(
                null,
                departmentDTO.name(),
                departmentDTO.description(),
                null
            );
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
