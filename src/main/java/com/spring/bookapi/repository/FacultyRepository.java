package com.spring.bookapi.repository;

import com.spring.bookapi.entity.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "faculties", path = "faculties")
public interface FacultyRepository extends JpaRepository<Faculty, Long> {
}
