package com.aws.demo.dto;

import com.aws.demo.model.User;
import lombok.Data;

import java.util.Set;

@Data
public class RegisterRequest {
    String username;
    String password;
    String email;
    String fullName;
    Set<User.Role> roles;
}
