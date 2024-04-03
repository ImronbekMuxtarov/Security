package com.example.securitychains.service;

import com.example.securitychains.RoleName;
import com.example.securitychains.entity.Roles;
import com.example.securitychains.entity.Users;
import com.example.securitychains.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.User;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.anyLong;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void getUserById() {
        Users user = Users.builder()
                .username("username")
                .password("password")
                .first_name("first name")
                .last_name("last name")
                .roles(Set.of(new Roles(RoleName.ROLE_USER)))
                .build();
        Long id = 11817L;

        when(userRepository.findById(anyLong())).thenReturn(Optional.ofNullable(user));

        Users find = userService.getUserById(id);

        Assertions.assertNotNull(find);
    }
}