package com.movieplex.serviceimpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.movieplex.dto.LoginRequest;
import com.movieplex.dto.RegisterRequest;
import com.movieplex.entity.User;
import com.movieplex.repository.UserRepository;
import com.movieplex.service.UserService;
import com.movieplex.jwt.JwtService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private JwtService jwtService;
    
    @Override
    public String registerUser(RegisterRequest request) {

        User user = new User();

        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());

        // Encrypt password before saving
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        user.setPhone(request.getPhone());
        if (request.getRole() == null || request.getRole().isBlank()) {
            user.setRole("ROLE_USER");
        } else {
            user.setRole("ROLE_" + request.getRole().toUpperCase());
        }

        userRepository.save(user);

        return "User Registered Successfully";
    }

    @Override
    public String loginUser(LoginRequest request) {

        // Step 1: Search user by email
        Optional<User> optionalUser = userRepository.findByEmail(request.getEmail());

        // Step 2: Email not found
        if (optionalUser.isEmpty()) {
            return "Email not found";
        }

        // Step 3: Get user object
        User user = optionalUser.get();

        // Step 4: Check password
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return "Invalid Password";
        }

        // Step 5: Login successful
        return jwtService.generateToken(user.getEmail());
    }
}