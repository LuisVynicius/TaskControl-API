package com.mevy.taskcontrolapi.services;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mevy.taskcontrolapi.entities.User;
import com.mevy.taskcontrolapi.entities.UserInformations;
import com.mevy.taskcontrolapi.entities.dtos.UserCreateDTO;
import com.mevy.taskcontrolapi.entities.dtos.UserUpdateDTO;
import com.mevy.taskcontrolapi.repositories.UserRepository;
import com.mevy.taskcontrolapi.securities.UserDetailsImpl;
import com.mevy.taskcontrolapi.services.exceptions.DatabaseIntegrityException;
import com.mevy.taskcontrolapi.services.exceptions.ResourceNotFoundException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {
    
    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public List<User> findAll() {
        List<User> users = userRepository.findAll();
        return users;
    }

    public User findByFullName(String name) {
        User user = userRepository.findByFullName(name).orElseThrow(
            () -> new ResourceNotFoundException(User.class, name)
        );
        return user;
    }

    public User findByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(
            () -> new ResourceNotFoundException(User.class, email)
        );
        return user;
    }

    public User create(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new DatabaseIntegrityException("Email already in use. ");
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setUserInformations(
            UserInformations
                            .builder()
                            .createDate(Instant.now())
                            .build()
        );
        user = userRepository.save(user);
        return user;
    }

    public void deleteByFullName(String fullName) {
        try {
            userRepository.deleteByFullName(fullName);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseIntegrityException("This user cannot be deleted. ");
        }
    }

    public void updateByCurrentUser(User newUser) {
        User user = getAuthenticatedUser();
        try {
            updateData(user, newUser);
            userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseIntegrityException("Something bad occured. ");
        }
    }

    public User fromDTO(UserCreateDTO userCreateDTO) {
        
        User user = User.builder()
                        .fullName(userCreateDTO.fullName())
                        .email(userCreateDTO.email())
                        .password(userCreateDTO.password())
                        .build();
        return user;
    }

    public User fromDTO(UserUpdateDTO userUpdateDTO) {
        User user = User.builder()
                        .fullName(userUpdateDTO.fullName())
                        .password(userUpdateDTO.password())
                        .build();
        return user;
    }

    private void updateData(User user, User newUser) {
        user.setFullName(
            Objects.nonNull(newUser.getFullName()) ? newUser.getFullName() : user.getFullName()
        );
        user.setPassword(
            Objects.nonNull(newUser.getPassword()) ? bCryptPasswordEncoder.encode(newUser.getPassword()) : user.getPassword()
        );
    }

    public User getAuthenticatedUser() {
        try {
            UserDetailsImpl userDetailsImpl = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = findByEmail(userDetailsImpl.getUsername());
            return user;
        } catch (Exception e) {
            return null;
        }
    }

}
