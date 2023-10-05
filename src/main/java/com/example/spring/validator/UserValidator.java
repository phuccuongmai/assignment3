package com.example.spring.validator;

import com.example.spring.model.entity.User;
import com.example.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        // Kiểm tra logic tùy chỉnh và thêm lỗi vào Errors nếu cần
        if (!StringUtils.isEmpty(user.getUsername()) && user.getUsername().equalsIgnoreCase("admin")) {
            errors.rejectValue("username", "error.username", "Username 'admin' is not allowed!");
        } else {
            // kiểm tra dưới username đã tồn tại hay chưa
            if (userService.findUserByUserName(user.getUsername()) != null) {
                errors.rejectValue("username", "error.username", "Username already exists!");
            }
        }
    }

}


