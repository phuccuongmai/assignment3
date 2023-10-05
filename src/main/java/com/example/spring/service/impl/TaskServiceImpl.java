package com.example.spring.service.impl;


import com.example.spring.model.entity.Task;
import com.example.spring.model.entity.User;
import com.example.spring.model.mapper.TaskMapper;
import com.example.spring.repository.TaskRepository;
import com.example.spring.repository.UserRepository;
import com.example.spring.request.TaskRequest;
import com.example.spring.response.TaskResponse;
import com.example.spring.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    protected TaskRepository taskRepository;

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected TaskMapper taskMapper;



    @Override
    public TaskResponse save(TaskRequest taskRequest, int id) {
        try {
            Optional<User> user = userRepository.findById(id);
            if (user.isPresent()) {
//                task.setUser(user.get());

                Task task = taskMapper.toEntity(taskRequest);
                task.setUser(user.get());

                taskRepository.saveAndFlush(task);
                return taskMapper.toResponse(task);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public TaskResponse update(TaskRequest taskRequest, int id) {
        try {
            Optional<User> user = userRepository.findById(id);
            if (user.isPresent()) {
//                task.setUser(user.get());

                Task task = taskMapper.toEntity(taskRequest);
                task.setUser(user.get());

                taskRepository.saveAndFlush(task);
                return taskMapper.toResponse(task);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean delete(int id) {
        try {
            taskRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Task> findByUserId(int userId) {
        try {
            User user = new User();
            user.setUserId(userId);
            return taskRepository.findByUser(user);
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public Task findByTaskId(int taskId) {
        try {
            Optional<Task> task = taskRepository.findById(taskId);
            return task.orElse(null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
