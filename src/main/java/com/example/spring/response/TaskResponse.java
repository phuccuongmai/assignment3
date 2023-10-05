package com.example.spring.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskResponse {

    //Task
    protected int taskId;

    protected String title;

    protected String description;

    protected Date dueDate;

    protected boolean status;

    //User
    private int userId;
    private UserResponse user;

}
