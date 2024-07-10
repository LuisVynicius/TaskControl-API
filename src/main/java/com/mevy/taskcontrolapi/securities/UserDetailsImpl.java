package com.mevy.taskcontrolapi.securities;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.mevy.taskcontrolapi.entities.enums.UserProfileEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDetailsImpl implements UserDetails {

    private Long id;
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;
    public UserDetailsImpl(Long id, String username, String password,
            Set<UserProfileEnum> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities
                                    .stream()
                                    .map(x -> new SimpleGrantedAuthority(x.getDescription()))
                                    .collect(Collectors.toSet());
    }

    
    
}
