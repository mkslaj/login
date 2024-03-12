package com.example.demo.entity;


import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    private String name;

    private Long id;
    private String password; // 存储加密后的密码
    private String salt; // 用户的盐值
}
