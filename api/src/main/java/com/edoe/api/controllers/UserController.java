package com.edoe.api.controllers;

import com.edoe.api.dto.UserDTO;
import com.edoe.api.models.User;
import com.edoe.api.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/users")
@RestController
public class UserController {

    @Autowired
    public UserService userService;

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody User newUser) {
        return new ResponseEntity(userService.createUser(newUser).toDTO(), HttpStatus.CREATED);
    }

    @GetMapping("/{email}")
    public ResponseEntity<UserDTO> getUser(@PathVariable String email) {
        return new ResponseEntity(userService.findUserByEmail(email).toDTO(), HttpStatus.OK);
    }
}
