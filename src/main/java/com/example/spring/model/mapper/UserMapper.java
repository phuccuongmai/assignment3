package com.example.spring.model.mapper;

import com.example.spring.model.entity.User;
import com.example.spring.response.UserResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponse toResponse(User user);

    List<UserResponse> toResponseList(List<User> userList);
}
