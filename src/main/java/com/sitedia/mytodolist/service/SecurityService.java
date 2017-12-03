package com.sitedia.mytodolist.service;

import java.util.Collection;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * Manage the security in the application
 * @author cedric
 *
 */
@Service
@Lazy
public class SecurityService {

    private static final String ROLE_ADMIN = "ROLE_ADMIN";

    /**
     * Checks if the user is full admin
     * @param authentication
     * @return
     */
    public boolean isAdmin() {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	
        // Check authentication
        if (authentication == null || !isAuthenticated(authentication)) {
            return false;
        }

        // Search for ROLE_ADMIN
        Collection<? extends GrantedAuthority> roles = authentication.getAuthorities();
        for (GrantedAuthority role : roles) {
            if (role.getAuthority().equals(ROLE_ADMIN)) {
                return true;
            }
        }

        // Admin role not found
        return false;
    }

    /**
     * Checks that the authentication is filled
     * @param authentication
     */
    private boolean isAuthenticated(Authentication authentication) {
        return authentication != null && authentication.getAuthorities() != null && authentication.getName() != null
                && !authentication.getName().equals("anonymousUser");
    }

    public String getUsername(Authentication authentication) {
        return authentication != null && authentication.getName() != null ? authentication.getName() : null;
    }

}
