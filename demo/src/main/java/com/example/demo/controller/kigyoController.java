package com.example.demo.controller;

import com.example.demo.entity.Kigyo;
import com.example.demo.entity.PageBean;
import com.example.demo.entity.Result;
import com.example.demo.service.KigyoSerivce;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("kigyo")
public class kigyoController {

    @Autowired
    private KigyoSerivce kigyoSerivce;
    @PutMapping("/update")
    public Result update(@RequestBody @Validated Kigyo kigyo){
        kigyoSerivce.update(kigyo);
        return Result.succcess();
    }
    @GetMapping
    public Result<PageBean<Kigyo>>list(Integer pageNum,
                                       Integer pageSize,
                                       @RequestParam(required = false)String name){
        if (pageNum==null){
            pageNum=1;
        }
        if (pageSize==null){
            pageSize=5;
        }
        PageBean<Kigyo> pb=kigyoSerivce.list(pageNum,pageSize,name);
        return Result.success(pb);
    }
    @DeleteMapping("/delete")
    public Result<String> delete(@NotNull Integer id) {
        kigyoSerivce.deleteKiigyo(id);
        return Result.succcess();
    }
    @PostMapping
    public Result add(@RequestBody Kigyo kigyo){
        kigyoSerivce.add(kigyo);
        return Result.succcess();
    }
}
