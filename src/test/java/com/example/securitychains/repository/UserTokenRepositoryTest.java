package com.example.securitychains.repository;

import com.example.securitychains.entity.UserToken;
import com.example.securitychains.entity.Users;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class UserTokenRepositoryTest {

    @Autowired
    private UserTokenRepository userTokenRepository;

    @Test
    void findUserTokenByToken() {
        String token = "ulgurmadim token olishga ";
        UserToken userToken = userTokenRepository.findUserTokenByToken(token).orElse(null);
        Assertions.assertNotNull(userToken, "it is null");
    }

    @Test
    void deleteByUsername() {
        String username = "imronbekm";
        userTokenRepository.deleteByUsername(username);
    }
}