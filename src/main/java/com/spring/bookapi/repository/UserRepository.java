package com.spring.bookapi.repository;

import com.spring.bookapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @RestResource
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
