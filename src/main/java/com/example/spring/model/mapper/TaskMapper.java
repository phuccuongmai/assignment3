package com.example.spring.model.mapper;

import com.example.spring.model.entity.Task;
import com.example.spring.request.TaskRequest;
import com.example.spring.response.TaskResponse;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    @Mapping(source = "task.taskId", target = "taskId")
    @Mapping(source = "task.title", target = "title")
    @Mapping(source = "task.description", target = "description")
    @Mapping(source = "task.dueDate", target = "dueDate")
    @Mapping(source = "task.status", target = "status")
    @Mapping(source = "user.userId", target = "userId")
    @Mapping(source = "user", target = "user")

    TaskResponse toResponse(Task task);

    List<TaskResponse> toResponseList(List<Task> tasks);

    Task toEntity(TaskRequest request);
}
