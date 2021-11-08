package com.edoe.api.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ForbiddenException extends RuntimeException{

    private String message = "Not authorized.";
    private String description = "User does not have access to this feature.";
}
