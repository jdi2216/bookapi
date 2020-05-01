package com.spring.bookapi.repository;

import com.spring.bookapi.entity.Faculty;
import com.spring.bookapi.entity.University;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(collectionResourceRel = "universities", path = "universities")
public interface UniversityRepository extends JpaRepository<University, Long> {

}
