package com.example.securitychains.repository;

import com.example.securitychains.entity.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserTokenRepository extends JpaRepository<UserToken,Long> {
    Optional<UserToken> findUserTokenByToken(String token);

    @Transactional
    void deleteByUsername(String username);
}
