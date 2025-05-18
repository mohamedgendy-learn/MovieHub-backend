package com.movieshub.controller;



import com.movieshub.model.LoginRequest;

import com.movieshub.model.User;

import com.movieshub.service.AuthUserService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.Authentication;

import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;



@RestController

@RequestMapping("/api/auth")

public class AuthController {



    @Autowired

    private AuthenticationManager authenticationManager;



    @Autowired

    private AuthUserService userService;



    @Autowired

    private PasswordEncoder passwordEncoder;



    @PostMapping("/login")

    public ResponseEntity<String> authenticateUser(@RequestBody LoginRequest loginRequest) {

        try {

            Authentication authentication = authenticationManager.authenticate(

                    new UsernamePasswordAuthenticationToken(

                            loginRequest.getUserName(),

                            loginRequest.getPassword()

                    )

            );



            SecurityContextHolder.getContext().setAuthentication(authentication);



            User user = userService.findByUsername(loginRequest.getUserName()).orElse(null);

            if (user != null) {

                String role = user.getRoles().stream()

                        .map(r -> r.getName())

                        .collect(Collectors.joining(",")); // Get roles

                return ResponseEntity.ok(role);

            }

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");





        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");

        }

    }

}