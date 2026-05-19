package com.fitness_tracker.fit_track.controller;

import org.springframework.web.bind.annotation.RestController;

import com.fitness_tracker.fit_track.dto.AuthResponse;
import com.fitness_tracker.fit_track.dto.LoginRequest;
import com.fitness_tracker.fit_track.dto.RegisterRequest;
import com.fitness_tracker.fit_track.model.User;
import com.fitness_tracker.fit_track.service.AuthService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/auth")
public class AuthController {
    
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public User register(
            @RequestBody RegisterRequest request
    ) {
        System.out.println("register");
        return authService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        System.out.println("login");
        String token = authService.login(request);
        return new AuthResponse(token);
    }
    
}

