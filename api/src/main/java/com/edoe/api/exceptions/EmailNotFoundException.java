package com.edoe.api.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmailNotFoundException extends RuntimeException {

    private String message = "Email not found";
    private String description = "Make sure the email is correct.";
}
