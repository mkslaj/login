package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Result<T> {
private Integer code;
private String message;
private T data;
public static<E>Result<E> success(E data){return new Result<>(0,"成功",data);}
public static Result succcess(){return new Result(0,"成功",null);}
public static Result error(String message){ return new Result(1,message,null);}
}
