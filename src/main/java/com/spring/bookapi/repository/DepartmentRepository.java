package com.spring.bookapi.repository;

import com.spring.bookapi.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "departments", path = "departments")
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
