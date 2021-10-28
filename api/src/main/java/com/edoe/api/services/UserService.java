package com.edoe.api.services;

import com.edoe.api.enums.Role;
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
        User admin = new User();
        admin.setEmail("admin@email.com");
        admin.setName("Admin");
        admin.setRole(Role.ADMIN);
        admin.setPhone("83652134850");
        admin.setIdentificationDocument("7445992130");
        admin.setPassword("admin");
        userRepository.save(admin);
    }

    private boolean existsByEmail(String email) {
        for(User u: userRepository.findAll()) {
            if(u.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    public User createUser(User newUser) {
        if(existsByEmail(newUser.getEmail())) {
            return null;
        }
        newUser.setRole(Role.APENAS_DOADOR);
        User u = userRepository.save(newUser);
        return u;
    }

}
