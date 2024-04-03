package com.example.demo.controller;

import com.example.demo.entity.PageBean;
import com.example.demo.entity.Result;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.example.demo.utils.JwtUtil;

import com.example.demo.utils.ThreadLocalUtil;

import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@Validated
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public Result register( String username, String password){

        User u=userService.findByUserName(username);
    if (null==u){
        userService.register(username,password);
        return Result.succcess();
    }
    else {
        return Result.error("ユーザーもありました");
      }
    }
    @PostMapping("/login")
    public Result<String> login(String username, String password) {
        User loginUser = userService.findByUserName(username);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        if (loginUser == null) {
            return Result.error("error");
        }

        // 使用.matches方法验证密码
        if (passwordEncoder.matches(password, loginUser.getPassword())) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", loginUser.getId());
            claims.put("username", loginUser.getUsername());
            String token = JwtUtil.getToken(claims);
            return Result.success(token);
        }

        return Result.error("password error");
    }
    @GetMapping("/userInfo")
    public Result<User> userInfo(){
        Map<String,Object> map= ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User user =userService.findByUserName(username);
        return Result.success(user);
    }
    @PutMapping("/update")
    public Result update(@RequestBody @Validated User user){
        userService.update(user);
        return Result.succcess();
    }
    @PatchMapping("/updateAvatar")
    public Result updateAvatar(@RequestParam  String urlSearchParams){
        userService.updateAvatar(urlSearchParams);
        return Result.succcess();
    }
    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody Map<String,String>params){
        //引数検査
        String oldPwd= params.get("old_pwd");
        String newPwd = params.get("new_pwd");
        String rePwd = params.get("re_pwd");
        // 检查密码是否输入
        if (!StringUtils.hasLength(oldPwd) || !StringUtils.hasLength(newPwd) || !StringUtils.hasLength(rePwd)) {
            return Result.error("引数欠乏"); // 参数缺失
        }

// 检查新密码和重复输入的新密码是否匹配
        if (!newPwd.equals(rePwd)) {
            return Result.error("新しいパスワードが一致しません"); // 新密码不匹配
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        Map<String, Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User loginUser = userService.findByUserName(username);

// 检查旧密码是否正确
        if (!passwordEncoder.matches(oldPwd, loginUser.getPassword())) {
            return Result.error("元のパスワードが間違っています"); // 原密码错误
        }

        // 更新密码逻辑
        String encodedNewPwd = passwordEncoder.encode(newPwd); // 加密新密码
        userService.updatePwd(username, encodedNewPwd);

// 返回更新成功的结果
        return Result.success("パスワードが更新されました"); // 密码已更新
    }
    //page分ける
    @DeleteMapping("/delete")
    public Result<String> delete(@NotNull Integer id) {
        userService.deleteUser(id);
        return Result.succcess();
    }
    @GetMapping
    public Result<PageBean<User>>list(Integer pageNum,
                                      Integer pageSize,
                                      @RequestParam(required = false)String username,
                                      @RequestParam(required = false)String usermibun){
        if (pageNum==null){
            pageNum=1;
        }
        if (pageSize==null){
            pageSize=5;
        }
        PageBean<User> pb =userService.list(pageNum,pageSize,username,usermibun);
        return Result.success(pb);
    }
    @PostMapping
    public Result add(@RequestBody User user){
        User u=userService.findByUserName(user.getUsername());
        if (null==u){
            userService.addKanri(user);
            return Result.succcess();
        }
        else {
            return Result.error("ユーザーもありました");
        }
    }
}
