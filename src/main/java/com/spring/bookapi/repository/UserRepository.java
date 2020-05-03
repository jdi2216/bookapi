package com.spring.bookapi.repository;

import com.spring.bookapi.entity.Book;
import com.spring.bookapi.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    @RestResource(path = "searchbylastname")
    List<User> findByLastName(@Param(value = "lastName") String lastName);

    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
