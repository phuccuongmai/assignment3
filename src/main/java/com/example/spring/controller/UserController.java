package com.example.spring.controller;

import com.example.spring.model.entity.User;
import com.example.spring.model.mapper.UserMapper;
import com.example.spring.response.UserResponse;
import com.example.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    protected UserService userService;

    @Autowired
    protected UserMapper userMapper;

    @GetMapping("/")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<User> users = userService.findAll();
        if (!users.isEmpty()) {
            return ResponseEntity.ok(userMapper.toResponseList(users));
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable("id") int id) {
        User user = userService.findById(id);
        if (user != null) {
            return ResponseEntity.ok(userMapper.toResponse(user));
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @PutMapping("/")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    public ResponseEntity<User> updateUser(@RequestBody User user) {
//        User updatedUser = userService.save(user);
//        if (updatedUser != null) {
//            return ResponseEntity.ok(updatedUser);
//        } else {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") int id) {
        boolean deleted = userService.delete(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

