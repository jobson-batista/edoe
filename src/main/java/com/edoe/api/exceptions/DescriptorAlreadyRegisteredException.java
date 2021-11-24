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
public class DescriptorAlreadyRegisteredException extends RuntimeException {

    private String message = "Descriptor already registered";
    private String description = "This descriptor is already registered in the system. There cannot be the same descriptors.";
}
