package com.example.demo.mapper;

import org.apache.catalina.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.boot.autoconfigure.security.SecurityProperties;


@Mapper
public interface UserMapper {
    void insertUser(User user);
    User findByName(String name);
}