package com.example.securitychains.controller;

import com.example.securitychains.dto.ResponseDTO;
import com.example.securitychains.dto.UsersDTO;
import com.example.securitychains.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class Login {
    private final AuthService service;

    @PostMapping
    public ResponseDTO login(@RequestBody UsersDTO usersDTO){
        return service.login(usersDTO);
    }


}
