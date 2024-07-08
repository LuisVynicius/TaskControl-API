package com.mevy.taskcontrolapi.resources;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
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
import com.mevy.taskcontrolapi.services.TaskService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/task")
@AllArgsConstructor
public class TaskResource {
    
    private final TaskService taskService;

    @GetMapping("/all")
    public ResponseEntity<List<Task>> findAll() {
        List<Task> tasks = taskService.findAll();
        return ResponseEntity.ok().body(tasks);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Task> findByName(
            @PathVariable
            String name
    ) {
        Task task = taskService.findByName(name);
        return ResponseEntity.ok().body(task);
    }

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

    @DeleteMapping("/name/{name}")
    public ResponseEntity<Void> deleteByName(
            @PathVariable
            String name
    ) {
        taskService.deleteByName(name);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/name/{name}")
    public ResponseEntity<Void> updateByname(
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
