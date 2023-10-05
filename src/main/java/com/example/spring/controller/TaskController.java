package com.example.spring.controller;

import com.example.spring.model.entity.Task;

import com.example.spring.model.mapper.TaskMapper;
import com.example.spring.request.TaskRequest;
import com.example.spring.response.TaskResponse;
import com.example.spring.service.TaskService;
import com.example.spring.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    @Autowired
    TaskMapper taskMapper;


    @GetMapping(value = {"", "/list"})
    public ResponseEntity<List<TaskResponse>> getAllTasks() {
        int userId = userService.getUserIdFromReq();
        List<Task> tasks = taskService.findByUserId(userId);
        if (tasks != null) {
            return ResponseEntity.ok(taskMapper.toResponseList(tasks));
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TaskResponse> findByTaskId(@PathVariable int id) {
        Task task = taskService.findByTaskId(id);

        if (task != null) {
            return ResponseEntity.ok(taskMapper.toResponse(task));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> createTask(@RequestBody TaskRequest taskRequest) {
        int userId = userService.getUserIdFromReq();
        TaskResponse rs = taskService.save(taskRequest, userId);

        if (rs != null) {
            return ResponseEntity.ok("Created task.");
        } else {
            return ResponseEntity.badRequest().body("Create task failed.");
        }
    }

    @PutMapping("/")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> updateTask(@RequestBody TaskRequest taskRequest) {
        int userId = userService.getUserIdFromReq();
        TaskResponse rs = taskService.update(taskRequest, userId);

        if (rs != null) {
            return ResponseEntity.ok("Updated task.");
        } else {
            return ResponseEntity.badRequest().body("Update task failed.");
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<HttpStatus> deleteTask(@PathVariable("id") int id) {
        boolean isDeleted = taskService.delete(id);

        if (isDeleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}