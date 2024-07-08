package com.mevy.taskcontrolapi.services;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.mevy.taskcontrolapi.entities.User;
import com.mevy.taskcontrolapi.entities.dtos.UserCreateDTO;
import com.mevy.taskcontrolapi.entities.dtos.UserUpdateDTO;
import com.mevy.taskcontrolapi.repositories.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {
    
    private final UserRepository userRepository;

    public List<User> findAll() {
        List<User> users = userRepository.findAll();
        return users;
    }

    public User findByFullName(String name) {
        User user = userRepository.findByFullName(name).get();
        return user;
    }

    public User create(User user) {
        user = userRepository.save(user);
        return user;
    }

    public void deleteByFullName(String fullName) {
        userRepository.deleteByFullName(fullName);
    }

    public void updateByFullName(String fullName, User newUser) {
        User user = userRepository.findByFullName(fullName).get();
        updateData(user, newUser);
    }

    public User fromDTO(UserCreateDTO userCreateDTO) {
        return new User(
                null,
                userCreateDTO.fullName(),
                userCreateDTO.email(),
                userCreateDTO.password(),
                null
            );
    }

    public User fromDTO(UserUpdateDTO userUpdateDTO) {
        return new User(
                null,
                userUpdateDTO.fullName(),
                null,
                userUpdateDTO.password(),
                null
            );
    }

    private void updateData(User user, User newUser) {
        user.setFullName(
            Objects.nonNull(newUser.getFullName()) ? newUser.getFullName() : user.getFullName()
        );
        user.setPassword(
            Objects.nonNull(newUser.getPassword()) ? newUser.getPassword() : user.getPassword()
        );
    }

}
