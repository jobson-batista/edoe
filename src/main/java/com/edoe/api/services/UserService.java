package com.edoe.api.services;

import com.edoe.api.dto.UserDTO;
import com.edoe.api.enums.Role;
import com.edoe.api.exceptions.BadRequestException;
import com.edoe.api.exceptions.EmailNotFoundException;
import com.edoe.api.exceptions.ForbiddenException;
import com.edoe.api.exceptions.NotFoundException;
import com.edoe.api.models.Item;
import com.edoe.api.models.User;
import com.edoe.api.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import javax.servlet.ServletException;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTService jwtService;

    @PostConstruct
    private void init() {
        User admin = new User("admin","Admin","83652134850",Role.ADMIN,"admin","7445992130", null);
        User donator = new User("donator", "Donator","83652134850", Role.APENAS_DOADOR, "123","7445992137", null);
        User receptor = new User("receptor", "Receptor","83652134850", Role.APENAS_RECEPTOR, "123","7445992134",null);
        userRepository.save(admin);
        userRepository.save(receptor);
        userRepository.save(donator);
    }

    public User createUser(User newUser) throws BadRequestException {

        if(emailExists(newUser.getEmail())) {
            throw new BadRequestException("Email already registered","There is another user with this email.");
        }
        if(anyNullOrEmptyFields(newUser)) {
            throw new BadRequestException("Any null or empty fields","Null or empty mandatory fields must not be accepted.");
        }
        if(!isEmailDcx(newUser.getEmail())) {
            throw new BadRequestException("Invalid email.","Must belong to DCX domain.");
        }
        if(documentExists(newUser.getIdentificationDocument())) {
            throw new BadRequestException("Identification document already registered","There must not be more than one identification document with different users.");
        }
        newUser.setRole(Role.APENAS_DOADOR);
        return userRepository.save(newUser);
    }

    public UserDTO findUserByEmail(String email, String header) throws ServletException {
        Optional<User> user = userRepository.findById(email);
        if(!(isAdmin(header) || havePermission(header, email))){
            throw new ForbiddenException();
        }
        if(!user.isPresent()) {
            throw new EmailNotFoundException();
        }
        return user.get().toDTO();
    }

    public UserDTO changeRole(String email, String token, Role role) throws ServletException {
        if(!isAdmin(token)) {
            throw new ForbiddenException();
        }
        if(!emailExists(email)) {
            throw new EmailNotFoundException();
        }
        Optional<User> user = userRepository.findById(email);
        user.get().setRole(role);
        userRepository.save(user.get());
        return user.get().toDTO();
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
        return u.getName() == null
                || u.getIdentificationDocument() == null
                || u.getPassword() == null
                || u.getEmail() == null
                || u.getEmail().isBlank()
                || u.getName().isBlank()
                || u.getIdentificationDocument().isBlank()
                || u.getPassword().isBlank();
    }

    public boolean isEmailDcx(String email) {
        return email.contains("@dcx.ufpb.br");
    }

    public boolean havePermission(String token, String email) throws ServletException{
        String subject = jwtService.getSubjectToken(token);
        Optional<User> optUser = userRepository.findById(subject);
        return optUser.isPresent() && optUser.get().getEmail().equals(email);
    }

    public boolean isAdmin(String token) throws ServletException {
        String subject = jwtService.getSubjectToken(token);
        Optional<User> opt = userRepository.findById(subject);
        return opt.get().getRole().equals(Role.ADMIN);
    }

    public boolean isDonor(String token) throws ServletException {
        String subject = jwtService.getSubjectToken(token);
        try {
            Optional<User> opt = userRepository.findById(subject);
            return (opt.get().getRole().equals(Role.APENAS_DOADOR) || opt.get().getRole().equals(Role.DOADOR_RECEPTOR));
        } catch (NoSuchElementException e) {
            throw new NotFoundException();
        }

    }

    public boolean isOwnerItem(String token, Item item) throws ServletException {
        String subject = jwtService.getSubjectToken(token);
        return item.getUser().getEmail().equals(subject) ? true : false;
    }

    public User getUserByToken(String token) throws  ServletException {
        String subject = jwtService.getSubjectToken(token);
        Optional<User> opt = userRepository.findById(subject);
        if(!opt.isPresent()){
            throw new BadRequestException("Invalid email","User not found");
        }
        return opt.get();
    }
}
