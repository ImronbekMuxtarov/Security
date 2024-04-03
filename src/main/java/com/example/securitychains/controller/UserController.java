package com.example.securitychains.controller;

import com.example.securitychains.entity.Users;
import com.example.securitychains.service.AuthService;
import com.example.securitychains.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @GetMapping("/{id}")
    public Users getUserById(@PathVariable Long id){
        return service.getUserById(id);
    }
}
