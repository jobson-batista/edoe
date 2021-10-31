package com.edoe.api.services;

import com.edoe.api.enums.Role;
import com.edoe.api.exceptions.BadRequestException;
import com.edoe.api.models.User;
import com.edoe.api.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    private void init() {
        User admin = new User("Admin","admin@dcx.ufpb.br","83652134850",Role.ADMIN,"admin","7445992130");
        userRepository.save(admin);
    }

    public User createUser(User newUser) throws BadRequestException {

        if(emailExists(newUser.getEmail())) {
            throw new BadRequestException("Email already exists.");
        }
        if(anyNullOrEmptyFields(newUser)) {
            throw new BadRequestException("Any null or empty fields.");
        }
        if(!isEmailDcx(newUser.getEmail())) {
            throw new BadRequestException("Invalid email. Must belong to DCX domain.");
        }
        if(documentExists(newUser.getIdentificationDocument())) {
            throw new BadRequestException("Identification document already registered.");
        }
        newUser.setRole(Role.APENAS_DOADOR);
        return userRepository.save(newUser);
    }

    private boolean emailExists(String email) {
        for(User u: userRepository.findAll()) {
            if(u.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    private boolean documentExists(String doc) {
        for(User u: userRepository.findAll()) {
            if(u.getIdentificationDocument().equals(doc)) {
                return true;
            }
        }
        return false;
    }

    private boolean anyNullOrEmptyFields(User u) {
        return (u.getName() == null
                || u.getIdentificationDocument() == null
                || u.getPassword() == null
                || u.getEmail() == null
                || u.getEmail().isBlank()
                || u.getName().isBlank()
                || u.getIdentificationDocument().isBlank()
                || u.getPassword().isBlank()
        ) ? true : false;
    }

    private boolean isEmailDcx(String email) {
        return email.contains("@dcx.ufpb.br") ? true : false;
    }

}
