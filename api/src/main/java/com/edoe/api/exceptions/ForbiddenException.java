package com.edoe.api.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
@Getter
@AllArgsConstructor
public class ForbiddenException extends RuntimeException{

    private String message;
    private String description;
}
