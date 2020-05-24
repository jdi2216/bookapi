package com.spring.bookapi.controller;


import com.spring.bookapi.entity.Faculty;
import com.spring.bookapi.repository.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/")
public class FacultyController {

    @Autowired
    FacultyRepository facultyRepository;

    @GetMapping("/faculties")
    public ResponseEntity<List<Faculty>> getAllFaculties() {
        try {
            List<Faculty> faculties = new ArrayList<Faculty>();

            facultyRepository.findAll().forEach(faculties::add);

            if (faculties.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(faculties, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/faculties/{id}")
    public ResponseEntity<Faculty> getFacultyById(@PathVariable("id") long id) {
        Optional<Faculty> facultyData = facultyRepository.findById(id);

        if (facultyData.isPresent()) {
            return new ResponseEntity<>(facultyData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/faculties")
    public Faculty createFaculty(@Valid @RequestBody Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    @PutMapping("/faculties/{id}")
    public ResponseEntity<Faculty> updateFaculty(@PathVariable("id") long id, @RequestBody Faculty faculty) {
        Optional<Faculty> facultyData = facultyRepository.findById(id);

        if (facultyData.isPresent()) {
            Faculty _faculty = facultyData.get();
            _faculty.setTitle(faculty.getTitle());
            _faculty.setShortTitle(faculty.getShortTitle());
            _faculty.setUniversity(faculty.getUniversity());
            return new ResponseEntity<>(facultyRepository.save(_faculty), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/faculties/{id}")
    public ResponseEntity<HttpStatus> deleteFaculty(@PathVariable("id") long id) {
        try {
            facultyRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}
