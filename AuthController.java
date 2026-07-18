package com.movieplex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.movieplex.dto.RegisterRequest;
import com.movieplex.service.UserService;
import com.movieplex.dto.LoginRequest;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {

        System.out.println("Name = " + request.getFullName());
        System.out.println("Email = " + request.getEmail());
        System.out.println("Password = " + request.getPassword());
        System.out.println("Phone = " + request.getPhone());
        System.out.println("Role = " + request.getRole());

        return userService.registerUser(request);
        
   
    }
    @PostMapping("/login")
    public String login(@RequestBody com.movieplex.dto.LoginRequest request) {

        return userService.loginUser(request);

    }
}