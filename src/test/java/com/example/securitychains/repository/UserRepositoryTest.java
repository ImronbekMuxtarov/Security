package com.example.securitychains.repository;

import com.example.securitychains.RoleName;
import com.example.securitychains.entity.Roles;
import com.example.securitychains.entity.Users;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void findByUsername() {
        String username = "imronbekm";
        Users find = userRepository.findByUsername(username).orElse(null);
        Assertions.assertNotNull(find);
//        Users user = Users.builder()
//                .username("username")
//                .password("password")
//                .first_name("first name")
//                .last_name("last name")
//                .roles(Set.of(new Roles(RoleName.ROLE_USER)))
//                .build();
    }
}