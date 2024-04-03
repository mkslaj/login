package com.example.demo.mapper;

import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface UserMapper {

    User findByUserName(String username);

    void add(String username, String encodedPassword);

    void update(User user);

    void updateAvatar(String urlSearchParams, Integer id);

    void updatePasswordByUsername(String username, String encodedNewPwd);

    List<User> List(Integer id, String username, String usermibun);

    void delete(Integer id);

    void addKanri(User user);
}