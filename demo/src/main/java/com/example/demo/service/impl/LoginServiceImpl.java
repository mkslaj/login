package com.example.demo.service.impl;

import com.example.demo.mapper.UserMapper;
import com.example.demo.service.LoginService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Service;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserMapper userMapper;

    private static final SecureRandom RANDOM = new SecureRandom();
    private static final int SALT_LENGTH = 16;

    @Override
    public boolean registerUser(String name, String password) {
        String salt = generateSalt();
        String hashedPassword = hashPassword(password, salt);
        SecurityProperties.User user = new SecurityProperties.User();
        user.setName(name);
        user.setPassword(hashedPassword);
        user.setName(salt);
        userMapper.insertUser((User) user);
        return true;
    }

    @Override
    public boolean validateUser(String name, String password) {
        User user = userMapper.findByName(name);
        if (user != null) {
            String hashedPassword = hashPassword(password, user.getName());
            return hashedPassword.equals(user.getPassword());
        }
        return false;
    }

    private String generateSalt() {
        byte[] salt = new byte[SALT_LENGTH];
        RANDOM.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    private String hashPassword(String password, String salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(Base64.getDecoder().decode(salt));
            byte[] hashedPassword = md.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hashedPassword);
        } catch (NoSuchAlgorithmException e) {
            // 在实际应用中应该处理这个异常，例如记录日志或者抛出一个运行时异常
            throw new RuntimeException("Hashing algorithm not found", e);
        }
    }
}