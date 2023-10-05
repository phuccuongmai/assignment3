package com.example.spring.service.impl;

import com.example.spring.model.entity.User;
import com.example.spring.repository.UserRepository;
import com.example.spring.security.JwtToken;
import com.example.spring.security.UserSecurity;
import com.example.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected JwtToken jwtToken;

    @Override
    public User save(User user) {
        return userRepository.saveAndFlush(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findUserByUserName(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    @Override
    public User findById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public boolean delete(int id) {
        try {
            userRepository.deleteById(id);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public int getUserIdFromReq() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserSecurity userSecurity = (UserSecurity) authentication.getPrincipal();
        User user = userSecurity.getUser();
        return user.getUserId();
    }

}
