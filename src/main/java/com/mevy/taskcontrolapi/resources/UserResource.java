package com.mevy.taskcontrolapi.resources;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
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
import com.mevy.taskcontrolapi.services.UserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserResource {
    
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        List<User> users = userService.findAll();
        return ResponseEntity.ok().body(users);
    }

    @GetMapping("/fullName/{fullName}")
    public ResponseEntity<User> findByfullName(
            @PathVariable String fullName
    ) {
        User user = userService.findByFullName(fullName);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping
    public ResponseEntity<Void> create(
            @RequestBody User user
    ) {
        user = userService.create(user);
        URI uri = ServletUriComponentsBuilder
                                            .fromCurrentRequest()
                                            .path("/{id}")
                                            .buildAndExpand(user.getId())
                                            .toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/fullName/{fullName}")
    public ResponseEntity<Void> deleteByFullName(
            @PathVariable String fullName
    ) {
        userService.deleteByFullName(fullName);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/fullName/{fullName}")
    public ResponseEntity<Void> updateByFullName(
            @PathVariable String fullName,
            @RequestBody User user
    ) {
        userService.updateByFullName(fullName, user);
        return ResponseEntity.noContent().build();
    }

}
