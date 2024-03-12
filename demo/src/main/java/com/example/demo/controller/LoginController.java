package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User credentials) {
        boolean success = loginService.registerUser(credentials.getName(), credentials.getPassword());
        if (success) {
            return ResponseEntity.ok("注册成功");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("注册失败");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User credentials) {
        boolean isValidUser = loginService.validateUser(credentials.getName(), credentials.getPassword());
        if (isValidUser) {
            // 登录成功的逻辑
            return ResponseEntity.ok("登录成功");
        } else {
            // 登录失败的逻辑
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("用户名或密码错误");
        }
    }
}
