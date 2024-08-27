package com.flexe.authservice.auth;

import com.flexe.authservice.entity.user.User;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class SessionAuthenticationToken extends AbstractAuthenticationToken {
    private final String token;
    private final String userId;

    public SessionAuthenticationToken(String token) {
        super(null);
        this.token = token;
        this.userId = null;
        setAuthenticated(false);
    }

    public SessionAuthenticationToken(String userId, String token) {
        super(null);
        this.token = token;
        this.userId = userId;
        setAuthenticated(true);
    }

    public SessionAuthenticationToken(String token, String userId, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.token = token;
        this.userId = null;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    public String getUserId() {
        return userId;
    }
}
