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

import com.mevy.taskcontrolapi.entities.Task;
import com.mevy.taskcontrolapi.entities.dtos.TaskDTO;
import com.mevy.taskcontrolapi.resources.interfaces.TaskDocsInterface;
import com.mevy.taskcontrolapi.services.TaskService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/task")
@AllArgsConstructor
public class TaskResource implements TaskDocsInterface {
    
    private final TaskService taskService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<List<Task>> findAll() {
        List<Task> tasks = taskService.findAll();
        return ResponseEntity.ok().body(tasks);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/department/{name}")
    public ResponseEntity<List<Task>> findByDepartmentName(
            @PathVariable
            String name
    ) {
        List<Task> tasks = taskService.findByDepartmentName(name);
        return ResponseEntity.ok().body(tasks);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/name/{name}")
    public ResponseEntity<Task> findByName(
            @PathVariable
            String name
    ) {
        Task task = taskService.findByName(name);
        return ResponseEntity.ok().body(task);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<Void> create(
            @RequestBody
            @Valid
            TaskDTO taskDTO
    ) {
        Task task = taskService.fromDTO(taskDTO);
        task = taskService.create(task);
        URI uri = ServletUriComponentsBuilder
                                            .fromCurrentRequest()
                                            .path("/{id}")
                                            .buildAndExpand(task.getId())
                                            .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/name/{name}")
    public ResponseEntity<Void> deleteByName(
            @PathVariable
            String name
    ) {
        taskService.deleteByName(name);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/name/{name}")
    public ResponseEntity<Void> updateByName(
            @PathVariable
            String name,
            @RequestBody
            @Valid
            TaskDTO taskDTO
    ) {
        Task task = taskService.fromDTO(taskDTO);
        taskService.updateByName(name, task);
        return ResponseEntity.noContent().build();
    }

}
