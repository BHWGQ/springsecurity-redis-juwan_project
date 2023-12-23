package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.Date;

@SpringBootTest
class Demo5ApplicationTests {

    @Test
    void contextLoads() {
        String rawPassword = "2003";

        // 创建BCryptPasswordEncoder实例
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        // 对密码进行加密
        String encodedPassword = passwordEncoder.encode(rawPassword);
        System.out.println(encodedPassword);

    }

}
