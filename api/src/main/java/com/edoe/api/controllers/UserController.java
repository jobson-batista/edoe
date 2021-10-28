package com.edoe.api.controllers;

import com.edoe.api.dto.UserDTO;
import com.edoe.api.services.UserService;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RequestMapping("/users")
@RestController
public class UserController {

    @Autowired
    public UserService userService;

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody @Valid UserDTO newUser) {
        if(userService.createUser(newUser.toUser()) == null) {
            JSONObject response = new JSONObject();
            response.put("message","Email already registered.");
            return new ResponseEntity(response.toString(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(userService.createUser(newUser.toUser()), HttpStatus.CREATED);
    }
}
