package com.edoe.api.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BadRequestException extends RuntimeException {

    private String message = "Bad Request";
    private String description = "Request error, check request body or uri.";
}
