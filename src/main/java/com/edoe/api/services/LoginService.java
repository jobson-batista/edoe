package com.edoe.api.services;

import com.edoe.api.exceptions.LoginNotDone;
import com.edoe.api.models.User;
import com.edoe.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    public boolean userIsValid(User u) {
        try {
            Optional<User> user = userRepository.findById(u.getEmail());
            if(!(user.isPresent() && user.get().getPassword().equals(u.getPassword()))){
                throw new LoginNotDone("Login not done","Email or password is incorrect.");
            }
            return true;
        } catch (NoSuchElementException e) {
            throw new LoginNotDone("Login not done","Email or password is incorrect.");
        }
    }
}
