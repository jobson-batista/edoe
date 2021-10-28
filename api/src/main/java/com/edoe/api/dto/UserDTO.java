package com.edoe.api.dto;

import com.edoe.api.models.User;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class UserDTO {

    @NotBlank(message = "{name.not.blank}")
    private String name;

    @Email(message = "{email.not.valid}")
    @NotBlank(message = "{email.not.blank}")
    private String email;

    @NotBlank(message = "{identification.document.not.blank}")
    private String identificationDocument;

    private String phone;

    @NotBlank(message = "{password.not.blank}")
    private String password;

    public User toUser() {
        User newUser = new User();
        newUser.setName(this.name);
        newUser.setEmail(this.email);
        newUser.setPhone(this.phone);
        newUser.setIdentificationDocument(this.identificationDocument);
        newUser.setPassword(this.password);
        return newUser;
    }
}
