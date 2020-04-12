package com.spring.bookapi.repository;

import com.spring.bookapi.entity.ERole;
import com.spring.bookapi.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}