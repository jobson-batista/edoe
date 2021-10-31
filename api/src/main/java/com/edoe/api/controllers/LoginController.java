package com.edoe.api.controllers;

import com.edoe.api.models.User;
import com.edoe.api.services.JWTService;
import com.edoe.api.services.LoginService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
public class LoginController {

    @Autowired
    public LoginService loginService;

    @Autowired
    private JWTService jwtService;

    @PostMapping("/login")
    public ResponseEntity authenticate(@RequestBody User user) {
        return new ResponseEntity(jwtService.authenticate(user), HttpStatus.OK);
    }
}
