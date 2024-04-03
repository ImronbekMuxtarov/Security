package com.example.securitychains.config;

import com.example.securitychains.entity.Users;
import com.example.securitychains.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final UserRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = repository.findByUsername(username).
                orElseThrow(() -> new UsernameNotFoundException("User Not Found"));

        return PrincipleUser.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRoles())
                .isCredentialsNonExpired(user.getIsCredentialsNonExpired())
                .isAccountExpired(user.getIsAccountExpired())
                .isEnabled(user.getIsEnabled())
                .isAccountNonLocked(user.getIsAccountNonLocked())
                .build();
    }
}
