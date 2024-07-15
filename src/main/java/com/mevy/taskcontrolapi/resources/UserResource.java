package com.mevy.taskcontrolapi.resources;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mevy.taskcontrolapi.entities.User;
import com.mevy.taskcontrolapi.entities.dtos.UserCreateDTO;
import com.mevy.taskcontrolapi.entities.dtos.UserUpdateDTO;
import com.mevy.taskcontrolapi.resources.interfaces.UserDocsInterface;
import com.mevy.taskcontrolapi.services.UserService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserResource implements UserDocsInterface {
    
    private final UserService userService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        List<User> users = userService.findAll();
        return ResponseEntity.ok().body(users);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/fullName/{fullName}")
    public ResponseEntity<User> findByfullName(
            @PathVariable
            String fullName
    ) {
        User user = userService.findByFullName(fullName);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping
    public ResponseEntity<Void> create(
            @RequestBody
            @Valid
            UserCreateDTO userCreateDTO
    ) {
        User user = userService.fromDTO(userCreateDTO);
        user = userService.create(user);
        URI uri = ServletUriComponentsBuilder
                                            .fromCurrentRequest()
                                            .path("/{id}")
                                            .buildAndExpand(user.getId())
                                            .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/fullName/{fullName}")
    public ResponseEntity<Void> deleteByFullName(
            @PathVariable
            String fullName
    ) {
        userService.deleteByFullName(fullName);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/current")
    public ResponseEntity<Void> updateByCurrentUser(
            @RequestBody
            @Valid
            UserUpdateDTO userUpdateDTO
    ) {
        User user = userService.fromDTO(userUpdateDTO);
        userService.updateByCurrentUser(user);
        return ResponseEntity.noContent().build();
    }

}
