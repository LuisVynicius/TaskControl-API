package com.mevy.taskcontrolapi.configs;

import java.util.Arrays;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.mevy.taskcontrolapi.entities.Department;
import com.mevy.taskcontrolapi.entities.Task;
import com.mevy.taskcontrolapi.entities.User;
import com.mevy.taskcontrolapi.repositories.DepartmentRepository;
import com.mevy.taskcontrolapi.repositories.TaskRepository;
import com.mevy.taskcontrolapi.repositories.UserRepository;

@Configuration
public class Seed implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void run(String... args) throws Exception {
        
        Department department01 = Department.builder()
                                            .name("Department01")
                                            .description("Department Description01")
                                            .build();

        Department department02 = Department.builder()
                                            .name("Department02")
                                            .description("Department Description02")
                                            .build();

        User user01 = User.builder()
                            .fullName("User01")
                            .email("Email01")
                            .password(bCryptPasswordEncoder.encode("senha1234"))
                            .authorities(Set.of(1,2))
                            .department(department01)
                            .build();
        
        User user02 = User.builder()
                            .fullName("User02")
                            .email("Email02")
                            .password(bCryptPasswordEncoder.encode("senha1234"))
                            .authorities(Set.of(2))
                            .department(department02)
                            .build();

        Task task01 = Task.builder()
                            .name("Task01")
                            .description("Task Description01")
                            .isCompleted(false)
                            .department(department01)
                            .build();


        Task task02 = Task.builder()
                            .name("Task02")
                            .description("Task Description02")
                            .isCompleted(false)
                            .department(department02)
                            .build();
        
        departmentRepository.saveAll(Arrays.asList(department01, department02));

        userRepository      .saveAll(Arrays.asList(user01, user02));

        taskRepository      .saveAll(Arrays.asList(task01, task02));

    }
    
}
