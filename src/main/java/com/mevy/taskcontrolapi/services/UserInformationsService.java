package com.mevy.taskcontrolapi.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mevy.taskcontrolapi.entities.UserInformations;
import com.mevy.taskcontrolapi.repositories.UserInformationsRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserInformationsService {
    
    private final UserInformationsRepository userInformationsRepository;

    public List<UserInformations> findAll() {
        List<UserInformations> usersInformations = userInformationsRepository.findAll();
        return usersInformations;
    }

}
