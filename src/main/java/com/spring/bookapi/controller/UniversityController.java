package com.spring.bookapi.controller;

import com.spring.bookapi.entity.University;
import com.spring.bookapi.repository.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhostL8080")
@RestController
@RequestMapping("/api")
public class UniversityController {

    @Autowired
    UniversityRepository universityRepository;

    @GetMapping("/universities")
    public ResponseEntity<List<University>> getAllUniversities() {
        try {
            List<University> universities = new ArrayList<University>();

            universityRepository.findAll().forEach(universities::add);

            if (universities.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(universities, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/universities/{id}")
    public ResponseEntity<University> getUniversityById(@PathVariable("id") long id) {
        Optional<University> universityData = universityRepository.findById(id);

        if (universityData.isPresent()) {
            return new ResponseEntity<>(universityData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/universities")
    public University createUniversity(@Valid @RequestBody University university) {
        return universityRepository.save(university);
    }

    @PutMapping("/universities/{id}")
    public ResponseEntity<University> updateUniversity(@PathVariable("id") long id, @RequestBody University university) {
        Optional<University> universityData = universityRepository.findById(id);

        if (universityData.isPresent()) {
            University _university = universityData.get();
            _university.setTitle(university.getTitle());
            _university.setShortTitle(university.getShortTitle());
            return new ResponseEntity<>(universityRepository.save(_university), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/universities/{id}")
    public ResponseEntity<HttpStatus> deleteUniversity(@PathVariable("id") long id) {
        try {
            universityRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

}

