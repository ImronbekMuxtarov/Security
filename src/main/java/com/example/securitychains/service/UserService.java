package com.example.securitychains.service;

import com.example.securitychains.entity.Users;
import com.example.securitychains.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    @Cacheable(value = "users",key = "#id")
    public Users getUserById(Long id){
        log.info("Working with Database");
        return userRepository.findById(id).orElse(null);
    }
}
