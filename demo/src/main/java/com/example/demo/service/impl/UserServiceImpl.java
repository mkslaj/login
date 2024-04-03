package com.example.demo.service.impl;

import com.example.demo.entity.PageBean;
import com.example.demo.entity.Result;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import com.example.demo.utils.ThreadLocalUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.tomcat.util.security.MD5Encoder;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.Base64;
import java.util.List;
import java.util.Map;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void deleteUser(Integer id) {
        userMapper.delete(id);
    }

    @Override
    public User findByUserName(String username) {
        return userMapper.findByUserName(username);
    }

    @Override
    public void register(String username, String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
// 将明文密码进行加密
        String encodedPassword = passwordEncoder.encode(password);

// 存储用户名和加密后的密码
        userMapper.add(username, encodedPassword);
    }

    @Override
    public void update(User user) {
        userMapper.update(user);
    }

    @Override
    public void updateAvatar(String urlSearchParams ) {
        Map<String,Object>map=ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        userMapper.updateAvatar(urlSearchParams , id);
    }

    @Override
    public void updatePwd(String username, String encodedNewPwd) {
        userMapper.updatePasswordByUsername(username, encodedNewPwd);
    }

    @Override
    public PageBean<User> list(Integer pageNum, Integer pageSize, String username, String usermibun) {
        //創造pagebean対象
        PageBean<User> pb=new PageBean<>();
        //page分ける pagehelper
        PageHelper.startPage(pageNum,pageSize);
        //mapper
        Map<String,Object>map=ThreadLocalUtil.get();
        Integer id=(Integer) map.get("id");
       List<User> as=userMapper.List(id,username,usermibun);
       //page可以获取pagehelper分页查询后，得到的总记录和当页数据
        Page<User>p=(Page<User>)as;
        //数据填充到pagebean
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());
        return pb;
    }

    @Override
    public void addKanri(@NotNull User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            // 如果密码为空，则设置默认密码为'123456'并进行加密
            String encodedPassword = passwordEncoder.encode("123456");
            user.setPassword(encodedPassword);
        } else {
            // 如果密码不为空，则对密码进行加密
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        // 调用userMapper的addKanri方法将User对象插入数据库
        userMapper.addKanri(user);
    }
}