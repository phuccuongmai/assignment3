package com.example.spring.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class UserRequest implements Serializable {

    protected String fullname;

    protected String username;

    protected String password;

}
