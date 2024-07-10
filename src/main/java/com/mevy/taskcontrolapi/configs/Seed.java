package com.mevy.taskcontrolapi.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.mevy.taskcontrolapi.entities.User;
import com.mevy.taskcontrolapi.repositories.UserRepository;

@Configuration
public class Seed implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void run(String... args) throws Exception {
        userRepository.save(
            User.builder()
                .fullName("User01")
                .email("Email01")
                .password(bCryptPasswordEncoder.encode("senha1234"))
                .build()
        );
    }
    
}
