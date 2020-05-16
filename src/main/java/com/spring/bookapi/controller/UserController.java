package com.spring.bookapi.controller;

import com.spring.bookapi.entity.Book;
import com.spring.bookapi.entity.Role;
import com.spring.bookapi.entity.User;
import com.spring.bookapi.repository.BookRepository;
import com.spring.bookapi.repository.RoleRepository;
import com.spring.bookapi.repository.UserRepository;
import com.spring.bookapi.security.services.UserDetailsImpl;
import com.spring.bookapi.security.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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

    @GetMapping("/users/{id}/roles")
    public ResponseEntity<User> getUserRolesById(@PathVariable("id") long id) {
        Optional<User> userData = userRepository.findById(id);

        if(userData.isPresent()) {
            return new ResponseEntity<>(userData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long id,
                                           @RequestBody User user) {
        Optional<User> userData = userRepository.findById(id);

        if (userData.isPresent()) {
            User _user = new User();
            _user.setEmail(user.getEmail());
            _user.setFirstName(user.getFirstName());
            _user.setLastName(user.getLastName());
            _user.setUsername(user.getUsername());
            return new ResponseEntity<>(userRepository.save(_user), HttpStatus.OK);
        } else  {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Transactional
    public List<Role> getRolesList() {
        return roleRepository.findAll();
    }
    @Transactional
    public void modifyUserRoles(Long userId, String ids) {
        List<Long> roleIds = new ArrayList<Long>();
        String[] arr = ids.split(",");
        for (String str : arr) {
            roleIds.add(Long.parseLong(str));
        }
        Set<Role> roles = roleRepository.findAllByIds(roleIds);
        User user = userRepository.findOneById(userId);
        user.setRoles(roles);
        userRepository.save(user);
    }

    @PutMapping("users/{id}/roles")
    public void updateRoles(@PathVariable("id") Long id,
                                            @RequestParam(value = "roleIds") String roleIds) {
//        Optional<User> userData = userRepository.findById(id);
//
//        if (userData.isPresent()) {
//            User _user = new User();
//            _user.setRoles(user.getRoles());
//            return new ResponseEntity<>(userRepository.save(_user), HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }

        modifyUserRoles(id, roleIds);
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
