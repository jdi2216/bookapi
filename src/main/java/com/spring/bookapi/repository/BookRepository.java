package com.spring.bookapi.repository;

import com.spring.bookapi.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("http://localhost:8080")
public interface BookRepository extends JpaRepository<Book, Long> {
    @RestResource(path = "category")
    Page<Book> findByCategoryId(@Param("id") Long id, Pageable pageable);
    @RestResource(path = "department")
    Page<Book> findByDepartmentId(@Param("id") Long id, Pageable pageable);
    @RestResource(path = "searchbykeyword")
    Page<Book> findByTitleContaining(@Param("title") String keyword, Pageable pageable);
}
