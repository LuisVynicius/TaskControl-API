package com.mevy.taskcontrolapi.resources;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mevy.taskcontrolapi.entities.UserInformations;
import com.mevy.taskcontrolapi.entities.dtos.UserInformationsUpdateDTO;
import com.mevy.taskcontrolapi.resources.interfaces.UserInformationsDocsInterface;
import com.mevy.taskcontrolapi.services.UserInformationsService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/userInformations")
@AllArgsConstructor
public class UserInformationsResource implements UserInformationsDocsInterface {
    
    private final UserInformationsService userInformationsService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<List<UserInformations>> findAll() {
        List<UserInformations> usersInformations = userInformationsService.findAll();
        return ResponseEntity.ok().body(usersInformations);   
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/current")
    public ResponseEntity<Void> updateByCurrentUser(
            @RequestBody
            UserInformationsUpdateDTO userInformationsUpdateDTO
    ) {
        UserInformations userInformations = userInformationsService.fromDTO(userInformationsUpdateDTO);
        userInformationsService.updateByCurrentUser(userInformations);
        return ResponseEntity.noContent().build();
    }

}
