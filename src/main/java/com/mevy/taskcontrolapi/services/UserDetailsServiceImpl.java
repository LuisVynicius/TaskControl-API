package com.mevy.taskcontrolapi.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mevy.taskcontrolapi.entities.User;
import com.mevy.taskcontrolapi.repositories.UserRepository;
import com.mevy.taskcontrolapi.securities.UserDetailsImpl;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username).orElseThrow(
            () -> new UsernameNotFoundException("Email is not in use. Email: " + username)
        );
        UserDetailsImpl userDetailsImpl = new UserDetailsImpl(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                user.getAuthorities()
            );
        return userDetailsImpl;
    }
    
}
