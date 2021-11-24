package com.edoe.api.filters;

import io.jsonwebtoken.*;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TokenFilter extends GenericFilterBean {

    public final static int TOKEN_INDEX = 7;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletResponse req = (HttpServletResponse) servletResponse;

        String header = req.getHeader("Authorization");
        if(header == null || !header.startsWith("Bearer ")){
            throw new ServletException("Non-existent or poorly formatted token!");
        }

        String token = header.substring(TOKEN_INDEX);

        try {
            Jwts.parser().setSigningKey("|ptqAr-lB(z0RAhow#/d").parseClaimsJws(token).getBody();
        } catch (SignatureException | ExpiredJwtException | MalformedJwtException | PrematureJwtException | UnsupportedJwtException | IllegalArgumentException e) {
            ((HttpServletResponse) servletResponse).sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
            return ;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
