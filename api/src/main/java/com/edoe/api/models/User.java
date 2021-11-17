package com.edoe.api.models;

import com.edoe.api.dto.UserDTO;
import com.edoe.api.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String name;

    private String phone;

    @Column(nullable = false)
    private Role role;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true, length = 14, name = "document")
    private String identificationDocument;

    @OneToMany(mappedBy = "user")
    private List<Item> items;

    public UserDTO toDTO() {
        UserDTO dto = new UserDTO();
        dto.setEmail(this.email);
        dto.setName(this.name);
        dto.setIdentificationDocument(this.identificationDocument);
        dto.setPhone(this.phone);
        dto.setRole(this.role);
        return dto;
    }
}
