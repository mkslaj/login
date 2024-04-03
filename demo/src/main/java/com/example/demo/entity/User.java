package com.example.demo.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    @NotNull
    private Integer id;
    private String usercode;
    private String username;
    @JsonIgnore
    private String password;
    @NotEmpty
    private String usernamekanji;
    @NotEmpty
    private String usernamekana;
    @NotEmpty
    private String usermibun;
    @NotEmpty
    @Email
    private String email;
    private String usersyashin;
    private String yukoukunbun;

}
