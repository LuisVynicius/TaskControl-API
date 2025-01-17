package com.mevy.taskcontrolapi.resources;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mevy.taskcontrolapi.entities.Department;
import com.mevy.taskcontrolapi.entities.dtos.DepartmentDTO;
import com.mevy.taskcontrolapi.resources.interfaces.DepartmentDocsInterface;
import com.mevy.taskcontrolapi.services.DepartmentService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/department")
@AllArgsConstructor
public class DepartmentResource implements DepartmentDocsInterface{
    
    private final DepartmentService departmentService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/all")
    public ResponseEntity<List<Department>> findAll(){ 
        List<Department> departments = departmentService.findAll();
        return ResponseEntity.ok().body(departments);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/enableds")
    public ResponseEntity<List<Department>> findEnabledDepartments(){ 
        List<Department> departments = departmentService.findEnabledDepartments();
        return ResponseEntity.ok().body(departments);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/name/{name}")
    public ResponseEntity<Department> findByName(
            @PathVariable
            String name
    ) {
        Department department = departmentService.findByName(name);
        return ResponseEntity.ok().body(department);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<Void> create(
            @RequestBody
            @Valid
            DepartmentDTO departmentDTO
    ) {
        Department department = departmentService.fromDTO(departmentDTO);
        department = departmentService.create(department);
        URI uri = ServletUriComponentsBuilder
                                            .fromCurrentRequest()
                                            .path("/{id}")
                                            .buildAndExpand(department.getId())
                                            .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/name/{name}")
    public ResponseEntity<Void> deleteByName(
            @PathVariable
            String name
    ) {
        departmentService.deleteByName(name);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/name/{name}")
    public ResponseEntity<Void> updateByName(
            @PathVariable
            String name,
            @RequestBody
            @Valid
            DepartmentDTO departmentDTO
    ) {
        Department department = departmentService.fromDTO(departmentDTO);
        departmentService.updateByName(name, department);
        return ResponseEntity.noContent().build();
    }

}
