package com.spring.bookapi.controller;

import com.spring.bookapi.entity.*;
import com.spring.bookapi.payload.response.MessageResponse;
import com.spring.bookapi.repository.BookCategoryRepository;
import com.spring.bookapi.repository.BookRepository;
import com.spring.bookapi.repository.DepartmentRepository;
import com.spring.bookapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BookCategoryRepository categoryRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks(@RequestParam(required = false) String title) {
        try {
            List<Book> books = new ArrayList<Book>();

            if (title == null)
                bookRepository.findAll().forEach(books::add);
            else
                bookRepository.findByTitleContaining(title).forEach(books::add);

            if (books.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }


            return new ResponseEntity<>(books, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") long id) {
        Optional<Book> bookData = bookRepository.findById(id);

        if (bookData.isPresent()) {
            return new ResponseEntity<>(bookData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/books")
    public Book createBook(@Valid @RequestBody Book book) {
        return bookRepository.save(book);
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable("id") long id,
                                           @RequestBody Book book) {
        Optional<Book> bookData = bookRepository.findById(id);

        if (bookData.isPresent()) {
            Book _book = bookData.get();
            _book.setISBN(book.getISBN());
            _book.setTitle(book.getTitle());
            _book.setDescription(book.getDescription());
            _book.setAnnotation(book.getAnnotation());
            _book.setYear(book.getYear());
            _book.setImageUrl(book.getImageUrl());
            _book.setBookUrl(book.getBookUrl());
            _book.setDepartment(book.getDepartment());
            _book.setCategory(book.getCategory());
            _book.setUsers(book.getUsers());
            return new ResponseEntity<>(bookRepository.save(_book), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/addAuthor/{id}")
    public ResponseEntity<?> updateAuthor(@PathVariable("id") Long id,
                                            @RequestBody String authorIds) {
        Optional<Book> bookData = bookRepository.findById(id);
        Set<User> users = new HashSet<>();
        List<Long> ids = new ArrayList<>();
        String[] arr= authorIds.split(",");
        for(String str : arr){
            ids.add(Long.parseLong(str));
        }
        users = userRepository.findAllByIds(ids);
        if (bookData.isPresent()) {
            Book _book = bookData.get();
            _book.setUsers(users);
            bookRepository.save(_book);
            return ResponseEntity.ok(new MessageResponse("Author changed successfully!"));
        } else {
            return ResponseEntity.ok(new MessageResponse("Author change bad!"));
        }
    }

    @PutMapping("/addCategory/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable("id") Long id,
                                          @RequestBody String categoryIdStr) {
        Optional<Book> bookData = bookRepository.findById(id);
        BookCategory category;
        Long categoryId = Long.parseLong(categoryIdStr);
        category = categoryRepository.findOneById(categoryId);
        if (bookData.isPresent()) {
            Book _book = bookData.get();
            _book.setCategory(category);
            bookRepository.save(_book);
            return ResponseEntity.ok(new MessageResponse("Category changed successfully!"));
        } else {
            return ResponseEntity.ok(new MessageResponse("Category change bad!"));
        }
    }

    @PutMapping("/addDepartment/{id}")
    public ResponseEntity<?> updateDeparment(@PathVariable("id") Long id,
                                             @RequestBody String depIdStr) {
        Optional<Book> bookData = bookRepository.findById(id);
        Department dep;
        Long depId = Long.parseLong(depIdStr);
        dep = departmentRepository.findOneById(depId);
        if (bookData.isPresent()) {
            Book _book = bookData.get();
            _book.setDepartment(dep);
            bookRepository.save(_book);
            return ResponseEntity.ok(new MessageResponse("Department changed successfully!"));
        } else {
            return ResponseEntity.ok(new MessageResponse("Department change bad!"));
        }
    }




    @DeleteMapping("/books/{id}")
    public ResponseEntity<HttpStatus> deleteBook(@PathVariable("id") long id) {
        try {
            bookRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}
