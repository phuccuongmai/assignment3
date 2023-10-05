package com.example.spring.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class UserResponse implements Serializable {

    private int userId;
    private String fullname;
    private String username;
    private String role;
    private boolean status;

}
