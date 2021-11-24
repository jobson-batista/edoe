package com.edoe.api.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.OK)
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoginNotDone extends RuntimeException{

    private String message = "Login not done";
    private String description = "Login not done, check username or password.";
}
