package com.example.demo.entity;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Kigyo {
    private Integer id;
    private String name;
    private String gyousyu;
    private Integer kibo;
    private String jyusyo;
    private Integer faxx;
    @Email
    private String email;
    private String tantousya;
    private String yuubinbango;
}
