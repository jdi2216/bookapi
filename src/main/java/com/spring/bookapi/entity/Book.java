package com.spring.bookapi.entity;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tbl_book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ISBN;

    private String title;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "tbl_book_authors",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns =
            @JoinColumn(name = "user_id"))
    private Set<User> users = new HashSet<>();

    @Column(columnDefinition = "text")
    private String description;

    @Column(columnDefinition = "text")
    private String annotation;

    private int year;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "book_url")
    private String bookUrl;

    @CreatedDate
    @Column(name="date_created")
    private Date createdOn;

    @Column(name = "last_updated")
    private Date updatedOn;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = true)
    private BookCategory category;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = true)
    private Department department;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getBookUrl() {
        return bookUrl;
    }

    public void setBookUrl(String bookUrl) {
        this.bookUrl = bookUrl;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public BookCategory getCategory() {
        return category;
    }

    public void setCategory(BookCategory category) {
        this.category = category;
    }

    public  Book() {
    }
/*
    public Book(String ISBN, String title, Set<User> users, String description, String annotation,
                int year, String imageUrl, String bookUrl, BookCategory category,
                Department department) {
        this.ISBN = ISBN;
        this.title = title;
        this.users = users;
        this.description = description;
        this.annotation = annotation;
        this.year = year;
        this.imageUrl = imageUrl;
        this.bookUrl = bookUrl;
        this.category = category;
        this.department = department;
    }

 */
}
