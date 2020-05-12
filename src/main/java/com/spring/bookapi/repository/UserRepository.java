package com.spring.bookapi.repository;

import com.spring.bookapi.entity.Book;
import com.spring.bookapi.entity.User;
import org.hibernate.sql.Update;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    @RestResource(path = "searchbylastname")
    List<User> findByLastName(@Param(value = "lastName") String lastName);

    List<User> findByRoleId(@Param(value = "id") Long role_id);

    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
