package com.mevy.taskcontrolapi.services;

import java.util.List;
import java.util.Objects;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.mevy.taskcontrolapi.entities.Department;
import com.mevy.taskcontrolapi.entities.Task;
import com.mevy.taskcontrolapi.entities.dtos.TaskDTO;
import com.mevy.taskcontrolapi.repositories.TaskRepository;
import com.mevy.taskcontrolapi.services.exceptions.DatabaseIntegrityException;
import com.mevy.taskcontrolapi.services.exceptions.ResourceNotFoundException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TaskService {
    
    private final TaskRepository taskRepository;

    private final DepartmentService departmentService;

    public List<Task> findAll() {
        List<Task> tasks = taskRepository.findAll();
        return tasks;
    }

    public List<Task> findByDepartmentName(String name) {
        Department department = departmentService.findByName(name);
        List<Task> tasks = taskRepository.findByDepartmentId(department.getId());
        return tasks;
    }

    public Task findByName(String name) {
        Task task = taskRepository.findByName(name).orElseThrow(
            () -> new ResourceNotFoundException(Task.class, name)
        );
        return task;
    }

    public Task create(Task task) {
        task = taskRepository.save(task);
        return task;
    }

    public void deleteByName(String name) {
        try {
            taskRepository.deleteByName(name);
        } catch (Exception e) {
            throw new DatabaseIntegrityException("This Task cannot be deleted. ");
        }
    }

    public void updateByName(String name, Task newTask) {
        Task task = findByName(name);
        try {
            updateData(task, newTask);
            taskRepository.save(task);   
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseIntegrityException("Something bad occured. ");
        }
    }

    public Task fromDTO(TaskDTO taskDTO) {
        Task task = Task.builder()
                        .name(taskDTO.name())
                        .description(taskDTO.description())
                        .build();
        return task;
        
    }

    private void updateData(Task task, Task newTask) {
        task.setName(
            Objects.nonNull(newTask.getName()) ? newTask.getName() : task.getName()
        );
        task.setDescription(
            Objects.nonNull(newTask.getDescription()) ? newTask.getDescription() : task.getDescription()
        );
        task.setIsCompleted(
            Objects.nonNull(newTask.getIsCompleted()) ? newTask.getIsCompleted() : task.getIsCompleted()
        );
    }

}
