package com.example.demo.service;

import com.example.demo.entity.Kigyo;
import com.example.demo.entity.PageBean;

public interface KigyoSerivce {
    void update(Kigyo kigyo);

    PageBean<Kigyo> list(Integer pageNum, Integer pageSize, String name);

    void deleteKiigyo(Integer id);

    void add(Kigyo kigyo);
}
