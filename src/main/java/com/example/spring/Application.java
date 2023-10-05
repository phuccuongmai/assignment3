package com.example.spring;


import com.example.spring.model.entity.User;
import com.example.spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        String pass = passwordEncoder.encode("123456");
        System.out.println("PASS: " + pass);

        Optional<User> userAdmin = userRepository.findByUsername("admin");
        if (!userAdmin.isPresent()) {
            User user = new User();
            user.setFullName("admin");
            user.setUsername("admin");
            user.setPassword(pass);
            user.setRole("ADMIN");
            user.setStatus(true);
            userRepository.save(user);
        }
    }

}
