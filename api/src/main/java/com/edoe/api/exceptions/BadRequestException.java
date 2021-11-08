package com.edoe.api.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
@Getter
public class BadRequestException extends RuntimeException {

    private String message;
    private String description;

    public BadRequestException(String message, String description) {
        super(message);
        this.description = description;
        this.message = message;
    }
}
