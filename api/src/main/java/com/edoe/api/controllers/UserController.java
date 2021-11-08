package com.edoe.api.controllers;

import com.edoe.api.dto.UserDTO;
import com.edoe.api.exceptions.ForbiddenException;
import com.edoe.api.models.User;
import com.edoe.api.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;

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
    public ResponseEntity<UserDTO> getUser(@PathVariable String email, @RequestHeader("Authorization") String header) {
        try {
            return new ResponseEntity<UserDTO>(userService.findUserByEmail(email, header), HttpStatus.OK);
        } catch (IllegalArgumentException iae) {
            return new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);
        } catch (ServletException | ForbiddenException e) {
            throw new ForbiddenException();
        }
    }

    @PatchMapping("/{email}")
    public ResponseEntity<UserDTO> changeRole(@PathVariable String email, @RequestBody User user, @RequestHeader("Authorization") String header) throws ServletException {
        return new ResponseEntity<UserDTO>(userService.changeRole(email, header, user.getRole()), HttpStatus.OK);
    }
}
