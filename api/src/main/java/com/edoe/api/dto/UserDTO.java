package com.edoe.api.dto;

import com.edoe.api.models.User;

import lombok.Data;

@Data
public class UserDTO {

    private String name;

    private String email;

    private String identificationDocument;

    private String phone;

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
