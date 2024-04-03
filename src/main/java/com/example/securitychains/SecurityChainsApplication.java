package com.example.securitychains;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SecurityChainsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityChainsApplication.class, args);
    }

}
