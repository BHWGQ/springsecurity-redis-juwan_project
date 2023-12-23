package com.example.demo.service.impl;

import org.springframework.stereotype.Service;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class PasswordService {
    private ConcurrentHashMap<String, String> userPasswords = new ConcurrentHashMap<>();

    public void storePassword(String username, String password) {
        userPasswords.put(username, password);
    }

    public String getPassword(String username) {
        return userPasswords.get(username);
    }
}
