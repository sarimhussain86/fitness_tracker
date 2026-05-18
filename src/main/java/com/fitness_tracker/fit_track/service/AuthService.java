package com.fitness_tracker.fit_track.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fitness_tracker.fit_track.dto.LoginRequest;
import com.fitness_tracker.fit_track.dto.RegisterRequest;
import com.fitness_tracker.fit_track.model.User;
import com.fitness_tracker.fit_track.repository.UserRepository;
import com.fitness_tracker.fit_track.security.JwtService;

@Service
public class AuthService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(
            UserRepository repository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService
    ) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public User register(RegisterRequest request) {

        User user = new User();

        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());

        // HASH PASSWORD
        user.setPassword(
                passwordEncoder.encode(request.getPassword())
        );

        return repository.save(user);
    }

    public String login(LoginRequest request) {

        User user = repository.findByEmail(request.getEmail())
                .orElseThrow();

        boolean valid = passwordEncoder.matches(
                request.getPassword(),
                user.getPassword()
        );

        if (!valid) {
            throw new RuntimeException("Invalid password");
        }

        return jwtService.generateToken(user.getEmail());
    }
}