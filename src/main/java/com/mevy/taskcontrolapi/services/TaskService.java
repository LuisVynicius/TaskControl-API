package com.mevy.taskcontrolapi.services;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.mevy.taskcontrolapi.entities.Task;
import com.mevy.taskcontrolapi.entities.dtos.TaskDTO;
import com.mevy.taskcontrolapi.repositories.TaskRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TaskService {
    
    private final TaskRepository taskRepository;

    public List<Task> findAll() {
        List<Task> tasks = taskRepository.findAll();
        return tasks;
    }

    public Task findByName(String name) {
        Task task = taskRepository.findByName(name).get();
        return task;
    }

    //public List<Task> findTaskByDepartment

    public Task create(Task task) {
        task = taskRepository.save(task);
        return task;
    }

    public void deleteByName(String name) {
        taskRepository.deleteByName(name);
    }

    public void updateByName(String name, Task newTask) {
        Task task = taskRepository.findByName(name).get();
        updateData(task, newTask);
        taskRepository.save(task);
    }

    public Task fromDTO(TaskDTO taskDTO) {
        return new Task(
                null,
                taskDTO.name(),
                taskDTO.description()
            );
    }

    private void updateData(Task task, Task newTask) {
        task.setName(
            Objects.nonNull(newTask.getName()) ? newTask.getName() : task.getName()
        );
        task.setDescription(
            Objects.nonNull(newTask.getDescription()) ? newTask.getDescription() : task.getDescription()
        );
    }

}
