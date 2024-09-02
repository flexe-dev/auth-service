package com.flexe.authservice.auth;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.web.util.WebUtils;

import java.io.IOException;

public class SessionTokenFilter extends AbstractPreAuthenticatedProcessingFilter {

    private final SessionAuthenticationProvider sessionAuthenticationProvider;


    public SessionTokenFilter(SessionAuthenticationProvider sessionAuthenticationProvider) {
        this.sessionAuthenticationProvider = sessionAuthenticationProvider;
    }

    @Override
    protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
        //Get Authorisation Header
        String sessionToken = request.getHeader("Authorization");
        //Format (Bearer <token>)
        if (sessionToken != null && sessionToken.startsWith("Bearer ")) {
            return sessionToken.substring(7);
        }
        return null;
    }

    @Override
    protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
        return getPreAuthenticatedPrincipal(request);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        try{

        String sessionToken = (String) getPreAuthenticatedPrincipal((HttpServletRequest) request);

        if (sessionToken != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // Authenticate the session token using SessionAuthenticationProvider
            Authentication auth = new SessionAuthenticationToken(sessionToken);
            auth = sessionAuthenticationProvider.authenticate(auth);

            if (auth != null) {
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }

        chain.doFilter(request, response);
        }
        catch (Exception e){
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
        }
    }
}
