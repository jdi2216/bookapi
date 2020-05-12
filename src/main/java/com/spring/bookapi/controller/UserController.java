package com.spring.bookapi.controller;

import com.spring.bookapi.entity.ERole;
import com.spring.bookapi.entity.Role;
import com.spring.bookapi.entity.User;
import com.spring.bookapi.repository.RoleRepository;
import com.spring.bookapi.repository.UserRepository;
import com.spring.bookapi.security.services.UserDetailsServiceImpl;
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
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserDetailsServiceImpl userDetailsServiceImpl;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(@RequestParam(required = false) String username)
    {
        try {
            List<User> users = new ArrayList<User>();

            if (username == null)
                userRepository.findAll().forEach(users::add);
            else
                userRepository.findByUsername(username);

            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
        Optional<User> userData = userRepository.findById(id);

        if (userData.isPresent()) {
            return new ResponseEntity<>(userData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

//    @PostMapping("/users")
//    public User createUser(@Valid @RequestBody User user) {return userRepository.save(user);}

    @PutMapping("/users/{id}/role")
    public ResponseEntity<User> updateUserRoleId(@PathVariable("id") long id, @RequestBody User user) {
        Optional<User> userdata = userRepository.findById(id);

        if (userdata.isPresent()) {
            User _user = userdata.get();
            _user.setRole(user.getRole());
            return new ResponseEntity<>(userRepository.save(_user), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @DeleteMapping("/users/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") long id) {
        try {
            userRepository .deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}
