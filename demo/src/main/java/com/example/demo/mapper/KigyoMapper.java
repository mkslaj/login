package com.example.demo.mapper;

import com.example.demo.entity.Kigyo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface KigyoMapper {

    void update(Kigyo kigyo);

    List<Kigyo> List(Integer id, String name);

    void delete(Integer id);

    void add(Kigyo kigyo);
}
