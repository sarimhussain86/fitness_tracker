package com.fitness_tracker.fit_track.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fitness_tracker.fit_track.model.User;


public interface UserRepository extends JpaRepository <User, Long> {

    Optional<User> findByEmail(String email);
}