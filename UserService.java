package com.movieplex.service;

import com.movieplex.dto.LoginRequest;
import com.movieplex.dto.RegisterRequest;

public interface UserService {

    String registerUser(RegisterRequest request);

    String loginUser(LoginRequest request);

}