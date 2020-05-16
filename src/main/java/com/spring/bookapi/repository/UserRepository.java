package com.spring.bookapi.repository;

import com.spring.bookapi.entity.Role;
import com.spring.bookapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    @Query(value = "select u from User u where u.id = ?1")
    User findOneById(Long id);

    @RestResource(path = "searchbylastname")
    List<User> findByLastName(@Param(value = "lastName") String lastName);

    //List<User> findByRolesId(@Param(value = "id") Long role_id);

    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}

