package com.spring.bookapi.repository;

import com.spring.bookapi.entity.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "universities", path = "universities")
public interface UniversityRepository extends JpaRepository<University, Long> {
}
