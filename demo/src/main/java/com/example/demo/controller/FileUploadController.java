package com.example.demo.controller;

import com.example.demo.entity.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
public class FileUploadController {
 @PostMapping("/upload")
    public Result<String>upload(MultipartFile file) throws IOException {
     String originalFilename = file.getOriginalFilename();
     //uuid
     String filename= UUID.randomUUID().toString()+originalFilename.substring(originalFilename.lastIndexOf("."));
     file.transferTo(new File("C:\\Users\\sawya\\OneDrive\\Desktop\\file\\"+filename));
     return Result.success(filename);
 }
}
