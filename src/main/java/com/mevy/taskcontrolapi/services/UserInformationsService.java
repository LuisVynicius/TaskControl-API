package com.mevy.taskcontrolapi.services;

import java.util.List;
import java.util.Objects;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.mevy.taskcontrolapi.entities.User;
import com.mevy.taskcontrolapi.entities.UserInformations;
import com.mevy.taskcontrolapi.entities.dtos.UserInformationsUpdateDTO;
import com.mevy.taskcontrolapi.repositories.UserInformationsRepository;
import com.mevy.taskcontrolapi.services.exceptions.DatabaseIntegrityException;
import com.mevy.taskcontrolapi.services.exceptions.ResourceNotFoundException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserInformationsService {
    
    private final UserInformationsRepository userInformationsRepository;

    private final UserService userService;

    public List<UserInformations> findAll() {
        List<UserInformations> usersInformations = userInformationsRepository.findAll();
        return usersInformations;
    }

    public void updateByCurrentUser(UserInformations newUserInformations) {
        User user = userService.getAuthenticatedUser();
        UserInformations userInformations = findById(user.getId());
        try {
            updateData(userInformations, newUserInformations);
            userInformationsRepository.save(userInformations);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseIntegrityException("Something bad occured. ");
        }
    }

    private void updateData(UserInformations userInformations, UserInformations newUserInformations) {
        userInformations.setAge(
            (Objects.nonNull(newUserInformations.getAge())) ? newUserInformations.getAge() : userInformations.getAge() 
        );
        userInformations.setPhoneNumber(
            (Objects.nonNull(newUserInformations.getPhoneNumber())) ? newUserInformations.getPhoneNumber() : userInformations.getPhoneNumber()
        );
    }

    private UserInformations findById(Long id) {
        UserInformations userInformations = userInformationsRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException(UserInformations.class, id)
        );
        return userInformations;
    }

    public UserInformations fromDTO(UserInformationsUpdateDTO userInformationsUpdateDTO) {
        return UserInformations.builder()
                                .age(userInformationsUpdateDTO.age())
                                .phoneNumber(userInformationsUpdateDTO.phoneNumber())
                                .build();
    }

}
