package com.zelda.sheikahslateapi.controllers;

import com.zelda.sheikahslateapi.models.User;
import com.zelda.sheikahslateapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Manages source of requests
@CrossOrigin("*")
@RestController
//Optional, Every route in controller starts with this
@RequestMapping("/hyrule/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    //Curly braces are path variable, like /rest-demo/users/5
    @GetMapping("id/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
        User user = userService.getUserById(id);
        //ResponseEntity adds status code
        return ResponseEntity.ok(user);
    }

    @GetMapping("username/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        User user = userService.getUserByUsername(username);
        return ResponseEntity.ok(user);
    }

    @PutMapping("id/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Integer id, @RequestBody User userUpdate) {
        User updateUser = userService.updateUser(id, userUpdate);
        return ResponseEntity.ok(updateUser);
    }

    @DeleteMapping("id/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
