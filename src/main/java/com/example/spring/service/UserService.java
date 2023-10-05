package com.example.spring.service;

import com.example.spring.model.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    User save(User user);
    List<User> findAll();
    User findUserByUserName(String username);
    User findById(int id);
    boolean delete(int id);

    int getUserIdFromReq();
}
