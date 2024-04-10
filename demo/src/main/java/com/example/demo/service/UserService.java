package com.example.demo.service;


import com.example.demo.entity.PageBean;
import com.example.demo.entity.User;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {


   void deleteUser(Integer id);
   User findByUserName(String username);

   void register(String username, String password);

   void update(User user);
//写真を更新する
   void updateAvatar(String urlSearchParams);

   void updatePwd(String username, String encodedNewPwd);

   PageBean<User> list(Integer pageNum, Integer pageSize, String username, String usermibun);

   void addKanri(User user);
}