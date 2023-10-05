package com.example.spring.controller;


import com.example.spring.exception.ApplicationException;
import com.example.spring.request.AuthRequest;
import com.example.spring.request.LoginRequest;
import com.example.spring.request.RegisterRequest;
import com.example.spring.response.ApiResponse;
import com.example.spring.response.AuthResponse;
import com.example.spring.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<AuthResponse>> register(@RequestBody RegisterRequest request) {
        try {
            AuthResponse authResponse = authService.register(request);

            ApiResponse apiResponse = new ApiResponse();
            apiResponse.ok(authResponse);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (Exception ex) {
            throw new ApplicationException();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponse>> login(@RequestBody LoginRequest request) {
        try {
            AuthResponse authResponse = authService.login(request);

            ApiResponse apiResponse = new ApiResponse();
            apiResponse.ok(authResponse);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ApplicationException();
        }
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<ApiResponse<AuthResponse>> refreshToken(@RequestBody AuthRequest request) {
        try {
            AuthResponse authResponse = authService.refreshToken(request);

            ApiResponse apiResponse = new ApiResponse();
            apiResponse.ok(authResponse);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (Exception ex) {
            throw new ApplicationException();
        }
    }

}