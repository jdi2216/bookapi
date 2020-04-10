package com.spring.bookapi.repository;

import com.spring.bookapi.entity.University;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UniversityRepository extends JpaRepository<University, Long> {
}
