package com.mevy.taskcontrolapi.resources;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mevy.taskcontrolapi.entities.UserInformations;
import com.mevy.taskcontrolapi.services.UserInformationsService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/userInformations")
@AllArgsConstructor
public class UserInformationsResource {
    
    private final UserInformationsService userInformationsService;

    @GetMapping("/all")
    public ResponseEntity<List<UserInformations>> findAll() {
        List<UserInformations> usersInformations = userInformationsService.findAll();
        return ResponseEntity.ok().body(usersInformations);   
    }

}
