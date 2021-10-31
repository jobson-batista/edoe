package com.edoe.api.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
@Getter
public class EmailNotFoundException extends RuntimeException {

    private String message;
    private String description;

    public EmailNotFoundException(String message, String description) {
        super(message);
        this.message = message;
        this.description = description;
    }
}
