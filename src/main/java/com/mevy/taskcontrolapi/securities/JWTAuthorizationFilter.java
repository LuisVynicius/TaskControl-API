package com.mevy.taskcontrolapi.securities;

import java.io.IOException;
import java.util.Objects;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
    
    private final JWTUtils jwtUtils;
    private final UserDetailsService userDetailsServiceimpl;
    
    public JWTAuthorizationFilter(AuthenticationManager authenticationManager, JWTUtils jwtUtils, UserDetailsService userDetailsServiceimpl) {
        super(authenticationManager);
        this.jwtUtils = jwtUtils;
        this.userDetailsServiceimpl = userDetailsServiceimpl;
    }

    private UsernamePasswordAuthenticationToken getAuthentication(String token) {
        if (jwtUtils.isValidToken(token)) {
            String email = jwtUtils.getEmail(token);
            UserDetails user = userDetailsServiceimpl.loadUserByUsername(email);
            UsernamePasswordAuthenticationToken authenticatedUser = new UsernamePasswordAuthenticationToken(
                user,
                null,
                user.getAuthorities()
            );
            return authenticatedUser;
        }
        return null;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain FilterChain) throws IOException, ServletException {
        String authorizationHeader = request.getHeader("Authorization");
        if (Objects.nonNull(authorizationHeader) && authorizationHeader.startsWith("bearer ")) {
            String token = authorizationHeader.substring(7);
            UsernamePasswordAuthenticationToken auth = getAuthentication(token);
            if (Objects.nonNull(auth)) {
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        FilterChain.doFilter(request, response);
    }

}
