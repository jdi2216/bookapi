package com.spring.bookapi.repository;

import com.spring.bookapi.entity.Book;
import com.spring.bookapi.entity.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(collectionResourceRel = "departments", path = "departments")
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    @RestResource(path = "faculty")
    Page<Department> findByFacultyId(@Param("id") Long id, Pageable pageable);
}
