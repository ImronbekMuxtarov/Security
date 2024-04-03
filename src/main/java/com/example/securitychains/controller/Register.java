package com.example.securitychains.controller;

import com.example.securitychains.dto.RegisterDTO;
import com.example.securitychains.dto.RegisterResponseDTO;
import com.example.securitychains.dto.UsersDTO;
import com.example.securitychains.entity.Users;
import com.example.securitychains.repository.RoleRepository;
import com.example.securitychains.repository.UserRepository;
import com.example.securitychains.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
@RequiredArgsConstructor
public class Register {
    private final AuthService service;
    @PostMapping
    public String register(@RequestBody @Valid RegisterDTO users){
        return service.register(users);
    }


    @GetMapping("/{token}/{code}")
    public RegisterResponseDTO verify(@PathVariable String token, @PathVariable String code) throws Exception {
            return service.verify(code,token);
    }
}
