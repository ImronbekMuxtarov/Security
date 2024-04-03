package com.example.securitychains;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.time.LocalDate;

@SpringBootTest
class SecurityChainsApplicationTests {

    @Test
    void contextLoads() {
        File folder = new File("uploads/" + LocalDate.now());
        if (folder.exists()){
            folder.delete();
        }
    }

}
