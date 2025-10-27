package com.aws.demo.controller;

import com.aws.demo.dto.JwtResponse;
import com.aws.demo.dto.LoginRequest;
import com.aws.demo.dto.RegisterRequest;
import com.aws.demo.model.User;
import com.aws.demo.repository.UserRepository;
import com.aws.demo.security.JwtUtils;
import com.aws.demo.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationManager authManager;
    private final JwtUtils jwtUtils;
    private final UserRepository userRepo;
    private final UserService userService;

    public AuthController(AuthenticationManager authManager, JwtUtils jwtUtils, UserRepository userRepo, UserService userService) {
        this.authManager = authManager;
        this.jwtUtils = jwtUtils;
        this.userRepo = userRepo;
        this.userService = userService;
    }

    @PostMapping("/login")
    public JwtResponse login(@RequestBody LoginRequest rq) {
        Authentication auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(rq.username, rq.password));
        String token = jwtUtils.generateJwtToken(auth);
        var user = userRepo.findByUsername(rq.username).orElseThrow();
        var res = new JwtResponse();
        res.token = token; res.id = user.getId(); res.username = user.getUsername(); res.email = user.getEmail();
        return res;
    }

    @PostMapping("/register")
    public User register(@RequestBody RegisterRequest u) {
        if (userRepo.existsByUsername(u.getUsername())) throw new RuntimeException("username exists");
        if (userRepo.existsByEmail(u.getEmail())) throw new RuntimeException("email exists");
        if (u.getRoles() == null || u.getRoles().isEmpty()) u.setRoles(Set.of(User.Role.ROLE_USER));
        User user = User.builder()
                .username(u.getUsername())
                .password(u.getPassword())
                .email(u.getEmail())
                .roles(u.getRoles())
                .build();
        return userService.create(user);
    }
}
