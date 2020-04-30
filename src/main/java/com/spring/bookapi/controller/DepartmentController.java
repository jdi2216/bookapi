package com.spring.bookapi.controller;


import com.spring.bookapi.entity.Department;
import com.spring.bookapi.repository.DepartmentRepository;
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
@RequestMapping("/api")
public class DepartmentController {

    @Autowired
    DepartmentRepository departmentRepository;

    @GetMapping("/departments")
    public ResponseEntity<List<Department>> getAllDepartments() {
        try {
            List<Department> departments = new ArrayList<Department>();

            departmentRepository.findAll().forEach(departments::add);

            if (departments.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(departments, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/departments/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable("id") long id) {
        Optional<Department> departmentData = departmentRepository.findById(id);

        if (departmentData.isPresent()) {
            return new ResponseEntity<>(departmentData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/departments")
    public Department createdepartment(@Valid @RequestBody Department department) {
        return departmentRepository.save(department);
    }

    @PutMapping("/departments/{id}")
    public ResponseEntity<Department> updateDepartment(@PathVariable("id") long id,
                                                       @RequestBody Department department) {
        Optional<Department> departmentData = departmentRepository.findById(id);

        if (departmentData.isPresent()) {
            Department _department = departmentData.get();
            _department.setTitle(department.getTitle());
            _department.setShortTitle(department.getShortTitle());
            _department.setFaculty(department.getFaculty());
            return new ResponseEntity<>(departmentRepository.save(_department), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/departments/{id}")
    public ResponseEntity<HttpStatus> deleteDepartment(@PathVariable("id") long id) {
        try {
            departmentRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}