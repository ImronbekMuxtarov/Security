package com.example.securitychains.config;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class UserTokenAuth extends AbstractAuthenticationToken {
    private final PrincipleUser principleUser;
    public UserTokenAuth(PrincipleUser principleUser) {
        super(principleUser.getAuthorities());
        this.principleUser = principleUser;
        setAuthenticated(principleUser.isAccountNonExpired()&&principleUser.isAccountNonLocked()&&principleUser.isCredentialsNonExpired()&&principleUser.isEnabled())   ;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return principleUser;
    }
}
