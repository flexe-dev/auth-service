package com.flexe.authservice.auth;

import com.flexe.authservice.entity.auth.Session;
import com.flexe.authservice.entity.user.User;
import com.flexe.authservice.exceptions.SessionInvalidException;
import com.flexe.authservice.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SessionAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private SessionRepository sessionRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String sessionToken = (String) authentication.getCredentials();

        // Validate session token through Session Table
        Session session = sessionRepository.findSessionBySessionToken(sessionToken).orElse(null);
        if(session == null || session.isExpired()){
            throw new AuthenticationException("Session Token Provided is Invalid or Expired"){};
        }

        return new SessionAuthenticationToken(session.getUserId(), sessionToken);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return SessionAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
