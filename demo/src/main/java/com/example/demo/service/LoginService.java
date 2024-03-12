package com.example.demo.service;



public interface LoginService {
   boolean registerUser(String name, String password);
   boolean validateUser(String name, String password);
}