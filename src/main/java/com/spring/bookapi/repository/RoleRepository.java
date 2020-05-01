package com.spring.bookapi.repository;

import com.spring.bookapi.entity.ERole;
import com.spring.bookapi.entity.Role;
import com.spring.bookapi.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
