package com.example.securitychains.config;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.securitychains.RoleName;
import com.example.securitychains.entity.Roles;
import com.example.securitychains.entity.Users;
import com.example.securitychains.service.AuthService;
import com.example.securitychains.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class Security1 extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserService authService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String auth = request.getHeader("Authorization");
        String jwt;
        if (auth == null || !auth.startsWith("Bearer ")){
            filterChain.doFilter(request,response);
            return;
        }
        jwt = auth.substring(7);
        DecodedJWT decode = jwtService.decode(jwt);

        Users userById = authService.getUserById(Long.valueOf(decode.getSubject()));

        PrincipleUser user = PrincipleUser.builder()
                .id(userById.getId())
                .username(userById.getUsername())
                .roles(userById.getRoles())
                .isAccountExpired(userById.getIsAccountExpired())
                .isAccountNonLocked(userById.getIsAccountNonLocked())
                .isEnabled(userById.getIsEnabled())
                .isCredentialsNonExpired(userById.getIsCredentialsNonExpired())
                .build();

        SecurityContextHolder.getContext().setAuthentication(new UserTokenAuth(user));
        filterChain.doFilter(request,response);
    }


    public Set<Roles> getRolesFromToken(DecodedJWT decodedJWT){
        List<String> strings = decodedJWT.getClaim("roles").asList(String.class);

        Set<Roles> roles = strings.stream()
                .map(RoleName::valueOf)
                .map(Roles::new)
                .collect(Collectors.toSet());

        return roles;
    }
}
