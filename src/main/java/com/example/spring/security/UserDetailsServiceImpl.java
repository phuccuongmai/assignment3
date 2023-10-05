package com.example.spring.security;

import com.example.spring.model.entity.User;
import com.example.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Kiểm tra xem user có tồn tại trong CSDL không?
        User user = userService.findUserByUserName(username);
        if (user == null) {
           throw new UsernameNotFoundException("User Not Found!");
        }

        UserSecurity userSecurity = new UserSecurity(user);
        return userSecurity;
    }

}