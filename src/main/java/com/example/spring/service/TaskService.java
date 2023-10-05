package com.example.spring.service;

import com.example.spring.model.entity.Task;
import com.example.spring.request.TaskRequest;
import com.example.spring.response.TaskResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TaskService {

    boolean delete(int id);

    List<Task> findByUserId(int userId);

    Task findByTaskId(int taskId);

    TaskResponse save(TaskRequest taskRequest, int id);

    TaskResponse update(TaskRequest taskRequest, int id);



}
