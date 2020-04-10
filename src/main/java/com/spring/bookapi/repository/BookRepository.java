package com.spring.bookapi.repository;

import com.spring.bookapi.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends JpaRepository<Book, Long> {
    Page<Book> findByCategoryId(@Param("id") Long id, Pageable pageable);
    Page<Book> findByDepartmentId(@Param("id") Long id, Pageable pageable);
}
