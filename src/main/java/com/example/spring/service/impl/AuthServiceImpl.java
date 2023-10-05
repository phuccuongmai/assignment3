package com.example.spring.service.impl;

import com.example.spring.model.entity.User;
import com.example.spring.request.AuthRequest;
import com.example.spring.request.LoginRequest;
import com.example.spring.request.RegisterRequest;
import com.example.spring.response.AuthResponse;
import com.example.spring.security.JwtToken;
import com.example.spring.security.UserSecurity;
import com.example.spring.service.AuthService;
import com.example.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtToken jwtToken;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public AuthResponse register(RegisterRequest request) {
        // new User
        User user = new User();
        user.setFullName(request.getFullname());
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole("GUEST");
        user.setStatus(true);

        // Save
        userService.save(user);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setUsername(request.getUsername());

        return authResponse;
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        User user = userService.findUserByUserName(request.getUsername());
        if (user != null) {
            UserSecurity userSecurity = new UserSecurity(user);

            Map<String, Object> extraClaims = new HashMap<>();
            extraClaims.put("username", user.getUsername());
            extraClaims.put("authorities", userSecurity.getAuthorities());

            String accessToken = jwtToken.generateToken(extraClaims, userSecurity);
            String refreshToken = jwtToken.generateRefreshToken(userSecurity);

            AuthResponse authResponse = new AuthResponse();
            authResponse.setUsername(request.getUsername());
            authResponse.setAccessToken(accessToken);
            authResponse.setRefreshToken(refreshToken);

            return authResponse;
        }

        return null;
    }

    @Override
    public AuthResponse refreshToken(AuthRequest request) {
        return null;
    }


}