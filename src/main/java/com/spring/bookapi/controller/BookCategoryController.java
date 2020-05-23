package com.spring.bookapi.controller;

import com.spring.bookapi.entity.Book;
import com.spring.bookapi.entity.BookCategory;
import com.spring.bookapi.payload.response.MessageResponse;
import com.spring.bookapi.repository.BookCategoryRepository;
import com.spring.bookapi.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class BookCategoryController {

    @Autowired
    BookCategoryRepository bookCategoryRepository;

    @Autowired
    BookRepository bookRepository;

    @GetMapping("/categories")
    public ResponseEntity<List<BookCategory>> getAllBookCategories() {
        try {
            List<BookCategory> categories = new ArrayList<BookCategory>();

            bookCategoryRepository.findAll().forEach(categories::add);

            if (categories.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(categories, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<BookCategory> getBookCategoryById(@PathVariable("id") long id) {
        Optional<BookCategory> categoryData = bookCategoryRepository.findById(id);

        if (categoryData.isPresent()) {
            return new ResponseEntity<>(categoryData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/categories")
    public BookCategory createBookCategory(@Valid @RequestBody BookCategory category) {
        return bookCategoryRepository.save(category);
    }

    @PutMapping("/categories/{id}")
    public ResponseEntity<BookCategory> updateBookCategory(@PathVariable("id") long id,
                                                           @RequestBody BookCategory category) {
        Optional<BookCategory> categoryData = bookCategoryRepository.findById(id);

        if (categoryData.isPresent()) {
            BookCategory _category = categoryData.get();
            _category.setCategoryName(category.getCategoryName());
            return new ResponseEntity<>(bookCategoryRepository.save(_category), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<?> deleteBookCategory(@PathVariable("id") long id) {
        try {
            Page<Book> bookPage = bookRepository.findByCategoryId(id, Pageable.unpaged());
            if (!bookPage.isEmpty()) {
                bookCategoryRepository.deleteById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return ResponseEntity.ok(new MessageResponse("Данная категория содержит ЭУМК"));
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}
