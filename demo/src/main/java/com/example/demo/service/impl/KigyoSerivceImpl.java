package com.example.demo.service.impl;

import com.example.demo.entity.Kigyo;
import com.example.demo.entity.PageBean;
import com.example.demo.entity.User;
import com.example.demo.mapper.KigyoMapper;
import com.example.demo.service.KigyoSerivce;
import com.example.demo.utils.ThreadLocalUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class KigyoSerivceImpl implements KigyoSerivce {
@Autowired
private KigyoMapper kigyoMapper;
    @Override
    public void update(Kigyo kigyo) {
        kigyoMapper.update(kigyo);
    }

    @Override
    public PageBean<Kigyo> list(Integer pageNum, Integer pageSize, String name) {
        //創造pagebean対象
        PageBean<Kigyo> pb=new PageBean<>();
        //page分ける pagehelper
        PageHelper.startPage(pageNum,pageSize);
        //mapper
        Map<String,Object> map= ThreadLocalUtil.get();
        Integer id=(Integer) map.get("id");
        List<Kigyo> as=kigyoMapper.List(id,name);
        //page可以获取pagehelper分页查询后，得到的总记录和当页数据
        Page<Kigyo> p=(Page<Kigyo>)as;
        //数据填充到pagebean
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());
        return pb;
    }

    @Override
    public void deleteKiigyo(Integer id) {
        kigyoMapper.delete(id);
    }

    @Override
    public void add(Kigyo kigyo) {
        kigyoMapper.add(kigyo);
    }
}
