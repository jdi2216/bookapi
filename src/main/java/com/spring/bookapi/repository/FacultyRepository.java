package com.spring.bookapi.repository;

import com.spring.bookapi.entity.Department;
import com.spring.bookapi.entity.Faculty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(collectionResourceRel = "faculties", path = "faculties")
public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    @RestResource(path = "university")
    Page<Faculty> findByUniversityId(@Param("id") Long id, Pageable pageable);
}
