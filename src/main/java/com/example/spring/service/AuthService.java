package com.example.spring.service;


import com.example.spring.request.AuthRequest;
import com.example.spring.request.LoginRequest;
import com.example.spring.request.RegisterRequest;
import com.example.spring.response.AuthResponse;

public interface AuthService {

    AuthResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request);

    AuthResponse refreshToken(AuthRequest request);

}
