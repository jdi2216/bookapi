package com.spring.bookapi.repository;

import com.spring.bookapi.entity.BookCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "bookCategories", path = "category")
public interface BookCategoryRepository extends JpaRepository<BookCategory, Long> {

}
