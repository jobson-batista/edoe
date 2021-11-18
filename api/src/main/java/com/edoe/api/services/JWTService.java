package com.edoe.api.services;

import com.edoe.api.exceptions.LoginNotDone;
import com.edoe.api.filters.TokenFilter;
import com.edoe.api.models.User;
import com.edoe.api.utils.ResponseLogin;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import java.util.Date;

@Service
public class JWTService {

    @Autowired
    private LoginService loginService;

    private final String TOKEN_KEY = "|ptqAr-lB(z0RAhow#/d";

    public ResponseLogin authenticate(User user) {
        if(!loginService.userIsValid(user)){
            throw new LoginNotDone("Login not done","Email or password is incorrect.");
        }
        String token = generateToken(user.getEmail());
        return new ResponseLogin(token);
    }

    private String generateToken(String email) {
        return Jwts.builder().setSubject(email)
                .signWith(SignatureAlgorithm.HS512, TOKEN_KEY)
                .setExpiration(new Date((long) (System.currentTimeMillis() + 30 * 60 * 1000))).compact();
    }

    public String getSubjectToken(String authorizationHeader) throws ServletException {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new ServletException("Non-existent or poorly formatted token!");
        }

        String token = authorizationHeader.substring(TokenFilter.TOKEN_INDEX);

        String subject = null;
        try {
            subject = Jwts.parser().setSigningKey(TOKEN_KEY).parseClaimsJws(token).getBody().getSubject();
        } catch (SignatureException e) {
            throw new ServletException("Invalid token!");
        }
        return subject;
    }
}
