package com.edoe.api.models;

import com.edoe.api.dto.UserDTO;
import com.edoe.api.enums.Role;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
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

    @OneToMany(mappedBy = "user", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<Item> items;

    public User(String email, String name, Role role, String password) {
        this.email = email;
        this.name = name;
        this.role = role;
        this.password = password;
    }

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
