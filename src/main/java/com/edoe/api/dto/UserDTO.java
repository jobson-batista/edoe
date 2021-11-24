package com.edoe.api.dto;

import com.edoe.api.enums.Role;

import lombok.Data;

@Data
public class UserDTO {

    private String name;

    private String email;

    private String identificationDocument;

    private String phone;

    private Role role;
}
