package com.spring.bookapi.controller;

import com.spring.bookapi.entity.Book;
import com.spring.bookapi.entity.ERole;
import com.spring.bookapi.entity.Role;
import com.spring.bookapi.entity.User;
import com.spring.bookapi.payload.response.MessageResponse;
import com.spring.bookapi.repository.BookRepository;
import com.spring.bookapi.repository.RoleRepository;
import com.spring.bookapi.repository.UserRepository;
import com.spring.bookapi.security.services.UserDetailsImpl;
import com.spring.bookapi.security.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/users/")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    PasswordEncoder encoder;

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

    @PutMapping("/rolesAdd/{id}")
    public ResponseEntity<?> updateUserRole(@PathVariable("id") Long id,
                                            @RequestBody String roleIds) {

        Optional<User> userData = userRepository.findById(id);
        Set<Role> roleData = userData.get().getRoles();
        Set<Role> roles = new HashSet<>();
        List<Long> ids = new ArrayList<>();
        String[] arr= roleIds.split(",");
        for(String str : arr){
            ids.add(Long.parseLong(str));
        }
        roles = roleRepository.findAllByIds(ids);
        if (userData.isPresent()) {
            User _user = userData.get();
            _user.setRoles(roles);
            userRepository.save(_user);
            return ResponseEntity.ok(new MessageResponse("User role changed successfully!"));
        } else {
            return ResponseEntity.ok(new MessageResponse("User role change bad!"));
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
            _user.setPassword(encoder.encode(user.getPassword()));
            return new ResponseEntity<>(userRepository.save(_user), HttpStatus.OK);
        } else  {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("users/{id}/roles")
    public ResponseEntity<User> updateRoles(@PathVariable("id") Long id,
                                            @RequestParam String strRoles) {
        Optional<User> userData = userRepository.findById(id);

        Set<Role> roles = new HashSet<>();
        List<Long> roleIds = new ArrayList<>();
        String[] arr = strRoles.split(",");
        for (String str : arr) {
            roleIds.add(Long.parseLong(str));
        }
        roles = roleRepository.findAllByIds(roleIds);

//        Set<Role> roles = new HashSet<>();
//        roles.add(role);
        if (userData.isPresent()) {
            User _user = new User(userData.get().getUsername(),
                    userData.get().getFirstName(),
                    userData.get().getLastName(),
                    userData.get().getEmail(),
                    encoder.encode(userData.get().getPassword()));
            _user.setRoles(roles);
            return new ResponseEntity<>(userRepository.save(_user), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") long id) {
        try {
            userRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}
